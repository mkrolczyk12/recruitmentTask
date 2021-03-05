package io.github.restfulApiWebservice.restfulApiWebservice.notes;

import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
class NoteService {
    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);
    private final NoteRepository repository;
    private final ObjectMapper objectMapper;

    NoteService(final NoteRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    List<NoteReadModel> findAll() {
        return repository
                .findAllByActualVersionAndDeleted(true, false)
                .stream()
                .map(NoteReadModel::new)
                .collect(Collectors.toList());
    }

    Boolean checkIfGivenNoteExist(final String noteId) {
        return repository.existsByNoteIdAndDeleted(noteId, false);
    }

    NoteReadModel findById(final String noteId) throws NotFoundException {
        return repository
                .findByNoteIdAndDeletedAndActualVersion(noteId, false, true)
                .map(NoteReadModel::new)
                .orElseThrow(() -> new NotFoundException("note with id = " + noteId + " does not exist!"));
    }

    NoteReadModel addNote(final NoteWriteModel note) {
        Note result = note.toNote();
        repository.save(result);
        return new NoteReadModel(result);
    }

    void fullUpdate(final NoteWriteModel data, final String noteId) throws NotFoundException {
        Note prevVersionOfNote = repository
                .findByNoteIdAndActualVersion(noteId, true)
                .orElseThrow(() -> new NotFoundException("note with id = " + noteId + " does not exist!"));
        prevVersionOfNote.setModified(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        prevVersionOfNote.setActualVersion(false);
        Note prevNotePartCopy = new Note.NoteBuilder().build(prevVersionOfNote);
        var result = updateNoteByPutTypeOfRequest(data, prevNotePartCopy);

        repository.save(result);
    }

    private Note updateNoteByPutTypeOfRequest(NoteWriteModel data, Note noteCopy) {
        noteCopy.setTitle(data.getTitle());
        noteCopy.setContent(data.getContent());
        noteCopy.setActualVersion(true);
        return noteCopy;
    }

    void partUpdate(final HttpServletRequest request, final String noteId) throws IOException, NotFoundException {
        Note previousNoteVersion = repository
                .findByNoteIdAndActualVersion(noteId, true)
                .orElseThrow(() -> new NotFoundException("note with id = " + noteId + " does not exist!"));
        Note updatedVersionOfNote = objectMapper.readerForUpdating(new Note.NoteBuilder().build(previousNoteVersion)).readValue(request.getReader());
        previousNoteVersion.setActualVersion(false);
        previousNoteVersion.setModified(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        repository.save(updatedVersionOfNote);
    }

    void deleteNote(final String noteId) {
        List<Note> result = repository.findAllByNoteId(noteId);
        result
            .stream()
            .forEach(eachVersion -> {
                eachVersion.setDeleted(true);
                repository.saveAndFlush(eachVersion);
            });
    }

    List<NoteHistoryReadModel> showHistoryOfGivenNote(final String noteId) {
        List<Note> result = repository.findAllByNoteIdOrderByVersionAsc(noteId);
        return result
                .stream()
                .map(NoteHistoryReadModel::new)
                .collect(Collectors.toList());
    }
}

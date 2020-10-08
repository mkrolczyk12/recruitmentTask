package io.github.recruitmentTask.recruitmentTask.notes;

import io.github.recruitmentTask.recruitmentTask.common.ExceptionResponse;
import io.github.recruitmentTask.recruitmentTask.common.GeneralExceptionsProcessing;
import io.github.recruitmentTask.recruitmentTask.notes.projection.NoteReadModel;
import io.github.recruitmentTask.recruitmentTask.notes.projection.NoteWriteModel;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@RestController
@GeneralExceptionsProcessing
@RequestMapping("/notes")
public class NoteController {
    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
    private final NoteService service;

    public NoteController(final NoteService service) {
        this.service = service;
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteReadModel> addNote(@RequestBody @Valid final NoteWriteModel note) {
            NoteReadModel result = service.addNote(note);
            logger.info("posted new note with id = " + result.getNoteId());
            return ResponseEntity.created(URI.create("/" + result.getNoteId())).body(result);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NoteReadModel>> readAllNotes() {
        logger.info("reading all notes from database");
            List<NoteReadModel> notes = service.findAll();
            return ResponseEntity.ok(notes);
    }
    @GetMapping(path = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteReadModel> readNote(@PathVariable final String noteId) throws NotFoundException {
            if(!service.checkIfGivenNoteExist(noteId)) {
                final String message = "Note with given id does not exist!";
                ExceptionResponse resp = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), message, "-");
                return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
            }
            NoteReadModel resp = service.findById(noteId);
            return ResponseEntity.ok(resp);
    }
    @Transactional
    @PutMapping(path = "/{noteId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> fullNoteUpdate(@PathVariable final String noteId,
                                        @RequestBody @Valid final NoteWriteModel note) throws NotFoundException {
        if(!service.checkIfGivenNoteExist(noteId)) {
            final String message = "Note with given id does not exist!";
            ExceptionResponse resp = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), message, "-");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
        service.fullUpdate(note, noteId);
        return ResponseEntity.ok().build();
    }
    @Transactional
    @PatchMapping(path = "/{noteId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> partNoteUpdate(@PathVariable final String noteId,
                                        @Valid final HttpServletRequest request) throws IOException, NotFoundException {
        if(!service.checkIfGivenNoteExist(noteId)) {
            final String message = "Note with given id does not exist!";
            ExceptionResponse resp = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), message, "-");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }

        service.partUpdate(request, noteId);
        return ResponseEntity.ok().build();
    }
    @Transactional
    @DeleteMapping(path = "/{noteId}")
    ResponseEntity<Object> deleteNote(@PathVariable final String noteId) {
        if(!service.checkIfGivenNoteExist(noteId)) {
            final String message = "Note with given id does not exist!";
            ExceptionResponse resp = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), message, "-");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
        service.deleteNote(noteId);
        logger.info("deleted note with id = " + noteId);
        return ResponseEntity.ok().build();
    }

}

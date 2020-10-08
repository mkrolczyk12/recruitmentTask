package io.github.recruitmentTask.recruitmentTask.notes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);
    private final NoteRepository repository;

    public NoteService(final NoteRepository repository) {
        this.repository = repository;
    }

    List<Note> findAll() {
        return(repository.findAllByActualVersionAndDeleted(true, false));
    }
}

package io.github.recruitmentTask.recruitmentTask.notes;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.recruitmentTask.recruitmentTask.common.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@RestController
@RequestMapping("/notes")
public class NoteController {
    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
    private final NoteService service;
    private final ObjectMapper objectMapper;

    public NoteController(final NoteService service, final ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Note>> readAllNotes() {
        logger.info("reading all notes from database");
        try {
            List<Note> notes = service.findAll();
            return ResponseEntity.ok(notes);
        } catch (Exception e) {
            final String message = "an error occured while loading all notes, try again later";
            ExceptionResponse resp = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), message, "-");
            return new ResponseEntity(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

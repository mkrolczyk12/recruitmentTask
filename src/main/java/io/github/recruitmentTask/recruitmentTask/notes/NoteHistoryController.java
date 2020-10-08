package io.github.recruitmentTask.recruitmentTask.notes;

import io.github.recruitmentTask.recruitmentTask.common.ExceptionResponse;
import io.github.recruitmentTask.recruitmentTask.common.GeneralExceptionsProcessing;
import io.github.recruitmentTask.recruitmentTask.notes.projection.NoteHistoryReadModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@GeneralExceptionsProcessing
@RequestMapping("/notes/history")
public class NoteHistoryController {
    private static final Logger logger = LoggerFactory.getLogger(NoteHistoryController.class);
    private final NoteService service;

    public NoteHistoryController(final NoteService service) {
        this.service = service;
    }

    @GetMapping(path = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NoteHistoryReadModel>> readNoteHistory(@PathVariable final String noteId) {
            if(!service.checkIfGivenNoteExist(noteId)) {
                final String message = "Note with given id does not exist!";
                ExceptionResponse resp = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), message, "-");
                return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
            }
            List<NoteHistoryReadModel> resp = service.showHistoryOfGivenNote(noteId);
            logger.info("showing history of note with id = " + noteId);
            return ResponseEntity.ok(resp);
    }
}

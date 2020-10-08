package io.github.recruitmentTask.recruitmentTask.notes.projection;

import io.github.recruitmentTask.recruitmentTask.notes.Note;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class NoteWriteModel {
    @NotBlank(message = "Note title can not be null or empty")
    private String title;
    @NotBlank(message = "Note content can not be null or empty")
    private String content;

    public String getTitle() {return title;}
    public void setTitle(final String title) {this.title = title;}

    public String getContent() {return content;}
    public void setContent(final String content) {this.content = content;}

    public Note toNote() {
        var note = new Note(title, content);
        note.setNoteId(UUID.randomUUID().toString());
        note.setDeleted(false);
        note.setVersion(1);
        note.setActualVersion(true);
        note.setCreated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return note;
    }
}

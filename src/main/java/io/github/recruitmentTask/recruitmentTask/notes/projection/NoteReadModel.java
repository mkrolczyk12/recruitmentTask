package io.github.recruitmentTask.recruitmentTask.notes.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.recruitmentTask.recruitmentTask.notes.Note;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@JsonIgnoreProperties({"id"})
public class NoteReadModel {
    private Long id;
    private String title;
    private String content;
    private String noteId;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime created;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime modified;

    public NoteReadModel(Note sourceNote) {
        this.id = sourceNote.getId();
        this.title = sourceNote.getTitle();
        this.content = sourceNote.getContent();
        this.noteId = sourceNote.getNoteId();
        this.created = sourceNote.getCreated();
        this.modified = sourceNote.getModified();
    }

    public Long getId() {return id;}

    public String getTitle() {return title;}
    public void setTitle(final String title) {this.title = title;}

    public String getContent() {return content;}
    public void setContent(final String content) {this.content = content;}

    public String getNoteId() {return noteId;}

    public LocalDateTime getCreated() {return created;}
    public void setCreated(final LocalDateTime created) {this.created = created;}

    public LocalDateTime getModified() {return modified;}
    public void setModified(final LocalDateTime modified) {this.modified = modified;}
}

package io.github.restfulApiWebservice.restfulApiWebservice.notes;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class NoteReadModel {
    private String noteId;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime created;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime modified;

    public NoteReadModel(Note sourceNote) {
        this.noteId = sourceNote.getNoteId();
        this.title = sourceNote.getTitle();
        this.content = sourceNote.getContent();
        this.created = sourceNote.getCreated();
        this.modified = sourceNote.getModified();
    }

    public String getNoteId() {return noteId;}
    public String getTitle() {return title;}
    public String getContent() {return content;}
    public LocalDateTime getCreated() {return created;}
    public LocalDateTime getModified() {return modified;}
}

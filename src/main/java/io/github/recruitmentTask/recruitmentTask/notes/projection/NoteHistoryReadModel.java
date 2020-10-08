package io.github.recruitmentTask.recruitmentTask.notes.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.recruitmentTask.recruitmentTask.notes.Note;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@JsonIgnoreProperties({"noteId"})
public class NoteHistoryReadModel {
    private String title;
    private String content;
    private String noteId;
    private Integer version;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime created;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime modified;

    public NoteHistoryReadModel(Note sourceNote) {
        this.title = sourceNote.getTitle();
        this.content = sourceNote.getContent();
        this.noteId = sourceNote.getNoteId();
        this.version = sourceNote.getVersion();
        this.created = sourceNote.getCreated();
        this.modified = sourceNote.getModified();
    }

    public String getTitle() {return title;}
    public void setTitle(final String title) {this.title = title;}

    public String getContent() {return content;}
    public void setContent(final String content) {this.content = content;}

    public String getNoteId() {return noteId;}

    public Integer getVersion() {return version;}
    public void setVersion(final Integer version) {this.version = version;}

    public LocalDateTime getCreated() {return created;}
    public void setCreated(final LocalDateTime created) {this.created = created;}

    public LocalDateTime getModified() {return modified;}
    public void setModified(final LocalDateTime modified) {this.modified = modified;}
}

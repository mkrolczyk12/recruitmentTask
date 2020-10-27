package io.github.recruitmentTask.recruitmentTask.notes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"id", "version", "actualVersion", "deleted", "created", "modified"})
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Note title can not be null or empty")
    private String title;
    @NotBlank(message = "Note content can not be null or empty")
    private String content;

    private String noteId;
    private Integer version;
    private Boolean actualVersion;
    private Boolean deleted = false;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime created;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime modified;

    /**
     * Hibernate use it
     */
    Note() {
    }

    private Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    Note(Note prevNote) {
        this.title = prevNote.getTitle();
        this.content = prevNote.getContent();
        this.noteId = prevNote.noteId;
        this.version = prevNote.getVersion() + 1;
        this.actualVersion = prevNote.actualVersion;
        this.deleted = prevNote.deleted;
        this.created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public Long getId() {return id;}

    public String getTitle() {return title;}
    void setTitle(final String title) {this.title = title;}

    public String getContent() {return content;}
    void setContent(final String content) {this.content = content;}

    public String getNoteId() {return noteId;}
    private void setNoteId(final String noteId) {this.noteId = noteId;}

    public Integer getVersion() {return version;}
    private void setVersion(final Integer version) {this.version = version;}

    public Boolean getActualVersion() {return actualVersion;}
    void setActualVersion(final Boolean actualVersion) {this.actualVersion = actualVersion;}

    public Boolean getDeleted() {return deleted;}
    void setDeleted(final Boolean deleted) {this.deleted = deleted;}

    public LocalDateTime getCreated() {return created;}
    private void setCreated(final LocalDateTime created) {this.created = created;}

    public LocalDateTime getModified() {return modified;}
    void setModified(final LocalDateTime modified) {this.modified = modified;}

    public static Note prepareNote(final String title, final String content) {
        var note = new Note(title, content);
        note.setNoteId(UUID.randomUUID().toString());
        note.setDeleted(false);
        note.setVersion(1);
        note.setActualVersion(true);
        note.setCreated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return note;
    }
}


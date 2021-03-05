package io.github.restfulApiWebservice.restfulApiWebservice.notes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "notes")
class Note {
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

    @PersistenceConstructor
    protected Note() {
    }

    private Note(String title, String content) {
        this.title = title;
        this.content = content;
        prepareNote(this);
    }

    private Note(Note prevNote) {
        this.title = prevNote.getTitle();
        this.content = prevNote.getContent();
        this.noteId = prevNote.noteId;
        this.version = prevNote.getVersion() + 1;
        this.actualVersion = prevNote.actualVersion;
        this.deleted = prevNote.deleted;
        this.created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    private Note(NoteBuilder builder) {
        this.title = builder.title;
        this.content = builder.content;
        prepareNote(this);
    }

    /*
        Set the note's initial data
     */
    private void prepareNote(Note toPrepare) {
        toPrepare.setNoteId(UUID.randomUUID().toString());
        toPrepare.setDeleted(false);
        toPrepare.setVersion(1);
        toPrepare.setActualVersion(true);
        toPrepare.setCreated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }

    Long getId() {return id;}

    String getTitle() {return title;}
    void setTitle(final String title) {this.title = title;}

    String getContent() {return content;}
    void setContent(final String content) {this.content = content;}

    String getNoteId() {return noteId;}
    private void setNoteId(final String noteId) {this.noteId = noteId;}

    Integer getVersion() {return version;}
    private void setVersion(final Integer version) {this.version = version;}

    Boolean getActualVersion() {return actualVersion;}
    void setActualVersion(final Boolean actualVersion) {this.actualVersion = actualVersion;}

    Boolean getDeleted() {return deleted;}
    void setDeleted(final Boolean deleted) {this.deleted = deleted;}

    LocalDateTime getCreated() {return created;}
    private void setCreated(final LocalDateTime created) {this.created = created;}

    LocalDateTime getModified() {return modified;}
    void setModified(final LocalDateTime modified) {this.modified = modified;}

    static class NoteBuilder {
        @NotBlank(message = "Note title can not be null or empty")
        private String title;
        @NotBlank(message = "Note content can not be null or empty")
        private String content;

        NoteBuilder setTitle(final String title) {
            this.title = title;
            return this;
        }

        NoteBuilder setDescription(final String content) {
            this.content = content;
            return this;
        }

        Note build() {
            return new Note(this);
        }

        Note build(Note prevNote) {
            return new Note(prevNote);
        }

        Note build(final String title, final String content) {
            return new Note(title, content);
        }
    }
}


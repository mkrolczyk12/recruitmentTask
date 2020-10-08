package io.github.recruitmentTask.recruitmentTask.notes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({"noteId", "version", "actualVersion", "deleted"})
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Note title can not be null or empty")
    private String title;
    @NotBlank(message = "Note content can not be null or empty")
    private String content;

    // String uniqueID = UUID.randomUUID().toString();
    private String noteId;
    private Integer version = 1;
    private Boolean actualVersion;
    private Boolean deleted = false;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime created;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime modified;

    /**
     * Hibernate use it
     */
    public Note() {
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() {return id;}

    public String getTitle() {return title;}
    public void setTitle(final String title) {this.title = title;}

    public String getContent() {return content;}
    public void setContent(final String content) {this.content = content;}

    public String getNoteId() {return noteId;}
    public void setNoteId(final String noteId) {this.noteId = noteId;}

    public Integer getVersion() {return version;}
    public void setVersion(final Integer version) {this.version = version;}

    public Boolean getActualVersion() {return actualVersion;}
    public void setActualVersion(final Boolean actualVersion) {
        this.actualVersion = actualVersion;}

    public Boolean getDeleted() {return deleted;}
    public void setDeleted(final Boolean deleted) {this.deleted = deleted;}

    public LocalDateTime getCreated() {return created;}
    public void setCreated(final LocalDateTime created) {this.created = created;}

    public LocalDateTime getModified() {return modified;}
    public void setModified(final LocalDateTime modified) {this.modified = modified;}

    public void fullUpdate(Note toUpdate) {
        this.title = toUpdate.title;
        this.content = toUpdate.content;
    }

}


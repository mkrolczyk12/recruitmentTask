package io.github.recruitmentTask.recruitmentTask.notes.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.recruitmentTask.recruitmentTask.notes.Note;

@JsonIgnoreProperties({"noteId"})
public class NoteHistoryReadModel extends NoteReadModel {
    private Integer version;

    public NoteHistoryReadModel(final Note sourceNote) {
        super(sourceNote);
        this.version = sourceNote.getVersion();
    }

    public Integer getVersion() {return version;}
}

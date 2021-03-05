package io.github.restfulApiWebservice.restfulApiWebservice.notes;

public class NoteHistoryReadModel extends NoteReadModel {
    private Integer version;

    public NoteHistoryReadModel(final Note sourceNote) {
        super(sourceNote);
        this.version = sourceNote.getVersion();
    }

    public Integer getVersion() {return version;}
}

package io.github.recruitmentTask.recruitmentTask.notes;

import java.util.List;

public interface NoteRepository {
    public List<Note> findAllByActualVersionAndDeleted(Boolean actualVersion, Boolean deleted);
}

package io.github.restfulApiWebservice.restfulApiWebservice.notes;

import java.util.List;
import java.util.Optional;

interface NoteRepository {
    Note save(final Note toNote);
    Note saveAndFlush(final Note toUpdate);

    Boolean existsByNoteIdAndDeleted(String noteId, Boolean deleted);
    Optional<Note> findByNoteIdAndDeletedAndActualVersion(final String noteId, Boolean deleted, Boolean actualVersion);
    Optional<Note> findByNoteIdAndActualVersion(String noteId, Boolean actualVersion);
    List<Note> findAllByActualVersionAndDeleted(Boolean actualVersion, Boolean deleted);
    List<Note> findAllByNoteId(final String noteId);
    List<Note> findAllByNoteIdOrderByVersionAsc(final String noteId);
    List<Note> findAllByNoteIdAndDeleted(final String noteId, Boolean deleted);
}

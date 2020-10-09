package io.github.recruitmentTask.recruitmentTask.notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlNoteRepository extends NoteRepository, JpaRepository<Note, Long> {
}

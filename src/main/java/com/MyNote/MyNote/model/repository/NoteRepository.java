package com.MyNote.MyNote.model.repository;

import com.MyNote.MyNote.model.entity.Note;
import com.MyNote.MyNote.model.entity.NoteUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByNoteUser(NoteUser noteUser, Sort sort);

}

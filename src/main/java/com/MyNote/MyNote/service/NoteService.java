package com.MyNote.MyNote.service;

import com.MyNote.MyNote.model.entity.Note;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface NoteService {

    void createNewNote(String username, Note note);

    Optional<Note> findById(Long id);

    void editNoteById(Long id, Note targeNnote);

    void deleteNote(Long id);
}

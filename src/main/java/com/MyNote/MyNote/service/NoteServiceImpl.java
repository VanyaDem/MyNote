package com.MyNote.MyNote.service;

import com.MyNote.MyNote.model.entity.Note;
import com.MyNote.MyNote.model.repository.NoteRepository;
import com.MyNote.MyNote.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    @Override
    public void createNewNote(String username, Note note) {
        var noteUser = userRepository.findByUsername(username).orElseThrow();
        note.setNoteUser(noteUser);
        note.setLastModified(LocalDateTime.now());
        noteRepository.save(note);
    }

    @Override
    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public void editNoteById(Long id, Note targeNnote) {
        var existingNote = noteRepository.findById(id);
        if (existingNote.isPresent()) {

            Note updatedNote = existingNote.get();

            updatedNote.setTitle(targeNnote.getTitle());
            updatedNote.setContent(targeNnote.getContent());
            updatedNote.setLastModified(LocalDateTime.now());
            noteRepository.save(updatedNote);
        }
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository
                .findById(id)
                .ifPresent(noteRepository::delete);
    }
}

package com.MyNote.MyNote.service;

import com.MyNote.MyNote.model.entity.Note;
import com.MyNote.MyNote.model.repository.NoteRepository;
import com.MyNote.MyNote.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    @Override
    public List<Note> findAllNotesByUsername(String username) {

        var noteUser = userRepository.findByUsername(username)
                .orElseThrow();
        return noteRepository
                .findAllByNoteUser(noteUser, Sort.by("lastModified").descending());

    }
}

package com.MyNote.MyNote.service;

import com.MyNote.MyNote.model.dto.UserRegistrationDTO;
import com.MyNote.MyNote.model.entity.Note;
import com.MyNote.MyNote.model.entity.NoteUser;
import com.MyNote.MyNote.model.repository.NoteRepository;
import com.MyNote.MyNote.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Note> findAllNotesByUsername(String username) {

        var noteUser = userRepository.findByUsername(username)
                .orElseThrow();
        return noteRepository
                .findAllByNoteUser(noteUser, Sort.by("lastModified").descending());

    }

    @Override
    public void addNewUser(UserRegistrationDTO userDto) {
        var user = new NoteUser();
        user.setUsername(userDto.username());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        userRepository.save(user);
    }
}

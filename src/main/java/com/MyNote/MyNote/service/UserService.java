package com.MyNote.MyNote.service;

import com.MyNote.MyNote.model.dto.UserRegistrationDTO;
import com.MyNote.MyNote.model.entity.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<Note> findAllNotesByUsername(String username);

    void addNewUser(UserRegistrationDTO userDto);
}

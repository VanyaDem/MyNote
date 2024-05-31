package com.MyNote.MyNote.service;

import com.MyNote.MyNote.model.entity.NoteUser;
import com.MyNote.MyNote.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
        createInitialUser(userRepository);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    private void createInitialUser(UserRepository userRepository) {
        NoteUser noteUser = new NoteUser();
        noteUser.setUsername("2");
        noteUser.setPassword("$2a$10$6PiGUEiHZ5bQKihtmkMtQ.FMlzdEMqa8A./46h3aWLWju5P./vom6");
        userRepository.save(noteUser);
    }

}

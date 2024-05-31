package com.MyNote.MyNote.model.repository;

import com.MyNote.MyNote.model.entity.NoteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<NoteUser, Integer> {

    Optional<NoteUser> findByUsername(String username);
}

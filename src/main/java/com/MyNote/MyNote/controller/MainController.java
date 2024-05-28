package com.MyNote.MyNote.controller;

import com.MyNote.MyNote.model.entity.Note;
import com.MyNote.MyNote.model.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final NoteRepository noteRepository;

    @GetMapping("/")
    public String welcome(Model model, Principal principal){
        return "welcome";
    }

    @GetMapping("/main")
    public String index(Model model, Principal principal) {
        List<Note> notes = noteRepository.findAll(Sort.by("lastModified").descending());
        model.addAttribute("user", principal);
        model.addAttribute("notes", notes);
        return "index";
    }

    @GetMapping("/create")
    public String createNoteForm(Model model, Principal principal) {
        model.addAttribute("note", new Note());
        return "note-form";
    }

    @PostMapping("/create")
    public String createNote(@ModelAttribute Note note) {
        note.setLastModified(LocalDateTime.now());
        noteRepository.save(note);
        return "redirect:/main";
    }

    @GetMapping("/edit/{id}")
    public String editNoteForm(@PathVariable Long id, Model model, Principal principal) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()) {
            model.addAttribute("note", note.get());
            return "note-form";
        } else {
            return "redirect:/main";
        }
    }

    @PostMapping("/edit/{id}")
    public String editNote(@PathVariable Long id, @ModelAttribute Note note) {
        Optional<Note> existingNote = noteRepository.findById(id);
        if (existingNote.isPresent()) {
            Note updatedNote = existingNote.get();
            updatedNote.setTitle(note.getTitle());
            updatedNote.setContent(note.getContent());
            updatedNote.setLastModified(LocalDateTime.now());
            noteRepository.save(updatedNote);
        }
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id, @ModelAttribute Note note){
        Optional<Note> targetNote = noteRepository.findById(id);
        targetNote.ifPresent(noteRepository::delete);
        return "redirect:/main";
    }
}

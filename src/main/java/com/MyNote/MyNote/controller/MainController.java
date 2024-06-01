package com.MyNote.MyNote.controller;

import com.MyNote.MyNote.model.entity.Note;
import com.MyNote.MyNote.service.interfaces.NoteService;
import com.MyNote.MyNote.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService userService;

    private final NoteService noteService;

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/main")
    public String index(Model model, Principal principal) {

        var listOfNotes = userService.findAllNotesByUsername(principal.getName());
        model.addAttribute("notes", listOfNotes);
        return "index";
    }

    @GetMapping("/create")
    public String createNoteForm(Model model) {
        model.addAttribute("note", new Note());
        return "note-form";
    }

    @PostMapping("/create")
    public String createNote(@ModelAttribute Note note, Principal principal) {
        noteService.createNewNote(principal.getName(), note);
        return "redirect:/main";
    }

    @GetMapping("/edit/{id}")
    public String editNoteForm(@PathVariable Long id, Model model) {

        var optionalNote = noteService.findById(id);

        return optionalNote
                .map(note -> putNoteToModel(model, note))
                .orElse("redirect:/main");
    }

    @PostMapping("/edit/{id}")
    public String editNote(@PathVariable Long id, @ModelAttribute Note note) {
        noteService.editNoteById(id, note);
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/main";
    }

    private String putNoteToModel(Model model, Note note) {
        model.addAttribute("note", note);
        return "note-form";
    }
}

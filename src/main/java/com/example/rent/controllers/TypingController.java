package com.example.rent.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.rent.models.Arendator;
import com.example.rent.models.Space;
import com.example.rent.models.Typing;
import com.example.rent.services.TypingServices;

@Controller
public class TypingController {
    private final TypingServices typingService;

    public TypingController(TypingServices typingService) {
        this.typingService = typingService;
    }

    @GetMapping("/typing")
    public String typingPage(Model model) {
        List<Typing> allTyping = typingService.getAllTypesOfSpaces();
        model.addAttribute("allTyping", allTyping);
        return "typing";
    }

    @GetMapping("/delete/typing/{id}")
    public String deleteTyping(@PathVariable Long id) {
        typingService.deleteTypeOfSpace(id);
        return "redirect:/typing";
    }

    @GetMapping("/addTyping")
    public String showAddForm(Model model) {
        model.addAttribute("typing", new Typing());
        return "addTyping";
    }

    // Обработка формы добавления типа
    @PostMapping("/addTyping")
    public String addTyping(@ModelAttribute Typing newTyping) {
        typingService.saveTypeOfSpace(newTyping);
        return "redirect:/typing";
    }

    @GetMapping("/editTyping/{id}")
    public String showEditSpaceForm(@PathVariable("id") long typingId, Model model) {
        Typing typing = typingService.getTypeOfSpaceById(typingId)
                .orElseThrow(() -> new RuntimeException("Запись не найдена"));
        model.addAttribute("typing", typing);
        return "editTyping";
    }

    @PostMapping("/editTyping/{id}")
    public String editTyping(@PathVariable long id, @ModelAttribute Typing typing) {
        // Получаем существующий объект Typing по его идентификатору
        Typing existingTyping = typingService.getTypeOfSpaceById(id)
                .orElseThrow(() -> new RuntimeException("Запись не найдена"));

        // Обновляем данные объекта Typing
        existingTyping.setName(typing.getName());
        // Можете добавить другие обновления, если необходимо

        // Сохраняем обновленный объект Typing в базе данных
        typingService.saveTypeOfSpace(existingTyping);
        return "redirect:/typing";
    }
}

package com.example.rent.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rent.models.Arendator;
import com.example.rent.models.Space;
import com.example.rent.models.Typing;
import com.example.rent.services.ArendatorServices;
import com.example.rent.services.SpaceServices;
import com.example.rent.services.TypingServices;

@Controller
public class SpaceController {
    private final SpaceServices spaceService;
    private final ArendatorServices arendatorService;
    private final TypingServices typingService;

    public SpaceController(SpaceServices spaceService, ArendatorServices arendatorService,
            TypingServices typingService) {
        this.spaceService = spaceService;
        this.arendatorService = arendatorService;
        this.typingService = typingService;

    }

    @GetMapping("/space")
    public String spacePage(Model model) {
        List<Space> spaces = spaceService.getAllSpaces();
        model.addAttribute("spaces", spaces);
        return "space";
    }

    @GetMapping("/delete/space/{id}")
    public String deleteSpace(@PathVariable Long id) {
        spaceService.deleteSpace(id);
        return "redirect:/space";
    }

    @GetMapping("/addSpace")
    public String showAddForm(Model model) {
        model.addAttribute("space", new Space());
        model.addAttribute("arendators", arendatorService.getAllArendator()); // Получаем список Арендаторов
        model.addAttribute("types", typingService.getAllTypesOfSpaces()); // Получаем список Типов помещений
        model.addAttribute("allSpace", spaceService.getAllSpaces());// Получаем существующих помещений

        return "addSpace";
    }

    // Обработка формы добавления помещения
    @PostMapping("/addSpace")
    public String addSpace(@ModelAttribute Space newSpace) {
        if (newSpace.getInsert_id() == null) {
            newSpace.setInsert_id(newSpace);
        }
        spaceService.saveSpace(newSpace);
        return "redirect:/space";
    }

    @GetMapping("/editSpace/{id}")
    public String showEditSpaceForm(@PathVariable("id") long spaceId, Model model) {
        Space space = spaceService.getSpaceById(spaceId).orElseThrow(() -> new RuntimeException("Запись не найдена"));
        List<Arendator> arendators = arendatorService.getAllArendator();
        List<Typing> typings = typingService.getAllTypesOfSpaces();
        List<Space> allSpace = spaceService.getAllSpaces();

        model.addAttribute("space", space);
        model.addAttribute("arendators", arendators);
        model.addAttribute("types", typings);
        model.addAttribute("allSpaces", allSpace);
        return "editSpace";
    }

    @PostMapping("/editSpace/{id}")
    public String editSpace(@PathVariable("id") long spaceId, @ModelAttribute("space") Space updatedSpace) {
        spaceService.updateSpace(spaceId, updatedSpace);
        return "redirect:/space";
    }
}

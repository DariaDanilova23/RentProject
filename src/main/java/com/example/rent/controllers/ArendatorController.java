package com.example.rent.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rent.models.Arendator;
import com.example.rent.models.Typing;
import com.example.rent.services.ArendatorServices;

@Controller
public class ArendatorController {
    private final ArendatorServices arendatorService;
    private static final Logger log = LoggerFactory.getLogger(ArendatorController.class);

    public ArendatorController(ArendatorServices arendatorService) {
        this.arendatorService = arendatorService;
    }

    @GetMapping("/arendator")
    public String arendatorPage(Model model) {
        List<Arendator> arendators = arendatorService.getAllArendator();
        model.addAttribute("arendators", arendators);
        return "arendator";
    }

    @GetMapping("/delete/arendator/{id}")
    public String deleteArendator(@PathVariable Long id) {
        arendatorService.deleteArendator(id);
        return "redirect:/arendator";
    }

    @GetMapping("/addArendator")
    public String showAddForm(Model model) {
        model.addAttribute("arendator", new Arendator());
        return "addArendator";
    }

    // Обработка формы добавления пользователя
    @PostMapping("/addArendator")
    public String addArendator(@ModelAttribute Arendator newArendator) {
        arendatorService.saveArendator(newArendator);
        return "redirect:/arendator";
    }

    @GetMapping("/editArendator/{id}")
    public String showEditSpaceForm(@PathVariable("id") long arendatorId, Model model) {
        Arendator arendator = arendatorService.getArendatorById(arendatorId);
        model.addAttribute("arendator", arendator);
        return "editArendator";
    }

    @PostMapping("/editArendator/{id}")
    public String editArendator(@PathVariable long id, @ModelAttribute Arendator arendator) {

        arendatorService.updateArendator(arendator);
        return "redirect:/arendator";

    }
}

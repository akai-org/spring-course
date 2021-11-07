package pl.org.akai.springbasic.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.org.akai.springbasic.entity.ProgrammingLanguage;
import pl.org.akai.springbasic.repository.ProgrammingLanguageRepository;

@Controller
@AllArgsConstructor
public class ProgrammingLanguageController {
    private final ProgrammingLanguageRepository programmingLanguageRepository;

    @GetMapping("/")
    public String listProgrammingLanguage(Model model) {
        var programmingLanguages = programmingLanguageRepository.findAll();
        model.addAttribute("programmingLanguages", programmingLanguages);
        return "views/index";
    }

    @GetMapping("/form")
    public String addProgrammingLanguageForm(Model model) {
        model.addAttribute("programmingLanguage", new ProgrammingLanguage());
        model.addAttribute("formTitle", "Formularz dodania języku programowania");
        return "views/form";
    }

    @PostMapping("/form")
    public String addProgrammingLanguageSubmit(@ModelAttribute ProgrammingLanguage programmingLanguage) {
        programmingLanguageRepository.save(programmingLanguage);
        return "redirect:/";
    }
}

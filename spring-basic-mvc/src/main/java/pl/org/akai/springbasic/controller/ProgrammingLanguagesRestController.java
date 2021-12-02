package pl.org.akai.springbasic.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.org.akai.springbasic.entity.ProgrammingLanguage;
import pl.org.akai.springbasic.repository.ProgrammingLanguageRepository;

@RestController
@AllArgsConstructor
public class ProgrammingLanguagesRestController {

    private final ProgrammingLanguageRepository programmingLanguageRepository;

    @GetMapping("/json/{id}")
    public ProgrammingLanguage listProgrammingLanguage(@PathVariable("id") int pl) {
        return programmingLanguageRepository.findById(pl).orElse(new ProgrammingLanguage());
    }

}

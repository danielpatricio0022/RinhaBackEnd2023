package org.rinha.controller;

import org.rinha.model.PersonRqDTO;
import org.rinha.model.PersonRsDTO;
import org.rinha.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@Validated
@RequestMapping("/pessoas")
public class Controllerapi implements WebMvcConfigurer {

    @Autowired
    private PersonService personService;
    private PersonRqDTO personRqDTO;

    @PostMapping
    public ResponseEntity<?> createPerson(@Valid @RequestBody PersonRqDTO personRqDTO, BindingResult result) {

        if (result.hasErrors()) {

            StringBuilder errorMessages = new StringBuilder();
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body("Erro de validação: " + errorMessages.toString());
        }

        PersonRsDTO createdPerson = personService.createPerson(personRqDTO);

        return ResponseEntity.ok(createdPerson);
    }

    @GetMapping
    public ResponseEntity<List<PersonRsDTO>> getPersonsByTerm(@RequestParam(value = "t", required = false) String term) {
        List<PersonRsDTO> persons = personService.getPersonByTerm(term != null ? term : "");
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonRsDTO> getPersonById(@PathVariable UUID id) {
        PersonRsDTO person = personService.getPersonById(id);
        return ResponseEntity.ok(person);
    }


}

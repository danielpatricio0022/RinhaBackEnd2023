package org.rinha.controller;


import org.rinha.exception.ExceptionBadRequest;
import org.rinha.exception.UnprocessableEntityException;
import org.rinha.model.PersonRqDTO;
import org.rinha.model.PersonRsDTO;
import org.rinha.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class PersonController implements WebMvcConfigurer {

    @Autowired
    private PersonService personService;
    private PersonRqDTO personRqDTO;

    @PostMapping
    public ResponseEntity<?> createPerson(@Valid @RequestBody PersonRqDTO personRqDTO) {

        if (personRqDTO.getApelido() == null || personRqDTO.getNome() == null) {
            throw new UnprocessableEntityException("apelido and name are required");
        }

            PersonRsDTO createdPerson = personService.createPerson(personRqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);// 201
    }

    @GetMapping
    public ResponseEntity<List<PersonRsDTO>> getPersonsByTerm(
            @RequestParam(value = "t") String term) {

        if (term == null || term.isBlank()) {
            throw new ExceptionBadRequest();
        }

        List<PersonRsDTO> persons = personService.getPersonByTerm(term);
        return ResponseEntity.ok(persons);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PersonRsDTO> getPersonById(@PathVariable UUID id) {

            PersonRsDTO person = personService.getPersonById(id);
        return ResponseEntity.ok(person);
    }


    @GetMapping("/contagem-pessoas")
    public ResponseEntity<String> getTotalPerson() {

        long count = personService.getTotalPerson();
        return ResponseEntity.ok(String.valueOf(count));
    }

}

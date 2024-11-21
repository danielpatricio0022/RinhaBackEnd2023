package org.rinha.service;
import org.rinha.exception.NotFoundException;
import org.rinha.exception.UnprocessableEntityException;
import org.rinha.model.Person;
import org.rinha.model.PersonRqDTO;
import org.rinha.model.PersonRsDTO;
import org.rinha.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public PersonRsDTO createPerson( PersonRqDTO personRqDTO) {
          var data = personRepository.existsByApelido(personRqDTO.getApelido());
            if (data) {
                throw new UnprocessableEntityException("apelido already exists "); // 422
            }
              var person = new Person(
                      personRqDTO.getApelido(),
                      personRqDTO.getNome(),
                      personRqDTO.getNascimento(),
                      personRqDTO.getStack()
              );

                var response = personRepository.save(person);

                return new PersonRsDTO(
                        response.getUuid(),
                        response.getApelido(),
                        response.getNome(),
                        response.getNascimento(),
                        response.getStack()
                );
    }


    @Transactional(readOnly = true)
    public List<PersonRsDTO> getPersonByTerm(String term) {

        var result = personRepository.searchByTerm(term);

        return result.stream()
                .map(p -> new PersonRsDTO(
                        p.getUuid(),
                        p.getApelido(),
                        p.getNome(),
                        p.getNascimento(),
                        p.getStack()
                ))
                .limit(50)
                .collect(Collectors.toList());
    }


    public PersonRsDTO getPersonById( UUID id){

        Person person = personRepository.findById(id)
                .orElseThrow(NotFoundException::new);

                return new PersonRsDTO(
                        person.getUuid(),
                        person.getApelido(),
                        person.getNome(),
                        person.getNascimento(),
                        person.getStack()
                );
    }


    public Long getTotalPerson() {
        return personRepository.count();
    }


}




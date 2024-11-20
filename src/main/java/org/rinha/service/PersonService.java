package org.rinha.service;
import org.rinha.exception.UnprocessableEntity;
import org.rinha.model.Person;
import org.rinha.model.PersonRqDTO;
import org.rinha.model.PersonRsDTO;
import org.rinha.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public PersonRsDTO createPerson( PersonRqDTO personRqDTO) {
          var data = personRepository.existsByApelido(personRqDTO.getApelido());
            if (data) {
                throw new UnprocessableEntity("apelido already exists"); // 422 TODO: exception
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

      boolean data = personRepository.existsById(id);
          if(!data){
              throw new UnprocessableEntity("not found"); // tratar
          }

            Optional<Person> personOpt = personRepository.findById(id);

               Person person = personOpt.orElseThrow
                                          (RuntimeException::new);
              // is present

                return new PersonRsDTO(
                        person.getUuid(),
                        person.getApelido(),
                        person.getNome(),
                        person.getNascimento(),
                        person.getStack()
                );
    }
}




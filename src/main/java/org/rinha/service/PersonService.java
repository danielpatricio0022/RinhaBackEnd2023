package org.rinha.service;
import org.hibernate.mapping.List;
import org.rinha.exception.UnprocessableEntity;
import org.rinha.model.Person;
import org.rinha.model.PersonRqDTO;
import org.rinha.model.PersonRsDTO;
import org.rinha.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public PersonRsDTO createPerson(PersonRqDTO personRqDTO) {
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

    public PersonRsDTO getAllPerson(PersonRqDTO personRqDTO){


    }

    public PersonRsDTO getPersonById(PersonRqDTO personRqDTO, UUID id){

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



}

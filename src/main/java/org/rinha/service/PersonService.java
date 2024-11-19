package org.rinha.service;
import org.rinha.model.Person;
import org.rinha.model.PersonRqDTO;
import org.rinha.model.PersonRsDTO;
import org.rinha.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public PersonRsDTO createPerson(PersonRqDTO personRqDTO) {
          var data = personRepository.existsByApelido(personRqDTO.getApelido());
            if (data) {
                throw new RuntimeException("apelido already exists"); // 422 TODO: exception
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


}

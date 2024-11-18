package org.rinha.service;
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
                throw new RuntimeException("Apelido já existe"); // TODO: criar exceção
            }

    }

}

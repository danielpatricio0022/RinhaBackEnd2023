package org.rinha.service;


import org.rinha.model.PersonRqDTO;
import org.rinha.model.PersonRsDTO;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public PersonRsDTO createPerson(PersonRqDTO personRqDTO) {
        return personRqDTO;
    }

}

package org.rinha.repository;

import org.rinha.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    @Query("SELECT COUNT(p) > 0 FROM Person p WHERE p.apelido = :apelido")
    boolean existsByApelido(@Param("apelido") String apelido);

}

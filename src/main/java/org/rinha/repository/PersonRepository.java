package org.rinha.repository;

import org.rinha.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    @Query("SELECT COUNT(p) > 0 FROM Person p WHERE p.apelido = :apelido")
    boolean existsByApelido(@Param("apelido") String apelido); // select count(*) from person where apelido = :apelido

    @Query("SELECT p FROM Person p " +
            "WHERE LOWER(p.apelido) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "   OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "   OR EXISTS (SELECT s FROM p.stack s WHERE LOWER(s) LIKE LOWER(CONCAT('%', :term, '%'))) " +
            "ORDER BY p.nome")
    List<Person> searchByTerm(@Param("term") String term); // select * from person where apelido like %term% or nome like %term% or stack like %term%
}

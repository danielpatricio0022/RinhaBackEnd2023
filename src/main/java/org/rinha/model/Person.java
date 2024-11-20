package org.rinha.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @Column(name = "apelido", nullable = false, length = 32, unique = true)
    private String apelido;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "nascimento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nascimento;

    @ElementCollection
    @CollectionTable(name = "stack", joinColumns = @JoinColumn(name = "person_id"))
    @Column(name = "stack", length = 32, nullable = true) // mapping for the collection table
    private List<String> stack;

    public Person() {
    }

    public Person(String apelido, String nome, LocalDate nascimento, List<String> stack) {
        this.apelido = apelido;
        this.nome = nome;
        this.nascimento = nascimento;
        this.stack = stack;
    }

    public UUID getUuid() {
        return uuid;
    }


    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }
}

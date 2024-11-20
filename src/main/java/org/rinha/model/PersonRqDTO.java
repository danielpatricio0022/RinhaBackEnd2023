package org.rinha.model;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;


public class PersonRqDTO {

    @NotNull(message = "The nickname must be informed")
    @NotBlank(message = "The nickname must be informed")
    @Pattern(regexp = "^[A-Za-z ]+$")
    @Size(min = 1, max = 32 , message = "The nickname must be between 1 and 32 characters")
    private String apelido;

    @NotNull(message = "The name must be informed")
    @NotBlank(message = "The name must be informed")
    @Pattern(regexp = "^[A-Za-z ]+$")
    @Size(min = 1, max = 100 , message = "The name must be between 1 and 100 characters")
    private String nome;
    @NotNull(message = "The birth date must be informed")
    private LocalDate nascimento;

    @Size(min = 1, message = "Stack not be empty")
    @Valid
    private List<@Pattern(regexp = "^[A-Za-z]+$", message = "Each stack item must be a valid language") String> stack;

    public PersonRqDTO() {
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

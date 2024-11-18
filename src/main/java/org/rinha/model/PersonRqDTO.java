package org.rinha.model;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class PersonRqDTO {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 32)
    private String apelido;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String nome;
    @NotNull
    private LocalDate nascimento;

    private List<@Size(min = 1, max = 32) String> stack;

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

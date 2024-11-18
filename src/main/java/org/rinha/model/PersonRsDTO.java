package org.rinha.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PersonRsDTO {
    private UUID id;
    private String apelido;
    private String nome;
    private LocalDate nascimento;
    private List<String> stack;
}

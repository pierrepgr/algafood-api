package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
@Entity
@Table(name = "cozinha")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Favor preencher o campo nome!")
    @Column(name = "nome", nullable = false)
    private String nome;
}

package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "permissao")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Permissao {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Favor preencher o campo nome!")
    @Column(name = "nome")
    private String nome;
    @NotBlank(message = "Favor preencher o campo descricao!")
    @Column(name = "descricao")
    private String descricao;
}

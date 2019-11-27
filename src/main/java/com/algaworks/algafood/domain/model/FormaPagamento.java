package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "forma_pagamento")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FormaPagamento {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Favor preencher o campo descrição!")
    @Column(name = "descricao", nullable = false)
    private String descricao;
}

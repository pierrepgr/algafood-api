package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "restaurante")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Favor preencher o campo nome!")
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;
    @NotNull(message = "Favor selecionar uma cozinha!")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;
    @Embedded
    @JsonIgnore
    private Endereco endereco;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamentos = new ArrayList<FormaPagamento>();
}

package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "produto")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean ativo;
    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
}

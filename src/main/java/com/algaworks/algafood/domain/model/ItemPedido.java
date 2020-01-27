package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "item_pedido")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Favor preencher o campo quantidade!")
    @Column(name = "quantidade")
    private Integer quantidade;
    @NotNull(message = "Favor preencher o campo preço unitário!")
    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;
    @NotNull(message = "Favor preencher o preço total!")
    @Column(name = "preco_total")
    private BigDecimal precoTotal;
    @Column(name = "obervacao", columnDefinition = "TEXT")
    private String observacao;
    @NotNull(message = "Favor selecionar o pedido!")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @OneToOne
    @NotNull(message = "Favor selecionar o produto!")
    @JoinColumn(name = "produto_id")
    private Produto produto;
}

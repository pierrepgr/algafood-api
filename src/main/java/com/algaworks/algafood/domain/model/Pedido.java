package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pedido")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DecimalMin(value = "0", message = "O valor mínimo do campo Sub. total é de 0.00")
    @NotNull(message = "Favor preencher o campo Sub. Total!")
    @Column(name = "sub_total")
    private BigDecimal subTotal;
    @DecimalMin(value = "0", message = "O valor mínimo do campo Taxa frete é de 0.00")
    @NotNull(message = "Favor preencher o campo Taxa frete!")
    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;
    @DecimalMin(value = "0", message = "O valor mínimo do campo Valor total é de 0.00")
    @NotNull(message = "Favor preencher o campo Valor Total!")
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @CreationTimestamp
    @Column(name = "data_criacao")
    @NotNull(message = "Favor preencher o campo data de criação!")
    private LocalDateTime dataCriacao;
    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;
    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;
    @Column(name = "data_entrega")
    private LocalDateTime dataEntrega;
    @OneToOne
    @NotNull(message = "Favor selecionar um forma de pagamento!")
    @JoinColumn(name = "forma_pagamento_id")
    private FormaPagamento formaPagamento;
    @OneToOne
    @NotNull(message = "Favor selecionar um Restaurante!")
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
    @OneToOne
    @NotNull(message = "Favor selecionar um cliente!")
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;
    @Embedded
    private Endereco enderecoEntrega;
    @NotNull(message = "Favor selecionar um status!")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<ItemPedido>();
}

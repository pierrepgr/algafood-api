package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {

    public List<FormaPagamento> listar();
    public FormaPagamento buscarPorId(Long id);
    public FormaPagamento salvar(FormaPagamento formaPagamento);
    public void remover(FormaPagamento formaPagamento);
}

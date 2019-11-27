package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {

    public List<Cidade> listar();
    public Cidade buscarPorId(Long id);
    public Cidade salvar(Cidade cidade);
    public void remover(Cidade cidade);
}

package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    public List<Cozinha> listar();
    public Cozinha buscarPorId(Long id);
    public Cozinha salvar(Cozinha cozinha);
    public void remover(Cozinha cozinha);
}

package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    public List<Cozinha> todas();
    public Cozinha porId(Long id);
    public Cozinha adicionar(Cozinha cozinha);
    public void remover(Cozinha cozinha);
}

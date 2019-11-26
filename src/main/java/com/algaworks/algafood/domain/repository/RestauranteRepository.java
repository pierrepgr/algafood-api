package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    public List<Restaurante> listar();
    public Restaurante buscarPorId(Long id);
    public Restaurante salvar(Restaurante restaurante);
    public void remover(Restaurante restaurante);
}

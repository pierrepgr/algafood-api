package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepositoryQuery {

    List<Restaurante> findComFreteGratis(String nome);
}

package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        return this.manager.createQuery("from Estado", Estado.class)
                .getResultList();
    }

    @Override
    public Estado buscarPorId(Long id) {
        return this.manager.find(Estado.class, id);
    }

    @Override
    public Estado salvar(Estado estado) {
        return this.manager.merge(estado);
    }

    @Override
    public void remover(Estado estado) {
        estado = this.buscarPorId(estado.getId());
        this.manager.remove(estado);
    }
}

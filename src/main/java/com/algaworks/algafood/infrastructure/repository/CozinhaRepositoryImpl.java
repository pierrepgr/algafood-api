package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {
        return this.manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

    @Override
    public Cozinha buscarPorId(Long id) {
        return this.manager.find(Cozinha.class, id);
    }

    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return null;
    }

    @Override
    public void remover(Cozinha cozinha) {
        cozinha = this.buscarPorId(cozinha.getId());
        this.manager.remove(cozinha);
    }
}

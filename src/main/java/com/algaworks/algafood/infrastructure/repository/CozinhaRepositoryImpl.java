package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {
        return this.manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

    @Override
    public List<Cozinha> consultarPorNome(String nome) {
        return this.manager.createQuery("from Cozinha where nome like :nome", Cozinha.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Override
    public Cozinha buscarPorId(Long id) {
        return this.manager.find(Cozinha.class, id);
    }

    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return this.manager.merge(cozinha);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Cozinha cozinha = this.buscarPorId(id);

        if (cozinha == null)
            throw new EmptyResultDataAccessException(1);

        this.manager.remove(cozinha);
    }
}

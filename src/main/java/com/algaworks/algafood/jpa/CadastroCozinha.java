package com.algaworks.algafood.jpa;

import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listar() {
        return this.manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

    public Cozinha buscar(Long id) {
        return this.manager.find(Cozinha.class, id);
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return this.manager.merge(cozinha);
    }

    @Transactional
    public void remover (Cozinha cozinha) {
        cozinha = buscar(cozinha.getId());
        this.manager.remove(cozinha);
    }
}

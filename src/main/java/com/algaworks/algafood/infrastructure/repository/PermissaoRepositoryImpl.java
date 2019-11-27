package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> listar() {
        return this.manager.createQuery("from Permissao", Permissao.class)
                .getResultList();
    }

    @Override
    public Permissao buscarPorId(Long id) {
        return this.manager.find(Permissao.class, id);
    }

    @Override
    public Permissao salvar(Permissao permissao) {
        return this.manager.merge(permissao);
    }

    @Override
    public void remover(Permissao permissao) {
        permissao = this.buscarPorId(permissao.getId());
        this.manager.remove(permissao);
    }
}

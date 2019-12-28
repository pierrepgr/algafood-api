package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> from = criteria.from(Restaurante.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (!StringUtils.isEmpty(nome))
            predicates.add(builder.like(from.<String>get("nome"),"%" + nome + "%"));

        if (taxaFreteInicial != null)
            predicates.add(builder.greaterThanOrEqualTo(from.<BigDecimal>get("taxaFrete"), taxaFreteInicial));

        if (taxaFreteFinal != null)
            predicates.add(builder.lessThanOrEqualTo(from.<BigDecimal>get("taxaFrete"), taxaFreteFinal));

        criteria.where(predicates.toArray(new Predicate[predicates.size()]));

        TypedQuery<Restaurante> query = this.manager.createQuery(criteria);
        return query.getResultList();
    }
}

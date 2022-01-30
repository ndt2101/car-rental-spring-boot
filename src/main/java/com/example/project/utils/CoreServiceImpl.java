package com.example.project.utils;

import com.example.project.base.BaseEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaDelete;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class CoreServiceImpl {

    @PersistenceContext
    protected EntityManager entityManager;

    public final <T extends BaseEntity> int deleteAll(Class<T> clazz) {
        CriteriaDelete<T> criteriaDelete = entityManager.getCriteriaBuilder().createCriteriaDelete(clazz);
        criteriaDelete.from(clazz);
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }

}
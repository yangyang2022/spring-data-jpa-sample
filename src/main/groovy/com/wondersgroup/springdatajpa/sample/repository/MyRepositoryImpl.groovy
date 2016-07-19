package com.wondersgroup.springdatajpa.sample.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository

import javax.persistence.EntityManager
import javax.persistence.TypedQuery
import javax.persistence.criteria.*

import static org.apache.commons.lang3.StringUtils.*
import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders

/**
 * Created by wangqiang on 16/7/19.
 */
class MyRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID> {

    private EntityManager em

    MyRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager)

        this.em = entityManager
    }

    @Override
    List<T> findAll(Map filters) {
//        findAll(filters, null)
    }

    @Override
    Page<T> findAll(Map filters, Pageable pageable) {
        TypedQuery<T> query = getQuery(filters, pageable)
        !pageable ? new PageImpl<T>(query.resultList) : readPage(query, pageable, filters)
    }

    protected TypedQuery<T> getQuery(Map filters, Pageable pageable) {
        getQuery(filters, getDomainClass(), pageable?.sort)
    }

    protected <S extends T> TypedQuery<S> getQuery(Map filters, Class<S> domainClass, Sort sort) {

        CriteriaBuilder builder = em.criteriaBuilder
        CriteriaQuery<S> query = builder.createQuery(domainClass)

        Root<S> root = applyFiltersToCriteria(filters, domainClass, query)
        query.select(root)

        if (sort) {
            query.orderBy(toOrders(sort, root, builder))
        }

        em.createQuery(query)
    }

    private <S, U extends T> Root<U> applyFiltersToCriteria(Map filters, Class<U> domainClass,
                                                            CriteriaQuery<S> query) {
//        Assert.notNull(query)
//        Assert.notNull(domainClass)
        Root<U> root = query.from(domainClass)

        if (!filters) {
            return root
        }

        def cb = em.criteriaBuilder
        def predicates = []
        filters.each { k, v ->
            def name = substringBefore(k, '_')
            def operation = substringAfter(k, '_')

            def path = root
            def names = split(name, '.')
            names.each {
                path = equalsIgnoreCase(operation, 'fetch') ? path.fetch(it, JoinType.LEFT) : path.get(it)
            }

            if (containsIgnoreCase(operation, 'like')) {
                v = '%' + v + '%'
            }

            if (v) {
                predicates << cb."$operation"(path, v)
            }
        }

        if (predicates) {
            query.where(predicates as Predicate[])
        }

        root
    }

}


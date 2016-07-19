package com.wondersgroup.springdatajpa.sample.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

/**
 * Created by wangqiang on 16/7/19.
 */
@NoRepositoryBean
interface MyRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    List<T> findAll(Map filters)

    Page<T> findAll(Map filters, Pageable pageable)

}
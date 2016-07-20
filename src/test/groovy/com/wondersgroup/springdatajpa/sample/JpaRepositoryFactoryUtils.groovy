package com.wondersgroup.springdatajpa.sample

import com.wondersgroup.springdatajpa.sample.repository.MyRepositoryImpl
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory

/**
 * Created by wangqiang on 16/7/19.
 */
class JpaRepositoryFactoryUtils {

    static final JpaRepositoryFactory jpaRepositoryFactory

    static {
        jpaRepositoryFactory = new JpaRepositoryFactory(JpaUtils.entityManager)
        jpaRepositoryFactory.repositoryBaseClass = MyRepositoryImpl
    }

    private JpaRepositoryFactoryUtils() {}

}

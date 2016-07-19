package com.wondersgroup.springdatajpa.sample

import com.wondersgroup.springdatajpa.sample.repository.MyRepositoryImpl
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory
import org.springframework.data.repository.core.support.RepositoryFactorySupport

/**
 * Created by wangqiang on 16/7/19.
 */
class JpaRepositoryFactoryUtils {

    static JpaRepositoryFactory factory

    private JpaRepositoryFactoryUtils() {}

    static RepositoryFactorySupport getJpaRepositoryFactory() {
        if (!factory) {
            factory = new JpaRepositoryFactory(JpaUtils.entityManager)
            factory.repositoryBaseClass = MyRepositoryImpl.class
        }
        factory
    }

}

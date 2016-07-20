package com.wondersgroup.springdatajpa.sample

import javax.persistence.EntityManager
import javax.persistence.Persistence

/**
 * Created by wangqiang on 16/7/19.
 */
class JpaUtils {

    static final EntityManager entityManager

    static {
        def factory = Persistence.createEntityManagerFactory('default')
        entityManager = factory.createEntityManager()
    }

    private JpaUtils() {}
}

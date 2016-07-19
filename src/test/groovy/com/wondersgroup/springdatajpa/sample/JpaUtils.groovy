package com.wondersgroup.springdatajpa.sample

import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

/**
 * Created by wangqiang on 16/7/19.
 */
class JpaUtils {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    private JpaUtils() {}

    static def getEntityManagerFactory() { emf }

    static def getEntityManager() { emf.createEntityManager() }
}

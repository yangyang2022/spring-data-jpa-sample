package com.wondersgroup.springdatajpa.sample.entity

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * Created by wangqiang on 16/7/19.
 */
@MappedSuperclass
abstract class AbstractBaseEntity implements Serializable {

    @Id
    @GeneratedValue
    Long id;

}

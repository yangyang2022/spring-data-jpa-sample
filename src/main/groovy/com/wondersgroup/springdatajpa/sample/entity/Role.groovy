package com.wondersgroup.springdatajpa.sample.entity

import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by wangqiang on 16/7/20.
 */
@Entity
@Table(name = 'sec_role')
class Role extends AbstractBaseEntity {

    String name

    String description

}

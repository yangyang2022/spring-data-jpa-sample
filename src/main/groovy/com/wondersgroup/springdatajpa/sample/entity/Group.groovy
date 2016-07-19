package com.wondersgroup.springdatajpa.sample.entity

import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by wangqiang on 16/7/19.
 */
@Entity
@Table(name = 'sec_group')
class Group extends AbstractBaseEntity {

    String name

    String description

}

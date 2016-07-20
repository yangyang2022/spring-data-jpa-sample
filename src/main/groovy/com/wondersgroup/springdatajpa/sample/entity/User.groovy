package com.wondersgroup.springdatajpa.sample.entity

import javax.persistence.*

/**
 * Created by wangqiang on 16/7/19.
 */
@Entity
@Table(name = 'sec_user')
class User extends AbstractBaseEntity {

    String username

    @Column(nullable = false, unique = true)
    String loginName

    String password

    int age

    @Temporal(TemporalType.DATE)
    Date birthday

    boolean enabled

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Group group

    @ManyToMany
    @JoinTable(name = 'sec_user_role',
            joinColumns = @JoinColumn(name = 'user_id'),
            inverseJoinColumns = @JoinColumn(name = 'role_id')
    )
    Set<Role> roles

}

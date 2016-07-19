package com.wondersgroup.springdatajpa.sample.repository

import com.wondersgroup.springdatajpa.sample.entity.User

/**
 * Created by wangqiang on 16/7/19.
 */
interface UserRepository extends MyRepository<User, Long> {

}
package com.wondersgroup.springdatajpa.sample.repository;

import com.wondersgroup.springdatajpa.sample.JpaRepositoryFactoryUtils;
import com.wondersgroup.springdatajpa.sample.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangqiang on 16/7/19.
 */
public class UserRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        RepositoryFactorySupport support = JpaRepositoryFactoryUtils.getJpaRepositoryFactory();
        userRepository = support.getRepository(UserRepository.class);
    }

    @Test
    public void testFindOne() throws Exception {
        User user = userRepository.findOne(1L);
        System.out.println("user = " + user);
    }

    @Test
    public void testFinaAll() throws Exception {

        Map<String, Object> filters = new HashMap<>();
        filters.put("username_notLike", "qiang");
        filters.put("password_equal", "123456");
        filters.put("group_fetch", null);
        filters.put("age_gt", 22);

        userRepository.findAll(filters, null);
    }
}
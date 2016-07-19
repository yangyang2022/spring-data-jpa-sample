package com.wondersgroup.springdatajpa.sample.repository;

import com.wondersgroup.springdatajpa.sample.JpaRepositoryFactoryUtils;
import com.wondersgroup.springdatajpa.sample.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.util.LinkedHashMap;
import java.util.List;
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

        Map<String, Object> filters = new LinkedHashMap<>();
        filters.put("username_notLike", "qiang");
        filters.put("password_equal", "123456");
        filters.put("age_gt", 22);
        filters.put("group.name_equal", "group");
        filters.put("group.description_notEqual", "desc");
        filters.put("group_fetch", null);

        Page<User> page = userRepository.findAll(filters, null);
        List<User> users = page.getContent();
        for (User user : users) {
            System.out.println("user.getUsername() = " + user.getUsername());
        }
    }
}
package com.wondersgroup.springdatajpa.sample.repository;

import com.wondersgroup.springdatajpa.sample.JpaRepositoryFactoryUtils;
import com.wondersgroup.springdatajpa.sample.JpaUtils;
import com.wondersgroup.springdatajpa.sample.entity.Group;
import com.wondersgroup.springdatajpa.sample.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNull;

/**
 * Created by wangqiang on 16/7/19.
 */
public class UserRepositoryTest {

    private GroupRepository groupRepository;

    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        RepositoryFactorySupport support = JpaRepositoryFactoryUtils.getJpaRepositoryFactory();
        groupRepository = support.getRepository(GroupRepository.class);
        userRepository = support.getRepository(UserRepository.class);
    }

    @Test
    public void testSave() throws Exception {

        EntityManager entityManager = JpaUtils.getEntityManager();
        entityManager.getTransaction().begin();

        Group group = new Group();
        group.setName("group");
        group.setDescription("description");
        groupRepository.save(group);

        User user = new User();
        user.setUsername("王强");
        user.setLoginName("wangqiang");
        user.setPassword("123456");
        user.setAge(33);
        user.setBirthday(new Date());
        user.setEnabled(true);
        user.setGroup(group);

        userRepository.save(user);
        entityManager.getTransaction().commit();
    }

    @Test
    public void testFindOne() throws Exception {
        User user = userRepository.findOne(1L);
        assertNull(user);
    }

    @Test
    public void testFindAll() throws Exception {

        Map<String, Object> filters = new LinkedHashMap<>();
        filters.put("username_notLike", "qiang");
        filters.put("password_equal", "123456");
        filters.put("age_gt", 22);
        filters.put("group.name_equal", "group");
        filters.put("group.description_notEqual", "desc");
        filters.put("group_fetch", null);
        filters.put("roles_fetch", null);

        Page<User> page = userRepository.findAll(filters, null);
        List<User> users = page.getContent();
        for (User user : users) {
            System.out.println("user.getUsername() = " + user.getUsername());
        }
    }
}
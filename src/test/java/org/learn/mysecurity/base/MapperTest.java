package org.learn.mysecurity.base;

import org.junit.jupiter.api.Test;
import org.learn.mysecurity.mapper.UserMapper;
import org.learn.mysecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Created by wqg on 2023/1/6.
 */
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }


}

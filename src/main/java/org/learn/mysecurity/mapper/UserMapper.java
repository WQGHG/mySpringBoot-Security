package org.learn.mysecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.learn.mysecurity.model.User;

/**
 * Created by wqg on 2023/1/6.
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

package org.learn.mysecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.learn.mysecurity.model.Menu;

import java.util.List;

/**
 * Created by wqg on 2023/2/3.
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

}

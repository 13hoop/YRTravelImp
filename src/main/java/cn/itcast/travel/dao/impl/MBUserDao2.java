package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MBUserDao2 {

    @Select("select * from tab_user")
    List<User> findAll();
}

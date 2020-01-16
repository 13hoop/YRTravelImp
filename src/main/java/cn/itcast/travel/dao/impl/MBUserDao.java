package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.User;

import java.util.List;

public interface MBUserDao {
    List<User> findAll();
}

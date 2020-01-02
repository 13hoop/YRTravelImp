package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.User;

public interface UserDao {
    boolean add(User user);

    User findUserByName(String username);
}

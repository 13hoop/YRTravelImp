package cn.itcast.travel.service.impl;

import cn.itcast.travel.domain.User;

public interface UserService {
    boolean register(User user);
    boolean active(User user);
    User login(User user);
}

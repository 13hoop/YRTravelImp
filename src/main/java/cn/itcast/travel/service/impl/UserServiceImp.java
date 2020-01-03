package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImp;
import cn.itcast.travel.domain.User;

public class UserServiceImp implements UserService {
    @Override
    public boolean register(User user) {

        UserDao dao = new UserDaoImp();

        // 用户名是否被占用
        User u = dao.findUserByName(user.getUsername());
        if(u == null) {
            dao.add(user);
            return  true;
        }else  {
            return false;
        }
    }

    @Override
    public boolean active(User user) {
        UserDao dao = new UserDaoImp();

        User u = dao.findUserByName(user.getUsername());
        if(u == null) {
            dao.updateStatus(user);
            return  true;
        }else  {
            return false;
        }

    }

    @Override
    public User login(User user) {
        UserDao dao = new UserDaoImp();
        User u = null;
        u = dao.findUserBy(user.getUsername(), user.getPassword());
        return u;
    }
}

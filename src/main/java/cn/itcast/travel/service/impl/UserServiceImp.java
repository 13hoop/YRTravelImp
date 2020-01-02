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
}

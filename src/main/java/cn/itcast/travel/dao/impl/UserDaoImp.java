package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.YRJDBCTools;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImp implements UserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(YRJDBCTools.getDataSource());

    @Override
    public boolean add(User user) {

        String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email) values (?, ?, ?, ?, ?, ?, ?)";
        int r = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail());
        return r > 0;
    }

    @Override
    public User findUserByName(String username) {

        String sql = "select * from tab_user where username = ?";
        User u = null;
        u = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        return u;
    }
}

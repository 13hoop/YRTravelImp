package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.YRJDBCTools;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImp implements UserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(YRJDBCTools.getDataSource());

    @Override
    public boolean add(User user) {

        String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email, code, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int r = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(), user.getCode(), user.getStatus());

        System.out.println(">>> sql: " + sql + " \n >> reult " + user.toString());
        return r > 0;
    }

    @Override
    public boolean updateStatus(User user) {
        String sql = "update tab_user set status = 'Y' where uid=?";
        int r = jdbcTemplate.update(sql, user.getUid());
        System.out.println(">>> sql: " + sql + " \n >> reult " + user.toString());
        return r > 0;
    }

    @Override
    public User findUserByName(String username) {

        String sql = "select * from tab_user where username = ?";
        User u = null;
        try {
            u = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(">>> sql: " + sql + " \n >> findUser name " + username);

        return u;
    }

    @Override
    public User findUserBy(String username, String password) {
        User u = null;
        String sql = "select * from tab_user where username = ? and password = ?";
        try {
            u = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(">>> sql: " + sql + "  [func] ~~> findUser ");
        return u;
    }
}

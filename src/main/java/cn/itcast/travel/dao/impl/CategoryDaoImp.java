package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.YRJDBCTools;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class CategoryDaoImp implements CategoryDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(YRJDBCTools.getDataSource());

    @Override
    public List findAll() {
        List list = null;

        String sql = "select * from tab_category";

        RowMapper rowMapper = new BeanPropertyRowMapper<>(Category.class);
        list = jdbcTemplate.query(sql, rowMapper);

        System.out.println(">>> sql: " + sql + " \n >> reult " + list.toString());
        return list;
    }
}

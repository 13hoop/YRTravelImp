package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.YRJDBCTools;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface CategoryDao {
    List findAll();
}

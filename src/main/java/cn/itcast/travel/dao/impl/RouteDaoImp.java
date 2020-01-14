package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.YRJDBCTools;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class RouteDaoImp implements RouteDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(YRJDBCTools.getDataSource());

    @Override
    public List queryRouteList(int cid, int start, int end) {

        List list = null;
        String sql = "select * from tab_route where cid = ? limit ?,?";

        RowMapper mapper = new BeanPropertyRowMapper(Route.class);
        list = jdbcTemplate.query(sql, mapper, cid, start, end);
        System.out.println(">>> queryRouteList sql: " + sql + " \n >> reult " + list.toString());
        return list;
    }

    @Override
    public int queryRouteCount(int cid) {

        Integer count = 0;
        String sql = "select count(*) from tab_route where cid = ?";
        count = jdbcTemplate.queryForObject(sql, Integer.class, cid);
        System.out.println(">>> queryRouteCount sql: " + sql + " \n >> reult " + count);
        return count;
    }
}

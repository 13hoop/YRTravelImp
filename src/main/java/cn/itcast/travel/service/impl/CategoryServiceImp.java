package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImp;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImp implements CategoryService {

    @Override
    public List queryAll() {

        System.out.println("queryAll in CategoryServiceImp ");


        List<Category> list = null;
        try {

            Jedis jedis = JedisUtil.getJedis();

            // 查询
            Set<Tuple> rs = jedis.zrangeWithScores("category", 0, -1);

            if (rs.isEmpty() || rs == null) {

                System.out.println(" 未查询到，去数据库查 ");

                // 未查询到，去数据库查
                CategoryDao dao = new CategoryDaoImp();
                list = dao.findAll();

                // 储存
                for (Category item: list) {
                    jedis.zadd("category", item.getCid(), item.getCname());
                }

            }else {
                list = new ArrayList<Category>();
                for (Tuple key: rs) {
                    Category i = new Category();
                    i.setCname(key.getElement());
                    i.setCid((int)key.getScore());
                    list.add(i);
                }
            }
        }catch (JedisConnectionException e) {
            e.printStackTrace();

            System.out.println(" JedisConnectionException here");

            CategoryDao dao = new CategoryDaoImp();
            list = dao.findAll();
        }

        System.out.println("queryAll in CategoryServiceImp result: " + list.toString());

        return list;
    }
}

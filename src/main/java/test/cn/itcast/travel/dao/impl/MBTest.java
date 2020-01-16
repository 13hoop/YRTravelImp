package test.cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.impl.MBUserDao;
import cn.itcast.travel.dao.impl.MBUserDao2;
import cn.itcast.travel.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.apache.ibatis.io.Resources;

import java.io.InputStream;
import java.util.List;

public class MBTest {
    public static void main(String arg[]) throws Exception {
        System.out.println(" ~~~> MBTest here");
        // config
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        // factory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);

        // session
        SqlSession session = factory.openSession();

        // proxy
        MBUserDao2 dao = session.getMapper(MBUserDao2.class);

        // do what you want
        List<User> list = dao.findAll();
        System.out.println(list.toString());

        // close
        session.close();
        inputStream.close();
    }
}

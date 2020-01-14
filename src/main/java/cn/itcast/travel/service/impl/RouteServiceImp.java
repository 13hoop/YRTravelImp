package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImp;

import java.util.List;

public class RouteServiceImp implements RouteService {

    private RouteDao dao = new RouteDaoImp();

    @Override
    public int queryRouteCount(int cid) {
        int count = 0;
        count = dao.queryRouteCount(cid);
        return count;
    }

    @Override
    public List queryRouteList(int cid, int start, int size) {
        List list = null;
        list = dao.queryRouteList(cid, start, start + size);
        return list;
    }
}

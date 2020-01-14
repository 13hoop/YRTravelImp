package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    List queryRouteList(int cid, int start, int end);
    int queryRouteCount(int cid);
}

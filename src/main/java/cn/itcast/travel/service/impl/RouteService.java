package cn.itcast.travel.service.impl;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteService {
    int queryRouteCount(int cid);
    List<Route> queryRouteList(int cid, int start, int end);
}

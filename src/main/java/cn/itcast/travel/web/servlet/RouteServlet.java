package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.impl.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService service = new RouteServiceImp();
    public void queryRouteList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        //1.接受参数
        String currentPageStr = req.getParameter("currentPage");
        String cidStr = req.getParameter("cid");
        int cid = Integer.parseInt(cidStr) + 1;
        int perPageSize = 10;



        int totleCount = service.queryRouteCount(cid);
        int currPage = Integer.parseInt(currentPageStr) - 1;
        int sumPage = totleCount / perPageSize == 0 ? totleCount / perPageSize : totleCount/perPageSize + 1;

        int start = currPage * perPageSize;
        int end = currPage * perPageSize + perPageSize;
        System.out.println(" ~~~> queryRouteList been called with cid=" + cid + " ( " + start + ","+ end + ")");
        List<Route> list = service.queryRouteList(cid, start, end);
        PageBean<Route> bean = new PageBean<>();
        bean.setCountOfPrePage(perPageSize);
        bean.setTotalCount(totleCount);
        bean.setCurrPage(currPage);
        bean.setSumPage(sumPage);
        bean.setList(list);

        writeValue(bean, resp);
    }
}

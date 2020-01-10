package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.impl.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService service = new CategoryServiceImp();

    public void queryAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(" ~~~> queryAll been called");

        List list = service.queryAll();
        writeValue(list, resp);
    }
}

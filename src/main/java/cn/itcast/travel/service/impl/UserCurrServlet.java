package cn.itcast.travel.service.impl;

import cn.itcast.travel.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getCurrentUserServlet")
public class UserCurrServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = null;
        u = (User) req.getSession().getAttribute("currUser");
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(), u);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

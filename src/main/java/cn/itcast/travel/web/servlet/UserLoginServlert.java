package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserService;
import cn.itcast.travel.service.impl.UserServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

//@WebServlet("/loginServlet")
public class UserLoginServlert extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        UserService service = new UserServiceImp();

        // 验证码
        String checkCodeStr = req.getParameter("check");
        HttpSession session = req.getSession();
        String codeStr = (String) session.getAttribute("CHECKCODE_SERVER");
        if(!checkCodeStr.equalsIgnoreCase(codeStr) || checkCodeStr == null) {

            ResultInfo r = new ResultInfo();
            r.setFlag(false);
            r.setErrorMsg("验证码错误，请重试");

            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(r);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(jsonStr);
            return;
        }

        User user = new User();
        Map<String, String[]> map = req.getParameterMap();
        try {
            BeanUtils.populate(user, map);
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        User rUser = service.login(user);
        ResultInfo info = new ResultInfo();
        if (rUser == null) {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码不正确，请重试");
        }else {

            req.getSession().setAttribute("currUser",rUser);
            // 是否激活
            if (rUser.getStatus().equalsIgnoreCase("Y")) {
                info.setFlag(true);
                info.setData(rUser);
            }else {
                info.setFlag(true);
                info.setData(rUser);
                info.setErrorMsg("请激活用户在完成操作");
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(info);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }
}

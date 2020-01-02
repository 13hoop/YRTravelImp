package cn.itcast.travel.service.impl;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
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

@WebServlet("/registerServlet")
public class UserRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

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


        Map<String, String[]> map = req.getParameterMap();
        User u = new User();
        try {
            BeanUtils.populate(u, map);

        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService service = new UserServiceImp();
        ResultInfo r = new ResultInfo();
        if(service.register(u)) {
            r.setFlag(true);
        }else  {
            r.setFlag(false);
            r.setErrorMsg("注册失败");
        }


        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(mapper);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}

package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserService;
import cn.itcast.travel.service.impl.UserServiceImp;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImp();

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(" ~~~> login been called");

        // 验证码
        String checkCodeStr = req.getParameter("check");
        HttpSession session = req.getSession();
        String codeStr = (String) session.getAttribute("CHECKCODE_SERVER");
        if(!checkCodeStr.equalsIgnoreCase(codeStr) || checkCodeStr == null) {

            ResultInfo r = new ResultInfo();
            r.setFlag(false);
            r.setErrorMsg("验证码错误，请重试");

//            ObjectMapper mapper = new ObjectMapper();
//            String jsonStr = mapper.writeValueAsString(r);
//            resp.setContentType("application/json;charset=utf-8");
//            resp.getWriter().write(jsonStr);
            this.writeValueAsString(r, resp);
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

//        ObjectMapper mapper = new ObjectMapper();
//        String jsonStr = mapper.writeValueAsString(info);
//        resp.setContentType("application/json;charset=utf-8");
//        resp.getWriter().write(jsonStr);
        this.writeValueAsString(info, resp);
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        // 验证码
        String checkCodeStr = req.getParameter("check");
        HttpSession session = req.getSession();
        String codeStr = (String) session.getAttribute("CHECKCODE_SERVER");
        if(!checkCodeStr.equalsIgnoreCase(codeStr) || checkCodeStr == null) {

            ResultInfo r = new ResultInfo();
            r.setFlag(false);
            r.setErrorMsg("验证码错误，请重试");

//            ObjectMapper mapper = new ObjectMapper();
//            String jsonStr = mapper.writeValueAsString(r);
//            resp.setContentType("application/json;charset=utf-8");
//            resp.getWriter().write(jsonStr);
//            this.writeValueAsString(r,resp);
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

        u.setStatus("N");
        u.setCode(UuidUtil.getUuid());

        // 发送激活邮件
        String mailStr = "<a href='http://localhost:8090/activeUserServlert?code=" + u.getCode() + ">激活用户</a>";
        MailUtils.sendMail(u.getEmail(), mailStr, "YrTravel激活用户测试");

//        UserService service = new UserServiceImp();
        ResultInfo r = new ResultInfo();
        if(service.register(u)) {
            r.setFlag(true);
        }else  {
            r.setFlag(false);
            r.setErrorMsg("注册失败");
        }

//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(r);
//        resp.setContentType("application/json;charset=utf-8");
//        resp.getWriter().write(json);
        this.writeValueAsString(r, resp);
    }

    public void getCurrentUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(" ~~~> getCurrentUser been called");

        User u = null;
        u = (User) req.getSession().getAttribute("currUser");
//        ObjectMapper mapper = new ObjectMapper();
//        resp.setContentType("application/json;charset=utf-8");
//        mapper.writeValue(resp.getOutputStream(), u);
        this.writeValue(u, resp);
    }

}

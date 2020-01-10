package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        System.out.println(" [" + this + "]~~> uri is " + uri);

        // 通过uri来匹配method名
        String methordName = uri.substring(uri.lastIndexOf("/") + 1);

        try {
            Method method = this.getClass().getMethod(methordName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeValue(Object obj, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(), obj);
    }

    public void writeValueAsString(Object obj, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(obj);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }
}

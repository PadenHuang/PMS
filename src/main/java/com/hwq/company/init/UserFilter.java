package com.hwq.company.init;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(urlPatterns = {"/web/page/*","/EmployeeServlet","/DepartmentServlet","/ProjectServlet","/ScoreServlet"})
public class UserFilter implements Filter {// 防盗链

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req=(HttpServletRequest)request;
        String action =req.getParameter("action");
        if ("reg".equals(action)|"login".equals(action)){
            chain.doFilter(request, response);
            return;
        }
        Object user=req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/web/login.jsp").forward(request,response);
        }else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}

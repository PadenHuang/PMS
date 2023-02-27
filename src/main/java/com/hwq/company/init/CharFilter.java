package com.hwq.company.init;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 过滤器——中文编码
 */
@WebFilter("/*")
public class CharFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

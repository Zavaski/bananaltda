package com.banana.Filter;

import com.banana.Bean.LoginBean;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Inject
    private LoginBean loginBean;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String url = req.getRequestURL().toString();
        if (url.contains("/restrito") && loginBean.getUsuario() == null) {
            System.out.println("Redirecionado usuario");
            res.sendRedirect(req.getServletContext().getContextPath()+"/login.xhtml");
        }
        else{
            System.out.println("else");
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
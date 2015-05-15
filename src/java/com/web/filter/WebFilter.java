package com.web.filter;

import com.web.entity.WebRole;
import com.web.entity.WebUser;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@javax.servlet.annotation.WebFilter(urlPatterns = {"*.html","*.saa","*.sma","*.sca"})
public class WebFilter implements Filter {

    private FilterConfig filterConfig = null;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        String url=servletRequest.getRequestURI().replace(servletRequest.getContextPath(), "");
        HttpSession session = servletRequest.getSession();
        WebUser user = (WebUser) session.getAttribute("user");
        if (user != null) {
            if (url.contains(".sma")) {
                if (user.getRole() == WebRole.MANAGER) {
                    chain.doFilter(request, response);
                } else {
                    request.getRequestDispatcher("login.html").forward(request, response);
                }
            } else if (url.contains(".saa")) {
                if (user.getRole() == WebRole.ADMIN) {
                    chain.doFilter(request, response);
                } else {
                    request.getRequestDispatcher("login.html").forward(request, response);
                }
            } else if (url.contains(".sca")) {
                if (user.getRole() == WebRole.CLIENT) {
                    chain.doFilter(request, response);
                } else {
                    request.getRequestDispatcher("login.html").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("login.html").forward(request, response);
            }
        } else {
            if (url.contains(".html") || url.equals("/")) {
                chain.doFilter(request, response);
            }else{
                request.getRequestDispatcher("login.html").forward(request, response);
            }
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
    }
}

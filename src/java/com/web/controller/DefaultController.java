package com.web.controller;

import com.web.entity.WebUser;
import com.web.service.WebAuthManager;
import com.web.service.WebMailManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DefaultController", urlPatterns = {"*.html","","*.ajax"},loadOnStartup = 1,asyncSupported = true)
public class DefaultController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURL().toString().contains("logout.html")) {
            logoutAction(request, response);
        }else {
            request.getRequestDispatcher("/WEB-INF/public/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("AJAX")!=null) {
            ajaxAction(request, response);
        } else if (request.getParameter("MAIL")!=null) {
            mailAction(request, response);
        }else if (request.getParameter("LOGIN") != null) {
            loginAction(request, response);
        }
    }

    private void loginAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebUser user = new WebUser(request);
        HttpSession session = request.getSession();
        WebAuthManager manager = new WebAuthManager();

        if (manager.isAuthenticated(user)) {
            user = manager.getAutenticatedUser();
            session.setAttribute("user", user);
            switch (user.getRole()) {
                case ADMIN:
                    request.setAttribute("page", "/index.saa");
                    request.getRequestDispatcher("/WEB-INF/public/ajax/redirect.jsp").forward(request, response);
                    break;
                case MANAGER:
                    request.setAttribute("page","/index.sma");
                    request.getRequestDispatcher("/WEB-INF/public/ajax/redirect.jsp").forward(request, response);
                    break;
                case CLIENT:
                    request.setAttribute("page", "/index.sca");
                    request.getRequestDispatcher("/WEB-INF/public/ajax/redirect.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        } else {
            response.sendError(401);
        }
    }
    private void logoutAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.html");
    }
    private void ajaxAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("CHANGEPASSWORD")!=null){
            request.getRequestDispatcher("/WEB-INF/public/ajax/password.jsp").forward(request, response);
        }
    }
    
    private void mailAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("NEW")!=null){
            request.getRequestDispatcher("/WEB-INF/public/ajax/mail.jsp").forward(request, response);
        }
        if(request.getParameter("SEND")!=null){
            try {
                WebMailManager manager=new WebMailManager(request);
                if(manager.send()){
                    response.sendError(201);
                }
            } catch (Exception ex) {
                Logger.getLogger(DefaultController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

package com.web.controller;

import com.web.entity.WebUser;
import com.web.service.WebEntityManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "WebClient", urlPatterns = {"/index.sca"},loadOnStartup = 1)
public class WebClientController extends HttpServlet {
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("entity", new WebEntityManager().findOne("WebClient.findByEmail",((WebUser)request.getSession().getAttribute("user")).getEmail()));
        request.getRequestDispatcher("/WEB-INF/public/WebClient/index.jsp").forward(request, response);   
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}

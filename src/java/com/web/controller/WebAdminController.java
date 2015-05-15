package com.web.controller;

import com.web.service.WebEntityManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "WebAdmin", urlPatterns = {"/index.saa"},loadOnStartup = 1)
public class WebAdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("state", new WebEntityManager().findOne("WebEntity.countProduct"));
        request.getRequestDispatcher("/WEB-INF/public/WebAdmin/index.jsp").forward(request, response);
    }
}

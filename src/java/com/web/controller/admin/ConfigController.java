package com.web.controller.admin;

import com.web.entity.WebApplication;
import com.web.exception.WebException;
import com.web.service.WebEntityManager;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "configController", urlPatterns = {"/config.saa"})
public class ConfigController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        indexAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("SAVE")!=null){
            saveAction(request, response);
        }else{
            indexAction(request, response);
        }
    }
    private boolean indexAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebApplication> entityManager=new WebEntityManager<>(WebApplication.class);
        List<WebApplication> list=entityManager.findEntities();
        WebApplication application=(list.isEmpty())? new WebApplication():list.get(0);
        request.setAttribute("entity", application);
        request.getRequestDispatcher("/WEB-INF/public/WebAdmin/config/index.jsp").forward(request, response);
        return true;
    }
    private boolean saveAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebApplication> entityManager=new WebEntityManager<>(WebApplication.class);
        WebApplication config=new WebApplication(request);
        if(config.getId()!=null ){
            try {
                entityManager.edit(config);
            } catch (WebException ex) {
                Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            entityManager.create(config);
        }
        indexAction(request, response);
        return true;
    }
}
package com.web.controller.admin;

import com.web.entity.WebInformation;
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

@WebServlet(name = "infController", urlPatterns = {"/inf.saa"})
public class InfController extends HttpServlet {

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
        WebEntityManager<WebInformation> entityManager=new WebEntityManager<>(WebInformation.class);
        List<WebInformation> list=entityManager.findEntities();
        WebInformation application=(list.isEmpty())? new WebInformation():list.get(0);
        request.setAttribute("entity", application);
        request.getRequestDispatcher("/WEB-INF/public/WebAdmin/inf/index.jsp").forward(request, response);
        return true;
    }
    private boolean saveAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebInformation> entityManager=new WebEntityManager<>(WebInformation.class);
        WebInformation inf=new WebInformation(request);
        if(inf.getId()!=null ){
            try {
                entityManager.edit(inf);
            } catch (WebException ex) {
                Logger.getLogger(InfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            entityManager.create(inf);
        }
        indexAction(request, response);
        return true;
    }
}
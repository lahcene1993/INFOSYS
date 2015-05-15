package com.web.controller.agent;

import com.web.entity.WebCategory;
import com.web.entity.WebFacture;
import com.web.exception.WebException;
import com.web.service.WebEntityManager;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "categoryController", urlPatterns = {"/category.sma"})
public class CategoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        indexAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("SHOW")!=null){
            showAction(request, response);
        }else if(request.getParameter("SEARCH")!=null){
            searchAction(request, response);
        }else if(request.getParameter("DELETE")!=null){
            deleteAction(request, response);
        }else if(request.getParameter("CREATE")!=null){
            createAction(request, response);
        }else if(request.getParameter("SAVE")!=null){
            saveAction(request, response);
        }else{
            indexAction(request, response);
        }
    }
    private boolean indexAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebCategory> entityManager=new WebEntityManager<>(WebCategory.class);
        int page=(request.getParameter("page")!=null)?Integer.valueOf(request.getParameter("page")):1;
        request.setAttribute("count", (int)(entityManager.getCount()/10));
        request.setAttribute("entites", entityManager.findEntities(10,page*10-10));
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/category/index.jsp").forward(request, response);
        return true;
    }
    private boolean searchAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebCategory> manager=new WebEntityManager<>(WebCategory.class);
        if (request.getParameter("nom") != null) {
            request.setAttribute("entites", manager.findAll("WebCategory.findByNom",request.getParameter("nom")));
        }
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/category/search.jsp").forward(request, response);
        return true;
    }
    private boolean createAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebCategory> manager=new WebEntityManager<>(WebCategory.class);
        request.setAttribute("categories", manager.findEntities());
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/category/create.jsp").forward(request, response);
        return true;
    }
    private boolean deleteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            WebEntityManager<WebCategory> entityManager=new WebEntityManager<>(WebCategory.class);
            entityManager.destroy(Long.valueOf(request.getParameter("id")));
            indexAction(request, response);
        } catch (WebException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    private boolean showAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebCategory> entityManager=new WebEntityManager<>(WebCategory.class);        
        WebCategory category=entityManager.find(Long.valueOf(request.getParameter("id")));
        request.setAttribute("entity", category);
        WebEntityManager<WebCategory> manager=new WebEntityManager<>(WebCategory.class);
        request.setAttribute("categories", manager.findEntities());
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/category/create.jsp").forward(request, response);
        return true;
    }
    private boolean saveAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebCategory> entityManager=new WebEntityManager<>(WebCategory.class);
        WebCategory category=new WebCategory(request);
        if(category.getId()!=null ){
            try {
                
                entityManager.edit(category);
            } catch (WebException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            entityManager.create(category);
        }
        indexAction(request, response);
        return true;
    }
    
}

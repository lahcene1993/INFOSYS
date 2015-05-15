package com.web.controller.agent;

import com.web.entity.WebFournisseur;
import com.web.exception.WebException;
import com.web.service.WebEntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "fournisseurController", urlPatterns = {"/fournisseur.sma"})
public class FournisseurController extends HttpServlet {
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
        WebEntityManager<WebFournisseur> entityManager=new WebEntityManager<>(WebFournisseur.class);
        int page=(request.getParameter("page")!=null)?Integer.valueOf(request.getParameter("page")):1;
        request.setAttribute("count", (int)(entityManager.getCount()/10));
        request.setAttribute("entites", entityManager.findEntities(10,page*10-10));
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/fournisseur/index.jsp").forward(request, response);
        return true;
    }
    private boolean searchAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebFournisseur> entityManager=new WebEntityManager<>(WebFournisseur.class);
        if (request.getParameter("nom") != null) {
            request.setAttribute("entites", entityManager.findAll("WebFournisseur.findByNom",request.getParameter("nom")));
        }
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/fournisseur/search.jsp").forward(request, response);
        return true;
    }
    private boolean createAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/fournisseur/create.jsp").forward(request, response);
        return true;
    }
    private boolean deleteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            WebEntityManager<WebFournisseur> entityManager=new WebEntityManager<>(WebFournisseur.class);
            entityManager.destroy(Long.valueOf(request.getParameter("id")));
            indexAction(request, response);
        } catch (WebException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    private boolean showAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebFournisseur> entityManager=new WebEntityManager<>(WebFournisseur.class);
        
        WebFournisseur fournisseur=entityManager.find(Long.valueOf(request.getParameter("id")));
        request.setAttribute("entity", fournisseur);
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/fournisseur/create.jsp").forward(request, response);
        return true;
    }
    private boolean saveAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebFournisseur> entityManager=new WebEntityManager<>(WebFournisseur.class);
        WebFournisseur fournisseur=new WebFournisseur(request);
        if(fournisseur.getId()!=null ){
            try {
                entityManager.edit(fournisseur);
            } catch (WebException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            entityManager.create(fournisseur);
        }
        indexAction(request, response);
        return true;
    }
}

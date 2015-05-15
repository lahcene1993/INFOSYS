package com.web.controller.agent;

import com.web.entity.WebCategory;
import com.web.entity.WebFournisseur;
import com.web.entity.WebProduct;
import com.web.entity.WebStock;
import com.web.exception.WebException;
import com.web.service.WebEntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "stockController", urlPatterns = {"/stock.sma"})
public class StockController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        indexAction(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session =request.getSession();
        if(session.getAttribute("stock")==null){
            session.setAttribute("stock", new HashMap<>());
        }
        if(request.getParameter("STOCK")!=null){
            stockAction(request, response);
        }else if(request.getParameter("SHOW")!=null){
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
    
    private boolean stockAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session=request.getSession();
        HashMap<String,WebStock> stocks=(HashMap<String,WebStock>) session.getAttribute("stock");
        if(request.getParameter("ADD")!=null){    
            WebStock stock=new WebStock(request);
            stocks.put("F"+stock.getFournisseur().getId()+"P"+stock.getProduct().getId()+"D"+stock.getDate(),stock);
            response.sendError(200);
        }else if(request.getParameter("REMOVE")!=null){
            stocks.remove(request.getParameter("product"));
            response.sendError(200);
        }
        return true;
    }
    private boolean indexAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebStock> entityManager=new WebEntityManager<>(WebStock.class);
        int page=(request.getParameter("page")!=null)?Integer.valueOf(request.getParameter("page")):1;
        request.setAttribute("count", (int)(entityManager.getCount()/10));
        request.setAttribute("entites", entityManager.findEntities(10,page*10-10));
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/stock/index.jsp").forward(request, response);
        return true;
    }
    private boolean searchAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebStock> entityManager=new WebEntityManager<>(WebStock.class);
        List<WebStock> entites = new ArrayList<>();
        switch(request.getParameter("by")){
            case "1":
                WebStock stock=entityManager.find(Long.valueOf(request.getParameter("value")));
                if (stock!=null) {
                    entites.add(stock);
                }
                break;
            case "2":
                entites=entityManager.findAll("WebStock.findByNom", request.getParameter("value"));
                break;
        }    
        request.setAttribute("entites", entites);
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/stock/search.jsp").forward(request, response);
        return true;
    }
    private boolean createAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebCategory> manager=new WebEntityManager<>(WebCategory.class);
        WebEntityManager<WebFournisseur> entityManager=new WebEntityManager<>(WebFournisseur.class);
        WebEntityManager<WebProduct> entityManager1=new WebEntityManager<>(WebProduct.class);
        request.setAttribute("fournisseurs", entityManager.findEntities());
        request.setAttribute("products", entityManager1.findEntities());
        request.setAttribute("categories", manager.findEntities());
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/stock/create.jsp").forward(request, response);
        return true;
    }
    private boolean deleteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            WebEntityManager<WebStock> entityManager=new WebEntityManager<>(WebStock.class);
            entityManager.destroy(Long.valueOf(request.getParameter("id")));
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (WebException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    private boolean showAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebStock> entityManager=new WebEntityManager<>(WebStock.class);        
        WebStock stock=entityManager.find(Long.valueOf(request.getParameter("id")));
        request.setAttribute("entity", stock);
        WebEntityManager<WebCategory> manager=new WebEntityManager<>(WebCategory.class);
        request.setAttribute("categories", manager.findEntities());
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/stock/create.jsp").forward(request, response);
        return true;
    }
    private boolean saveAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebEntityManager<WebStock> entityManager=new WebEntityManager<>(WebStock.class);
        Enumeration<String> it=request.getParameterNames();
        HttpSession session=request.getSession();
        HashMap<String,WebStock> stocks=(HashMap<String,WebStock>) session.getAttribute("stock");
        WebEntityManager<WebProduct> entityManager1=new WebEntityManager<>(WebProduct.class);
        for(WebStock object : stocks.values()) {
            object.setProduct(entityManager1.find(object.getProduct().getId()));
            entityManager.create(object);
        }
        stocks.clear();
        indexAction(request, response);
        return true;
    }
}

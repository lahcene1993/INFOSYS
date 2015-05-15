package com.web.controller.agent;

import com.web.entity.WebCategory;
import com.web.entity.WebProduct;
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

@WebServlet(name = "productController", urlPatterns = {"/product.sma"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        indexAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("SHOW") != null) {
            showAction(request, response);
        } else if (request.getParameter("SEARCH") != null) {
            searchAction(request, response);
        } else if (request.getParameter("DELETE") != null) {
            deleteAction(request, response);
        } else if (request.getParameter("CREATE") != null) {
            createAction(request, response);
        } else if (request.getParameter("SAVE") != null) {
            saveAction(request, response);
        } else {
            indexAction(request, response);
        }
    }

    private boolean indexAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebProduct> entityManager = new WebEntityManager<>(WebProduct.class);
        int page = (request.getParameter("page") != null) ? Integer.valueOf(request.getParameter("page")) : 1;
        WebEntityManager<WebCategory> categoryManager = new WebEntityManager<>(WebCategory.class);
        request.setAttribute("categories", categoryManager.findEntities());
        request.setAttribute("count", (int) (entityManager.getCount() / 10));
        request.setAttribute("entites", entityManager.findEntities(10, page * 10 - 10));
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/product/index.jsp").forward(request, response);
        return true;
    }

    private boolean searchAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebProduct> entityManager = new WebEntityManager<>(WebProduct.class);
        if (request.getParameter("libele") != null && request.getParameter("category") != null && !request.getParameter("category").equals("")) {
            request.setAttribute("entites", entityManager.findAll("WebProduct.findByLibeleAndCategory", request.getParameter("libele"), Long.valueOf(request.getParameter("category"))));
        } else if (request.getParameter("libele") != null) {
            request.setAttribute("entites", entityManager.findAll("WebProduct.findByLibele", request.getParameter("libele")));
        } else if (request.getParameter("category") != null && !request.getParameter("category").equals("")) {
            request.setAttribute("entites", entityManager.findAll("WebProduct.findByCategory", Long.valueOf(request.getParameter("category"))));
        }
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/product/search.jsp").forward(request, response);
        return true;
    }

    private boolean createAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebCategory> manager = new WebEntityManager<>(WebCategory.class);
        request.setAttribute("categories", manager.findEntities());
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/product/create.jsp").forward(request, response);
        return true;
    }

    private boolean deleteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            WebEntityManager<WebProduct> entityManager = new WebEntityManager<>(WebProduct.class);
            entityManager.destroy(Long.valueOf(request.getParameter("id")));
            indexAction(request, response);
        } catch (WebException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private boolean showAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebProduct> entityManager = new WebEntityManager<>(WebProduct.class);
        WebProduct product = entityManager.find(Long.valueOf(request.getParameter("id")));
        request.setAttribute("entity", product);
        WebEntityManager<WebCategory> manager = new WebEntityManager<>(WebCategory.class);
        request.setAttribute("categories", manager.findEntities());
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/product/create.jsp").forward(request, response);
        return true;
    }

    private boolean saveAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebProduct> entityManager = new WebEntityManager<>(WebProduct.class);
        WebProduct product = new WebProduct(request);
        if (product.getId() != null) {
            try {
                entityManager.edit(product);
            } catch (WebException ex) {
                ex.printStackTrace();
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            entityManager.create(product);
        }
        indexAction(request, response);
        return true;
    }
}

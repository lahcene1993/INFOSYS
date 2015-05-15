package com.web.controller.agent;

import com.web.entity.WebClient;
import com.web.entity.WebFacture;
import com.web.entity.WebFactureItem;
import com.web.entity.WebProduct;
import com.web.entity.WebReglementMode;
import com.web.entity.WebTax;
import com.web.entity.WebType;
import com.web.exception.WebException;
import com.web.service.WebEntityManager;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@WebServlet(name = "FactureController", urlPatterns = {"/facture.sma"})
public class FactureController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        indexAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("facture") == null) {
            WebFacture facture = new WebFacture();
            session.setAttribute("facture", facture);
        }
        if (request.getParameter("PDF") != null) {
            exportAction(request, response);
        }else if (request.getParameter("ADD") != null) {
            addkAction(request, response);
        } else if (request.getParameter("CLEAR") != null) {
            clearAction(request, response);
        } else if (request.getParameter("REMOVE") != null) {
            removekAction(request, response);
        } else if (request.getParameter("SET") != null) {
            setAction(request, response);
        } else if (request.getParameter("SHOW") != null) {
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

    private boolean addkAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebFacture facture = (WebFacture) request.getSession().getAttribute("facture");
        WebFactureItem item = new WebFactureItem(request);
        if (item.isValid()) {
            facture.add(item);
        }
        createAction(request, response);
        return true;
    }

    private boolean setAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebFacture facture = (WebFacture) request.getSession().getAttribute("facture");
        facture.activate(request);
        createAction(request, response);
        return true;
    }

    private boolean removekAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            WebFacture facture = (WebFacture) request.getSession().getAttribute("facture");
        WebFactureItem item = new WebFactureItem(request)
                .setProduct(new WebProduct().setLibele(request.getParameter("libele")))
                .setFacture(new WebFacture());
        facture.remove(item);
        createAction(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return true;
    }

    private boolean indexAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebFacture> manager = new WebEntityManager<>(WebFacture.class);
        WebEntityManager<WebClient> clientmanager = new WebEntityManager<>(WebClient.class);
        int page = (request.getParameter("page") != null) ? Integer.valueOf(request.getParameter("page")) : 1;
        request.setAttribute("count", (int) (manager.getCount() / 10));
        request.setAttribute("clients", clientmanager.findEntities());
        request.setAttribute("entites", manager.findEntities(10, page * 10 - 10));
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/facture/index.jsp").forward(request, response);
        return true;
    }

    private boolean searchAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebFacture> manager = new WebEntityManager<>(WebFacture.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if (request.getParameter("client") != null && request.getParameter("date") != null) {
            try {
                date = sdf.parse(request.getParameter("date"));
            } catch (ParseException ex) {
                Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("entites", manager.findAll("WebFacture.finByClientAndDate", date, request.getParameter("client")));
        } else if (request.getParameter("client") != null) {
            request.setAttribute("entites", manager.findAll("WebFacture.finByClient", request.getParameter("client")));

        } else if (request.getParameter("date") != null) {
            request.setAttribute("entites", manager.findAll("WebFacture.finByDate", date));
        }
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/facture/search.jsp").forward(request, response);
        return true;
    }

    private boolean createAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebClient> cm = new WebEntityManager<>(WebClient.class);
        WebEntityManager<WebProduct> pm = new WebEntityManager<>(WebProduct.class);
        WebEntityManager<WebTax> tm = new WebEntityManager<>(WebTax.class);
        WebEntityManager<WebReglementMode> rm = new WebEntityManager<>(WebReglementMode.class);
        request.setAttribute("modes", rm.findEntities());
        request.setAttribute("taxes", tm.findEntities());
        request.setAttribute("clients", cm.findEntities());
        request.setAttribute("produits", pm.findEntities());
        request.setAttribute("types", WebType.values());
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/facture/create.jsp").forward(request, response);
        return true;
    }

    private boolean deleteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("DELETE") != null) {
            try {
                WebEntityManager<WebFacture> manager = new WebEntityManager<>(WebFacture.class);
                manager.destroy(Long.valueOf(request.getParameter("id")));
            } catch (WebException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    private boolean showAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebFacture> manager = new WebEntityManager<>(WebFacture.class);
        request.setAttribute("entity", manager.find(Long.valueOf(request.getParameter("id"))));
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/facture/show.jsp").forward(request, response);
        return true;
    }

    private boolean saveAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebFacture facture = (WebFacture) request.getSession().getAttribute("facture");
        if (facture.isValid()) {
            WebEntityManager manager = new WebEntityManager();
            manager.create(facture);
            request.getSession().setAttribute("facture", new WebFacture());
        }
        request.setAttribute("entity", facture);
        request.getRequestDispatcher("/WEB-INF/public/WebAgent/facture/show.jsp").forward(request, response);
        return true;
    }

    private boolean clearAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebFacture facture = (WebFacture) request.getSession().getAttribute("facture");
        request.getSession().setAttribute("facture", new WebFacture());
        request.setAttribute("s", "success");
        return true;
    }
    void exportAction (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            WebEntityManager entityManager=new WebEntityManager();
            EntityManager e;
            e = entityManager.getEntityManager();
            e.getTransaction().begin();
            java.sql.Connection connection = e.unwrap(java.sql.Connection.class);
            e.getTransaction().commit();
            Map  hm=new java.util.HashMap();
            hm.put("ID", Long.valueOf(request.getParameter("id")));
            hm.put("TYPE", request.getParameter("type"));
            
            JasperReport jasperReport = null;
            JasperDesign jasperDesign = null;
            String path = getServletContext().getRealPath("/WEB-INF/");
            jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/com/web/report/model.jrxml"));
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            byte[] byteStream = JasperRunManager.runReportToPdf(jasperReport, hm, connection);                            
            OutputStream outStream = response.getOutputStream();
            response.setHeader("Content-Disposition", "inline; filename=\""+hm.get("TYPE")+request.getParameter("id")+".pdf\"");
            response.setContentType("application/pdf");
            response.setContentLength(byteStream.length);
            outStream.write(byteStream,0,byteStream.length);
            
        } catch (JRException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

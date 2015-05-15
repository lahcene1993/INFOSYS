package com.web.controller.client;

import com.web.controller.agent.*;
import com.web.entity.WebClient;
import com.web.entity.WebMessage;
import com.web.entity.WebSupportTicket;
import com.web.entity.WebUser;
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

@WebServlet(name = "ticketController", urlPatterns = {"/ticket.sca"})
public class TicketController extends HttpServlet {

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
        } else if (request.getParameter("SEND") != null) {
            sendAction(request, response);
        } else if (request.getParameter("CREATE") != null) {
            createAction(request, response);
        } else if (request.getParameter("SAVE") != null) {
            saveAction(request, response);
        } else {
            indexAction(request, response);
        }
    }

    private boolean indexAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebSupportTicket> entityManager = new WebEntityManager<>(WebSupportTicket.class);
        int page = (request.getParameter("page") != null) ? Integer.valueOf(request.getParameter("page")) : 1;
        WebClient client=new WebEntityManager<WebClient>(WebClient.class).findOne("WebClient.findByEmail", ((WebUser)request.getSession().getAttribute("user")).getEmail());
        request.setAttribute("entites", entityManager.findAll("WebSupportTicket.finByClient",client));
        request.getRequestDispatcher("/WEB-INF/public/WebClient/ticket/index.jsp").forward(request, response);
        return true;
    }

    private boolean searchAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebSupportTicket> entityManager = new WebEntityManager<>(WebSupportTicket.class);
        List<WebSupportTicket> entites = new ArrayList<>();
        switch (request.getParameter("by")) {
            case "1":
                WebSupportTicket ticket = entityManager.find(Long.valueOf(request.getParameter("value")));
                if (ticket != null) {
                    entites.add(ticket);
                }
                break;
            case "2":
                entites = entityManager.findAll("WebSupportTicket.findByNom", request.getParameter("value"));
                break;
        }
        request.setAttribute("entites", entites);
        request.getRequestDispatcher("/WEB-INF/public/WebClient/ticket/search.jsp").forward(request, response);
        return true;
    }

    private boolean createAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebClient> entityManager = new WebEntityManager<>(WebClient.class);
        WebClient client = entityManager.findOne("WebClient.findByEmail", ((WebUser) request.getSession().getAttribute("user")).getEmail());
        request.setAttribute("client", client);
        request.setAttribute("entity", new WebSupportTicket());
        request.getRequestDispatcher("/WEB-INF/public/WebClient/ticket/create.jsp").forward(request, response);
        return true;
    }

    private boolean sendAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebEntityManager<WebMessage> entityManager = new WebEntityManager<>(WebMessage.class);
        WebMessage message = new WebMessage(request);
        entityManager.create(message);
        request.setAttribute("entites", entityManager.findAll("WebMessage.findByTicket", message.getTicket()));
        request.getRequestDispatcher("/WEB-INF/public/WebClient/ticket/show.jsp").forward(request, response);
        return true;
    }

    private boolean showAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebSupportTicket> entityManager = new WebEntityManager<>(WebSupportTicket.class);
        WebSupportTicket ticket = entityManager.find(Long.valueOf(request.getParameter("id")));
        request.setAttribute("entites", entityManager.findAll("WebMessage.findByTicket", ticket));
        request.getRequestDispatcher("/WEB-INF/public/WebClient/ticket/show.jsp").forward(request, response);
        return true;
    }

    private boolean saveAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebEntityManager<WebSupportTicket> entityManager = new WebEntityManager<>(WebSupportTicket.class);
        WebSupportTicket ticket = new WebSupportTicket(request);
        entityManager.create(ticket);
        WebEntityManager<WebMessage> manager = new WebEntityManager<>(WebMessage.class);
        WebMessage message = new WebMessage(request);
        message.setTicket(ticket);
        manager.create(message);
        request.setAttribute("entites", entityManager.findAll("WebMessage.findByTicket", ticket));
        request.getRequestDispatcher("/WEB-INF/public/WebClient/ticket/show.jsp").forward(request, response);
        return true;
    }
}

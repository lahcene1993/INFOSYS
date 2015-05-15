package com.web.entity;

import com.web.service.WebEntityManager;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;
@Entity
@NamedQuery(name = "WebMessage.findByTicket",query = "SELECT m FROM WebMessage AS m WHERE m.ticket=?1 ORDER BY m.id")
public class WebMessage extends WebEntity {
    
    @NotNull
    @Column
    private String message;
    
    @Temporal(TemporalType.TIMESTAMP)
    @OrderBy(value = "DESC")
    private Date date;
    
    @Column
    private boolean sent;
    
    @JoinColumn(name = "ticket", referencedColumnName = "id")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private WebSupportTicket ticket;

    public WebMessage() {
    }
    
    public WebMessage(HttpServletRequest request) {
        super(request);
        if (request.getParameter("message") != null) {
            this.message = request.getParameter("message");
        }
        if (request.getParameter("sent") != null) {
            this.sent = true;
        }
        if(request.getParameter("ticket") != null){
            this.ticket=new WebEntityManager<WebSupportTicket>(WebSupportTicket.class).find(Long.valueOf(request.getParameter("ticket")));
        }
        this.date=new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public WebSupportTicket getTicket() {
        return ticket;
    }

    public void setTicket(WebSupportTicket ticket) {
        this.ticket = ticket;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
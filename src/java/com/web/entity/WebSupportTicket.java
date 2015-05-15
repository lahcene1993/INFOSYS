package com.web.entity;

import com.web.service.WebEntityManager;
import java.util.Date;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;

@Entity
@Table
@NamedQuery(name = "WebSupportTicket.finByClient",query = "SELECT t FROM WebSupportTicket AS t WHERE t.client=?1")
public class WebSupportTicket extends WebEntity {
    
    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.OrderBy(value = "DESC")
    private Date date;
    
    @Column
    private boolean etat;
    
    @Column
    private String name;
    
    @OneToMany(mappedBy="ticket" ,cascade=CascadeType.PERSIST, orphanRemoval=true,fetch = FetchType.EAGER)
    private Collection<WebMessage> messages;

    @JoinColumn(name = "client", referencedColumnName = "id")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private WebClient client;

    public WebSupportTicket() {
    }
    public WebSupportTicket(HttpServletRequest request) {
        super(request);
        if (request.getParameter("name") != null) {
            this.name = request.getParameter("name");
        }
        if(request.getParameter("client") != null){
            this.client=new WebEntityManager<WebClient>(WebClient.class).find(Long.valueOf(request.getParameter("client")));
        }
        this.date=new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<WebMessage> getMessages() {
        return messages;
    }

    public void setMessages(Collection<WebMessage> messages) {
        this.messages = messages;
    }

    public WebClient getClient() {
        return client;
    }

    public void setClient(WebClient client) {
        this.client = client;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
}
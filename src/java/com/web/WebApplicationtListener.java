package com.web;

import com.web.entity.WebApplication;
import com.web.entity.WebCategory;
import com.web.entity.WebClient;
import com.web.entity.WebFournisseur;
import com.web.entity.WebManager;
import com.web.entity.WebProduct;
import com.web.entity.WebStock;
import com.web.service.WebEntityManager;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;

@WebListener()
public class WebApplicationtListener implements ServletContextListener, javax.servlet.http.HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        /*
        WebConfig.setDriver("org.hsqldb.jdbcDriver");
        WebConfig.setPassword("ADMIN");
        WebConfig.setUsername("ADMIN");
        WebConfig.setUrl("jdbc:hsqldb:file:" + sce.getServletContext().getRealPath("/WEB-INF/data") + java.io.File.separator + "data.db");
        */
        /*WebEntityManager<WebApplication> wm = new WebEntityManager(WebApplication.class);
        List<WebApplication> app = wm.findEntities();
        if (!app.isEmpty()) {
            
            WebApplication application = app.get(0);
            if(application.getServer()!=null 
                    && application.getDatabase()!=null 
                    && application.getUsername()!=null){
                WebConfig.setDriver("org.postgresql.Driver");
                WebConfig.setPassword(application.getPassword());
                WebConfig.setUsername(application.getUsername());
                WebConfig.setUrl("jdbc:postgresql://"+application.getServer()+":" + application.getPort()+"/"+application.getDatabase());
            }
        }*/
        
        WebConfig.setDriver("org.postgresql.Driver");
            WebConfig.setPassword("ADMIN");
            WebConfig.setUsername("postgres");
            WebConfig.setUrl("jdbc:postgresql://127.0.0.1:5432/db");
        
        if (Boolean.valueOf(sce.getServletContext().getInitParameter("DEV"))) {
            WebEntityManager emanager = new WebEntityManager();
            try {

                for (int i = 0; i < 50; i++) {
                    WebClient client = new WebClient()
                            .setAdress("ADRESSE" + i)
                            .setCodePostal("CLIENT" + i)
                            .setEmail("CLIENT" + i + "@hotmail.com")
                            .setNom("CLIENT" + i)
                            .setPays("MAROC" + i)
                            .setFax("002125000000" + i)
                            .setIdentifiantFiscal("IF" + i)
                            .setTelephoneFixe("002125000000" + i)
                            .setTelephoneMobile("002126000000" + i)
                            .setVille("RABAT");
                    WebManager manager = new WebManager()
                            .setAdress("ADRESSE e" + i)
                            .setCodePostal("E " + i)
                            .setEmail("E" + i + "@hotmail.com")
                            .setNom("E " + i)
                            .setPays("MAROC")
                            .setTelephoneFixe("002125000010" + i)
                            .setTelephoneMobile("002126000000" + i)
                            .setVille("SALE" + i);
                    WebFournisseur fournisseur = new WebFournisseur()
                            .setAdress("ADRESSE F" + i)
                            .setCodePostal("CLIENT" + i)
                            .setEmail("fournisseur" + i + "@hotmail.com")
                            .setNom("fournisseur" + i)
                            .setPays("MAROC" + i)
                            .setFax("002125100000" + i)
                            .setIdentifiantFiscal("IF" + i)
                            .setTelephoneFixe("002125100000" + i)
                            .setTelephoneMobile("002126100000" + i)
                            .setVille("RABAT");
                    WebCategory category = new WebCategory()
                            .setNom("CATEGORIE" + i);
                    WebProduct product = new WebProduct()
                            .setCategory(category)
                            .setDescription("DESCRIPTION" + i)
                            .setLibele("PRODUIT" + i)
                            .setPrix(i * i);
                    WebStock stock = new WebStock()
                            .setFournisseur(fournisseur)
                            .setDate(new Date())
                            .setPrix(i * i)
                            .setQuantite(i * 5)
                            .setRemarque("REMARQUE" + i)
                            .setProduct(product);
                    emanager.create(stock);
                    emanager.create(category);
                    emanager.create(product);
                    emanager.create(fournisseur);
                    emanager.create(client);
                    emanager.create(manager);

                }

            } catch (Exception e) {
            }

        }

        /*try {
            WebManager manager = new WebManager()
                    .setAdress("ADMIN")
                    .setCodePostal("ADMIN")
                    .setEmail("ADMIN")
                    .setNom("ADMIN")
                    .setPays("ADMIN")
                    .setTelephoneFixe("ADMIN")
                    .setTelephoneMobile("ADMIN")
                    .setVille("ADMIN")
                    .setAdmin(true);
            new WebEntityManager(WebManager.class).create(manager);
        } catch (Exception e) {
        }*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}

package com.web.service;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class WebMailManager {

    private final Session session;
    private Message message;
    private String from;
    private String to;
    private String subject;
    private String body;
    private String[] files;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String[] getFiles() {
        return files;
    }

    public void setFiles(String... files) {
        this.files = files;
    }

    public WebMailManager(HttpServletRequest request) throws Exception {
        this();
        if (request.getParameter("from") != null && !request.getParameter("from").equals("")) {
            this.from = request.getParameter("from");
        }
        if (request.getParameter("to") != null && !request.getParameter("to").equals("")) {
            this.to = request.getParameter("to");
        }
        if (request.getParameter("subject") != null && !request.getParameter("subject").equals("")) {
            this.subject = request.getParameter("subject");
        }
        if (request.getParameter("body") != null && !request.getParameter("body").equals("")) {
            this.body = request.getParameter("body");
        }
    }

    public WebMailManager() throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.debug", "true");
         props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS


        this.session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("af.lahcene1993@gmail.com", "195610ADMIN");
                    }
                });

    }

    public boolean send() {
        MimeMultipart mp = new MimeMultipart("mixed");
        MimeBodyPart txtPart = new MimeBodyPart();
        try {
            if (this.message == null) {
                if (this.session != null) {
                    this.message = new MimeMessage(this.session);
                }
            }

            this.session.setDebug(true);
            this.message.setFrom(new InternetAddress(this.from));
            this.message.setRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
            this.message.setSubject(this.subject);

            txtPart.setDisposition(Part.INLINE);
            txtPart.setText(body,"UTF-8", "html");
            mp.addBodyPart(txtPart);
            if (files != null) {
                for (String f : files) {
                    MimeBodyPart filePart = new MimeBodyPart();
                    File file = (File) new File(f);
                    FileDataSource fds = new FileDataSource(file);
                    DataHandler dh = new DataHandler(fds);
                    filePart.setFileName(file.getName());
                    filePart.setDisposition(Part.ATTACHMENT);
                    filePart.setDescription("Attached file: " + file.getName());
                    filePart.setDataHandler(dh);
                    mp.addBodyPart(filePart);
                }
            }
            this.message.setContent(mp);
            Transport.send(this.message,message.getAllRecipients());
           
            return true;
        } catch (MessagingException e) {
            return false;
        }

    }
}

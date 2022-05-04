package com.nyha.webfinal.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;

/**
 * The utility is responsible for sending messages
 *
 * @author Dovlet Jumaev
 */
public class MailSender {
    static Logger logger = LogManager.getLogger();
    private static final Properties properties = new Properties();
    private static final String MAIL_PROPERTIES = "mail.properties";
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String MAIL_USER_PASSWORD = "mail.user.password";
    private static final String MAIL_FROM = "mail.from";


    static {
        try (InputStream inputStream = MailSender.class.getClassLoader().getResourceAsStream(MAIL_PROPERTIES)) {
            properties.load(inputStream);
        } catch (Exception e) {
            logger.error("Load properties exception: " + e.getMessage());
        }
    }

    private MailSender() {
    }

    /**
     * Sends message
     *
     * @param emailTo     {@link String} recipient email
     * @param subject     {@link String} message subject
     * @param messageText {@link String} message text
     */
    public static void sendEmail(String emailTo, String subject, String messageText) {
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(properties.getProperty(MAIL_USER_NAME), properties.getProperty(MAIL_USER_PASSWORD));
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty(MAIL_FROM)));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject(subject);
            message.setText(messageText);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Send message exception: " + e.getMessage());
        }
    }
}
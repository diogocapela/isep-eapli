/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author pushdword
 */
public class EmailService {
    public static void sendEmail(String subject, String body, String rcpt) {
        String sender = "eapli@mailtest.ret.re";
        String host = "mailtest.ret.re";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                        "eapli", "rR;{(6sKb.-r");
                }
            });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(rcpt));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

        } catch (AddressException e) {
        } catch (MessagingException e) {
        }
    }
}

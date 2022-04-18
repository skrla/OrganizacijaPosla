/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skrla.util;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import skrla.model.Djelatnik;
import skrla.model.Posao;

/**
 *
 * @author skrla
 */
public class SlanjeMail {

    Posao posao;
    List<Djelatnik> djelatnici;

    public SlanjeMail(Posao posao, List<Djelatnik> djelatnici) {
        this.posao = posao;
        this.djelatnici = djelatnici;
        posalji();

    }

    private void posalji() {
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("javajp25@gmail.com", "J@vaJP25");
            }

        });

        session.setDebug(true);

        for (Djelatnik d : djelatnici) {
            try {
                MimeMessage message = new MimeMessage(session);

                message.setFrom(new InternetAddress("Orgaznizacija Posla <javajp25@gmail.com>"));

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(d.getEmail()));

                message.setSubject(posao.getRadniNalog());

                message.setText("Napomena: " + "\n" + posao.getNapomena() + "\n\n" + "Opis posla: " + "\n" + posao.getOpisPosla() + "\n\n" + "Lijep pozdrav");

                Transport.send(message);

                
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
    }
}

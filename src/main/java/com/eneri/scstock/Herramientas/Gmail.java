/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Herramientas;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author RAscencio
 */
public class Gmail 
{
    public void mandarCorreo()
  {
    String correoEnvia = "gabrielg13rojas@gmail.com";
    String claveCorreo = "49022781612016";
    
    Properties properties = new Properties();
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.user", correoEnvia);
    properties.put("mail.password", claveCorreo);
    
    Session session = Session.getInstance(properties, null);
    int aviso = 0;
    try
    {
      MimeMessage mimeMessage = new MimeMessage(session);
      
      mimeMessage.setFrom(new InternetAddress(correoEnvia, "Prueba enviar c�digo desde java"));
      
      InternetAddress[] internetAddresses = {
        new InternetAddress("mariaraquelgarcete@gmail.com") };
      
      mimeMessage.setRecipients(Message.RecipientType.TO, 
        internetAddresses);
      
      mimeMessage.setSubject("Dato Java Enviando Correo.");
      
      MimeBodyPart mimeBodyPart = new MimeBodyPart();
      mimeBodyPart.setText("Prueba de java xxxxxxxxxxxxxxxxxxxx");
      
      MimeBodyPart mimeBodyPartAdjunto = new MimeBodyPart();
      mimeBodyPartAdjunto.attachFile("C:/Users/Gabriel Gonzalez/Desktop/S_323401-MLV20311841974_052015-O.jpg");
      
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(mimeBodyPart);
      multipart.addBodyPart(mimeBodyPartAdjunto);
      
      mimeMessage.setContent(multipart);
      
      Transport transport = session.getTransport("smtp");
      transport.connect(correoEnvia, claveCorreo);
      transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
      transport.close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null, "Sin conexi�n: " + ex.getMessage());
      JOptionPane.showMessageDialog(null, "Por favor, verifique si tiene acceso a internet");
      aviso = 1;
    }
    if (aviso == 0) {
      JOptionPane.showMessageDialog(null, "�Correo electr�nico enviado exitosamente!");
    }
  }
  
  public static void main(String[] args)
  {
    Gmail correoTexto = new Gmail();
    correoTexto.mandarCorreo();
  }    
}

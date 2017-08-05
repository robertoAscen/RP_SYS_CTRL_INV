/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Inicializacion;

import com.eneri.scstock.Gestor.Gestionar;
import com.eneri.scstock.Seguridad.Encriptador;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author RAscencio
 */
public class DriverDB 
{
    static Gestionar gestion = new Gestionar();
  static Encriptador cripto = new Encriptador();
  static File hostDB = new File(new File("data/servidor/data_base_host.dll").getAbsolutePath());
  static File puertoDB = new File(new File("data/servidor/data_base_puerto.dll").getAbsolutePath());
  static File passwordDB = new File(new File("data/servidor/data_base_password.dll").getAbsolutePath());
  static File usuarioDB = new File(new File("data/servidor/data_base_usuario.dll").getAbsolutePath());
  
  public static Connection obtenerConnection()
  {
    Connection c = null;
    
    String host = gestion.AbrirTexto(hostDB);
    String port = gestion.AbrirTexto(puertoDB);
    String user = gestion.AbrirTexto(usuarioDB);
    String password = gestion.AbrirTexto(passwordDB);
    
    String hostDesencrypt = cripto.desencriptar(host, cripto.ClaveEncryption());
    String prtDesencrypt = cripto.desencriptar(port, cripto.ClaveEncryption());
    String usuDesencrypt = cripto.desencriptar(user, cripto.ClaveEncryption());
    String passDesencrypt = cripto.desencriptar(password, cripto.ClaveEncryption());
    
    String url = null;
    String driverName = "org.postgresql.Driver";
    try
    {
      Class.forName(driverName);
      url = "jdbc:postgresql://" + hostDesencrypt + ":" + prtDesencrypt + "/";
      c = DriverManager.getConnection(url, usuDesencrypt, passDesencrypt);
    }
    catch (ClassNotFoundException e)
    {
      JOptionPane.showMessageDialog(null, "Error ClassNotFount: " + e);
      e.printStackTrace();
    }
    catch (SQLException e)
    {
      JOptionPane.showMessageDialog(null, "Error SQL: " + e);
      e.printStackTrace();
    }
    return c;
  }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Conector;

import com.eneri.scstock.Gestor.Gestionar;
import com.eneri.scstock.Seguridad.Encriptador;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author RAscencio
 */
public class DataBase 
{

  private Connection conexion = null;
  static Gestionar gestion = new Gestionar();
  static Encriptador cripto = new Encriptador();
  static File nombreDB = new File(new File("data/servidor/data_base_name.dll").getAbsolutePath());
  static File hostDB = new File(new File("data/servidor/data_base_host.dll").getAbsolutePath());
  static File puertoDB = new File(new File("data/servidor/data_base_puerto.dll").getAbsolutePath());
  static File passwordDB = new File(new File("data/servidor/data_base_password.dll").getAbsolutePath());
  static File usuarioDB = new File(new File("data/servidor/data_base_usuario.dll").getAbsolutePath());
  
  public Connection getConexion()
  {
    return this.conexion;
  }
  
  public boolean crearConexion()
  {
    try
    {
      Class.forName("org.postgresql.Driver");
      String nombDb = gestion.AbrirTexto(nombreDB);
      String passDb = gestion.AbrirTexto(passwordDB);
      String hostDb = gestion.AbrirTexto(hostDB);
      String puertoDb = gestion.AbrirTexto(puertoDB);
      String usuarioDb = gestion.AbrirTexto(usuarioDB);
      
      String hostDesencrypt = cripto.desencriptar(hostDb, cripto.ClaveEncryption());
      String prtDesencrypt = cripto.desencriptar(puertoDb, cripto.ClaveEncryption());
      String BDDesencrypt = cripto.desencriptar(nombDb, cripto.ClaveEncryption());
      String usuDesencrypt = cripto.desencriptar(usuarioDb, cripto.ClaveEncryption());
      String passDesencrypt = cripto.desencriptar(passDb, cripto.ClaveEncryption());
      
      this.conexion = DriverManager.getConnection("jdbc:postgresql://" + hostDesencrypt + ":" + prtDesencrypt + "/" + BDDesencrypt + "?characterEncoding =UTF8", usuDesencrypt, passDesencrypt);
      if (this.conexion != null) {
        return true;
      }
    }
    catch (SQLException ex)
    {
      System.out.println("error en conexion: " + ex);
    }
    catch (ClassNotFoundException ex)
    {
      System.out.println(ex);
    }
    return false;
  }
  
  public boolean ejecutarSQL(String sql)
  {
    try
    {
      PreparedStatement sentencia = this.conexion.prepareStatement(sql);
      sentencia.execute(sql);
      return true;
    }
    catch (SQLException ex) {}
    return false;
  }
  
  public ResultSet ejecutarSQLSelect(String sql)
  {
    try
    {
      PreparedStatement sentencia = this.conexion.prepareStatement(sql);
      return sentencia.executeQuery();
    }
    catch (SQLException ex)
    {
      System.err.println("Error " + ex);
    }
    return null;
  }    
}

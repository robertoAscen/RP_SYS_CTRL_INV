/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Conector;

import com.eneri.scstock.Gestor.Gestionar;
import com.eneri.scstock.Seguridad.Encriptador;
import com.mysql.jdbc.Statement;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author RAscencio
 */
public class BasedeDatos 
{
     public static Connection conexion;
    public static Statement stm;
    static Gestionar gestion = new Gestionar();
    static Encriptador cripto = new Encriptador();
    static File nombreDB = new File(new File("data/servidor/data_base_name.dll").getAbsolutePath());
    static File hostDB = new File(new File("data/servidor/data_base_host.dll").getAbsolutePath());
    static File puertoDB = new File(new File("data/servidor/data_base_puerto.dll").getAbsolutePath());
    static File passwordDB = new File(new File("data/servidor/data_base_password.dll").getAbsolutePath());
    static File usuarioDB = new File(new File("data/servidor/data_base_usuario.dll").getAbsolutePath());

    public static void Conectar()
    {
      try
      {
        Class.forName("org.postgresql.Driver");
      }
      catch (ClassNotFoundException excepcion)
      {
        excepcion.printStackTrace();
      }
      try
      {
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

        conexion = DriverManager.getConnection("jdbc:postgresql://" + hostDesencrypt + ":" + prtDesencrypt + "/" + BDDesencrypt + "?characterEncoding =UTF8", usuDesencrypt, passDesencrypt);
        stm = (Statement) conexion.createStatement();
      }
      catch (SQLException excepcion2)
      {
        excepcion2.printStackTrace();
      }
    }

    public static void desconectar()
    {
      try
      {
        conexion.close();
        stm.close();
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }    
}

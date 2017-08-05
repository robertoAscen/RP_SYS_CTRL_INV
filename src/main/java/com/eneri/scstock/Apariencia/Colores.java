/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Apariencia;

import com.eneri.scstock.Conector.BasedeDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAscencio
 */
public class Colores 
{
    private String color;
  
  public Colores()
  {
    this.color = "Blanco";
  }
  
  public Colores(String color)
  {
    this.color = color;
  }
  
  public String getColor()
  {
    return this.color;
  }
  
  public void setColor(String color)
  {
    this.color = color;
  }
  
  public static void ActualizarColorFactura(Colores editar)
  {
        try {
            String sql = "UPDATE colores SET color_factura='" + editar.getColor() + "'WHERE color_codigo=1";
            System.out.println(sql);
            BasedeDatos.Conectar();
            BasedeDatos.stm.executeUpdate(sql);
            BasedeDatos.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Colores.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public static void ActualizarColorFormularios(Colores editar)
  {
    String sql = "UPDATE colores SET color_formularios='" + editar.getColor() + "'WHERE color_codigo=1";
    System.out.println(sql);
    BasedeDatos.Conectar();
    try
    {
      BasedeDatos.stm.executeUpdate(sql);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
  }
  
  public static void ActualizarColorSubFormularios(Colores editar)
  {
    String sql = "UPDATE colores SET color_insertupdate='" + editar.getColor() + "'WHERE color_codigo=1";
    System.out.println(sql);
    BasedeDatos.Conectar();
    try
    {
      BasedeDatos.stm.executeUpdate(sql);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
  }
  
  public static Colores ConsutarColorFactura(String codigo)
  {
    String sql = "SELECT * FROM colores WHERE color_codigo=" + codigo;
    
    BasedeDatos.Conectar();
    Colores colores = null;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        colores = new Colores();
        colores.setColor(rs.getString("color_factura"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return colores;
  }
  
  public static Colores ConsutarColorFormularios(String codigo)
  {
    String sql = "SELECT * FROM colores WHERE color_codigo=" + codigo;
    
    BasedeDatos.Conectar();
    Colores colores = null;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        colores = new Colores();
        colores.setColor(rs.getString("color_formularios"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return colores;
  }
  
  public static Colores ConsutarColorSubFormularios(String codigo)
  {
    String sql = "SELECT * FROM colores WHERE color_codigo=" + codigo;
    
    BasedeDatos.Conectar();
    Colores colores = null;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        colores = new Colores();
        colores.setColor(rs.getString("color_insertupdate"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return colores;
  }    
}

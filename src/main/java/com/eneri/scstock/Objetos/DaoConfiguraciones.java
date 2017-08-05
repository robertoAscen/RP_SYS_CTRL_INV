/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloConfiguraciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author RAscencio
 */
public class DaoConfiguraciones 
{
    
  public static void GuardarConfiguraciones(ModeloConfiguraciones insertar)
  {
    String sql = "INSERT INTO configuraciones (conf_codigo, conf_nombre_empresa, conf_telefono, conf_fax, conf_localidad, conf_desde_contado, conf_hasta_contado, conf_desde_credito, conf_hasta_credito,conf_porc_utilidad_mayorista,conf_porc_utilidad_venta,conf_porc_utilidad_credito, conf_iva1,conf_iva2,conf_div1,conf_div2,conf_moneda,conf_simbolo, conf_eslogan, conf_email, conf_contrasenha) VALUES ('" + 
    
      insertar.getCodigo() + "','" + insertar.getNombreEmpresa() + "','" + insertar.getTelefono() + "', " + 
      "'" + insertar.getFax() + "', '" + insertar.getLocalidad() + "', '" + insertar.getDesdeContado() + "'," + 
      " '" + insertar.getHastaContado() + "','" + insertar.getDesdeCredito() + "','" + insertar.getHastaCredito() + "'," + 
      "'" + insertar.getUtilmayorista() + "','" + insertar.getUtilventa() + "','" + insertar.getUtilcredito() + "'," + 
      "'" + insertar.getIva1() + "','" + insertar.getIva2() + "','" + insertar.getDivIva1() + "','" + insertar.getDivIva2() + "'," + 
      "'" + insertar.getMoneda() + "','" + insertar.getSimbolo() + "','" + insertar.getEslogan() + "','" + insertar.getEmail() + 
      "','" + insertar.getContrasenha() + "')";
    BasedeDatos.Conectar();
    try
    {
      BasedeDatos.stm.executeUpdate(sql);
    }
    catch (SQLException excepcion)
    {
      excepcion.printStackTrace();
      JOptionPane.showMessageDialog(null, excepcion.getMessage(), "Error", 0);
    }
    BasedeDatos.desconectar();
  }
  
  public static void EditarConfiguraciones(ModeloConfiguraciones editar)
  {
    String sql = "UPDATE configuraciones SET conf_nombre_empresa='" + 
      editar.getNombreEmpresa() + "'," + 
      "conf_telefono='" + editar.getTelefono() + "'," + 
      "conf_fax='" + editar.getFax() + "'," + 
      "conf_localidad='" + editar.getLocalidad() + "'," + 
      "conf_desde_contado='" + editar.getDesdeContado() + "'," + 
      "conf_hasta_contado='" + editar.getHastaContado() + "'," + 
      "conf_desde_credito='" + editar.getDesdeCredito() + "'," + 
      "conf_hasta_credito='" + editar.getHastaCredito() + "'," + 
      "conf_porc_utilidad_mayorista='" + editar.getUtilmayorista() + "'," + 
      "conf_porc_utilidad_venta='" + editar.getUtilventa() + "'," + 
      "conf_porc_utilidad_credito='" + editar.getUtilcredito() + "'," + 
      "conf_iva1='" + editar.getIva1() + "'," + 
      "conf_iva2='" + editar.getIva2() + "'," + 
      "conf_div1='" + editar.getDivIva1() + "'," + 
      "conf_div2='" + editar.getDivIva2() + "'," + 
      "conf_moneda='" + editar.getMoneda() + "'," + 
      "conf_simbolo='" + editar.getSimbolo() + "'," + 
      "conf_eslogan='" + editar.getEslogan() + "'," + 
      "conf_email='" + editar.getEmail() + "'," + 
      "conf_contrasenha='" + editar.getContrasenha() + "'" + 
      
      " WHERE conf_codigo=" + editar.getCodigo() + " ";
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
  
  public static ModeloConfiguraciones ConsultarConfiguraciones(int codigo)
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='" + codigo + "';";
    BasedeDatos.Conectar();
    ModeloConfiguraciones configuracion = null;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        configuracion = new ModeloConfiguraciones();
        configuracion.setNombreEmpresa(rs.getString("conf_nombre_empresa"));
        configuracion.setTelefono(rs.getString("conf_telefono"));
        configuracion.setFax(rs.getString("conf_fax"));
        configuracion.setLocalidad(rs.getString("conf_localidad"));
        configuracion.setDesdeContado(rs.getInt("conf_desde_contado"));
        configuracion.setHastaContado(rs.getInt("conf_hasta_contado"));
        configuracion.setDesdeCredito(rs.getInt("conf_desde_credito"));
        configuracion.setHastaCredito(rs.getInt("conf_hasta_credito"));
        configuracion.setUtilmayorista(rs.getDouble("conf_porc_utilidad_mayorista"));
        configuracion.setUtilventa(rs.getDouble("conf_porc_utilidad_venta"));
        configuracion.setUtilcredito(rs.getDouble("conf_porc_utilidad_credito"));
        
        configuracion.setIva1(rs.getDouble("conf_iva1"));
        configuracion.setIva2(rs.getDouble("conf_iva2"));
        configuracion.setDivIva1(rs.getDouble("conf_div1"));
        configuracion.setDivIva2(rs.getDouble("conf_div2"));
        configuracion.setMoneda(rs.getString("conf_moneda"));
        configuracion.setSimbolo(rs.getString("conf_simbolo"));
        configuracion.setEmail(rs.getString("conf_email"));
        configuracion.setContrasenha(rs.getString("conf_contrasenha"));
        configuracion.setEslogan(rs.getString("conf_eslogan"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return configuracion;
  }
  
  public static ModeloConfiguraciones Existencia(int codigo)
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='" + codigo + "';";
    ModeloConfiguraciones configuracion = null;
    BasedeDatos.Conectar();
    System.out.println(sql);
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        configuracion = new ModeloConfiguraciones();
        configuracion.setCodigo(rs.getInt("conf_codigo"));
        configuracion.setNombreEmpresa(rs.getString("conf_nombre_empresa"));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return configuracion;
  }
  
  public static String NombreEmpresa()
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    ModeloConfiguraciones configuracion = null;
    String nombreEmpresa = "";
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        configuracion = new ModeloConfiguraciones();
        configuracion.setNombreEmpresa(rs.getString("conf_nombre_empresa"));
        configuracion.setTelefono(rs.getString("conf_telefono"));
        configuracion.setFax(rs.getString("conf_fax"));
        configuracion.setLocalidad(rs.getString("conf_localidad"));
        
        nombreEmpresa = rs.getString("conf_nombre_empresa");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return nombreEmpresa;
  }
  
  public static String Localidad()
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    
    String localidad = "";
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        localidad = rs.getString("conf_localidad");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return localidad;
  }
  
  public static int RangoLimiteFacContado()
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    ModeloConfiguraciones configuracion = null;
    int rangoLimite = 0;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        configuracion = new ModeloConfiguraciones();
        configuracion.setDesdeContado(rs.getInt("conf_desde_contado"));
        configuracion.setHastaContado(rs.getInt("conf_hasta_contado"));
        
        rangoLimite = rs.getInt("conf_hasta_contado");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return rangoLimite;
  }
  
  public static double PorcentajeUtilidadMayorista()
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    double utilidad = 0.0D;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        utilidad = rs.getDouble("conf_porc_utilidad_mayorista");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return utilidad;
  }
  
  public static double PorcentajeUtilidadVenta()
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    double utilidad = 0.0D;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        utilidad = rs.getDouble("conf_porc_utilidad_venta");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return utilidad;
  }
  
  public static double PorcentajeUtilidadCredito()
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    double utilidad = 0.0D;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        utilidad = rs.getDouble("conf_porc_utilidad_credito");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return utilidad;
  }
  
  public static double Iva1()
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    double iva1 = 0.0D;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        iva1 = rs.getDouble("conf_iva1");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return iva1;
  }
  
  public static double Iva2()
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    double iva2 = 0.0D;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        iva2 = rs.getDouble("conf_iva2");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return iva2;
  }
  
  public static double Divisor1()
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    double divisor1 = 0.0D;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        divisor1 = rs.getDouble("conf_div1");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return divisor1;
  }
  
  public static double Divisor2()
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    double divisor2 = 0.0D;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        divisor2 = rs.getDouble("conf_div2");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return divisor2;
  }
  
  public static String Moneda()
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    String moneda = "";
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        moneda = rs.getString("conf_moneda");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return moneda;
  }
  
  public static String Simbolo()
  {
    String sql = "SELECT * FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    String simbolo = "";
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        simbolo = rs.getString("conf_simbolo");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return simbolo;
  }
  
  public static String Eslogan()
  {
    String sql = "SELECT conf_eslogan FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    String eslogan = "";
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        eslogan = rs.getString("conf_eslogan");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return eslogan;
  }
  
  public static String CorreoEmpresa()
  {
    String sql = "SELECT conf_email FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    String correo = "";
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        correo = rs.getString("conf_email");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return correo;
  }
  
  public static String PasswordCorreoEmpresa()
  {
    String sql = "SELECT conf_contrasenha FROM configuraciones WHERE conf_codigo='1';";
    BasedeDatos.Conectar();
    String password = "";
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        password = rs.getString("conf_contrasenha");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return password;
  }    
}

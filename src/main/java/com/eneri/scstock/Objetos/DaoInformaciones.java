/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloInformaciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author RAscencio
 */
public class DaoInformaciones
{
    
  public static ModeloInformaciones Existencia(String codigoproducto, String fecha, String condicion)
  {
    String sql = "SELECT * FROM informaciones WHERE info_cod_producto='" + codigoproducto + "'AND info_fecha='" + fecha + "'AND info_condicion='" + condicion + "';";
    ModeloInformaciones informacion = null;
    BasedeDatos.Conectar();
    System.out.println(sql);
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        informacion = new ModeloInformaciones();
        informacion.setCodigoProducto(rs.getString("info_cod_producto"));
        informacion.setFecha(rs.getString("info_fecha"));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return informacion;
  }
  
  public static void InsertarInformacion(ModeloInformaciones informacion)
  {
    String sql = "INSERT INTO informaciones (info_codigo, info_cod_producto, info_cant_vendida, info_fecha, info_subtotal, info_condicion, info_precio_unitario, info_utilidad) VALUES ('" + 
    
      informacion.getCodigo() + "','" + informacion.getCodigoProducto() + 
      "','" + informacion.getCantVendida() + "','" + informacion.getFecha() + "', '" + informacion.getSubtotal() + "','" + 
      informacion.getCondicion() + "','" + informacion.getPrecioUnitario() + "','" + informacion.getUtilidad() + "')";
    BasedeDatos.Conectar();
    System.out.println(sql);
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
  
  public static int IncrementarCodigo()
  {
    String sql = "SELECT MAX (info_codigo) AS info_codigo FROM informaciones";
    int codigo = 0;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        codigo = rs.getInt("info_codigo");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return codigo;
  }
  
  public static void ActualizarCantidadVendida(ModeloInformaciones informaciones, String fecha, String condicion)
  {
    String sql = "UPDATE informaciones SET info_cant_vendida='" + 
      informaciones.getCantVendida() + "'," + 
      "info_utilidad='" + informaciones.getUtilidad() + "'," + 
      "info_subtotal='" + informaciones.getSubtotal() + "'" + 
      " WHERE info_cod_producto='" + informaciones.getCodigoProducto() + "' AND info_fecha='" + fecha + "' AND info_condicion='" + condicion + "'";
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
  
  public static void EliminarInformacion(String codigo, String fecha, String condicion)
  {
    String sql = "DELETE FROM informaciones WHERE info_cod_producto='" + codigo + "' AND info_fecha='" + fecha + "' AND info_condicion='" + condicion + "'";
    System.out.println(sql);
    BasedeDatos.Conectar();
    try
    {
      BasedeDatos.stm.executeUpdate(sql);
    }
    catch (SQLException excepcion)
    {
      JOptionPane.showMessageDialog(null, excepcion);
      excepcion.printStackTrace();
    }
    BasedeDatos.desconectar();
  }
  
  public static ModeloInformaciones ConsultaForUpdate(String codigo, String fecha, String condicion)
  {
    String sql = "SELECT * FROM informaciones INNER JOIN productos ON info_cod_producto = producto_codigo WHERE info_cod_producto= '" + codigo + "' AND info_fecha='" + fecha + "' AND info_condicion='" + condicion + "' ";
    
    BasedeDatos.Conectar();
    ModeloInformaciones informacion = null;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        informacion = new ModeloInformaciones();
        informacion.setCantVendida(rs.getDouble("info_cant_vendida"));
        informacion.setFecha(rs.getString("info_fecha"));
        informacion.setDescripcionProducto(rs.getString("producto_descripcion"));
        informacion.setSubtotal(rs.getDouble("info_subtotal"));
        informacion.setUtilidad(rs.getDouble("info_utilidad"));
        informacion.setPrecioUnitario(rs.getDouble("info_precio_unitario"));
        informacion.getProducto().setPrecioCosto(rs.getDouble("producto_precio_costo"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return informacion;
  }
  
  public static ArrayList<ModeloInformaciones> ConsultarParaGraficoDeBarras(String codigo, String fecha1, String fecha2, String condicion)
  {
    String sql = "SELECT * FROM informaciones INNER JOIN productos ON info_cod_producto = producto_codigo WHERE  info_fecha  BETWEEN '" + fecha1 + "'  AND '" + fecha2 + "' AND info_cod_producto= '" + codigo + "' AND info_condicion='" + condicion + "' ORDER BY info_fecha ASC";
    BasedeDatos.Conectar();
    ModeloInformaciones informacion = null;
    ArrayList<ModeloInformaciones> lista = new ArrayList();
    System.out.println(sql);
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        informacion = new ModeloInformaciones();
        informacion.setCantVendida(rs.getDouble("info_cant_vendida"));
        informacion.setFecha(rs.getString("info_fecha"));
        informacion.getProducto().setDescripcion(rs.getString("producto_descripcion"));
        
        double precio = 0.0D;
        double cantidad = 0.0D;
        int subtotal = 0;
        
        precio = rs.getDouble("info_precio_unitario");
        cantidad = rs.getDouble("info_cant_vendida");
        subtotal = (int)(cantidad * precio);
        
        informacion.setSubtotal(subtotal);
        informacion.setPrecioUnitario(precio);
        
        lista.add(informacion);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static ArrayList<ModeloInformaciones> ConsultarInformacionProducto(String codigo, String fecha1, String fecha2)
  {
    String sql = "SELECT * FROM informaciones WHERE  info_fecha  BETWEEN '" + fecha1 + "'  AND '" + fecha2 + "' AND info_cod_producto= '" + codigo + "' ORDER BY info_fecha ASC";
    BasedeDatos.Conectar();
    ModeloInformaciones informacion = null;
    ArrayList<ModeloInformaciones> lista = new ArrayList();
    System.out.println(sql);
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        informacion = new ModeloInformaciones();
        informacion.setCantVendida(rs.getDouble("info_cant_vendida"));
        informacion.setFecha(rs.getString("info_fecha"));
        lista.add(informacion);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static ArrayList<ModeloInformaciones> ConsultarParaGraficoPastel(String fecha1, String fecha2, String condicion)
  {
    String sql = "SELECT * FROM informaciones INNER JOIN productos ON info_cod_producto = producto_codigo WHERE info_fecha  between '" + fecha1 + "'  AND '" + fecha2 + "' AND info_condicion = '" + condicion + "'ORDER BY info_cant_vendida DESC";
    BasedeDatos.Conectar();
    ModeloInformaciones informacion = null;
    ArrayList<ModeloInformaciones> lista = new ArrayList();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        informacion = new ModeloInformaciones();
        informacion.setCantVendida(rs.getDouble("info_cant_vendida"));
        informacion.getProducto().setDescripcion(rs.getString("producto_descripcion"));
        informacion.setFecha(rs.getString("info_fecha"));
        lista.add(informacion);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static ArrayList<ModeloInformaciones> ConsultarParaInfoMejoresProductos(String fecha1, String fecha2, String limiteFila)
  {
    String sql = "SELECT * FROM informaciones INNER JOIN productos ON info_cod_producto = producto_codigo WHERE info_fecha  between '" + fecha1 + "'  AND '" + fecha2 + "' ORDER BY info_cant_vendida DESC LIMIT " + limiteFila;
    BasedeDatos.Conectar();
    ModeloInformaciones informacion = null;
    ArrayList<ModeloInformaciones> lista = new ArrayList();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        informacion = new ModeloInformaciones();
        informacion.setCodigoProducto(rs.getString("producto_codigo"));
        
        informacion.setDescripcionProducto(rs.getString("producto_descripcion"));
        informacion.setFecha(rs.getString("info_fecha"));
        informacion.setCantVendida(rs.getDouble("info_cant_vendida"));
        double precio = 0.0D;
        double cantidad = 0.0D;
        int subtotal = 0;
        
        precio = rs.getDouble("info_precio_unitario");
        cantidad = rs.getDouble("info_cant_vendida");
        subtotal = (int)(cantidad * precio);
        
        informacion.setSubtotal(subtotal);
        informacion.setPrecioUnitario(precio);
        lista.add(informacion);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static ArrayList<ModeloInformaciones> ConsultarParaInfoVentaDelDia(String fecha)
  {
    String sql = "SELECT * FROM informaciones INNER JOIN productos ON info_cod_producto = producto_codigo WHERE info_fecha='" + fecha + "' ORDER BY producto_descripcion;";
    BasedeDatos.Conectar();
    ModeloInformaciones informacion = null;
    ArrayList<ModeloInformaciones> lista = new ArrayList();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        informacion = new ModeloInformaciones();
        informacion.setCodigoProducto(rs.getString("producto_codigo"));
        informacion.setCantVendida(rs.getDouble("info_cant_vendida"));
        
        informacion.setDescripcionProducto(rs.getString("producto_descripcion"));
        informacion.getProducto().setStock(rs.getDouble("producto_stock"));
        lista.add(informacion);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static boolean ExistenciaDeLaInformacion(ModeloInformaciones productocodigo, String condicion)
  {
    boolean existencia = false;
    BasedeDatos.Conectar();
    
    String sql = "SELECT * FROM informaciones WHERE info_codigo<>? AND info_cod_producto=? AND info_condicion='" + condicion + "' ORDER BY info_fecha ASC";
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setInt(1, productocodigo.getCodigo());
      pstm.setString(2, productocodigo.getCodigoProducto());
      ResultSet rs = pstm.executeQuery();
      if (rs.next()) {
        existencia = true;
      }
    }
    catch (SQLException e)
    {
      JOptionPane.showMessageDialog(null, "Error :" + e.getMessage());
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return existencia;
  }
  
  public static ArrayList<ModeloInformaciones> ConsultaDetalladaInformacion(String fecha1, String condicion)
  {
    String sql = "SELECT * FROM informaciones INNER JOIN productos ON info_cod_producto = producto_codigo WHERE info_fecha ='" + fecha1 + "' AND info_condicion='" + condicion + "' ORDER BY producto_descripcion;";
    BasedeDatos.Conectar();
    ModeloInformaciones informacion = null;
    ArrayList<ModeloInformaciones> lista = new ArrayList();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        informacion = new ModeloInformaciones();
        informacion.setCodigoProducto(rs.getString("producto_codigo"));
        informacion.setCantVendida(rs.getDouble("info_cant_vendida"));
        
        informacion.setDescripcionProducto(rs.getString("producto_descripcion"));
        informacion.setStockActual(rs.getDouble("producto_stock"));
        informacion.setPrecioUnitario(rs.getDouble("info_precio_unitario"));
        informacion.setFecha(rs.getString("info_fecha"));
        informacion.setSubtotal(rs.getDouble("info_subtotal"));
        informacion.setUtilidad(rs.getDouble("info_utilidad"));
        lista.add(informacion);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }    
}

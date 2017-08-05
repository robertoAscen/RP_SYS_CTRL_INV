/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloInventarios;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author RAscencio
 */
public class DaoInventarios 
{
    
  public static void GuardarInventario(ModeloInventarios insertar)
  {
    String sql = "INSERT INTO inventarios (invent_codigo, invent_codProducto, invent_cantidadInventario, invent_stockActual, invent_estado, invent_faso, invent_costo, invent_fecha) VALUES ('" + 
    
      insertar.getCodigo() + "','" + insertar.getProducto().getCodigo() + "','" + insertar.getCantInventario() + "', '" + insertar.getStockActual() + 
      "', '" + insertar.getEstado() + "', '" + insertar.getFaSo() + "','" + insertar.getCosto() + "','" + insertar.getFecha() + "')";
    BasedeDatos.Conectar();
    try
    {
      BasedeDatos.stm.executeUpdate(sql);
      System.out.println(sql);
    }
    catch (SQLException excepcion)
    {
      JOptionPane.showMessageDialog(null, "Ya existe un detalle con �ste c�digo. No se pudo guardar los datos.", "Error", 0);
      JOptionPane.showMessageDialog(null, excepcion);
      excepcion.printStackTrace();
    }
    BasedeDatos.desconectar();
  }
  
  public static int IncrementarCodigo()
  {
    String sql = "SELECT MAX (invent_codigo) AS invent_codigo FROM inventarios";
    int codigo = 0;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        codigo = rs.getInt("invent_codigo");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return codigo;
  }
  
  public static ModeloInventarios Existencia(String codigoproducto, String fecha)
  {
    String sql = "SELECT * FROM inventarios WHERE invent_codproducto='" + codigoproducto + "'AND invent_fecha='" + fecha + "';";
    ModeloInventarios inventario = null;
    BasedeDatos.Conectar();
    System.out.println(sql);
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        inventario = new ModeloInventarios();
        inventario.getProducto().setCodigo(rs.getString("invent_codproducto"));
        inventario.setFecha(rs.getString("invent_fecha"));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return inventario;
  }
  
  public static List<ModeloInventarios> ConsultarInventarioPendiente()
  {
    String sql = "SELECT * FROM inventarios INNER JOIN productos ON producto_codigo = invent_codProducto ORDER BY invent_codigo";
    ModeloInventarios inventario = null;
    List<ModeloInventarios> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        inventario = new ModeloInventarios();
        
        inventario.getProducto().setCodigo(rs.getString("invent_codProducto"));
        inventario.getProducto().setDescripcion(rs.getString("producto_descripcion"));
        inventario.getProducto().setUnidadDeMedida(rs.getString("producto_unidad_medida"));
        inventario.setCantInventario(rs.getDouble("invent_cantidadinventario"));
        inventario.setStockActual(rs.getDouble("invent_stockactual"));
        inventario.setEstado(rs.getString("invent_estado"));
        inventario.setFaSo(rs.getDouble("invent_faso"));
        inventario.setCosto(rs.getDouble("invent_costo"));
        lista.add(inventario);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloInventarios> ConsultarProductosFaltantes()
  {
    String sql = "SELECT * FROM inventarios INNER JOIN productos ON producto_codigo = invent_codProducto WHERE invent_estado = 'FALTA' ORDER BY invent_codigo";
    ModeloInventarios inventario = null;
    List<ModeloInventarios> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        inventario = new ModeloInventarios();
        
        inventario.getProducto().setCodigo(rs.getString("invent_codProducto"));
        inventario.getProducto().setDescripcion(rs.getString("producto_descripcion"));
        inventario.getProducto().setUnidadDeMedida(rs.getString("producto_unidad_medida"));
        inventario.setCantInventario(rs.getDouble("invent_cantidadinventario"));
        inventario.setStockActual(rs.getDouble("invent_stockactual"));
        inventario.setEstado(rs.getString("invent_estado"));
        inventario.setFaSo(rs.getDouble("invent_faso"));
        inventario.setCosto(rs.getDouble("invent_costo"));
        
        int precio = 0;
        double cantidad = 0.0D;
        int subtotal = 0;
        
        precio = rs.getInt("invent_costo");
        cantidad = rs.getDouble("invent_faso");
        subtotal = (int)(cantidad * precio);
        inventario.setSubTotal(subtotal);
        
        lista.add(inventario);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloInventarios> ConsultarProductosSobrantes()
  {
    String sql = "SELECT * FROM inventarios INNER JOIN productos ON producto_codigo = invent_codProducto WHERE invent_estado = 'SOBRA' ORDER BY invent_codigo";
    ModeloInventarios inventario = null;
    List<ModeloInventarios> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        inventario = new ModeloInventarios();
        
        inventario.getProducto().setCodigo(rs.getString("invent_codProducto"));
        inventario.getProducto().setDescripcion(rs.getString("producto_descripcion"));
        inventario.getProducto().setUnidadDeMedida(rs.getString("producto_unidad_medida"));
        inventario.setCantInventario(rs.getDouble("invent_cantidadinventario"));
        inventario.setStockActual(rs.getDouble("invent_stockactual"));
        inventario.setEstado(rs.getString("invent_estado"));
        inventario.setFaSo(rs.getDouble("invent_faso"));
        inventario.setCosto(rs.getDouble("invent_costo"));
        
        int precio = 0;
        double cantidad = 0.0D;
        int subtotal = 0;
        
        precio = rs.getInt("invent_costo");
        cantidad = rs.getDouble("invent_faso");
        subtotal = (int)(cantidad * precio);
        inventario.setSubTotal(subtotal);
        
        lista.add(inventario);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static void ActualizarDetalleInventario(ModeloInventarios inventarios)
  {
    String sql = "UPDATE inventarios SET invent_cantidadinventario='" + 
      inventarios.getCantInventario() + "'," + 
      "invent_stockactual='" + inventarios.getStockActual() + "'," + 
      "invent_estado='" + inventarios.getEstado() + "'," + 
      "invent_faso='" + inventarios.getFaSo() + "'" + 
      " WHERE invent_codproducto='" + inventarios.getProducto().getCodigo() + "'";
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
}

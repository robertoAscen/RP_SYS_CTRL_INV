/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Modelos.ModeloProveedores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author RAscencio
 */
public class DaoProductos 
{
    
  public static void RegistrarProducto(ModeloProductos insertar)
  {
    String sql = "INSERT INTO productos (producto_codigo, producto_descripcion, producto_stock, producto_precio_costo, producto_precio_venta, producto_precio_mayorista, producto_precio_credito, producto_cod_proveedor, producto_unidad_medida, producto_cant_paquete, producto_facturar, producto_iva, producto_estante, producto_cod_marca, producto_cod_familia, producto_observaciones, producto_descuento) VALUES ('" + 
    
      insertar.getCodigo() + "','" + insertar.getDescripcion() + "','" + insertar.getStock() + "', '" + insertar.getPrecioCosto() + 
      "', '" + insertar.getPrecioVenta() + "', '" + insertar.getPrecioMayorista() + "', '" + insertar.getPrecioCredito() + "', '" + insertar.getCodProveedor() + "'," + 
      "'" + insertar.getUnidadDeMedida() + "', '" + insertar.getCantPaquete() + "', '" + insertar.getFacturar() + "','" + insertar.getIva() + "','" + insertar.getEstante() + "'," + 
      "'" + insertar.getMarcas().getCodigo() + "','" + insertar.getFamilia().getCodigo() + "','" + insertar.getObservaciones() + "','" + insertar.getDescuento() + "')";
    BasedeDatos.Conectar();
    try
    {
      BasedeDatos.stm.executeUpdate(sql);
    }
    catch (SQLException excepcion)
    {
      JOptionPane.showMessageDialog(null, "Ya existe un producto con �ste c�digo. No se pudo guardar los datos.");
      JOptionPane.showMessageDialog(null, excepcion);
      excepcion.printStackTrace();
    }
    BasedeDatos.desconectar();
  }
  
  public static void EditarProducto(ModeloProductos editar)
  {
    String sql = "UPDATE productos SET producto_descripcion='" + 
      editar.getDescripcion() + "'," + 
      "producto_stock='" + editar.getStock() + "'," + 
      "producto_precio_costo='" + editar.getPrecioCosto() + "'," + 
      "producto_precio_credito='" + editar.getPrecioCredito() + "'," + 
      "producto_precio_venta='" + editar.getPrecioVenta() + "'," + 
      "producto_cod_proveedor='" + editar.getCodProveedor() + "'," + 
      "producto_unidad_medida='" + editar.getUnidadDeMedida() + "'," + 
      "producto_facturar='" + editar.getFacturar() + "'," + 
      "producto_iva='" + editar.getIva() + "'," + 
      "producto_cod_marca='" + editar.getMarcas().getCodigo() + "'," + 
      "producto_cod_familia='" + editar.getFamilia().getCodigo() + "'," + 
      "producto_observaciones='" + editar.getObservaciones() + "'," + 
      "producto_estante='" + editar.getEstante() + "'," + 
      "producto_cant_paquete='" + editar.getCantPaquete() + "'," + 
      "producto_descuento='" + editar.getDescuento() + "'," + 
      "producto_precio_mayorista='" + editar.getPrecioMayorista() + "'" + 
      " WHERE producto_codigo='" + editar.getCodigo() + "' ";
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
  
  public static void ActualizarStock(ModeloProductos actualizar)
  {
    String sql = "UPDATE productos SET producto_stock='" + 
      actualizar.getStock() + "'" + " WHERE producto_codigo='" + actualizar.getCodigo() + "' ";
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
  
  public static void EliminarProducto(String codigo)
  {
    String sql = "DELETE FROM productos WHERE producto_codigo='" + codigo + "' ";
    BasedeDatos.Conectar();
    try
    {
      BasedeDatos.stm.executeUpdate(sql);
    }
    catch (SQLException excepcion)
    {
      JOptionPane.showMessageDialog(null, excepcion, "Error", 0);
      excepcion.printStackTrace();
    }
    BasedeDatos.desconectar();
  }
  
  public static List<ModeloProductos> consultarTodosLosProductos()
  {
    String sql = "SELECT * FROM productos INNER JOIN proveedores ON proveedor_codigo = producto_cod_proveedor INNER JOIN marcas ON marca_codigo = producto_cod_marca INNER JOIN familias ON familia_codigo = producto_cod_familia ORDER BY producto_descripcion";
    
    ModeloProductos productos = null;
    List<ModeloProductos> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        productos = new ModeloProductos();
        
        productos.setCodigo(rs.getString("producto_codigo"));
        productos.setDescripcion(rs.getString("producto_descripcion"));
        productos.setPrecioVenta(rs.getDouble("producto_precio_venta"));
        productos.setStock(rs.getDouble("producto_stock"));
        productos.setUnidadDeMedida(rs.getString("producto_unidad_medida"));
        productos.getProveedor().setNombre(rs.getString("proveedor_nombre"));
        productos.getMarcas().setDescripcion(rs.getString("marca_descripcion"));
        productos.getFamilia().setDescripcion(rs.getString("familia_descripcion"));
        productos.setEstante(rs.getString("producto_estante"));
        productos.getProveedor().setCodigo(rs.getInt("proveedor_codigo"));
        
        double precio = 0.0D;
        double cantidad = 0.0D;
        double subtotal = 0.0D;
        
        precio = rs.getInt("producto_precio_venta");
        cantidad = rs.getDouble("producto_stock");
        subtotal = cantidad * precio;
        
        productos.setSubtotal(subtotal);
        lista.add(productos);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloProductos> ConsProdXProv(int proveedor)
  {
    String sql = "SELECT * FROM productos INNER JOIN proveedores ON proveedor_codigo = producto_cod_proveedor INNER JOIN marcas ON marca_codigo = producto_cod_marca INNER JOIN familias ON familia_codigo = producto_cod_familia WHERE producto_cod_proveedor=" + 
      proveedor + "ORDER BY producto_descripcion";
    ModeloProductos productos = null;
    List<ModeloProductos> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        productos = new ModeloProductos();
        
        productos.setCodigo(rs.getString("producto_codigo"));
        productos.setDescripcion(rs.getString("producto_descripcion"));
        productos.setPrecioVenta(rs.getDouble("producto_precio_venta"));
        productos.setStock(rs.getDouble("producto_stock"));
        productos.setUnidadDeMedida(rs.getString("producto_unidad_medida"));
        productos.getProveedor().setNombre(rs.getString("proveedor_nombre"));
        productos.getMarcas().setDescripcion(rs.getString("marca_descripcion"));
        productos.getFamilia().setDescripcion(rs.getString("familia_descripcion"));
        productos.setEstante(rs.getString("producto_estante"));
        productos.getProveedor().setCodigo(rs.getInt("proveedor_codigo"));
        
        double precio = 0.0D;
        double cantidad = 0.0D;
        double subtotal = 0.0D;
        
        precio = rs.getInt("producto_precio_venta");
        cantidad = rs.getDouble("producto_stock");
        subtotal = cantidad * precio;
        
        productos.setSubtotal(subtotal);
        lista.add(productos);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloProductos> ConsultarPorCondStock(double stock, int proveedor)
  {
    String sql = "SELECT * FROM productos INNER JOIN proveedores ON proveedor_codigo = producto_cod_proveedor INNER JOIN marcas ON marca_codigo = producto_cod_marca INNER JOIN familias ON familia_codigo = producto_cod_familia WHERE producto_stock<" + 
      stock + " AND producto_cod_proveedor =" + proveedor + "ORDER BY producto_descripcion";
    ModeloProductos productos = null;
    List<ModeloProductos> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        productos = new ModeloProductos();
        
        productos.setCodigo(rs.getString("producto_codigo"));
        productos.setDescripcion(rs.getString("producto_descripcion"));
        productos.setPrecioVenta(rs.getDouble("producto_precio_venta"));
        productos.setStock(rs.getDouble("producto_stock"));
        productos.setUnidadDeMedida(rs.getString("producto_unidad_medida"));
        productos.getProveedor().setNombre(rs.getString("proveedor_nombre"));
        productos.getMarcas().setDescripcion(rs.getString("marca_descripcion"));
        productos.getFamilia().setDescripcion(rs.getString("familia_descripcion"));
        productos.setEstante(rs.getString("producto_estante"));
        productos.getProveedor().setCodigo(rs.getInt("proveedor_codigo"));
        
        double precio = 0.0D;
        double cantidad = 0.0D;
        double subtotal = 0.0D;
        
        precio = rs.getInt("producto_precio_venta");
        cantidad = rs.getDouble("producto_stock");
        subtotal = cantidad * precio;
        
        productos.setSubtotal(subtotal);
        lista.add(productos);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static ModeloProductos ConsutaParaModificar(String codigo)
  {
    String sql = "SELECT * FROM productos INNER JOIN proveedores ON proveedor_codigo = producto_cod_proveedor INNER JOIN marcas ON marca_codigo = producto_cod_marca INNER JOIN familias ON familia_codigo = producto_cod_familia WHERE producto_codigo= '" + 
    
      codigo + "'";
    
    BasedeDatos.Conectar();
    ModeloProductos productos = null;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        productos = new ModeloProductos();
        productos.setDescripcion(rs.getString("producto_descripcion"));
        productos.setStock(rs.getDouble("producto_stock"));
        productos.setPrecioCosto(rs.getDouble("producto_precio_costo"));
        productos.setPrecioCredito(rs.getDouble("producto_precio_credito"));
        productos.setPrecioMayorista(rs.getDouble("producto_precio_mayorista"));
        productos.setPrecioVenta(rs.getDouble("producto_precio_venta"));
        productos.setCodProveedor(rs.getInt("producto_cod_proveedor"));
        productos.setUnidadDeMedida(rs.getString("producto_unidad_medida"));
        productos.setCantPaquete(rs.getInt("producto_cant_paquete"));
        productos.getProveedor().setNombre(rs.getString("proveedor_nombre"));
        productos.setFacturar(rs.getString("producto_facturar"));
        productos.setIva(rs.getDouble("producto_iva"));
        productos.setEstante(rs.getString("producto_estante"));
        productos.getMarcas().setCodigo(rs.getInt("marca_codigo"));
        productos.getMarcas().setDescripcion(rs.getString("marca_descripcion"));
        productos.getFamilia().setCodigo(rs.getInt("familia_codigo"));
        productos.getFamilia().setDescripcion(rs.getString("familia_descripcion"));
        productos.setObservaciones(rs.getString("producto_observaciones"));
        productos.setDescuento(rs.getDouble("producto_descuento"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return productos;
  }
  
  public static ModeloProductos Descuento(String codigo)
  {
    String sql = "SELECT producto_descuento FROM productos WHERE producto_codigo= '" + codigo + "'";
    
    BasedeDatos.Conectar();
    ModeloProductos productos = null;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        productos = new ModeloProductos();
        productos.setDescuento(rs.getDouble("producto_descuento"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return productos;
  }
  
  public static List<ModeloProductos> BuscarProductoDescripcion(String descripcion)
  {
    String sql = "SELECT * FROM productos INNER JOIN proveedores ON proveedor_codigo = producto_cod_proveedor INNER JOIN marcas ON marca_codigo = producto_cod_marca  INNER JOIN familias ON familia_codigo = producto_cod_familia WHERE producto_descripcion  ILIKE ? ORDER BY producto_descripcion";
    
    ModeloProductos productos = null;
    List<ModeloProductos> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, descripcion + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        productos = new ModeloProductos();
        
        productos.setCodigo(rs.getString("producto_codigo"));
        productos.setDescripcion(rs.getString("producto_descripcion"));
        productos.setPrecioVenta(rs.getDouble("producto_precio_venta"));
        productos.setStock(rs.getDouble("producto_stock"));
        productos.setUnidadDeMedida(rs.getString("producto_unidad_medida"));
        
        productos.getMarcas().setDescripcion(rs.getString("marca_descripcion"));
        productos.getFamilia().setDescripcion(rs.getString("familia_descripcion"));
        productos.setEstante(rs.getString("producto_estante"));
        productos.getProveedor().setCodigo(rs.getInt("proveedor_codigo"));
        double precio = 0.0D;
        double cantidad = 0.0D;
        double subtotal = 0.0D;
        
        precio = rs.getInt("producto_precio_venta");
        cantidad = rs.getDouble("producto_stock");
        subtotal = cantidad * precio;
        productos.setSubtotal(subtotal);
        lista.add(productos);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloProductos> ConsultarProductoSinStock()
  {
    String sql = "SELECT * FROM productos INNER JOIN proveedores ON proveedor_codigo = producto_cod_proveedor INNER JOIN marcas ON marca_codigo = producto_cod_marca WHERE producto_stock<=0 ORDER BY producto_descripcion";
    
    ModeloProductos productos = null;
    List<ModeloProductos> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        productos = new ModeloProductos();
        productos.setCodigo(rs.getString("producto_codigo"));
        productos.setDescripcion(rs.getString("producto_descripcion"));
        productos.setPrecioVenta(rs.getDouble("producto_precio_venta"));
        productos.setStock(rs.getDouble("producto_stock"));
        productos.getMarcas().setDescripcion(rs.getString("marca_descripcion"));
        productos.getProveedor().setNombre(rs.getString("proveedor_nombre"));
        productos.getProveedor().setCodigo(rs.getInt("proveedor_codigo"));
        double precio = 0.0D;
        double cantidad = 0.0D;
        double subtotal = 0.0D;
        
        precio = rs.getInt("producto_precio_venta");
        cantidad = rs.getDouble("producto_stock");
        subtotal = cantidad * precio;
        productos.setSubtotal(subtotal);
        lista.add(productos);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloProductos> ConsProdSinStockXProv(int codProv)
  {
    String sql = "SELECT * FROM productos INNER JOIN proveedores ON proveedor_codigo = producto_cod_proveedor INNER JOIN marcas ON marca_codigo = producto_cod_marca WHERE producto_stock<=0 AND producto_cod_proveedor =" + 
    
      codProv + "ORDER BY producto_descripcion";
    ModeloProductos productos = null;
    List<ModeloProductos> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        productos = new ModeloProductos();
        productos.setCodigo(rs.getString("producto_codigo"));
        productos.setDescripcion(rs.getString("producto_descripcion"));
        productos.setPrecioVenta(rs.getDouble("producto_precio_venta"));
        productos.setStock(rs.getDouble("producto_stock"));
        productos.getMarcas().setDescripcion(rs.getString("marca_descripcion"));
        productos.getProveedor().setNombre(rs.getString("proveedor_nombre"));
        productos.getProveedor().setCodigo(rs.getInt("proveedor_codigo"));
        double precio = 0.0D;
        double cantidad = 0.0D;
        double subtotal = 0.0D;
        
        precio = rs.getInt("producto_precio_venta");
        cantidad = rs.getDouble("producto_stock");
        subtotal = cantidad * precio;
        productos.setSubtotal(subtotal);
        lista.add(productos);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloProductos> ConsultarProductoConStock()
  {
    String sql = "SELECT * FROM productos INNER JOIN proveedores ON proveedor_codigo = producto_cod_proveedor WHERE producto_stock>0 ORDER BY producto_descripcion";
    ModeloProductos productos = null;
    List<ModeloProductos> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        productos = new ModeloProductos();
        productos.setCodigo(rs.getString("producto_codigo"));
        productos.setDescripcion(rs.getString("producto_descripcion"));
        productos.setPrecioVenta(rs.getDouble("producto_precio_venta"));
        productos.setStock(rs.getDouble("producto_stock"));
        productos.getProveedor().setNombre(rs.getString("proveedor_nombre"));
        
        double precio = 0.0D;
        double cantidad = 0.0D;
        double subtotal = 0.0D;
        
        precio = rs.getInt("producto_precio_venta");
        cantidad = rs.getDouble("producto_stock");
        subtotal = cantidad * precio;
        productos.setSubtotal(subtotal);
        lista.add(productos);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloProductos> BuscarProductoCodigo(String codigo)
  {
    String sql = "SELECT * FROM productos INNER JOIN proveedores ON proveedor_codigo = producto_cod_proveedor INNER JOIN marcas ON marca_codigo = producto_cod_marca  INNER JOIN familias ON familia_codigo = producto_cod_familia WHERE producto_codigo ILIKE ? ORDER BY producto_descripcion;";
    
    ModeloProductos productos = null;
    List<ModeloProductos> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, codigo + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        productos = new ModeloProductos();
        
        productos.setCodigo(rs.getString("producto_codigo"));
        productos.setDescripcion(rs.getString("producto_descripcion"));
        productos.setPrecioVenta(rs.getDouble("producto_precio_venta"));
        productos.setStock(rs.getDouble("producto_stock"));
        productos.setUnidadDeMedida(rs.getString("producto_unidad_medida"));
        
        productos.getMarcas().setDescripcion(rs.getString("marca_descripcion"));
        productos.getFamilia().setDescripcion(rs.getString("familia_descripcion"));
        productos.setEstante(rs.getString("producto_estante"));
        productos.getProveedor().setCodigo(rs.getInt("proveedor_codigo"));
        
        double precio = 0.0D;
        double cantidad = 0.0D;
        double subtotal = 0.0D;
        
        precio = rs.getInt("producto_precio_venta");
        cantidad = rs.getDouble("producto_stock");
        subtotal = cantidad * precio;
        productos.setSubtotal(subtotal);
        lista.add(productos);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloProveedores> consultarTodosLosProveedores()
  {
    String sql = "SELECT * FROM proveedores ORDER BY proveedor_codigo";
    ModeloProveedores proveedores = null;
    List<ModeloProveedores> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        proveedores = new ModeloProveedores();
        
        proveedores.setCodigo(rs.getInt("proveedor_codigo"));
        proveedores.setNombre(rs.getString("proveedor_nombre"));
        lista.add(proveedores);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloProductos> ConsultaRango(String codigo1, String codigo2)
  {
    ArrayList<ModeloProductos> lista = new ArrayList();
    BasedeDatos.Conectar();
    
    ModeloProductos productos = null;
    String sql = "SELECT * FROM productos INNER JOIN proveedores ON proveedor_codigo = producto_cod_proveedor WHERE producto_descripcion BETWEEN '" + codigo1 + "' AND  '" + codigo2 + "' ORDER BY producto_descripcion ";
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        productos = new ModeloProductos();
        
        productos.setCodigo(rs.getString("producto_codigo"));
        productos.setDescripcion(rs.getString("producto_descripcion"));
        productos.setPrecioVenta(rs.getDouble("producto_precio_venta"));
        productos.setStock(rs.getDouble("producto_stock"));
        productos.setUnidadDeMedida(rs.getString("producto_unidad_medida"));
        productos.getProveedor().setNombre(rs.getString("proveedor_nombre"));
        
        double precio = 0.0D;
        double cantidad = 0.0D;
        double subtotal = 0.0D;
        
        precio = rs.getInt("producto_precio_venta");
        cantidad = rs.getDouble("producto_stock");
        subtotal = cantidad * precio;
        productos.setSubtotal(subtotal);
        lista.add(productos);
      }
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Error :" + e.getMessage());
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static String EmailProveedor(int proveedor)
  {
    String sql = "SELECT proveedor_email FROM proveedores WHERE proveedor_codigo='" + proveedor + "'";
    BasedeDatos.Conectar();
    String email = "";
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        email = rs.getString("proveedor_email");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return email;
  }    
}

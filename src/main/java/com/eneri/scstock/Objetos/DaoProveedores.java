/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
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
public class DaoProveedores 
{
    
  public static int IncrementarCodigo()
  {
    String sql = "SELECT MAX (proveedor_codigo) AS proveedor_codigo FROM proveedores";
    int codigo = 0;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        codigo = rs.getInt("proveedor_codigo");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return codigo;
  }
  
  public static List<ModeloProveedores> consultarTodosLosProveedores(String orden)
  {
    String sql = "SELECT * FROM proveedores ORDER BY " + orden;
    ModeloProveedores proveedores = null;
    List<ModeloProveedores> lista = new ArrayList();
    /*BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        proveedores = new ModeloProveedores();
        
        proveedores.setCodigo(rs.getInt("proveedor_codigo"));
        proveedores.setNombre(rs.getString("proveedor_nombre"));
        proveedores.setTelefono(rs.getString("proveedor_telefono"));
        proveedores.setSitioweb(rs.getString("proveedor_sitioweb"));
        proveedores.setEmail(rs.getString("proveedor_email"));
        lista.add(proveedores);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();*/
    return lista;
  }
  
  public static List<ModeloProveedores> consultarProveedorXRango(String orden, String rango1, String rango2)
  {
    String sql = "SELECT * FROM proveedores WHERE proveedor_nombre BETWEEN '" + rango1 + "' AND '" + rango2 + "'ORDER BY " + orden;
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
        proveedores.setTelefono(rs.getString("proveedor_telefono"));
        proveedores.setSitioweb(rs.getString("proveedor_sitioweb"));
        proveedores.setEmail(rs.getString("proveedor_email"));
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
  
  public static void RegistrarProveedor(ModeloProveedores insertar)
  {
    String sql = "INSERT INTO proveedores (proveedor_codigo, proveedor_nombre, proveedor_direccion, proveedor_telefono, proveedor_telefono2, proveedor_email, proveedor_sitioweb) VALUES ('" + 
    
      insertar.getCodigo() + "','" + insertar.getNombre() + "','" + insertar.getDireccion() + "', '" + insertar.getTelefono() + 
      "', '" + insertar.getTelefono2() + "', '" + insertar.getEmail() + "', '" + insertar.getSitioweb() + "')";
    BasedeDatos.Conectar();
    try
    {
      BasedeDatos.stm.executeUpdate(sql);
    }
    catch (SQLException excepcion)
    {
      excepcion.printStackTrace();
    }
    BasedeDatos.desconectar();
  }
  
  public static void EditarProveedor(ModeloProveedores editar)
  {
    String sql = "UPDATE proveedores SET proveedor_nombre='" + 
      editar.getNombre() + "'," + 
      "proveedor_direccion='" + editar.getDireccion() + "'," + 
      "proveedor_telefono='" + editar.getTelefono() + "'," + 
      "proveedor_telefono2='" + editar.getTelefono2() + "'," + 
      "proveedor_sitioweb='" + editar.getSitioweb() + "'," + 
      "proveedor_email='" + editar.getEmail() + "'" + 
      " WHERE proveedor_codigo=" + editar.getCodigo() + " ";
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
  
  public static void EliminarProveedor(int codigo)
  {
    String sql = "DELETE FROM proveedores WHERE proveedor_codigo=" + codigo + " ";
    BasedeDatos.Conectar();
    try
    {
      BasedeDatos.stm.executeUpdate(sql);
    }
    catch (SQLException excepcion)
    {
      JOptionPane.showMessageDialog(null, "�ste proveedor est� siendo utilizado en el registro de\nproductos, no puede eliminarlo hasta que elimine\nel producto que depende del mismo.");
    }
    BasedeDatos.desconectar();
  }
  
  public static ModeloProveedores ConsutaParaModificar(int codigo)
  {
    String sql = "SELECT * FROM proveedores WHERE proveedor_codigo= " + codigo + " ";
    
    BasedeDatos.Conectar();
    ModeloProveedores proveedores = null;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        proveedores = new ModeloProveedores();
        proveedores.setNombre(rs.getString("proveedor_nombre"));
        proveedores.setTelefono(rs.getString("proveedor_telefono"));
        proveedores.setTelefono2(rs.getString("proveedor_telefono2"));
        proveedores.setDireccion(rs.getString("proveedor_direccion"));
        proveedores.setEmail(rs.getString("proveedor_email"));
        proveedores.setSitioweb(rs.getString("proveedor_sitioweb"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return proveedores;
  }
  
  public static List<ModeloProveedores> BuscarProveedorOrdenNombre(String nombre)
  {
    String sql = "SELECT * FROM proveedores WHERE proveedor_nombre ILIKE ? ORDER BY proveedor_nombre";
    ModeloProveedores proveedores = null;
    List<ModeloProveedores> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, nombre + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        proveedores = new ModeloProveedores();
        proveedores.setCodigo(rs.getInt("proveedor_codigo"));
        proveedores.setNombre(rs.getString("proveedor_nombre"));
        proveedores.setTelefono(rs.getString("proveedor_telefono"));
        proveedores.setSitioweb(rs.getString("proveedor_sitioweb"));
        
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
  
  public static List<ModeloProveedores> BuscarProvedorOrdenCodigo(String nombre)
  {
    String sql = "SELECT * FROM proveedores WHERE proveedor_nombre ILIKE ? ORDER BY proveedor_codigo";
    ModeloProveedores proveedores = null;
    List<ModeloProveedores> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, nombre + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        proveedores = new ModeloProveedores();
        proveedores.setCodigo(rs.getInt("proveedor_codigo"));
        proveedores.setNombre(rs.getString("proveedor_nombre"));
        proveedores.setTelefono(rs.getString("proveedor_telefono"));
        proveedores.setSitioweb(rs.getString("proveedor_sitioweb"));
        
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
  
  public static String CorreoProveedor(int codProv)
  {
    String sql = "SELECT proveedor_email FROM proveedores WHERE proveedor_codigo=" + codProv + ";";
    
    BasedeDatos.Conectar();
    String correo = "";
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next()) {
        correo = rs.getString("proveedor_email");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return correo;
  }    
}

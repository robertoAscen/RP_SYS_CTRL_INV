/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloVendedores;
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
public class DaoVendedores
{
    
  public static int IncrementarCodigo()
  {
    String sql = "SELECT MAX (vend_codigo) AS vend_codigo FROM vendedores";
    int codigo = 0;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        codigo = rs.getInt("vend_codigo");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return codigo;
  }
  
  public static boolean VerificarExistenciaCedula(ModeloVendedores vendedor)
  {
    boolean existencia = false;
    BasedeDatos.Conectar();
    
    String sql = "SELECT * FROM vendedores WHERE vend_codigo<>? AND vend_cedula=? ORDER BY vend_codigo ASC";
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setInt(1, vendedor.getCodigo());
      pstm.setString(2, vendedor.getCedula());
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
  
  public static void RegistrarVendedor(ModeloVendedores insertar)
  {
    String sql = "INSERT INTO vendedores (vend_codigo, vend_nombre, vend_cedula, vend_contacto, vend_direccion, vend_observaciones, vend_estado, vend_comision, vend_salario) VALUES ('" + 
    
      insertar.getCodigo() + "','" + insertar.getNombre() + "','" + insertar.getCedula() + "', " + 
      "'" + insertar.getContacto() + "', '" + insertar.getDireccion() + "', '" + insertar.getObservaciones() + "', " + 
      "'" + insertar.getEstado() + "','" + insertar.getComision() + "','" + insertar.getSalario() + "')";
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
  
  public static void EliminarVendedor(int codigo)
  {
    String sql = "DELETE FROM vendedores WHERE vend_codigo=" + codigo + " ";
    BasedeDatos.Conectar();
    try
    {
      BasedeDatos.stm.executeUpdate(sql);
    }
    catch (SQLException e)
    {
      JOptionPane.showMessageDialog(null, "No puede eliminar un vendedor que ya fu�\n utilizado en una transacci�n", "Aviso", 1);
    }
    BasedeDatos.desconectar();
  }
  
  public static void EditarVendedor(ModeloVendedores editar)
  {
    String sql = "UPDATE vendedores SET vend_nombre='" + 
      editar.getNombre() + "'," + 
      "vend_cedula='" + editar.getCedula() + "'," + 
      "vend_direccion='" + editar.getDireccion() + "'," + 
      "vend_contacto='" + editar.getContacto() + "'," + 
      "vend_observaciones='" + editar.getObservaciones() + "'," + 
      "vend_salario='" + editar.getSalario() + "'," + 
      "vend_comision='" + editar.getComision() + "'," + 
      "vend_estado='" + editar.getEstado() + "'" + 
      " WHERE vend_codigo=" + editar.getCodigo() + " ";
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
  
  public static ModeloVendedores ConsutarPorCodigo(int codigo)
  {
    String sql = "SELECT * FROM vendedores WHERE vend_codigo= " + codigo + " ";
    BasedeDatos.Conectar();
    ModeloVendedores vendedor = null;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        vendedor = new ModeloVendedores();
        vendedor.setCodigo(rs.getInt("vend_codigo"));
        vendedor.setNombre(rs.getString("vend_nombre"));
        vendedor.setCedula(rs.getString("vend_cedula"));
        vendedor.setContacto(rs.getString("vend_contacto"));
        vendedor.setDireccion(rs.getString("vend_direccion"));
        vendedor.setSalario(rs.getDouble("vend_salario"));
        vendedor.setComision(rs.getDouble("vend_comision"));
        vendedor.setEstado(rs.getString("vend_estado"));
        vendedor.setObservaciones(rs.getString("vend_observaciones"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return vendedor;
  }
  
  public static List<ModeloVendedores> consultarTodoElRegistro()
  {
    String sql = "SELECT * FROM vendedores ORDER BY vend_codigo ASC";
    ModeloVendedores vendedor = null;
    List<ModeloVendedores> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        vendedor = new ModeloVendedores();
        vendedor.setCodigo(rs.getInt("vend_codigo"));
        vendedor.setNombre(rs.getString("vend_nombre"));
        vendedor.setCedula(rs.getString("vend_cedula"));
        vendedor.setContacto(rs.getString("vend_contacto"));
        vendedor.setDireccion(rs.getString("vend_direccion"));
        vendedor.setSalario(rs.getDouble("vend_salario"));
        vendedor.setComision(rs.getDouble("vend_comision"));
        vendedor.setEstado(rs.getString("vend_estado"));
        vendedor.setObservaciones(rs.getString("vend_observaciones"));
        
        lista.add(vendedor);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloVendedores> consultarTodoElRegistroForComboBox()
  {
    String sql = "SELECT * FROM vendedores WHERE vend_estado='ACTIVO' ORDER BY vend_codigo ASC";
    ModeloVendedores vendedor = null;
    List<ModeloVendedores> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        vendedor = new ModeloVendedores();
        vendedor.setCodigo(rs.getInt("vend_codigo"));
        vendedor.setNombre(rs.getString("vend_nombre"));
        
        lista.add(vendedor);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloVendedores> ConsultarPorNombre(String nombre)
  {
    String sql = "SELECT * FROM vendedores WHERE vend_nombre  ILIKE ? ORDER BY vend_codigo ASC";
    ModeloVendedores vendedor = null;
    List<ModeloVendedores> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, nombre + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        vendedor = new ModeloVendedores();
        vendedor.setCodigo(rs.getInt("vend_codigo"));
        vendedor.setNombre(rs.getString("vend_nombre"));
        vendedor.setCedula(rs.getString("vend_cedula"));
        vendedor.setContacto(rs.getString("vend_contacto"));
        vendedor.setDireccion(rs.getString("vend_direccion"));
        vendedor.setSalario(rs.getDouble("vend_salario"));
        vendedor.setComision(rs.getDouble("vend_comision"));
        vendedor.setEstado(rs.getString("vend_estado"));
        vendedor.setObservaciones(rs.getString("vend_observaciones"));
        
        lista.add(vendedor);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloVendedores> consultarPorCedula(String cedula)
  {
    String sql = "SELECT * FROM vendedores WHERE vend_cedula  ILIKE ? ORDER BY vend_codigo";
    ModeloVendedores vendedor = null;
    List<ModeloVendedores> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, cedula + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        vendedor = new ModeloVendedores();
        vendedor.setCodigo(rs.getInt("vend_codigo"));
        vendedor.setNombre(rs.getString("vend_nombre"));
        vendedor.setCedula(rs.getString("vend_cedula"));
        vendedor.setContacto(rs.getString("vend_contacto"));
        vendedor.setDireccion(rs.getString("vend_direccion"));
        vendedor.setSalario(rs.getDouble("vend_salario"));
        vendedor.setComision(rs.getDouble("vend_comision"));
        vendedor.setEstado(rs.getString("vend_estado"));
        vendedor.setObservaciones(rs.getString("vend_observaciones"));
        
        lista.add(vendedor);
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

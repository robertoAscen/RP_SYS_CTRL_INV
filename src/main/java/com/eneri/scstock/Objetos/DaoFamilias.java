/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloFamilia;
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
public class DaoFamilias 
{
    
  public static void RegistrarFamilia(ModeloFamilia insertar)
  {
    String sql = "INSERT INTO familias (familia_codigo, familia_descripcion, familia_estado) VALUES ('" + 
    
      insertar.getCodigo() + "','" + 
      insertar.getDescripcion() + "','" + insertar.getEstado() + "')";
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
  
  public static int IncrementarCodigo()
  {
    String sql = "SELECT MAX (familia_codigo) AS familia_codigo FROM familias";
    int codigo = 0;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        codigo = rs.getInt("familia_codigo");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return codigo;
  }
  
  public static void EditarFamilia(ModeloFamilia editar)
  {
    String sql = "UPDATE familias SET familia_codigo='" + 
      editar.getCodigo() + "'," + 
      "familia_descripcion='" + editar.getDescripcion() + "'," + 
      "familia_estado='" + editar.getEstado() + "'" + 
      " WHERE familia_codigo=" + editar.getCodigo() + " ";
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
  
  public static List<ModeloFamilia> ConsultarFamilias(String orden)
  {
    String sql = "SELECT * FROM familias ORDER BY " + orden;
    ModeloFamilia familia = null;
    List<ModeloFamilia> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        familia = new ModeloFamilia();
        
        familia.setCodigo(rs.getInt("familia_codigo"));
        familia.setDescripcion(rs.getString("familia_descripcion"));
        familia.setEstado(rs.getString("familia_estado"));
        
        lista.add(familia);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloFamilia> BuscarPorDescripcion(String descripcion)
  {
    String sql = "SELECT * FROM familias WHERE familia_descripcion  ILIKE ? ORDER BY familia_codigo";
    ModeloFamilia familia = null;
    List<ModeloFamilia> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, descripcion + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        familia = new ModeloFamilia();
        
        familia.setCodigo(rs.getInt("familia_codigo"));
        familia.setDescripcion(rs.getString("familia_descripcion"));
        familia.setEstado(rs.getString("familia_estado"));
        
        lista.add(familia);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloFamilia> ConsultarFamiliasActivas(String orden)
  {
    String sql = "SELECT * FROM familias WHERE familia_estado = 'ACTIVO' ORDER BY " + orden;
    ModeloFamilia familia = null;
    List<ModeloFamilia> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        familia = new ModeloFamilia();
        
        familia.setCodigo(rs.getInt("familia_codigo"));
        familia.setDescripcion(rs.getString("familia_descripcion"));
        
        lista.add(familia);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static void EliminarMarca(int codigo)
  {
    String sql = "DELETE FROM familias WHERE familia_codigo=" + codigo + " ";
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
}

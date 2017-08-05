/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloMarcas;
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
public class DaoMarcas 
{
    
  public static void RegistrarMarca(ModeloMarcas insertar)
  {
    String sql = "INSERT INTO marcas (marca_codigo, marca_descripcion, marca_estado) VALUES ('" + 
    
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
  
  public static List<ModeloMarcas> BuscarPorDescripcion(String descripcion)
  {
    String sql = "SELECT * FROM marcas WHERE marca_descripcion  ILIKE ? ORDER BY marca_codigo";
    ModeloMarcas familia = null;
    List<ModeloMarcas> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, descripcion + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        familia = new ModeloMarcas();
        
        familia.setCodigo(rs.getInt("marca_codigo"));
        familia.setDescripcion(rs.getString("marca_descripcion"));
        familia.setEstado(rs.getString("marca_estado"));
        
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
  
  public static int IncrementarCodigo()
  {
    String sql = "SELECT MAX (marca_codigo) AS marca_codigo FROM marcas";
    int codigo = 0;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        codigo = rs.getInt("marca_codigo");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return codigo;
  }
  
  public static void EditarMarca(ModeloMarcas editar)
  {
    String sql = "UPDATE marcas SET marca_codigo='" + 
      editar.getCodigo() + "'," + 
      "marca_descripcion='" + editar.getDescripcion() + "', " + 
      "marca_estado='" + editar.getEstado() + "' " + 
      " WHERE marca_codigo=" + editar.getCodigo() + " ";
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
  
  public static List<ModeloMarcas> ConsultarMarcas(String orden)
  {
    String sql = "SELECT * FROM marcas ORDER BY " + orden;
    ModeloMarcas marca = null;
    List<ModeloMarcas> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        marca = new ModeloMarcas();
        
        marca.setCodigo(rs.getInt("marca_codigo"));
        marca.setDescripcion(rs.getString("marca_descripcion"));
        marca.setEstado(rs.getString("marca_estado"));
        
        lista.add(marca);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloMarcas> ConsultarMarcasActivas(String orden)
  {
    String sql = "SELECT * FROM marcas WHERE marca_estado = 'ACTIVO' ORDER BY " + orden;
    ModeloMarcas marca = null;
    List<ModeloMarcas> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        marca = new ModeloMarcas();
        
        marca.setCodigo(rs.getInt("marca_codigo"));
        marca.setDescripcion(rs.getString("marca_descripcion"));
        
        lista.add(marca);
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
    String sql = "DELETE FROM marcas WHERE marca_codigo=" + codigo + " ";
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloSucursal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RAscencio
 */
public class DaoSucursal 
{
    
  public static void RegistrarSucursal(ModeloSucursal insertar)
  {
    String sql = "INSERT INTO sucursales (sucursal_codigo, sucursal_nombre, sucursal_telefono, sucursal_responsable, sucursal_direccion) VALUES ('" + 
    
      insertar.getCodigo() + "','" + 
      insertar.getNombreSucursal() + "','" + 
      insertar.getContacto() + "', '" + 
      insertar.getResponsable() + "', '" + 
      insertar.getDireccion() + "')";
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
    String sql = "SELECT MAX (sucursal_codigo) AS sucursal_codigo FROM sucursales";
    int codigo = 0;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        codigo = rs.getInt("sucursal_codigo");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return codigo;
  }
  
  public static void EditarSucursal(ModeloSucursal editar)
  {
    String sql = "UPDATE sucursales SET sucursal_codigo='" + 
      editar.getCodigo() + "'," + 
      "sucursal_nombre='" + editar.getNombreSucursal() + "'," + 
      "sucursal_responsable='" + editar.getResponsable() + "'," + 
      "sucursal_telefono='" + editar.getContacto() + "'," + 
      "sucursal_direccion='" + editar.getDireccion() + "'" + 
      " WHERE sucursal_codigo=" + editar.getCodigo() + " ";
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
  
  public static void EliminarSucursal(int codigo)
  {
    String sql = "DELETE FROM sucursales WHERE sucursal_codigo=" + codigo + " ";
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
  
  public static List<ModeloSucursal> ConsultarTodos()
  {
    String sql = "SELECT * FROM sucursales";
    ModeloSucursal sucursal = null;
    List<ModeloSucursal> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        sucursal = new ModeloSucursal();
        
        sucursal.setCodigo(rs.getInt("sucursal_codigo"));
        sucursal.setNombreSucursal(rs.getString("sucursal_nombre"));
        sucursal.setContacto(rs.getString("sucursal_telefono"));
        sucursal.setDireccion(rs.getString("sucursal_direccion"));
        sucursal.setResponsable(rs.getString("sucursal_responsable"));
        lista.add(sucursal);
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

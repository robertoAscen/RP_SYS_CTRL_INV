/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloClientes;
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
public class DaoClientes 
{
    
  public static int IncrementarCodigo()
  {
    String sql = "SELECT MAX (cliente_codigo) AS cliente_codigo FROM clientes";
    int codigo = 0;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        codigo = rs.getInt("cliente_codigo");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return codigo;
  }
  
  public static List<ModeloClientes> consultarTodoElRegistro(String orden)
  {
    String sql = "SELECT * FROM clientes ORDER BY " + orden + ";";
    ModeloClientes cliente = null;
    List<ModeloClientes> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        cliente = new ModeloClientes();
        
        cliente.setCodigo(rs.getInt("cliente_codigo"));
        cliente.setNombre(rs.getString("cliente_nombre"));
        cliente.setCedula(rs.getString("cliente_cedula"));
        cliente.setDireccion(rs.getString("cliente_direccion"));
        cliente.setContacto(rs.getString("cliente_contacto"));
        cliente.setContacto2(rs.getString("cliente_contacto2"));
        cliente.setEmail(rs.getString("cliente_email"));
        lista.add(cliente);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static void RegistrarCliente(ModeloClientes insertar)
  {
    String sql = "INSERT INTO clientes (cliente_codigo, cliente_nombre, cliente_cedula, cliente_direccion, cliente_contacto, cliente_contacto2, cliente_email, cliente_descuento) VALUES ('" + 
    
      insertar.getCodigo() + "','" + insertar.getNombre() + "','" + insertar.getCedula() + "', " + 
      "'" + insertar.getDireccion() + "', '" + insertar.getContacto() + "', '" + insertar.getContacto2() + "', " + 
      "'" + insertar.getEmail() + "','" + insertar.getDescuento() + "')";
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
  
  public static boolean CedulaDuplex(ModeloClientes cliente)
  {
    boolean existencia = false;
    BasedeDatos.Conectar();
    
    String sql = "SELECT * FROM clientes WHERE cliente_codigo<>? AND cliente_cedula=? ORDER BY cliente_codigo ASC";
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setInt(1, cliente.getCodigo());
      pstm.setString(2, cliente.getCedula());
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
  
  public static void EditarCliente(ModeloClientes editar)
  {
    String sql = "UPDATE clientes SET cliente_nombre='" + 
      editar.getNombre() + "'," + 
      "cliente_cedula='" + editar.getCedula() + "'," + 
      "cliente_direccion='" + editar.getDireccion() + "'," + 
      "cliente_contacto='" + editar.getContacto() + "'," + 
      "cliente_contacto2='" + editar.getContacto2() + "'," + 
      "cliente_descuento='" + editar.getDescuento() + "'," + 
      "cliente_email='" + editar.getEmail() + "'" + 
      " WHERE cliente_codigo=" + editar.getCodigo() + " ";
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
  
  public static void EliminarCliente(int codigo)
  {
    String sql = "DELETE FROM clientes WHERE cliente_codigo=" + codigo + " ";
    BasedeDatos.Conectar();
    try
    {
      BasedeDatos.stm.executeUpdate(sql);
    }
    catch (SQLException e)
    {
      JOptionPane.showMessageDialog(null, "No puede eliminar un cliente que ya fu�\n utilizado en una transacci�n", "Aviso", 1);
    }
    BasedeDatos.desconectar();
  }
  
  public static ModeloClientes ConsutaParaModificar(int codigo)
  {
    String sql = "SELECT * FROM clientes WHERE cliente_codigo= " + codigo + " ";
    
    BasedeDatos.Conectar();
    ModeloClientes cliente = null;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        cliente = new ModeloClientes();
        cliente.setNombre(rs.getString("cliente_nombre"));
        cliente.setCedula(rs.getString("cliente_cedula"));
        cliente.setContacto(rs.getString("cliente_contacto"));
        cliente.setContacto2(rs.getString("cliente_contacto2"));
        cliente.setDireccion(rs.getString("cliente_direccion"));
        cliente.setEmail(rs.getString("cliente_email"));
        cliente.setDescuento(rs.getDouble("cliente_descuento"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return cliente;
  }
  
  public static List<ModeloClientes> ConsultarPorNombre(String nombre)
  {
    String sql = "SELECT * FROM clientes WHERE cliente_nombre  ILIKE ? ORDER BY cliente_codigo";
    ModeloClientes clientes = null;
    List<ModeloClientes> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, nombre + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        clientes = new ModeloClientes();
        clientes.setCodigo(rs.getInt("cliente_codigo"));
        clientes.setNombre(rs.getString("cliente_nombre"));
        clientes.setCedula(rs.getString("cliente_cedula"));
        clientes.setContacto(rs.getString("cliente_contacto"));
        clientes.setDireccion(rs.getString("cliente_direccion"));
        lista.add(clientes);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloClientes> consultarPorCedula(String cedula)
  {
    String sql = "SELECT * FROM clientes WHERE cliente_cedula  ILIKE ? ORDER BY cliente_codigo";
    ModeloClientes clientes = null;
    List<ModeloClientes> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, cedula + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        clientes = new ModeloClientes();
        clientes.setCodigo(rs.getInt("cliente_codigo"));
        clientes.setNombre(rs.getString("cliente_nombre"));
        clientes.setCedula(rs.getString("cliente_cedula"));
        clientes.setContacto(rs.getString("cliente_contacto"));
        clientes.setDireccion(rs.getString("cliente_direccion"));
        
        lista.add(clientes);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static ModeloClientes ConsultaClientePFactura(int codigocliente)
  {
    String sql = "SELECT * FROM clientes WHERE cliente_codigo = " + codigocliente;
    BasedeDatos.Conectar();
    
    ModeloClientes cliente = null;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        cliente = new ModeloClientes();
        cliente.setCodigo(rs.getInt("cliente_codigo"));
        cliente.setNombre(rs.getString("cliente_nombre"));
        cliente.setDireccion(rs.getString("cliente_direccion"));
        cliente.setContacto(rs.getString("cliente_contacto"));
        cliente.setCedula(rs.getString("cliente_cedula"));
        cliente.setDescuento(rs.getDouble("cliente_descuento"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return cliente;
  }
  
  public static List<ModeloClientes> ConsultarPorRangoAlfabetico(String rango1, String rango2, String orden)
  {
    String sql = "SELECT * FROM clientes WHERE cliente_nombre BETWEEN '" + rango1 + "' AND  '" + rango2 + "'  ORDER BY " + orden + " ASC;";
    ModeloClientes clientes = null;
    List<ModeloClientes> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        clientes = new ModeloClientes();
        clientes.setCodigo(rs.getInt("cliente_codigo"));
        clientes.setNombre(rs.getString("cliente_nombre"));
        clientes.setCedula(rs.getString("cliente_cedula"));
        clientes.setContacto(rs.getString("cliente_contacto"));
        clientes.setDireccion(rs.getString("cliente_direccion"));
        
        lista.add(clientes);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloClientes> ConsultarPorRangoCodigo(int rango1, int rango2, String orden)
  {
    String sql = "SELECT * FROM clientes WHERE cliente_codigo >= '" + rango1 + "' AND cliente_codigo<= '" + rango2 + "'  ORDER BY " + orden + " ASC;";
    ModeloClientes clientes = null;
    List<ModeloClientes> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        clientes = new ModeloClientes();
        clientes.setCodigo(rs.getInt("cliente_codigo"));
        clientes.setNombre(rs.getString("cliente_nombre"));
        clientes.setCedula(rs.getString("cliente_cedula"));
        clientes.setContacto(rs.getString("cliente_contacto"));
        clientes.setDireccion(rs.getString("cliente_direccion"));
        
        lista.add(clientes);
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

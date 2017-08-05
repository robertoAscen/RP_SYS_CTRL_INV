/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloUsuarios;
import java.io.FileInputStream;
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
public class DaoUsuarios 
{
    
  public static ModeloUsuarios ComprobarAcceso(String usuario, String password)
  {
    String sql = "SELECT * FROM usuarios WHERE usuario_nombre='" + usuario + "'AND usuario_password='" + password + "';";
    ModeloUsuarios user = null;
    BasedeDatos.Conectar();
    System.out.println(sql);
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        user = new ModeloUsuarios();
        user.setCodigo(rs.getInt("usuario_codigo"));
        user.setNombre(rs.getString("usuario_nombre"));
        user.setPassword(rs.getString("usuario_password"));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return user;
  }
  
  public static ModeloUsuarios VerContrasenhas(String usuario, String password)
  {
    String sql = "SELECT * FROM usuarios WHERE usuario_nombre='" + usuario + "'AND usuario_password='" + password + "';";
    ModeloUsuarios user = null;
    BasedeDatos.Conectar();
    System.out.println(sql);
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        user = new ModeloUsuarios();
        user.setCodigo(rs.getInt("usuario_codigo"));
        user.setNombre(rs.getString("usuario_nombre"));
        user.setPassword(rs.getString("usuario_password"));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return user;
  }
  
  public static void RegistrarUsuario(ModeloUsuarios usuario, FileInputStream fis, int longitudBytes)
  {
    BasedeDatos.Conectar();
    
    String sql = "INSERT INTO usuarios (usuario_codigo, usuario_nombre, usuario_imagen, usuario_password) VALUES (?, ?, ?,?)";
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setInt(1, usuario.getCodigo());
      pstm.setString(2, usuario.getNombre());
      pstm.setBinaryStream(3, fis, longitudBytes);
      pstm.setString(4, usuario.getPassword());
      pstm.execute();
    }
    catch (SQLException e)
    {
      JOptionPane.showMessageDialog(null, "Error :" + e.getMessage());
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
  }
  
  public static List<ModeloUsuarios> ConsultarListaDeTodosLosUsuarios()
  {
    String sql = "SELECT * FROM usuarios ORDER BY usuario_codigo ASC";
    ModeloUsuarios u = null;
    List<ModeloUsuarios> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        u = new ModeloUsuarios();
        u.setCodigo(rs.getInt("usuario_codigo"));
        u.setNombre(rs.getString("usuario_nombre"));
        u.setPassword(rs.getString("usuario_password"));
        lista.add(u);
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
    String sql = "SELECT MAX (usuario_codigo) AS usuario_codigo FROM usuarios";
    int codigo = 0;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        codigo = rs.getInt("usuario_codigo");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return codigo;
  }
  
  public static void EditarUsuario(ModeloUsuarios usuario, FileInputStream fis, int longitudBytes)
  {
    BasedeDatos.Conectar();
    
    String sql = "UPDATE usuarios SET usuario_nombre=?, usuario_imagen=?, usuario_password=? WHERE usuario_codigo=?";
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, usuario.getNombre());
      pstm.setBinaryStream(2, fis, longitudBytes);
      pstm.setString(3, usuario.getPassword());
      pstm.setInt(4, usuario.getCodigo());
      pstm.executeUpdate();
    }
    catch (SQLException e)
    {
      JOptionPane.showMessageDialog(null, "Error :" + e.getMessage());
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
  }
  
  public static void EliminarUsuario(int codigo)
  {
    String sql = "DELETE FROM usuarios WHERE usuario_codigo=" + codigo + " ";
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
  
  public static boolean UsuarioExistente(ModeloUsuarios usuario)
  {
    boolean existencia = false;
    BasedeDatos.Conectar();
    
    String sql = "SELECT * FROM usuarios WHERE usuario_codigo<>? AND usuario_nombre=? ORDER BY usuario_codigo ASC";
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setInt(1, usuario.getCodigo());
      pstm.setString(2, usuario.getNombre());
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
  
  public static String PassAdmin()
  {
    String sql = "SELECT usuario_password FROM usuarios WHERE usuario_codigo=1";
    BasedeDatos.Conectar();
    
    String contrasenha = "";
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        contrasenha = rs.getString("usuario_password");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return contrasenha;
  }    
}

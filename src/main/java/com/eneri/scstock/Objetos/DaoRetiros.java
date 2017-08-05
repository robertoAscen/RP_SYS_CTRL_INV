/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloRetiro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author RAscencio
 */
public class DaoRetiros 
{
  public static int IncrementarCodigo()
  {
    String sql = "SELECT MAX (ret_codigo) AS ret_codigo FROM retiros";
    int codigo = 0;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        codigo = rs.getInt("ret_codigo");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return codigo;
  }
  
  public static void RetiroDinero(ModeloRetiro insertar)
  {
    String sql = "INSERT INTO retiros (ret_codigo, ret_fecha, ret_hora, ret_monto, ret_detalle) VALUES ('" + 
      insertar.getCodigo() + "','" + insertar.getFecha() + "','" + insertar.getHora() + "', " + 
      "'" + insertar.getMonto() + "', '" + insertar.getDetalle() + "')";
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
  
  public static List<ModeloRetiro> ConsultarRetiros(String fecha)
  {
    String sql = "SELECT * FROM retiros WHERE ret_fecha='" + fecha + "';";
    ModeloRetiro retiro = null;
    List<ModeloRetiro> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        retiro = new ModeloRetiro();
        retiro.setCodigo(rs.getInt("ret_codigo"));
        retiro.setFecha(rs.getString("ret_fecha"));
        retiro.setHora(rs.getString("ret_hora"));
        retiro.setMonto(rs.getInt("ret_monto"));
        retiro.setDetalle(rs.getString("ret_detalle"));
        
        lista.add(retiro);
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

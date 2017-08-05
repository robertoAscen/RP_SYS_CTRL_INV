/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloInfoCliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RAscencio
 */
public class DaoInfoClienteMes
{
    
  public static List<ModeloInfoCliente> ConsultarTodos(String orden)
  {
    String sql = "SELECT * FROM info_cliente_mes INNER JOIN clientes ON cliente_codigo = info_cod_cliente ORDER BY " + orden;
    ModeloInfoCliente informacion = null;
    List<ModeloInfoCliente> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        informacion = new ModeloInfoCliente();
        
        informacion.setCodigo(rs.getInt("info_codigo"));
        informacion.getCliente().setCedula(rs.getString("cliente_cedula"));
        informacion.getCliente().setNombre(rs.getString("cliente_nombre"));
        informacion.setMes(rs.getInt("info_mes"));
        informacion.setAnho(rs.getInt("info_anho"));
        informacion.setTotal(rs.getDouble("info_total"));
        
        lista.add(informacion);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloInfoCliente> ConsultarPorMes(String orden, int mes, int anho)
  {
    String sql = "SELECT * FROM info_cliente_mes INNER JOIN clientes ON cliente_codigo = info_cod_cliente WHERE info_mes = " + 
      mes + " AND info_anho = " + anho + " ORDER BY " + orden;
    ModeloInfoCliente informacion = null;
    List<ModeloInfoCliente> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        informacion = new ModeloInfoCliente();
        
        informacion.setCodigo(rs.getInt("info_codigo"));
        informacion.getCliente().setCedula(rs.getString("cliente_cedula"));
        informacion.getCliente().setNombre(rs.getString("cliente_nombre"));
        informacion.setMes(rs.getInt("info_mes"));
        informacion.setAnho(rs.getInt("info_anho"));
        informacion.setTotal(rs.getDouble("info_total"));
        
        lista.add(informacion);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloFacturaCredito;
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
public class DaoDevolucionesFacCredito 
{
    
  public static void ActualizarTotalFacturaCabecera(ModeloFacturaCredito update)
  {
    String sql = "UPDATE factura_cabcred SET fc_total='" + 
      update.getTotal() + "'," + 
      "fc_saldo='" + update.getTotal() + "'," + 
      "fc_estado='" + update.getEstado() + "'" + 
      " WHERE fc_codigo=" + update.getCodigoCabecera() + " ";
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
  
  public static void EliminarMercaderiaDevuelta(int codigo)
  {
    String sql = "DELETE FROM factura_detcred WHERE fd_codigo=" + codigo + " ";
    BasedeDatos.Conectar();
    try
    {
      BasedeDatos.stm.executeUpdate(sql);
    }
    catch (SQLException excepcion)
    {
      JOptionPane.showMessageDialog(null, excepcion);
      excepcion.printStackTrace();
    }
    BasedeDatos.desconectar();
  }
  
  public static List<ModeloFacturaCredito> consultarFacturaCabecera()
  {
    String sql = "SELECT * FROM factura_cabcred INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE fc_estado='EN DEUDA'";
    ModeloFacturaCredito cobranza = null;
    List<ModeloFacturaCredito> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        cobranza = new ModeloFacturaCredito();
        
        cobranza.setCodigoCabecera(rs.getInt("fc_codigo"));
        cobranza.getCliente().setCedula(rs.getString("cliente_cedula"));
        cobranza.getCliente().setNombre(rs.getString("cliente_nombre"));
        cobranza.setFecha(rs.getString("fc_fecha"));
        cobranza.setHora(rs.getString("fc_hora"));
        cobranza.setEstado(rs.getString("fc_estado"));
        cobranza.setTotal(rs.getInt("fc_total"));
        
        lista.add(cobranza);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloFacturaCredito> ConsultarPorNFactura(int codigo)
  {
    String sql = "SELECT * FROM factura_cabcred INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE fc_codigo='" + codigo + "' AND fc_estado='EN DEUDA'";
    ModeloFacturaCredito contado = null;
    List<ModeloFacturaCredito> lista = new ArrayList();
    
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        contado = new ModeloFacturaCredito();
        
        contado.setCodigoCabecera(rs.getInt("fc_codigo"));
        contado.getCliente().setCedula(rs.getString("cliente_cedula"));
        contado.getCliente().setNombre(rs.getString("cliente_nombre"));
        contado.setFecha(rs.getString("fc_fecha"));
        contado.setHora(rs.getString("fc_hora"));
        contado.setTotal(rs.getInt("fc_total"));
        
        lista.add(contado);
      }
      BasedeDatos.desconectar();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloFacturaCredito> ConsultarPorCedula(String codigo)
  {
    String sql = "SELECT * FROM factura_cabcred INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE cliente_cedula  ILIKE ? AND fc_estado='EN DEUDA'";
    ModeloFacturaCredito contado = null;
    List<ModeloFacturaCredito> lista = new ArrayList();
    
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, codigo + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        contado = new ModeloFacturaCredito();
        
        contado.setCodigoCabecera(rs.getInt("fc_codigo"));
        contado.getCliente().setCedula(rs.getString("cliente_cedula"));
        contado.getCliente().setNombre(rs.getString("cliente_nombre"));
        contado.setFecha(rs.getString("fc_fecha"));
        contado.setHora(rs.getString("fc_hora"));
        contado.setTotal(rs.getInt("fc_total"));
        contado.setEstado(rs.getString("fc_estado"));
        
        lista.add(contado);
      }
      BasedeDatos.desconectar();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return lista;
  }
  
  public static List<ModeloFacturaCredito> ConsultarPorNombre(String codigo)
  {
    String sql = "SELECT * FROM factura_cabcred INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE cliente_nombre  ILIKE ? AND fc_estado='EN DEUDA'";
    ModeloFacturaCredito contado = null;
    List<ModeloFacturaCredito> lista = new ArrayList();
    
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, codigo + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        contado = new ModeloFacturaCredito();
        
        contado.setCodigoCabecera(rs.getInt("fc_codigo"));
        contado.getCliente().setCedula(rs.getString("cliente_cedula"));
        contado.getCliente().setNombre(rs.getString("cliente_nombre"));
        contado.setFecha(rs.getString("fc_fecha"));
        contado.setHora(rs.getString("fc_hora"));
        contado.setTotal(rs.getInt("fc_total"));
        contado.setEstado(rs.getString("fc_estado"));
        
        lista.add(contado);
      }
      BasedeDatos.desconectar();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return lista;
  }    
}

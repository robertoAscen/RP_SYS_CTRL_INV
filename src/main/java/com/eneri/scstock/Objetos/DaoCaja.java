/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloCaja;
import com.eneri.scstock.Modelos.ModeloFacturaContado;
import com.eneri.scstock.Modelos.ModeloFacturaCredito;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author RAscencio
 */
public class DaoCaja 
{
    
  public static int IncrementarCodigo()
  {
    String sql = "SELECT MAX (caja_codigo) AS caja_codigo FROM caja";
    int codigo = 0;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        codigo = rs.getInt("caja_codigo");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return codigo;
  }
  
  public static void CerrarCaja(ModeloCaja insertar)
  {
    String sql = "INSERT INTO caja (caja_codigo, caja_total, caja_fecha_cierre, caja_hora_cierre, caja_retiro, caja_saldo) VALUES ('" + 
      insertar.getCodigo() + "','" + insertar.getTotalVenta() + "','" + insertar.getFechaCierre() + "', " + 
      "'" + insertar.getHoraCierre() + "', '" + insertar.getTotalRetiro() + "', '" + insertar.getSaldo() + "')";
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
  
  public static List<ModeloFacturaContado> consultarFacturaCabeceraContado(String fecha)
  {
    String sql = "SELECT * FROM factura_cabecera INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE fc_total>0 AND fc_fecha='" + fecha + "' AND fc_estado='EMITIDO';";
    ModeloFacturaContado cobranza = null;
    List<ModeloFacturaContado> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        cobranza = new ModeloFacturaContado();
        cobranza.setCodigoCliente(rs.getInt("fc_cod_cliente"));
        cobranza.setCodigoCabecera(rs.getInt("fc_codigo"));
        cobranza.getCliente().setCedula(rs.getString("cliente_cedula"));
        cobranza.getCliente().setNombre(rs.getString("cliente_nombre"));
        cobranza.setFecha(rs.getString("fc_fecha"));
        cobranza.setHora(rs.getString("fc_hora"));
        cobranza.setEstado(rs.getString("fc_estado"));
        cobranza.setTotal(rs.getInt("fc_total"));
        cobranza.getVendedor().setCodigo(rs.getInt("fc_cod_vendedor"));
        
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
  
  public static List<ModeloFacturaCredito> consultarFacturaCabeceraCredito(String fecha)
  {
    String sql = "SELECT * FROM factura_cabcred INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE fc_entrega>0 AND fc_fechaentrega='" + fecha + "';";
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
        cobranza.setCodigoCliente(rs.getInt("fc_cod_cliente"));
        cobranza.getCliente().setCedula(rs.getString("cliente_cedula"));
        cobranza.getCliente().setNombre(rs.getString("cliente_nombre"));
        cobranza.setFecha(rs.getString("fc_fecha"));
        cobranza.setHora(rs.getString("fc_hora"));
        cobranza.setTotal(rs.getInt("fc_total"));
        cobranza.setSaldo(rs.getInt("fc_saldo"));
        cobranza.setFechaUEntrega(rs.getString("fc_fechaentrega"));
        cobranza.setHoraEntrega(rs.getString("fc_horaentrega"));
        cobranza.setEstado(rs.getString("fc_estado"));
        cobranza.setEntrega(rs.getInt("fc_entrega"));
        cobranza.getVendedor().setCodigo(rs.getInt("fc_cod_vendedor"));
        
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
  
  public static ModeloCaja SaberSiLaCajaEstaCerrada(String fecha)
  {
    String sql = "SELECT * FROM caja WHERE caja_fecha_cierre BETWEEN '" + fecha + "' AND '" + fecha + "'";
    ModeloCaja caja = null;
    BasedeDatos.Conectar();
    System.out.println(sql);
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        caja = new ModeloCaja();
        caja.setCodigo(rs.getInt("caja_codigo"));
        caja.setFechaCierre(rs.getString("caja_fecha_cierre"));
        caja.setHoraCierre(rs.getString("caja_hora_cierre"));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return caja;
  }    
}

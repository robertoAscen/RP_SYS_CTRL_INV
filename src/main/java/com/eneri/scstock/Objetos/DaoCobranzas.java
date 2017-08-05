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
public class DaoCobranzas
{
    
  public static List<ModeloFacturaCredito> consultarFacturaCabecera()
  {
    String sql = "SELECT * FROM factura_cabcred INNER JOIN clientes ON fc_cod_cliente = cliente_codigo ORDER BY fc_codigo DESC";
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
        cobranza.setTotal(rs.getInt("fc_total"));
        cobranza.setSaldo(rs.getInt("fc_saldo"));
        cobranza.setFechaUEntrega(rs.getString("fc_fechaentrega"));
        cobranza.setHoraEntrega(rs.getString("fc_horaentrega"));
        cobranza.setEstado(rs.getString("fc_estado"));
        
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
  
  public static List<ModeloFacturaCredito> consultarDetalleDeLaFactura(int parametro)
  {
    String sql = "SELECT * FROM factura_detcred INNER JOIN productos ON fd_cod_producto = producto_codigo WHERE fd_cod_cabecera='" + parametro + "';";
    ModeloFacturaCredito cobranza = null;
    List<ModeloFacturaCredito> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        cobranza = new ModeloFacturaCredito();
        
        cobranza.setCodigoDetalle(rs.getInt("fd_codigo"));
        cobranza.getProducto().setCodigo(rs.getString("producto_codigo"));
        cobranza.getProducto().setDescripcion(rs.getString("producto_descripcion"));
        cobranza.setCantidad(rs.getDouble("fd_cantidad"));
        cobranza.setPrecioUnitario(rs.getInt("fd_punitario"));
        cobranza.setSubtotal(rs.getInt("fd_subtotal"));
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
  
  public static void ActualizarCobranza(ModeloFacturaCredito update)
  {
    String sql = "UPDATE factura_cabcred SET fc_saldo='" + 
      update.getSaldo() + "'," + 
      "fc_estado='" + update.getEstado() + "'," + 
      "fc_entrega='" + update.getEntrega() + "'," + 
      "fc_horaentrega='" + update.getHoraEntrega() + "'," + 
      "fc_fechaentrega='" + update.getFechaUEntrega() + "'" + 
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
  
  public static ModeloFacturaCredito ConsutarMasDetalles(int codigo)
  {
    String sql = "SELECT * FROM factura_cabcred WHERE fc_codigo= " + codigo + " ";
    
    BasedeDatos.Conectar();
    ModeloFacturaCredito credito = null;
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      if (rs.next())
      {
        credito = new ModeloFacturaCredito();
        credito.setFechaUEntrega(rs.getString("fc_fechaentrega"));
        credito.setHoraEntrega(rs.getString("fc_horaentrega"));
        credito.setTotal(rs.getInt("fc_total"));
        credito.setEntrega(rs.getInt("fc_entrega"));
        credito.setSaldo(rs.getInt("fc_saldo"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return credito;
  }
  
  public static void ActTotCabEnCasoDeStockInsuf(ModeloFacturaCredito update)
  {
    String sql = "UPDATE factura_cabcred SET fc_total='" + 
      update.getTotal() + "'," + 
      "fc_saldo='" + update.getSaldo() + "'" + 
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
  
  public static List<ModeloFacturaCredito> ConsultarPorCedula(String codigo)
  {
    String sql = "SELECT * FROM factura_cabcred INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE cliente_cedula  ILIKE ?";
    ModeloFacturaCredito credito = null;
    List<ModeloFacturaCredito> lista = new ArrayList();
    
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, codigo + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        credito = new ModeloFacturaCredito();
        
        credito.setCodigoCabecera(rs.getInt("fc_codigo"));
        credito.getCliente().setCedula(rs.getString("cliente_cedula"));
        credito.getCliente().setNombre(rs.getString("cliente_nombre"));
        credito.setFecha(rs.getString("fc_fecha"));
        credito.setHora(rs.getString("fc_hora"));
        credito.setTotal(rs.getInt("fc_total"));
        credito.setSaldo(rs.getInt("fc_saldo"));
        credito.setFechaUEntrega(rs.getString("fc_fechaentrega"));
        credito.setHoraEntrega(rs.getString("fc_horaentrega"));
        credito.setEstado(rs.getString("fc_estado"));
        
        lista.add(credito);
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
    String sql = "SELECT * FROM factura_cabcred INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE cliente_nombre  ILIKE ?";
    ModeloFacturaCredito credito = null;
    List<ModeloFacturaCredito> lista = new ArrayList();
    
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, codigo + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        credito = new ModeloFacturaCredito();
        
        credito.setCodigoCabecera(rs.getInt("fc_codigo"));
        credito.getCliente().setCedula(rs.getString("cliente_cedula"));
        credito.getCliente().setNombre(rs.getString("cliente_nombre"));
        credito.setFecha(rs.getString("fc_fecha"));
        credito.setHora(rs.getString("fc_hora"));
        credito.setTotal(rs.getInt("fc_total"));
        credito.setSaldo(rs.getInt("fc_saldo"));
        credito.setFechaUEntrega(rs.getString("fc_fechaentrega"));
        credito.setHoraEntrega(rs.getString("fc_horaentrega"));
        credito.setEstado(rs.getString("fc_estado"));
        
        lista.add(credito);
      }
      BasedeDatos.desconectar();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return lista;
  }
  
  public static List<ModeloFacturaCredito> ConsultarPorNFactura(int codigo)
  {
    String sql = "SELECT * FROM factura_cabcred INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE fc_codigo='" + codigo + "'";
    ModeloFacturaCredito credito = null;
    List<ModeloFacturaCredito> lista = new ArrayList();
    
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        credito = new ModeloFacturaCredito();
        
        credito.setCodigoCabecera(rs.getInt("fc_codigo"));
        credito.getCliente().setCedula(rs.getString("cliente_cedula"));
        credito.getCliente().setNombre(rs.getString("cliente_nombre"));
        credito.setFecha(rs.getString("fc_fecha"));
        credito.setHora(rs.getString("fc_hora"));
        credito.setTotal(rs.getInt("fc_total"));
        credito.setSaldo(rs.getInt("fc_saldo"));
        credito.setFechaUEntrega(rs.getString("fc_fechaentrega"));
        credito.setHoraEntrega(rs.getString("fc_horaentrega"));
        credito.setEstado(rs.getString("fc_estado"));
        
        lista.add(credito);
      }
      BasedeDatos.desconectar();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return lista;
  }
  
  public static void AnularDeuda(ModeloFacturaCredito update)
  {
    String sql = "UPDATE factura_cabcred SET fc_total='" + 
      update.getTotal() + "'," + 
      "fc_saldo='" + update.getSaldo() + "'," + 
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
  
  public static void EliminarDetalles(String codigo)
  {
    String sql = "DELETE FROM factura_detcred WHERE fd_codigo='" + codigo + "' ";
    System.out.println(sql);
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
}

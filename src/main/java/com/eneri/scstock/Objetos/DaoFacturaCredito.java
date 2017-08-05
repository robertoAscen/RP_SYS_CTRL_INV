/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloFacturaCredito;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author RAscencio
 */
public class DaoFacturaCredito 
{
    
  public static int IncrementarCodigoCabecera()
  {
    String sql = "SELECT MAX (fc_codigo) AS fc_codigo FROM factura_cabcred";
    int codigo = 0;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        codigo = rs.getInt("fc_codigo");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return codigo;
  }
  
  public static int IncrementarCodigoDetalle()
  {
    String sql = "SELECT MAX (fd_codigo) AS fd_codigo FROM factura_detcred";
    int codigo = 0;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next()) {
        codigo = rs.getInt("fd_codigo");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return codigo;
  }
  
  public static void GuardarFacturaDetalle(ModeloFacturaCredito insertar)
  {
    String sql = "INSERT INTO factura_detcred (fd_codigo, fd_cod_producto,fd_cantidad, fd_punitario,fd_subtotal,fd_cod_cabecera) VALUES ('" + 
    
      insertar.getCodigoDetalle() + "','" + 
      insertar.getCodigoProducto() + "','" + insertar.getCantidad() + "', " + 
      "'" + insertar.getPrecioUnitario() + "', " + 
      "'" + insertar.getSubtotal() + "', " + 
      "'" + insertar.getCodigoCabecera() + "')";
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
  
  public static void GuardarFacturaCabecera(ModeloFacturaCredito insertar)
  {
    String sql = "INSERT INTO factura_cabcred (fc_codigo, fc_cod_cliente, fc_fecha, fc_total, fc_saldo, fc_estado, fc_entrega, fc_horaentrega ,fc_fechaentrega, fc_hora, fc_cod_vendedor) VALUES('" + 
    
      insertar.getCodigoCabecera() + "','" + insertar.getCodigoCliente() + "'," + 
      "'" + insertar.getFecha() + "', " + 
      "'" + insertar.getTotal() + "', " + 
      "'" + insertar.getSaldo() + "', " + 
      "'" + insertar.getEstado() + "', " + 
      "'" + insertar.getEntrega() + "', " + 
      "'" + insertar.getHoraEntrega() + "', " + 
      "'" + insertar.getFechaUEntrega() + "', " + 
      "'" + insertar.getHora() + "', '" + insertar.getVendedor().getCodigo() + "')";
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
  
  public static void EliminarDetalles(int codigo)
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
}

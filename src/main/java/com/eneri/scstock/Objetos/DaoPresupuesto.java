/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloPresupuesto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RAscencio
 */
public class DaoPresupuesto 
{
    
  public static int IncrementarCodigoCabecera()
  {
    String sql = "SELECT MAX (fc_codigo) AS fc_codigo FROM presupuesto_cabecera";
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
    String sql = "SELECT MAX (fd_codigo) AS fd_codigo FROM presupuesto_detalle";
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
  
  public static void GuardarPresupuestoDetalle(ModeloPresupuesto insertar)
  {
    String sql = "INSERT INTO presupuesto_detalle (fd_codigo, fd_cod_producto,fd_cantidad, fd_punitario,fd_subtotal,fd_cod_cabecera) VALUES ('" + 
    
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
  
  public static void GuardarPresupuestoCabecera(ModeloPresupuesto insertar)
  {
    String sql = "INSERT INTO presupuesto_cabecera (fc_codigo, fc_cod_cliente, fc_fecha, fc_total, fc_estado, fc_hora, fc_cod_vendedor) VALUES('" + 
    
      insertar.getCodigoCabecera() + "','" + insertar.getCodigoCliente() + "'," + 
      "'" + insertar.getFecha() + "', " + 
      "'" + insertar.getTotal() + "', " + 
      "'" + insertar.getEstado() + "', " + 
      "'" + insertar.getHora() + "'," + 
      "'" + insertar.getVendedor().getCodigo() + "')";
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
  
  public static List<ModeloPresupuesto> consultarPresupuestoCabecera()
  {
    String sql = "SELECT * FROM presupuesto_cabecera INNER JOIN clientes ON fc_cod_cliente = cliente_codigo";
    ModeloPresupuesto cobranza = null;
    List<ModeloPresupuesto> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        cobranza = new ModeloPresupuesto();
        
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
  
  public static List<ModeloPresupuesto> consultarDetallePresupuesto(int parametro)
  {
    String sql = "SELECT * FROM presupuesto_detalle INNER JOIN productos ON fd_cod_producto = producto_codigo WHERE fd_cod_cabecera='" + parametro + "';";
    ModeloPresupuesto cobranza = null;
    List<ModeloPresupuesto> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        cobranza = new ModeloPresupuesto();
        
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
  
  public static List<ModeloPresupuesto> ConsultarPorNPresupuesto(int codigo)
  {
    String sql = "SELECT * FROM presupuesto_cabecera INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE fc_codigo='" + codigo + "'";
    ModeloPresupuesto contado = null;
    List<ModeloPresupuesto> lista = new ArrayList();
    
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        contado = new ModeloPresupuesto();
        
        contado.setCodigoCabecera(rs.getInt("fc_codigo"));
        contado.getCliente().setCedula(rs.getString("cliente_cedula"));
        contado.getCliente().setNombre(rs.getString("cliente_nombre"));
        contado.setFecha(rs.getString("fc_fecha"));
        contado.setHora(rs.getString("fc_hora"));
        contado.setTotal(rs.getInt("fc_total"));
        
        lista.add(contado);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return lista;
  }
  
  public static List<ModeloPresupuesto> ConsultarPorCedula(String codigo)
  {
    String sql = "SELECT * FROM presupuesto_cabecera INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE cliente_cedula  ILIKE ?";
    ModeloPresupuesto contado = null;
    List<ModeloPresupuesto> lista = new ArrayList();
    
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, codigo + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        contado = new ModeloPresupuesto();
        
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
  
  public static List<ModeloPresupuesto> ConsultarPorNombre(String codigo)
  {
    String sql = "SELECT * FROM presupuesto_cabecera INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE cliente_nombre  ILIKE ?";
    ModeloPresupuesto contado = null;
    List<ModeloPresupuesto> lista = new ArrayList();
    
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, codigo + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        contado = new ModeloPresupuesto();
        
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

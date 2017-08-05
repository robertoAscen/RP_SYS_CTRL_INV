/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloFacturaContado;
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
public class DaoFacturaContado 
{
    
  public static int IncrementarCodigoCabecera()
  {
    String sql = "SELECT MAX (fc_codigo) AS fc_codigo FROM factura_cabecera";
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
  
  public static void GuardarFacturaDetalle(ModeloFacturaContado insertar)
  {
    String sql = "INSERT INTO factura_detalle (fd_codigo, fd_um,fd_cod_producto,fd_cantidad, fd_punitario,fd_subtotal,fd_cod_cabecera, fd_iva) VALUES (default,'" + 
    
      insertar.getUnidadDeMedida() + "','" + insertar.getCodigoProducto() + "','" + insertar.getCantidad() + "', " + 
      "'" + insertar.getPrecioUnitario() + "', " + 
      "'" + insertar.getSubtotal() + "', " + 
      "'" + insertar.getCodigoCabecera() + "','" + insertar.getIva() + "')";
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
  
  public static void GuardarFacturaCabecera(ModeloFacturaContado insertar)
  {
    String sql = "INSERT INTO factura_cabecera (fc_codigo, fc_cod_cliente, fc_fecha, fc_total, fc_estado, fc_hora, fc_cod_vendedor, fc_tiva1, fc_tiva2, fc_descuento, fc_monto_descuento) VALUES('" + 
    
      insertar.getCodigoCabecera() + "','" + insertar.getCodigoCliente() + "'," + 
      "'" + insertar.getFecha() + "', " + 
      "'" + insertar.getTotal() + "', " + 
      "'" + insertar.getEstado() + "', " + 
      "'" + insertar.getHora() + "'," + 
      "'" + insertar.getVendedor().getCodigo() + "','" + 
      insertar.getTiva1() + "','" + insertar.getTiva2() + "','" + insertar.getDescuento() + "','" + insertar.getMontoDescuento() + "')";
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
  
  public static void ActTotCabEnCasoDeStockInsuf(ModeloFacturaContado update)
  {
    String sql = "UPDATE factura_cabecera SET fc_total='" + 
      update.getTotal() + "'" + 
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
  
  public static void ActTotIvaEnCasoDeStockInsuf(ModeloFacturaContado update)
  {
    String sql = "UPDATE factura_cabecera SET fc_iva_total='" + 
      update.getTotalIva() + "'" + 
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
  
  public static List<ModeloFacturaContado> consultarFacturaCabecera()
  {
    String sql = "SELECT * FROM factura_cabecera INNER JOIN clientes ON fc_cod_cliente = cliente_codigo";
    ModeloFacturaContado contado = null;
    List<ModeloFacturaContado> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        contado = new ModeloFacturaContado();
        
        contado.setCodigoCabecera(rs.getInt("fc_codigo"));
        contado.getCliente().setCedula(rs.getString("cliente_cedula"));
        contado.getCliente().setNombre(rs.getString("cliente_nombre"));
        contado.setFecha(rs.getString("fc_fecha"));
        contado.setHora(rs.getString("fc_hora"));
        contado.setEstado(rs.getString("fc_estado"));
        contado.setTotal(rs.getDouble("fc_total"));
        contado.setTiva1(rs.getDouble("fc_tiva1"));
        contado.setTiva2(rs.getDouble("fc_tiva2"));
        
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
  
  public static ModeloFacturaContado consultarDatosCabeceraPrint(int codigo)
  {
    String sql = "SELECT * FROM factura_cabecera INNER JOIN clientes ON fc_cod_cliente = cliente_codigo INNER JOIN vendedores ON fc_cod_vendedor = vend_codigo WHERE fc_codigo ='" + codigo + "'";
    ModeloFacturaContado contado = null;
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        contado = new ModeloFacturaContado();
        
        contado.setCodigoCabecera(rs.getInt("fc_codigo"));
        contado.getCliente().setCedula(rs.getString("cliente_cedula"));
        contado.getCliente().setNombre(rs.getString("cliente_nombre"));
        contado.getCliente().setContacto(rs.getString("cliente_contacto"));
        contado.getCliente().setDireccion(rs.getString("cliente_direccion"));
        contado.setFecha(rs.getString("fc_fecha"));
        contado.setHora(rs.getString("fc_hora"));
        contado.setEstado(rs.getString("fc_estado"));
        contado.setTotal(rs.getDouble("fc_total"));
        contado.setTiva1(rs.getDouble("fc_tiva1"));
        contado.setTiva2(rs.getDouble("fc_tiva2"));
        contado.getVendedor().setCodigo(rs.getInt("fc_cod_vendedor"));
        contado.getVendedor().setNombre(rs.getString("vend_nombre"));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    BasedeDatos.desconectar();
    return contado;
  }
  
  public static List<ModeloFacturaContado> consultarDetalleDeLaFactura(int parametro)
  {
    String sql = "SELECT * FROM factura_detalle INNER JOIN productos ON fd_cod_producto = producto_codigo WHERE fd_cod_cabecera='" + parametro + "';";
    ModeloFacturaContado cobranza = null;
    List<ModeloFacturaContado> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        cobranza = new ModeloFacturaContado();
        
        cobranza.setCodigoDetalle(rs.getInt("fd_codigo"));
        cobranza.getProducto().setCodigo(rs.getString("producto_codigo"));
        cobranza.setDescripcionProd(rs.getString("producto_descripcion"));
        cobranza.setCantidad(rs.getDouble("fd_cantidad"));
        cobranza.setUnidadDeMedida(rs.getString("producto_unidad_medida"));
        cobranza.setPrecioUnitario(rs.getDouble("fd_punitario"));
        cobranza.setSubtotal(rs.getDouble("fd_subtotal"));
        cobranza.setIva(rs.getString("fd_iva"));
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
  
  public static List<ModeloFacturaContado> ConsultarPorNFactura(int codigo)
  {
    String sql = "SELECT * FROM factura_cabecera INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE fc_codigo='" + codigo + "'";
    ModeloFacturaContado contado = null;
    List<ModeloFacturaContado> lista = new ArrayList();
    
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        contado = new ModeloFacturaContado();
        
        contado.setCodigoCabecera(rs.getInt("fc_codigo"));
        contado.getCliente().setCedula(rs.getString("cliente_cedula"));
        contado.getCliente().setNombre(rs.getString("cliente_nombre"));
        contado.setFecha(rs.getString("fc_fecha"));
        contado.setHora(rs.getString("fc_hora"));
        contado.setTotal(rs.getInt("fc_total"));
        contado.setTotalIva(rs.getDouble("fc_iva_total"));
        
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
  
  public static void AnularFactura(ModeloFacturaContado update)
  {
    String sql = "UPDATE factura_cabecera SET fc_estado='" + 
    
      update.getEstado() + "'" + 
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
  
  public static void EliminarDetalles(int codigo)
  {
    String sql = "DELETE FROM factura_detalle WHERE fd_codigo=" + codigo + " ";
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
  
  public static List<ModeloFacturaContado> ConsultarPorCedula(String codigo)
  {
    String sql = "SELECT * FROM factura_cabecera INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE cliente_cedula  ILIKE ?";
    ModeloFacturaContado contado = null;
    List<ModeloFacturaContado> lista = new ArrayList();
    
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, codigo + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        contado = new ModeloFacturaContado();
        
        contado.setCodigoCabecera(rs.getInt("fc_codigo"));
        contado.getCliente().setCedula(rs.getString("cliente_cedula"));
        contado.getCliente().setNombre(rs.getString("cliente_nombre"));
        contado.setFecha(rs.getString("fc_fecha"));
        contado.setHora(rs.getString("fc_hora"));
        contado.setTotal(rs.getInt("fc_total"));
        contado.setEstado(rs.getString("fc_estado"));
        contado.setTotalIva(rs.getDouble("fc_iva_total"));
        
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
  
  public static List<ModeloFacturaContado> ConsultarPorNombre(String codigo)
  {
    String sql = "SELECT * FROM factura_cabecera INNER JOIN clientes ON fc_cod_cliente = cliente_codigo WHERE cliente_nombre  ILIKE ?";
    ModeloFacturaContado contado = null;
    List<ModeloFacturaContado> lista = new ArrayList();
    
    BasedeDatos.Conectar();
    try
    {
      PreparedStatement pstm = BasedeDatos.conexion.prepareStatement(sql);
      pstm.setString(1, codigo + "%");
      ResultSet rs = pstm.executeQuery();
      while (rs.next())
      {
        contado = new ModeloFacturaContado();
        
        contado.setCodigoCabecera(rs.getInt("fc_codigo"));
        contado.getCliente().setCedula(rs.getString("cliente_cedula"));
        contado.getCliente().setNombre(rs.getString("cliente_nombre"));
        contado.setFecha(rs.getString("fc_fecha"));
        contado.setHora(rs.getString("fc_hora"));
        contado.setTotal(rs.getInt("fc_total"));
        contado.setEstado(rs.getString("fc_estado"));
        contado.setTotalIva(rs.getDouble("fc_iva_total"));
        
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

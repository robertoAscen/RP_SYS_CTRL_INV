/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Objetos;

import com.eneri.scstock.Conector.BasedeDatos;
import com.eneri.scstock.Modelos.ModeloFacturaContado;
import com.eneri.scstock.Modelos.ModeloFacturaCredito;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RAscencio
 */
public class DaoPagos 
{
    
  public static List<ModeloFacturaContado> consultarFacturaCabeceraContado(String desde, String hasta, int codigoVendedor)
  {
    String sql = "SELECT * FROM factura_cabecera INNER JOIN vendedores ON fc_cod_vendedor = vend_codigo WHERE fc_fecha BETWEEN '" + 
      desde + "' AND '" + hasta + "' AND fc_total>0 AND vend_codigo = '" + codigoVendedor + "';";
    ModeloFacturaContado cobranza = null;
    List<ModeloFacturaContado> lista = new ArrayList();
    BasedeDatos.Conectar();
    try
    {
      ResultSet rs = BasedeDatos.stm.executeQuery(sql);
      while (rs.next())
      {
        cobranza = new ModeloFacturaContado();
        cobranza.setCodigoCabecera(rs.getInt("fc_codigo"));
        cobranza.setFecha(rs.getString("fc_fecha"));
        cobranza.setHora(rs.getString("fc_hora"));
        cobranza.setTotal(rs.getInt("fc_total"));
        cobranza.getVendedor().setNombre(rs.getString("vend_nombre"));
        
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
  
  public static List<ModeloFacturaCredito> consultarFacturaCabeceraCredito(String desde, String hasta, int codigoVendedor)
  {
    String sql = "SELECT * FROM factura_cabcred INNER JOIN vendedores ON fc_cod_vendedor = vend_codigo WHERE fc_fecha BETWEEN '" + 
      desde + "' AND '" + hasta + "' AND fc_total>0 AND vend_codigo = '" + codigoVendedor + "';";
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
        cobranza.setFecha(rs.getString("fc_fecha"));
        cobranza.setHora(rs.getString("fc_hora"));
        cobranza.setTotal(rs.getInt("fc_total"));
        cobranza.getVendedor().setNombre(rs.getString("vend_nombre"));
        
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
}

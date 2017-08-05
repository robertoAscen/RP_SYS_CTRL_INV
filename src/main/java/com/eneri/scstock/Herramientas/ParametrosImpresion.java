/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Herramientas;

import com.eneri.scstock.jasper.ConfiguracaoReport;
import com.eneri.scstock.jasper.SwingExporterService;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author RAscencio
 */
public class ParametrosImpresion
{
    public static void impresionReporte(ArrayList<?> nombreLista, Map<String, String> parametros, String nombreReporte)
  {
    DataSource ds = new DataSource();
    try
    {
      ds.setLista(nombreLista);
      ConfiguracaoReport cf = new ConfiguracaoReport();
      cf.setRepositorioName(new File("data/reportes").getAbsolutePath());
      cf.setReportName(nombreReporte);
      cf.setParametros(parametros);
      cf.setBeanDataSource(ds);
      SwingExporterService.generateReport(cf, false);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, e);
    }
  }    
}

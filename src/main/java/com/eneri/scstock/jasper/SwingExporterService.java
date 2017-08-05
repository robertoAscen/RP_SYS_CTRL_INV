/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.jasper;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author RAscencio
 */
public class SwingExporterService 
{
    public static void generateReport(ConfiguracaoReport configuracaoReport, boolean vistaPrevia)
    throws Exception
  {
    if (configuracaoReport == null) {
      throw new Exception("Clase Configuraci�n Report no creada");
    }
    JasperPrint printer = null;
    if (configuracaoReport.getBeanDataSource() != null)
    {
      printer = JasperFillManager.fillReport(configuracaoReport.obtemReportPath(), configuracaoReport.getParametros(), configuracaoReport.getBeanDataSource());
      JasperViewer.viewReport(printer, vistaPrevia);
    }
  }    
}

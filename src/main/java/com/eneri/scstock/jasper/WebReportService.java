/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.jasper;

import com.eneri.scstock.util.ServiceLocator;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

/**
 *
 * @author RAscencio
 */
public class WebReportService 
{
    
  public static void generateReport(ConfiguracaoReport configuracaoReport, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    if (configuracaoReport == null) {
      throw new Exception("Clase Configuraci�n Report no creada");
    }
    JasperPrint printer = null;
    if (configuracaoReport.getBeanDataSource() != null)
    {
      printer = JasperFillManager.fillReport(configuracaoReport.obtemReportPath(), configuracaoReport.getParametros(), configuracaoReport.getBeanDataSource());
      
      PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
      configuracaoReport.obtemReportExporter().setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, defaultPrintService);
      
      generate(configuracaoReport, printer, request, response);
      return;
    }
    if ((configuracaoReport.getJndiDataSource() == null) || (configuracaoReport.getJndiDataSource().trim().length() <= 0)) {
      throw new Exception("Favor el Connection Pool caso el report use 'SQL' directo");
    }
    Connection conn = null;
    ServiceLocator service = ServiceLocator.getInstance();
    DataSource ds = service.getDataSource(configuracaoReport.getJndiDataSource());
    conn = ds.getConnection();
    printer = JasperFillManager.fillReport(configuracaoReport.obtemReportPath(), configuracaoReport.getParametros(), conn);
    generate(configuracaoReport, printer, request, response);
    try
    {
      if ((conn != null) && (!conn.isClosed())) {
        conn.close();
      }
    }
    catch (Exception e)
    {
      throw new Exception("asfsafasf");
    }
    printer = JasperFillManager.fillReport(configuracaoReport.obtemReportPath(), configuracaoReport.getParametros());
    generate(configuracaoReport, printer, request, response);
  }
  
  private static void generate(ConfiguracaoReport configuracaoReport, JasperPrint printer, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    if (!configuracaoReport.isShowReportOnBrowser())
    {
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      String fileName = configuracaoReport.getReportName() + "_" + sdf.format(new Date());
      response.setHeader("Content-disposition", "attachment; filename=" + fileName + "." + configuracaoReport.getFormato());
    }
    JRExporter exporter = configuracaoReport.obtemReportExporter();
    if ((exporter instanceof JRXlsExporter)) {
      exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
    }
    exporter.setParameter(JRExporterParameter.JASPER_PRINT, printer);
    exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "utf-8");
    if ((exporter instanceof JRHtmlExporter))
    {
      request.getSession().setAttribute("net.sf.jasperreports.j2ee.jasper_print", printer);
      exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());
      exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/servlets/image?image=");
      exporter.exportReport();
      return;
    }
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
    exporter.exportReport();
    response.setBufferSize(8000);
    response.setContentLength(baos.size());
    response.setContentType(configuracaoReport.obtemContentType());
    response.getOutputStream().write(baos.toByteArray());
    
    baos.flush();
    baos.close();
  }    
}

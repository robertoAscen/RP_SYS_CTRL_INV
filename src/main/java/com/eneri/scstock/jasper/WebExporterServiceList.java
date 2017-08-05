/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.jasper;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
/**
 *
 * @author RAscencio
 */
public class WebExporterServiceList implements Serializable
{
    
  public static void exportToPdfAndShowOnBrowser(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    generateReport(filePath, parametersReport, lData, ON_BROWSER, TYPE_PDF, fileName, new JRPdfExporter(), request, response);
  }
  
  public static void exportToPdfAndShowSaveFile(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    generateReport(filePath, parametersReport, lData, SAVE_IN_FILE, TYPE_PDF, fileName, new JRPdfExporter(), request, response);
  }
  
  public static void exportToCsvAndShowOnBrowser(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    generateReport(filePath, parametersReport, lData, ON_BROWSER, TYPE_CSV, fileName, new JRCsvExporter(), request, response);
  }
  
  public static void exportToCsvAndShowSaveFile(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    generateReport(filePath, parametersReport, lData, SAVE_IN_FILE, TYPE_CSV, fileName, new JRCsvExporter(), request, response);
  }
  
  public static void exportToXlsAndShowOnBrowser(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    generateReport(filePath, parametersReport, lData, ON_BROWSER, TYPE_XLS, fileName, new JRXlsExporter(), request, response);
  }
  
  public static void exportToXlsAndShowSaveFile(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    generateReport(filePath, parametersReport, lData, SAVE_IN_FILE, TYPE_XLS, fileName, new JRXlsExporter(), request, response);
  }
  
  public static void exportToDocAndShowOnBrowser(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    generateReport(filePath, parametersReport, lData, ON_BROWSER, TYPE_DOC, fileName, new JRRtfExporter(), request, response);
  }
  
  public static void exportToDocAndShowSaveFile(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    generateReport(filePath, parametersReport, lData, SAVE_IN_FILE, TYPE_DOC, fileName, new JRRtfExporter(), request, response);
  }
  
  public static void exportToXmlAndShowOnBrowser(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    generateReport(filePath, parametersReport, lData, ON_BROWSER, TYPE_XML, fileName, new JRXmlExporter(), request, response);
  }
  
  public static void exportToXmlAndShowSaveFile(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    generateReport(filePath, parametersReport, lData, SAVE_IN_FILE, TYPE_XML, fileName, new JRXmlExporter(), request, response);
  }
  
  public static void exportToHtmlAndShowOnBrowser(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    generateReport(filePath, parametersReport, lData, ON_BROWSER, TYPE_HTML, fileName, new JRHtmlExporter(), request, response);
  }
  
  public static void exportToHtmlAndShowSaveFile(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    generateReport(filePath, parametersReport, lData, SAVE_IN_FILE, TYPE_HTML, fileName, new JRHtmlExporter(), request, response);
  }
  
  private static void generateReport(String filePath, Map parametersReport, List lData, String visualize, String fileType, String fileName, JRExporter exporter, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    JasperPrint printer = null;
    JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(lData);
    printer = JasperFillManager.fillReport(filePath, parametersReport, jr);
    if (visualize.equals(SAVE_IN_FILE)) {
      response.setHeader("Content-disposition", "attachment; filename=" + fileName + "." + fileType);
    }
    if ((exporter instanceof JRXlsExporter)) {
      exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
    }
    exporter.setParameter(JRExporterParameter.JASPER_PRINT, printer);
    exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "iso-8859-1");
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
    response.setContentType(getContentType(fileType));
    response.getOutputStream().write(baos.toByteArray());
    baos.flush();
    baos.close();
  }
  
  private static String getContentType(String fileType)
  {
    if (fileType.equals(TYPE_PDF)) {
      return "application/pdf";
    }
    if (fileType.equals(TYPE_XLS)) {
      return "application/vnd.ms-excel";
    }
    if (fileType.equals(TYPE_DOC)) {
      return "application/msword";
    }
    if (fileType.equals(TYPE_CSV)) {
      return "text/html";
    }
    if (fileType.equals(TYPE_XML)) {
      return "text/html";
    }
    if (fileType.equals(TYPE_HTML)) {
      return "text/html";
    }
    return "text/html";
  }
  
  private static boolean canShowOnBrowser(String fileType, HttpServletRequest request)
  {
    boolean isIE = true;
    isIE = request.getHeader("User-Agent").toLowerCase().indexOf("msie") != -1;
    if ((fileType.equalsIgnoreCase(TYPE_DOC)) && (!isIE)) {
      return false;
    }
    return (!fileType.equalsIgnoreCase(TYPE_XLS)) || (isIE);
  }
  
  public static String TYPE_PDF = "pdf";
  public static String TYPE_XLS = "xls";
  public static String TYPE_CSV = "csv";
  public static String TYPE_DOC = "doc";
  public static String TYPE_XML = "xml";
  public static String TYPE_HTML = "html";
  private static String ON_BROWSER = "showOnBrowser";
  private static String SAVE_IN_FILE = "saveInFile";    
}

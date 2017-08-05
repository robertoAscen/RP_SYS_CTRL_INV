/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.jasper;

import com.eneri.scstock.util.ServiceLocator;
import java.io.Serializable;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author RAscencio
 */
public class ReportService implements Serializable
{
    
  private String typeOutReport;
  private String visualizeOn;
  
  public void generateReport(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    if (getVisualizeOn().equals("browser"))
    {
      generateReportOnBrowser(filePath, parametersReport, lData, fileName, request, response);
      return;
    }
    if (getVisualizeOn().equals("file")) {
      generateReportOnFile(filePath, parametersReport, lData, fileName, request, response);
    }
  }
  
  public void generateReport(String filePath, Map parametersReport, String dsPool, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ServiceLocator service = ServiceLocator.getInstance();
    DataSource ds = service.getDataSource(dsPool);
    Connection conn = ds.getConnection();
    if (getVisualizeOn().equals("browser")) {
      generateReportOnBrowser(filePath, parametersReport, conn, fileName, request, response);
    } else if (getVisualizeOn().equals("file")) {
      generateReportOnFile(filePath, parametersReport, conn, fileName, request, response);
    }
    conn.close();
  }
  
  private void generateReportOnBrowser(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    fileName = fileName + "_" + dateFormat.format(new Date());
    if (getTypeOutReport().equals(WebExporterServiceList.TYPE_CSV))
    {
      WebExporterServiceList.exportToCsvAndShowOnBrowser(filePath, parametersReport, lData, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceList.TYPE_DOC))
    {
      WebExporterServiceList.exportToDocAndShowOnBrowser(filePath, parametersReport, lData, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceList.TYPE_HTML))
    {
      WebExporterServiceList.exportToHtmlAndShowOnBrowser(filePath, parametersReport, lData, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceList.TYPE_PDF))
    {
      WebExporterServiceList.exportToPdfAndShowOnBrowser(filePath, parametersReport, lData, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceList.TYPE_XLS))
    {
      WebExporterServiceList.exportToXlsAndShowOnBrowser(filePath, parametersReport, lData, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceList.TYPE_XML)) {
      WebExporterServiceList.exportToXmlAndShowOnBrowser(filePath, parametersReport, lData, fileName, request, response);
    }
  }
  
  private void generateReportOnFile(String filePath, Map parametersReport, List lData, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    fileName = fileName + " " + dateFormat.format(new Date());
    if (getTypeOutReport().equals(WebExporterServiceList.TYPE_CSV))
    {
      WebExporterServiceList.exportToCsvAndShowSaveFile(filePath, parametersReport, lData, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceList.TYPE_DOC))
    {
      WebExporterServiceList.exportToDocAndShowSaveFile(filePath, parametersReport, lData, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceList.TYPE_HTML))
    {
      WebExporterServiceList.exportToHtmlAndShowSaveFile(filePath, parametersReport, lData, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceList.TYPE_PDF))
    {
      WebExporterServiceList.exportToPdfAndShowSaveFile(filePath, parametersReport, lData, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceList.TYPE_XLS))
    {
      WebExporterServiceList.exportToXlsAndShowSaveFile(filePath, parametersReport, lData, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceList.TYPE_XML)) {
      WebExporterServiceList.exportToXmlAndShowSaveFile(filePath, parametersReport, lData, fileName, request, response);
    }
  }
  
  private void generateReportOnBrowser(String filePath, Map parametersReport, Connection conn, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    fileName = fileName + "_" + dateFormat.format(new Date());
    if (getTypeOutReport().equals(WebExporterServiceConnection.TYPE_CSV))
    {
      WebExporterServiceConnection.exportToCsvAndShowOnBrowser(filePath, parametersReport, conn, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceConnection.TYPE_DOC))
    {
      WebExporterServiceConnection.exportToDocAndShowOnBrowser(filePath, parametersReport, conn, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceConnection.TYPE_HTML))
    {
      WebExporterServiceConnection.exportToHtmlAndShowOnBrowser(filePath, parametersReport, conn, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceConnection.TYPE_PDF))
    {
      WebExporterServiceConnection.exportToPdfAndShowOnBrowser(filePath, parametersReport, conn, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceConnection.TYPE_XLS))
    {
      WebExporterServiceConnection.exportToXlsAndShowOnBrowser(filePath, parametersReport, conn, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceConnection.TYPE_XML)) {
      WebExporterServiceConnection.exportToXmlAndShowOnBrowser(filePath, parametersReport, conn, fileName, request, response);
    }
  }
  
  private void generateReportOnFile(String filePath, Map parametersReport, Connection conn, String fileName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    fileName = fileName + "_" + dateFormat.format(new Date());
    if (getTypeOutReport().equals(WebExporterServiceConnection.TYPE_CSV))
    {
      WebExporterServiceConnection.exportToCsvAndShowSaveFile(filePath, parametersReport, conn, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceConnection.TYPE_DOC))
    {
      WebExporterServiceConnection.exportToDocAndShowSaveFile(filePath, parametersReport, conn, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceConnection.TYPE_HTML))
    {
      WebExporterServiceConnection.exportToHtmlAndShowSaveFile(filePath, parametersReport, conn, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceConnection.TYPE_PDF))
    {
      WebExporterServiceConnection.exportToPdfAndShowSaveFile(filePath, parametersReport, conn, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceConnection.TYPE_XLS))
    {
      WebExporterServiceConnection.exportToXlsAndShowSaveFile(filePath, parametersReport, conn, fileName, request, response);
      return;
    }
    if (getTypeOutReport().equals(WebExporterServiceConnection.TYPE_XML)) {
      WebExporterServiceConnection.exportToXmlAndShowSaveFile(filePath, parametersReport, conn, fileName, request, response);
    }
  }
  
  public String getTypeOutReport()
  {
    return this.typeOutReport;
  }
  
  public void setTypeOutReport(String typeOutReport)
  {
    this.typeOutReport = typeOutReport;
  }
  
  public String getVisualizeOn()
  {
    return this.visualizeOn;
  }
  
  public void setVisualizeOn(String visualizeOn)
  {
    this.visualizeOn = visualizeOn;
  }    
}

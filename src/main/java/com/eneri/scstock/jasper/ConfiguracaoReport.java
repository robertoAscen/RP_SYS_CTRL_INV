/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.jasper;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
/**
 *
 * @author RAscencio
 */
public class ConfiguracaoReport implements Serializable
{
    
  private static final String TYPE_PDF = "pdf";
  private static final String TYPE_XLS = "xls";
  private static final String TYPE_CSV = "csv";
  
  public ConfiguracaoReport()
  {
    this.parametros = new HashMap();
    this.formato = "pdf";
    this.showReportOnBrowser = true;
  }
  
  public void setReportName(String reportName)
  {
    this.reportName = reportName;
  }
  
  public String getReportName()
  {
    return this.reportName;
  }
  
  public void setRepositorioName(String repositorioName)
  {
    this.repositorioName = repositorioName;
  }
  
  public String getRepositorioName()
  {
    return this.repositorioName;
  }
  
  public void setJndiDataSource(String jndiDataSource)
  {
    this.jndiDataSource = jndiDataSource;
  }
  
  public String getJndiDataSource()
  {
    return this.jndiDataSource;
  }
  
  public void setBeanDataSource(JRDataSource dataSource)
  {
    this.beanDataSource = dataSource;
  }
  
  public JRDataSource getBeanDataSource()
  {
    return this.beanDataSource;
  }
  
  public void setBeanDataSource(List dataSource)
  {
    JRBeanCollectionDataSource aux = new JRBeanCollectionDataSource(
      dataSource);
    setBeanDataSource(aux);
  }
  
  public void setParametros(Map parametros)
  {
    this.parametros = parametros;
  }
  
  public Map getParametros()
  {
    return this.parametros;
  }
  
  public void setFormato(String formato)
  {
    this.formato = formato;
  }
  
  public String getFormato()
  {
    return this.formato;
  }
  
  public void setLanguage(String language)
  {
    this.language = language;
  }
  
  public String getLanguage()
  {
    return this.language;
  }
  
  public void setShowReportOnBrowser(boolean showReportOnBrowser)
  {
    this.showReportOnBrowser = showReportOnBrowser;
  }
  
  public boolean isShowReportOnBrowser()
  {
    if (("xls".equalsIgnoreCase(getFormato())) || 
      (TYPE_DOC.equalsIgnoreCase(getFormato()))) {
      return false;
    }
    return this.showReportOnBrowser;
  }
  
  public String obtemReportPath()
  {
    StringBuffer sb = new StringBuffer();
    sb.append(obterRepositorioReport()).append(File.separator).append(
      getReportName());
    if ((getLanguage() != null) && (getLanguage().trim().length() > 0)) {
      sb.append("_").append(getLanguage());
    }
    sb.append(".jasper");
    return sb.toString();
  }
  
  private String obterRepositorioReport()
  {
    String aux = getRepositorioName();
    if ((aux != null) && (aux.trim().length() > 0))
    {
      int j = 0;
      StringBuffer sb = new StringBuffer();
      for (int i = 1; i <= aux.length(); i++)
      {
        j = i - 1;
        String letra = aux.substring(j, i);
        if (letra.equals("/")) {
          letra = File.separator;
        }
        sb.append(letra);
      }
      return sb.toString();
    }
    return null;
  }
  
  public JRExporter obtemReportExporter()
  {
    if ("pdf".equalsIgnoreCase(getFormato())) {
      return new JRPdfExporter();
    }
    if ("csv".equalsIgnoreCase(getFormato())) {
      return new JRCsvExporter();
    }
    if ("xls".equalsIgnoreCase(getFormato())) {
      return new JRXlsExporter();
    }
    if (TYPE_DOC.equalsIgnoreCase(getFormato())) {
      return new JRRtfExporter();
    }
    if (TYPE_XML.equalsIgnoreCase(getFormato())) {
      return new JRXmlExporter();
    }
    if (TYPE_HTML.equalsIgnoreCase(getFormato())) {
      return new JRHtmlExporter();
    }
    return null;
  }
  
  public String obtemContentType()
  {
    if ("pdf".equalsIgnoreCase(getFormato())) {
      return "application/pdf";
    }
    if ("csv".equalsIgnoreCase(getFormato())) {
      return "text/html";
    }
    if ("xls".equalsIgnoreCase(getFormato())) {
      return CONTENT_TYPE_XLS;
    }
    if (TYPE_DOC.equalsIgnoreCase(getFormato())) {
      return CONTENT_TYPE_DOC;
    }
    if (TYPE_XML.equalsIgnoreCase(getFormato())) {
      return "text/html";
    }
    if (TYPE_HTML.equalsIgnoreCase(getFormato())) {
      return "text/html";
    }
    return null;
  }
  
  private static String TYPE_DOC = "doc";
  private static String TYPE_XML = "xml";
  private static String TYPE_HTML = "html";
  private static final String CONTENT_TYPE_PDF = "application/pdf";
  private static final String CONTENT_TYPE_HTML = "text/html";
  private static String CONTENT_TYPE_DOC = "application/msword";
  private static String CONTENT_TYPE_XLS = "application/vnd.ms-excel";
  private static final String JASPER_EXTENSION = ".jasper";
  private String reportName;
  private String repositorioName;
  private String jndiDataSource;
  private JRDataSource beanDataSource;
  private Map parametros;
  private String formato;
  private String language;
  private boolean showReportOnBrowser;    
}

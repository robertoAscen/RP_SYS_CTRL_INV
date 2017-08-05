/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.FacturaContado;

import com.eneri.scstock.Gestor.Gestionar;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

/**
 *
 * @author RAscencio
 */
public class MainClass 
{
    static Gestionar gestion = new Gestionar();
  public static String factura = gestion.AbrirTexto(new File("impresion.txt"));
  
  public static void imprimirCocina(String textoAImprimir)
  {
    agregarLogoATicket(textoAImprimir);
    PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
    
    byte[] bytes = textoAImprimir.getBytes();
    
    DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
    
    Doc doc = new SimpleDoc(bytes, flavor, null);
    
    DocPrintJob job = null;
    if (services.length > 0)
    {
      for (int i = 0; i < services.length; i++) {
        if (services[i].getName().equals("cocina")) {
          job = services[i].createPrintJob();
        } else {
          System.out.println("No se encontr� la impresora cocina");
        }
      }
      PrinterJob pj = PrinterJob.getPrinterJob();
      
      PageFormat mPageFormat = pj.defaultPage();
      if (pj.printDialog()) {
        try
        {
          pj.print();
        }
        catch (PrinterException localPrinterException) {}
      }
    }
  }
  
  public static void imprimirCaja(String textoAImprimir)
  {
    agregarLogoATicket(textoAImprimir);
    PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
    
    byte[] bytes = textoAImprimir.getBytes();
    
    DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
    
    Doc doc = new SimpleDoc(bytes, flavor, null);
    
    DocPrintJob job = null;
    if (services.length > 0)
    {
      PrintService[] arrayOfPrintService1;
      int j = (arrayOfPrintService1 = services).length;
      for (int i = 0; i < j; i++)
      {
        PrintService service = arrayOfPrintService1[i];
        if (service.getName().equals("HP Photosmart D110 series")) {
          job = service.createPrintJob();
        }
      }
    }
    try
    {
      job.print(doc, null);
    }
    catch (PrintException ex)
    {
      System.out.println(ex);
    }
  }
  
  public static void imprimirGenerico(String contentTicket)
  {
    PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
    
    byte[] bytes = contentTicket.getBytes();
    
    DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
    Doc doc = new SimpleDoc(bytes, flavor, null);
    DocPrintJob job = null;
    if (services.length > 0) {
      for (int i = 0; i < services.length; i++) {
        if (services[i].getName().equals("GP-5890X Series"))
        {
          job = services[i].createPrintJob();
          System.out.println(i + ": " + services[i].getName());
        }
      }
    }
    try
    {
      job.print(doc, null);
    }
    catch (PrintException ex)
    {
      System.out.println(ex);
    }
  }
  
  public static void main(String[] args)
  {
    MainClass impresion = new MainClass();
    
    imprimirCaja(factura);
  }
  
  private static String agregarLogoATicket(String textoAImprimir)
  {
    return textoAImprimir;
  }    
}

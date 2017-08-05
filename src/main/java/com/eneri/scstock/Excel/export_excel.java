/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Excel;

import com.eneri.scstock.Objetos.DaoConfiguraciones;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JTable;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 *
 * @author RAscencio
 */
public class export_excel 
{    
    private File archi;
  private List<JTable> tabla;
  private List<String> nom_hoja;
  private WritableCellFormat fomato_fila;
  private WritableCellFormat fomato_columna;
  
  public export_excel(List<JTable> tab, File ar)
    throws Exception
  {
    this.archi = ar;
    this.tabla = tab;
    if (tab.size() < 0) {
      throw new Exception("ERROR");
    }
  }
  
  public boolean export()
  {
    try
    {
      DataOutputStream out = new DataOutputStream(new FileOutputStream(this.archi));
      WritableWorkbook w = Workbook.createWorkbook(out);
      w.createSheet(DaoConfiguraciones.NombreEmpresa(), 0);
      for (int index = 0; index < this.tabla.size(); index++)
      {
        JTable table = (JTable)this.tabla.get(index);
        
        WritableSheet s = w.getSheet(0);
        for (int i = 0; i < table.getColumnCount(); i++) {
          for (int j = 0; j < table.getRowCount(); j++)
          {
            Object objeto = table.getValueAt(j, i);
            
            createColumna(s, table.getColumnName(i), i);
            createFilas(s, i, j + 1, String.valueOf(objeto));
          }
        }
      }
      w.write();
      w.close();
      out.close();
      return true;
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
    catch (WriteException ex)
    {
      ex.printStackTrace();
    }
    return false;
  }
  
  private void createColumna(WritableSheet sheet, String columna, int number_columna)
    throws WriteException
  {
    WritableFont times10pt = new WritableFont(WritableFont.TAHOMA, 14);
    
    WritableCellFormat times = new WritableCellFormat(times10pt);
    
    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TAHOMA, 11, WritableFont.BOLD, false, UnderlineStyle.SINGLE);
    this.fomato_columna = new WritableCellFormat(times10ptBoldUnderline);
    
    CellView cevell = new CellView();
    cevell.setSize(920);
    cevell.setDimension(70);
    cevell.setFormat(times);
    cevell.setFormat(this.fomato_columna);
    
    addColumna(sheet, number_columna, 0, columna, this.fomato_columna);
  }
  
  private void createFilas(WritableSheet sheet, int number_columna, int filas, String name_filas)
    throws WriteException
  {
    WritableFont times10pt = new WritableFont(WritableFont.ARIAL, 12);
    times10pt.setColour(Colour.GOLD);
    
    WritableCellFormat times = new WritableCellFormat(times10pt);
    times.setBorder(Border.TOP, BorderLineStyle.MEDIUM, Colour.GOLD);
    
    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
    this.fomato_fila = new WritableCellFormat(times10ptBoldUnderline);
    
    CellView cevell = new CellView();
    cevell.setDimension(70);
    cevell.setFormat(times);
    cevell.setFormat(this.fomato_fila);
    
    addFilas(sheet, number_columna, filas, name_filas, this.fomato_fila);
  }
  
  private void addColumna(WritableSheet sheet, int column, int row, String s, WritableCellFormat format)
    throws RowsExceededException, WriteException
  {
    Label label = new Label(column, row, s, format);
    sheet.addCell((WritableCell) label);
  }
  
  private void addFilas(WritableSheet sheet, int column, int row, String s, WritableCellFormat format)
    throws WriteException, RowsExceededException
  {
    Label label = new Label(column, row, s, format);
    sheet.addCell((WritableCell) label);
  }
}

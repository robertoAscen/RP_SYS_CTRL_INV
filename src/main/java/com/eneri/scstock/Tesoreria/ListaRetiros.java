/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Tesoreria;

import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Modelos.ModeloRetiro;
import com.eneri.scstock.Objetos.DaoRetiros;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author RAscencio
 */
public class ListaRetiros extends JDialog
{
    
  public static DefaultTableModel modeloRetiro = new DefaultTableModel(null, new String[] { "C�d. retiro", "Fecha", "Hora", "Monto", "Detalle" })
  {
    boolean[] columnEditables = new boolean[5];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JTable tbRetiros;
  private JScrollPane scrollPane;
  public static List<ModeloRetiro> listaRetiros;
  private JTextField tfRetiro;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          ListaRetiros dialog = new ListaRetiros();
          dialog.setDefaultCloseOperation(2);
          dialog.setLocationRelativeTo(null);
          dialog.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  public ListaRetiros()
  {
    setTitle("Lista de retiros realizados en el d�a");
    setResizable(false);
    setModal(true);
    setBounds(100, 100, 550, 301);
    
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(10, 11, 524, 210);
    getContentPane().add(this.scrollPane);
    
    this.tbRetiros = new JTable();
    this.scrollPane.setViewportView(this.tbRetiros);
    this.tbRetiros.setModel(modeloRetiro);
    
    this.tfRetiro = new JTextField();
    this.tfRetiro.setEditable(false);
    this.tfRetiro.setFont(new Font("Tahoma", 0, 16));
    this.tfRetiro.setBounds(330, 232, 204, 27);
    getContentPane().add(this.tfRetiro);
    this.tfRetiro.setColumns(10);
    
    JLabel lblTotal = new JLabel("Total:");
    lblTotal.setForeground(Color.WHITE);
    lblTotal.setBounds(288, 240, 59, 14);
    getContentPane().add(lblTotal);
    this.tbRetiros.getColumnModel().getColumn(0).setPreferredWidth(48);
    this.tbRetiros.getColumnModel().getColumn(1).setPreferredWidth(46);
    this.tbRetiros.getColumnModel().getColumn(2).setPreferredWidth(42);
    this.tbRetiros.getColumnModel().getColumn(3).setPreferredWidth(59);
    this.tbRetiros.getColumnModel().getColumn(4).setPreferredWidth(200);
    this.tbRetiros.getTableHeader().setReorderingAllowed(false);
    
    ConsultarRetiros();
    CalcularTotalRetiro();
  }
  
  public void CalcularTotalRetiro()
  {
    double sumatoria = 0.0D;
    double total = 0.0D;
    int totalRow = this.tbRetiros.getRowCount();
    totalRow--;
    int totalInt = 0;
    for (int i = 0; i <= totalRow; i++)
    {
      if (this.tbRetiros.getValueAt(i, 3).equals("")) {
        sumatoria = 0.0D;
      } else {
        sumatoria = Double.parseDouble(String.valueOf(this.tbRetiros.getValueAt(i, 3)));
      }
      total += sumatoria;
    }
    if (total > 9999999.0D)
    {
      totalInt = (int)total;
      String totalObtenido = String.valueOf(totalInt);
      
      this.tfRetiro.setText(totalObtenido);
    }
    else
    {
      String totalObtenido = String.valueOf(total);
      
      this.tfRetiro.setText(totalObtenido);
    }
  }
  
  public void ConsultarRetiros()
  {
    listaRetiros = new ArrayList();
    listaRetiros = DaoRetiros.ConsultarRetiros(fecha());
    ActualizarTablaRetiro();
  }
  
  private static void ActualizarTablaRetiro()
  {
    while (modeloRetiro.getRowCount() > 0) {
      modeloRetiro.removeRow(0);
    }
    String[] campos = new String[5];
    for (int i = 0; i < listaRetiros.size(); i++)
    {
      modeloRetiro.addRow(campos);
      ModeloRetiro retiro = (ModeloRetiro)listaRetiros.get(i);
      
      modeloRetiro.setValueAt(Integer.valueOf(retiro.getCodigo()), i, 0);
      modeloRetiro.setValueAt(retiro.getFecha(), i, 1);
      modeloRetiro.setValueAt(retiro.getHora(), i, 2);
      modeloRetiro.setValueAt(Double.valueOf(retiro.getMonto()), i, 3);
      modeloRetiro.setValueAt(retiro.getDetalle(), i, 4);
    }
  }
  
  public static String fecha()
  {
    Date fecha = new Date();
    SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
    return fechaFormato.format(fecha);
  }    
}

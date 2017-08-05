/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Informaciones;

import com.eneri.scstock.Modelos.ModeloClientes;
import com.eneri.scstock.Modelos.ModeloInfoCliente;
import com.eneri.scstock.Objetos.DaoInfoClienteAnho;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author RAscencio
 */
public class VentaAnualCliente extends JDialog
{
    
  private JPanel contentPane;
  private JTextField tfnombre;
  private JTextField textField_1;
  private JMonthChooser monthChooser;
  private JTable table;
  public static List<ModeloInfoCliente> lista;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] {
    "C�d. Info.", "N� C.I./RUC", "Nombre del cliente", "A�o", "Total compra" })
  {
    boolean[] columnEditables = new boolean[5];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JYearChooser yearChooser;
  private JButton btnSaludar;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          VentaAnualCliente dialog = new VentaAnualCliente();
          dialog.setLocationRelativeTo(null);
          dialog.setModal(true);
          dialog.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  public VentaAnualCliente()
  {
    setTitle("Informe de ventas anuales por cliente");
    setResizable(false);
    setDefaultCloseOperation(2);
    setBounds(100, 100, 680, 503);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    JLabel lblCliente = new JLabel("Mes:");
    lblCliente.setBounds(10, 101, 46, 20);
    this.contentPane.add(lblCliente);
    
    JLabel label = new JLabel("Cliente:");
    label.setBounds(10, 62, 46, 14);
    this.contentPane.add(label);
    
    JLabel lblInformeDeVentas = new JLabel("Informe de ventas por cliente");
    lblInformeDeVentas.setForeground(Color.WHITE);
    lblInformeDeVentas.setFont(new Font("Times New Roman", 0, 35));
    lblInformeDeVentas.setBounds(125, 11, 549, 43);
    this.contentPane.add(lblInformeDeVentas);
    
    this.tfnombre = new JTextField();
    this.tfnombre.setBounds(61, 59, 86, 20);
    this.contentPane.add(this.tfnombre);
    this.tfnombre.setColumns(10);
    
    this.textField_1 = new JTextField();
    this.textField_1.setColumns(10);
    this.textField_1.setBounds(157, 59, 321, 20);
    this.contentPane.add(this.textField_1);
    
    this.monthChooser = new JMonthChooser();
    this.monthChooser.setBounds(58, 101, 112, 20);
    this.contentPane.add(this.monthChooser);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 146, 654, 318);
    this.contentPane.add(scrollPane);
    
    this.table = new JTable();
    this.table.setModel(modelo);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(60);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(70);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(264);
    this.table.getColumnModel().getColumn(3).setPreferredWidth(52);
    this.table.getColumnModel().getColumn(4).setPreferredWidth(45);
    scrollPane.setViewportView(this.table);
    
    this.btnSaludar = new JButton("Econsultamikatu");
    this.btnSaludar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        VentaAnualCliente.this.consultarPorAnho();
      }
    });
    this.btnSaludar.setBounds(269, 100, 140, 23);
    this.contentPane.add(this.btnSaludar);
    
    this.yearChooser = new JYearChooser();
    this.yearChooser.setBounds(202, 101, 47, 20);
    this.contentPane.add(this.yearChooser);
    
    JLabel lblAo = new JLabel("A�o:");
    lblAo.setBounds(169, 101, 46, 20);
    this.contentPane.add(lblAo);
    consultarTodos();
  }
  
  private void consultarPorAnho()
  {
    lista = new ArrayList();
    lista = DaoInfoClienteAnho.ConsultarPorAnho("info_codigo", this.yearChooser.getYear());
    
    ActualizarTabla();
  }
  
  private void consultarTodos()
  {
    lista = new ArrayList();
    lista = DaoInfoClienteAnho.ConsultarTodos("info_codigo");
    
    ActualizarTabla();
  }
  
  private static void ActualizarTabla()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    DecimalFormat formatear = new DecimalFormat("###,###.##");
    String[] campos = new String[5];
    for (int i = 0; i < lista.size(); i++)
    {
      modelo.addRow(campos);
      ModeloInfoCliente informacion = (ModeloInfoCliente)lista.get(i);
      modelo.setValueAt(Integer.valueOf(informacion.getCodigo()), i, 0);
      modelo.setValueAt(informacion.getCliente().getCedula(), i, 1);
      modelo.setValueAt(informacion.getCliente().getNombre(), i, 2);
      modelo.setValueAt(Integer.valueOf(informacion.getAnho()), i, 3);
      modelo.setValueAt(formatear.format(informacion.getTotal()), i, 4);
    }
  }
  
  private Integer MesSeleccionado()
  {
    int mes = this.monthChooser.getMonth();
    if (mes == 0)
    {
      mes = 1;
      return Integer.valueOf(mes);
    }
    if (mes == 1)
    {
      mes = 2;
      return Integer.valueOf(mes);
    }
    if (mes == 2)
    {
      mes = 3;
      return Integer.valueOf(mes);
    }
    if (mes == 3)
    {
      mes = 4;
      return Integer.valueOf(mes);
    }
    if (mes == 4)
    {
      mes = 5;
      return Integer.valueOf(mes);
    }
    if (mes == 5)
    {
      mes = 6;
      return Integer.valueOf(mes);
    }
    if (mes == 6)
    {
      mes = 7;
      return Integer.valueOf(mes);
    }
    if (mes == 7)
    {
      mes = 8;
      return Integer.valueOf(mes);
    }
    if (mes == 8)
    {
      mes = 9;
      return Integer.valueOf(mes);
    }
    if (mes == 9)
    {
      mes = 10;
      return Integer.valueOf(mes);
    }
    if (mes == 10)
    {
      mes = 11;
      return Integer.valueOf(mes);
    }
    if (mes == 11)
    {
      mes = 12;
      return Integer.valueOf(mes);
    }
    return Integer.valueOf(mes);
  }
}

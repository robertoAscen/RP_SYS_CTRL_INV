/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Informaciones;

import com.eneri.scstock.Modelos.ModeloClientes;
import com.eneri.scstock.Modelos.ModeloInfoCliente;
import com.eneri.scstock.Objetos.DaoInfoClienteMes;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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
public class VentaMensualCliente extends JDialog
{
    
  private JPanel contentPane;
  private JTextField tfnombre;
  private JTextField textField_1;
  private JMonthChooser monthChooser;
  private JTable table;
  public static List<ModeloInfoCliente> lista;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] {
    "C�d. Info.", "N� C.I./RUC", "Nombre del cliente", "Mes", "A�o", "Total compra" })
  {
    boolean[] columnEditables = new boolean[6];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JYearChooser yearChooser;
  private JButton btnSaludar;
  private JLabel lblFondo;
  private JLabel lblTotal;
  private JTextField textField;
  private JTabbedPane tabbedPane_1;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          VentaMensualCliente dialog = new VentaMensualCliente();
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
  
  public VentaMensualCliente()
  {
    setTitle("Informe de ventas mensuales por cliente");
    setResizable(false);
    setDefaultCloseOperation(2);
    setBounds(100, 100, 800, 426);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    JLabel lblCliente = new JLabel("  Mes:");
    lblCliente.setForeground(Color.WHITE);
    lblCliente.setBounds(39, 60, 65, 20);
    this.contentPane.add(lblCliente);
    
    JLabel label = new JLabel("Cliente:");
    label.setForeground(Color.WHITE);
    label.setBounds(29, 104, 76, 14);
    this.contentPane.add(label);
    
    JLabel lblInformeDeVentas = new JLabel("Informe de ventas por cliente");
    lblInformeDeVentas.setForeground(Color.WHITE);
    lblInformeDeVentas.setFont(new Font("Times New Roman", 0, 35));
    lblInformeDeVentas.setBounds(208, 5, 549, 43);
    this.contentPane.add(lblInformeDeVentas);
    
    this.tfnombre = new JTextField();
    this.tfnombre.setBounds(77, 101, 108, 20);
    this.contentPane.add(this.tfnombre);
    this.tfnombre.setColumns(10);
    
    this.textField_1 = new JTextField();
    this.textField_1.setColumns(10);
    this.textField_1.setBounds(195, 101, 321, 20);
    this.contentPane.add(this.textField_1);
    
    this.monthChooser = new JMonthChooser();
    this.monthChooser.setBounds(76, 60, 112, 20);
    this.contentPane.add(this.monthChooser);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 132, 774, 214);
    this.contentPane.add(scrollPane);
    
    this.table = new JTable();
    this.table.setModel(modelo);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(60);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(70);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(264);
    this.table.getColumnModel().getColumn(3).setPreferredWidth(52);
    this.table.getColumnModel().getColumn(4).setPreferredWidth(45);
    scrollPane.setViewportView(this.table);
    
    this.btnSaludar = new JButton("Consultar");
    this.btnSaludar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        VentaMensualCliente.this.consultarPorMes();
      }
    });
    this.btnSaludar.setBounds(277, 59, 121, 23);
    this.contentPane.add(this.btnSaludar);
    
    this.yearChooser = new JYearChooser();
    this.yearChooser.setBounds(220, 60, 47, 20);
    this.contentPane.add(this.yearChooser);
    
    JLabel lblAo = new JLabel("A�o:");
    lblAo.setForeground(Color.WHITE);
    lblAo.setBounds(194, 60, 39, 20);
    this.contentPane.add(lblAo);
    
    this.lblTotal = new JLabel("Total:");
    this.lblTotal.setFont(new Font("Tahoma", 0, 20));
    this.lblTotal.setBounds(521, 357, 88, 32);
    this.contentPane.add(this.lblTotal);
    
    this.textField = new JTextField();
    this.textField.setEditable(false);
    this.textField.setFont(new Font("Tahoma", 0, 20));
    this.textField.setColumns(10);
    this.textField.setBounds(585, 354, 199, 35);
    this.contentPane.add(this.textField);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(10, 5, 774, 122);
    this.contentPane.add(tabbedPane);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(10, 47, 774, 80);
    this.contentPane.add(this.tabbedPane_1);
    
    this.lblFondo = new JLabel("");
    this.lblFondo.setBounds(0, 0, 794, 398);
    this.contentPane.add(this.lblFondo);
    this.lblFondo.setIcon(new ImageIcon(new File("data/graficos/Formularios.jpg").getAbsolutePath()));
    consultarTodos();
  }
  
  private void consultarPorMes()
  {
    lista = new ArrayList();
    lista = DaoInfoClienteMes.ConsultarPorMes("info_codigo", MesSeleccionado().intValue(), this.yearChooser.getYear());
    
    ActualizarTabla();
  }
  
  private void consultarTodos()
  {
    lista = new ArrayList();
    lista = DaoInfoClienteMes.ConsultarTodos("info_codigo");
    
    ActualizarTabla();
  }
  
  private static void ActualizarTabla()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[6];
    String[] Meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
    for (int i = 0; i < lista.size(); i++)
    {
      modelo.addRow(campos);
      ModeloInfoCliente informacion = (ModeloInfoCliente)lista.get(i);
      modelo.setValueAt(Integer.valueOf(informacion.getCodigo()), i, 0);
      modelo.setValueAt(informacion.getCliente().getCedula(), i, 1);
      modelo.setValueAt(informacion.getCliente().getNombre(), i, 2);
      int mes = informacion.getMes();
      String Mes = Meses[(mes - 1)].toUpperCase();
      modelo.setValueAt(Mes, i, 3);
      modelo.setValueAt(Integer.valueOf(informacion.getAnho()), i, 4);
      modelo.setValueAt(Double.valueOf(informacion.getTotal()), i, 5);
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

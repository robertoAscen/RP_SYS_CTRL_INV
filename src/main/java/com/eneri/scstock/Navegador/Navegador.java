/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Navegador;

import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author RAscencio
 */
public class Navegador extends JFrame
{    
  public static void main(String[] args)
  {
    String contrasenha = "GABRIEL GR".toUpperCase();
    String tentativo = "";
    
    String atomoContrasenha = "";
    String atomoTentativo = "";
    
    String constContrasenha = "";
    
    Scanner entradaEscanner = new Scanner(System.in);
    tentativo = entradaEscanner.nextLine().toUpperCase();
    int longContrasenha = contrasenha.length();
    int longTentativo = tentativo.length();
    if (longContrasenha == longTentativo)
    {
      int posicion = 0;
      for (int i = 0; i < tentativo.length(); i++)
      {
        atomoTentativo = tentativo.substring(posicion, i + 1);
        atomoContrasenha = contrasenha.substring(posicion, i + 1);
        if (atomoContrasenha.equals(atomoTentativo))
        {
          constContrasenha = constContrasenha + atomoTentativo;
        }
        else
        {
          System.out.println("Desigualdad en la posici�n: " + posicion);
          System.out.println("Construido: " + constContrasenha);
          System.out.println("[" + atomoTentativo + "]" + " no es igual a:[" + atomoContrasenha + "]");
          return;
        }
        posicion++;
      }
    }
    else
    {
      System.out.println("Error en la longitud ya luego kape");
    }
  }    
}

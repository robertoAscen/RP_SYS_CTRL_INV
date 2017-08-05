/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Herramientas;

/**
 *
 * @author RAscencio
 */
public class ConversionEnLetras 
{
    
  private int flag;
  public int numero;
  public String importe_parcial;
  public String num;
  public String num_letra;
  public String num_letras;
  public String num_letram;
  public String num_letradm;
  public String num_letracm;
  public String num_letramm;
  public String num_letradmm;
  
  public ConversionEnLetras()
  {
    this.numero = 0;
    this.flag = 0;
  }
  
  public ConversionEnLetras(int n)
  {
    this.numero = n;
    this.flag = 0;
  }
  
  private String unidad(int numero)
  {
    switch (numero)
    {
    case 9: 
      this.num = "NUEVE ";
      break;
    case 8: 
      this.num = "OCHO ";
      break;
    case 7: 
      this.num = "SIETE ";
      break;
    case 6: 
      this.num = "SEIS ";
      break;
    case 5: 
      this.num = "CINCO ";
      break;
    case 4: 
      this.num = "CUATRO ";
      break;
    case 3: 
      this.num = "TRES ";
      break;
    case 2: 
      this.num = "DOS ";
      break;
    case 1: 
      if (this.flag == 0) {
        this.num = "UNO ";
      } else {
        this.num = "UN ";
      }
      break;
    case 0: 
      this.num = "";
    }
    return this.num;
  }
  
  private String decena(int numero)
  {
    if ((numero >= 90) && (numero <= 99))
    {
      this.num_letra = "NOVENTA ";
      if (numero > 90) {
        this.num_letra = this.num_letra.concat("Y ").concat(unidad(numero - 90));
      }
    }
    else if ((numero >= 80) && (numero <= 89))
    {
      this.num_letra = "OCHENTA ";
      if (numero > 80) {
        this.num_letra = this.num_letra.concat("Y ").concat(unidad(numero - 80));
      }
    }
    else if ((numero >= 70) && (numero <= 79))
    {
      this.num_letra = "SETENTA ";
      if (numero > 70) {
        this.num_letra = this.num_letra.concat("Y ").concat(unidad(numero - 70));
      }
    }
    else if ((numero >= 60) && (numero <= 69))
    {
      this.num_letra = "SESENTA ";
      if (numero > 60) {
        this.num_letra = this.num_letra.concat("Y ").concat(unidad(numero - 60));
      }
    }
    else if ((numero >= 50) && (numero <= 59))
    {
      this.num_letra = "CINCUENTA ";
      if (numero > 50) {
        this.num_letra = this.num_letra.concat("Y ").concat(unidad(numero - 50));
      }
    }
    else if ((numero >= 40) && (numero <= 49))
    {
      this.num_letra = "CUARENTA ";
      if (numero > 40) {
        this.num_letra = this.num_letra.concat("Y ").concat(unidad(numero - 40));
      }
    }
    else if ((numero >= 30) && (numero <= 39))
    {
      this.num_letra = "TREINTA ";
      if (numero > 30) {
        this.num_letra = this.num_letra.concat("Y ").concat(unidad(numero - 30));
      }
    }
    else if ((numero >= 20) && (numero <= 29))
    {
      if (numero == 20) {
        this.num_letra = "VEINTE ";
      } else {
        this.num_letra = "VEINTI".concat(unidad(numero - 20));
      }
    }
    else
    {
      if ((numero >= 10) && (numero <= 19)) {}
      switch (numero)
      {
      case 10: 
        this.num_letra = "DIEZ ";
        break;
      case 11: 
        this.num_letra = "ONCE ";
        break;
      case 12: 
        this.num_letra = "DOCE ";
        break;
      case 13: 
        this.num_letra = "TRECE ";
        break;
      case 14: 
        this.num_letra = "CATORCE ";
        break;
      case 15: 
        this.num_letra = "QUINCE ";
        break;
      case 16: 
        this.num_letra = "DIECISEIS ";
        break;
      case 17: 
        this.num_letra = "DIECISIETE ";
        break;
      case 18: 
        this.num_letra = "DIECIOCHO ";
        break;
      case 19: 
        this.num_letra = "DIECINUEVE ";
      default: 
          this.num_letra = unidad(numero);
        break;        
      }
    }
    return this.num_letra;
  }
  
  private String centena(int numero)
  {
    if (numero >= 100)
    {
      if ((numero >= 900) && (numero <= 999))
      {
        this.num_letra = "NOVECIENTOS ";
        if (numero > 900) {
          this.num_letra = this.num_letra.concat(decena(numero - 900));
        }
      }
      else if ((numero >= 800) && (numero <= 899))
      {
        this.num_letra = "OCHOCIENTOS ";
        if (numero > 800) {
          this.num_letra = this.num_letra.concat(decena(numero - 800));
        }
      }
      else if ((numero >= 700) && (numero <= 799))
      {
        this.num_letra = "SETECIENTOS ";
        if (numero > 700) {
          this.num_letra = this.num_letra.concat(decena(numero - 700));
        }
      }
      else if ((numero >= 600) && (numero <= 699))
      {
        this.num_letra = "SEISCIENTOS ";
        if (numero > 600) {
          this.num_letra = this.num_letra.concat(decena(numero - 600));
        }
      }
      else if ((numero >= 500) && (numero <= 599))
      {
        this.num_letra = "QUINIENTOS ";
        if (numero > 500) {
          this.num_letra = this.num_letra.concat(decena(numero - 500));
        }
      }
      else if ((numero >= 400) && (numero <= 499))
      {
        this.num_letra = "CUATROCIENTOS ";
        if (numero > 400) {
          this.num_letra = this.num_letra.concat(decena(numero - 400));
        }
      }
      else if ((numero >= 300) && (numero <= 399))
      {
        this.num_letra = "TRESCIENTOS ";
        if (numero > 300) {
          this.num_letra = this.num_letra.concat(decena(numero - 300));
        }
      }
      else if ((numero >= 200) && (numero <= 299))
      {
        this.num_letra = "DOSCIENTOS ";
        if (numero > 200) {
          this.num_letra = this.num_letra.concat(decena(numero - 200));
        }
      }
      else if ((numero >= 100) && (numero <= 199))
      {
        if (numero == 100) {
          this.num_letra = "CIEN ";
        } else {
          this.num_letra = "CIENTO ".concat(decena(numero - 100));
        }
      }
    }
    else {
      this.num_letra = decena(numero);
    }
    return this.num_letra;
  }
  
  private String miles(int numero)
  {
    if ((numero >= 1000) && (numero < 2000)) {
      this.num_letram = "MIL ".concat(centena(numero % 1000));
    }
    if ((numero >= 2000) && (numero < 10000))
    {
      this.flag = 1;
      this.num_letram = unidad(numero / 1000).concat("MIL ").concat(centena(numero % 1000));
    }
    if (numero < 1000) {
      this.num_letram = centena(numero);
    }
    return this.num_letram;
  }
  
  private String decmiles(int numero)
  {
    if (numero == 10000) {
      this.num_letradm = "DIEZ MIL";
    }
    if ((numero > 10000) && (numero < 20000))
    {
      this.flag = 1;
      this.num_letradm = decena(numero / 1000).concat("MIL ").concat(centena(numero % 1000));
    }
    if ((numero >= 20000) && (numero < 100000))
    {
      this.flag = 1;
      this.num_letradm = decena(numero / 1000).concat(" MIL ").concat(miles(numero % 1000));
    }
    if (numero < 10000) {
      this.num_letradm = miles(numero);
    }
    return this.num_letradm;
  }
  
  private String cienmiles(int numero)
  {
    if (numero == 100000) {
      this.num_letracm = "CIEN MIL";
    }
    if ((numero >= 100000) && (numero < 1000000))
    {
      this.flag = 1;
      this.num_letracm = centena(numero / 1000).concat("MIL ").concat(centena(numero % 1000));
    }
    if (numero < 100000) {
      this.num_letracm = decmiles(numero);
    }
    return this.num_letracm;
  }
  
  private String millon(int numero)
  {
    if ((numero >= 1000000) && (numero < 2000000))
    {
      this.flag = 1;
      this.num_letramm = "UN MILLON ".concat(cienmiles(numero % 1000000));
    }
    if ((numero >= 2000000) && (numero < 10000000))
    {
      this.flag = 1;
      this.num_letramm = unidad(numero / 1000000).concat(" MILLONES ").concat(cienmiles(numero % 1000000));
    }
    if (numero < 1000000) {
      this.num_letramm = cienmiles(numero);
    }
    return this.num_letramm;
  }
  
  private String decmillon(int numero)
  {
    if (numero == 10000000) {
      this.num_letradmm = "DIEZ MILLONES";
    }
    if ((numero > 10000000) && (numero < 20000000))
    {
      this.flag = 1;
      this.num_letradmm = decena(numero / 1000000).concat("MILLONES ").concat(cienmiles(numero % 1000000));
    }
    if ((numero >= 20000000) && (numero < 100000000))
    {
      this.flag = 1;
      this.num_letradmm = decena(numero / 1000000).concat(" MILLONES ").concat(millon(numero % 1000000));
    }
    if (numero < 10000000) {
      this.num_letradmm = millon(numero);
    }
    return this.num_letradmm;
  }
  
  public String convertirLetras(int numero)
  {
    this.num_letras = decmillon(numero);
    return this.num_letras;
  }    
}

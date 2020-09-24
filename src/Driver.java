package Matriks;

import java.io.*;
class Driver{
  public static void main(String[] args){
    Matriks m;
    m = Matriks.BacaMatriks("a");
    System.out.printf("%.2f\n", m.Determinan());
    m.Adjoin().TulisMatriks();
    System.out.println("");
    m.Invers().TulisMatriks();
  }
}

package Matriks;

import java.io.*;
class Driver{
  public static void main(String[] args){
    Matriks m = new Matriks(3,3);
    m.BacaMatriks();
    System.out.printf("%.2f\n", m.Determinan());
    m.Adjoin().TulisMatriks();
    System.out.println("");
    m.Invers().TulisMatriks();
  }
}

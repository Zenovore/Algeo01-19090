package Matriks;

import java.io.*;
class Driver{
  public static void main(String[] args){
    Matriks m = new Matriks(3,4);
    m.BacaMatriks();
    System.out.println("");
    System.out.println("");
    m.SPLinvers().TulisMatriks();
  }
}

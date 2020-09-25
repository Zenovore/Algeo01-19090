package tubes;

import java.io.*;
import java.util.Scanner;
class Driver{
  public static void main(String[] args){
    /* TODO: Buat Main */
    int x;
    Matriks a ;
    Scanner s = new Scanner(System.in);
    System.out.printf("MENU");
    System.out.printf("\n1. Sistem Persamaan Linear\n2. Determinan \n3. Matriks balikan \n4. Interpolasi Polinom \n5. Regresi Linier Berganda \n6. Keluar\n");
    x = s.nextInt();
      if (x==1){ /* SPL */
        System.out.printf("\n1.Metode Eliminasi Gauss \n2.Metode Eliminasi Gauss-Jordan  \n3.Metode Matriks balikan  \n4.Kaidah Crammer\n");
        x = s.nextInt();
        if (x==1){
          a = Matriks.bacaMatriks();
          s.close();
          /* Gauss */
        }
        else if (x==2){
          a = Matriks.bacaMatriks();
          /* Gauss Jordan */
        }
        else if (x==3){/* METODE INVERS */
          a = Matriks.bacaMatriks();
          a.solusiSPLinvers().tulisMatriks();
        }
        else if (x==4){
          a = Matriks.bacaMatriks();
          /*crammer*/
        }
        else{
          System.out.printf("\nSalah input,ayo masukin yang bener >.<\n");
        }
      }
      else if (x==2){ /* DETERMINAN */
        System.out.printf("\n1.Metode Reduksi baris \n2.Metode Ekspansi Kofaktor\n");
        x = s.nextInt();
        if (x==1){
          a = Matriks.bacaMatriks();
          /* Reduksi baris */
        }
        else if (x==2){
          a = Matriks.bacaMatriks();
          System.out.printf("%.2f",a.determinan());
        }
        else{
          System.out.printf("\nSalah input,ayo masukin yang bener >.<\n");
        }
      }
      else if(x==3){ /* INVERS */
        System.out.printf("\n1.Metode OBE \n2.Metode Adjoin\n");
        x = s.nextInt();
        if (x==1){
          a = Matriks.bacaMatriks();
          /* OBE */
        }
        else if (x==2){
          a = Matriks.bacaMatriks();
          a.invers().tulisMatriks();
        }
        else{
          System.out.printf("\nSalah input,ayo masukin yang bener >.<\n");
        }
      }
      else if (x==4){/* Interpolasi Polinom */
        /* Interpolasi Polinom */
      }
      else if (x==5){/* Regresi Linear */
        /* Regresi Linear */
      }
      else if (x==6){ /* EXIT */
        System.exit(0);
      }
      else{
        System.out.printf("\nSalah input,ayo masukin yang bener >.<\n");
      }
  }
}

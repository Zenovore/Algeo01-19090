package tubes;

import java.io.FileNotFoundException;
import java.util.Scanner;
class Driver{
  public static void main(String[] args){
    int menu, source;
    Matriks a = new Matriks(0, 0);
    String f;
    Scanner s = new Scanner(System.in);
    System.out.printf("MENU");
    System.out.printf("\n1. Sistem Persamaan Linear\n2. Determinan \n3. Matriks balikan \n4. Interpolasi Polinom \n5. Regresi Linier Berganda \n6. Keluar\n");
    menu = s.nextInt();
    s.nextLine();
    if(0 < menu && menu < 6){ /* First input valid */
      System.out.printf("\n1.Input File \n2.Input dari Keyboard \nMasukan Input ");
      source = s.nextInt();
      s.nextLine();
      if(source == 1){
        f = s.nextLine();
        try{
          a = Matriks.bacaMatriks(f);
        }
        catch(FileNotFoundException e){
          s.close();
          return;
        }
      }
      else if(source == 2){
        a = Matriks.bacaMatriks(s);
      }
      s = new Scanner(System.in);

      /*---- 1. SPL ----*/
      if(menu == 1){
        System.out.println("1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("3. Metode Matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.printf("Masukkan input: ");
        menu = s.nextInt();
        if(menu == 1){
          a.gauss();
        }
        else if(menu == 2){
          a.solusiSPLGaussJordan().tulisSolusiSPL();
        }
        else if(menu == 3){
          a.solusiSPLinvers().tulisSolusiSPL();
        }
        else if(menu == 4){
          /* Cramer */
        }
        else{
          System.out.println("Salah input, ayo masukin yang bener >.<");
        }
      }

      /*---- 2. DETERMINAN ----*/
      else if(menu == 2){
        System.out.println("1. Metode Reduksi Baris");
        System.out.println("2. Metode Ekspansi Kofaktor");
        System.out.printf("Masukkan input: ");
        menu = s.nextInt();
        if(menu == 1){
          /* Reduksi baris */
          a.gauss();
        }
        else if(menu == 2){
          System.out.printf("%.2f\n", a.determinan());
        }
        else{
          System.out.println("\nSalah input, ayo masukin yang bener >.<");
        }
      }

      /*---- 3. INVERS ----*/
      else if(menu == 3){
        System.out.println("1. Metode OBE");
        System.out.println("2. Metode Adjoin");
        System.out.printf("Masukkan input: ");
        menu = s.nextInt();
        if(menu == 1){
          /* OBE */
          a.tambahkolom(a.kolom(), a.identitas(a.kolom())).gauss().gaussJordan().hapuskolom(a.baris()).tulisMatriks();
          System.out.println("\n");
        }
        if(menu == 2){
          a.invers().tulisMatriks();
          System.out.println("\n");
        }
        else{
          a.tambahkolom(a.kolom(), a.identitas(a.kolom())).tulisMatriks();
          System.out.println("\n");

          System.out.println("Salah input, ayo masukin yang bener >.<");
        }
      }

      /*----4. INTERPOLASI POLINOM----*/
      else if(menu == 4){
        /* TODO: Buat interpolasi polinom */
      }

      /*----5. REGRESI LINEAR----*/
      else if(menu == 5){
        /* TODO: Buat regresi linear */
      }
      else{
        System.out.printf("\nSalah input,ayo masukin yang bener >.<\n");
      }
    }
    else if(menu == 6){
      s.close();
      return;
    }
    else{
      System.out.printf("\nSalah input,ayo masukin yang bener >.<\n");
    }
    s.close();
  }
}

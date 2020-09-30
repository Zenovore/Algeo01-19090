package tubes;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
      String solusi = "";
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
          solusi = a.gauss().stringSolusiSPL("x");
        }
        else if(menu == 2){
          solusi = a.solusiSPLGaussJordan().stringSolusiSPL("x");
        }
        else if(menu == 3){
          solusi = a.solusiSPLinvers().stringSolusiSPL("x");
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
          solusi = String.format("%.2f", a.determinan());
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
          solusi = a.tambahkolom(a.kolom(), a.identitas(a.kolom())).gaussJordan().hapuskolom(a.baris()).stringOfMatriks();
        }
        else if(menu == 2){
          solusi = a.invers().stringOfMatriks();
        }
        else{
          System.out.println("Salah input, ayo masukin yang bener >.<");
        }
      }

      /*----4. INTERPOLASI POLINOM----*/
      else if(menu == 4){
        /* TODO: Buat interpolasi polinom */
      }

      /*----5. REGRESI LINEAR----*/
      else if(menu == 5){
        solusi = a.regresi().stringSolusiSPL("b");
        /* TODO: Buat regresi linear */
      }

      System.out.println("Mau dikeluarin disini (1) ato di file(2)?");
      menu = s.nextInt();
      if(menu == 1){
        System.out.println(solusi);
      }
      else if(menu == 2){
        System.out.print("Nama file output: ");
        s.nextLine();
        String nama = s.nextLine();
        try{
          FileWriter w = new FileWriter(nama);
          w.write(solusi);
          w.close();
        }
        catch(IOException e){
          System.out.println(solusi);
        }
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

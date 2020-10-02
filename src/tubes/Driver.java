package tubes;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
class Driver{
  public static void main(String[] args){
    int menu, source,bar, kolom;
    Matriks a = new Matriks(0, 0);
    Matriks b;
    String f;
    Scanner s = new Scanner(System.in);
    System.out.printf("MENU");
    System.out.printf("\n1. Sistem Persamaan Linear\n2. Determinan \n3. Matriks balikan \n4. Interpolasi Polinom \n5. Regresi Linier Berganda \n6. Keluar\n");
    menu = s.nextInt();
    s.nextLine();
    if(0 < menu && menu < 6){ /* First input valid */
      StringBuilder solusi = new StringBuilder();
      System.out.printf("\n1.Input File \n2.Input dari Keyboard \nMasukan Input ");
      source = s.nextInt();
      s.nextLine();
      if(source == 1){
        f = s.nextLine();
        try{
          a = Matriks.bacaMatriks(f);
          a.tulisMatriks();
          System.out.println();
        }
        catch(FileNotFoundException e){
          s.close();
          return;
        }
      }
      else if(source == 2){
        if(menu==4){
          a = Matriks.bacaInterpolasi(s);
        }
        else if (menu==2 || menu == 3) {
          System.out.printf("Masukkan n: ");
          bar = s.nextInt();
          a = Matriks.bacaMatriks(bar, bar, s);
        }
        else if (menu ==5){
          System.out.printf("Masukkan Jumlah Peubah X: ");
          kolom = s.nextInt();
          System.out.printf("Masukkan Jumlah Data: ");
          bar = s.nextInt();
          a = Matriks.bacaMatriks(bar, kolom+1, s);

        }
        else{
          System.out.printf("Masukkan Baris: ");
          bar = s.nextInt();
          System.out.printf("Masukkan Kolom: ");
          kolom = s.nextInt();
          a = Matriks.bacaMatriks(bar,kolom,s);
          System.out.printf("Masukkan Matriks B: \n");
          b = Matriks.bacaMatriks(bar,1,s);
          a = a.tambahkolom(b.kolom(), b);
        }

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
          solusi.append(a.gauss().stringSolusiSPL("x", 1));
        }
        else if(menu == 2){
          solusi.append(a.solusiSPLGaussJordan().stringSolusiSPL("x", 1));
        }
        else if(menu == 3){
          if (a.determinan()==0){
            solusi.append("Tidak ada Solusi SPL");
          }
          else {
            solusi.append(a.solusiSPLinvers().stringSolusiSPL("x", 1));
          }
        }
        else if(menu == 4){
          /* Cramer */
          solusi.append(a.cramer().stringSolusiSPL("x", 1));
        }
        else{
          System.out.println("Salah input, ayo masukin yang bener >.<");
        }

      }

      /*---- 2. DETERMINAN ----*/
      else if(menu == 2){
        System.out.println("1. Metode Reduksi Baris");
        System.out.println("2. Metode Ekspansi Kofaktor (Lebih baik digunakan ketika elemen matriks bukan pecahan)");
        System.out.printf("Masukkan input: ");
        menu = s.nextInt();
        if(menu == 1){
          /* Reduksi baris */
          solusi.append(String.format("%.64f", a.determinanReduksi()));
        }
        else if(menu == 2){
          solusi.append(String.format("%.64f", a.determinan()));
        }
        else{
          System.out.println("\nSalah input, ayo masukin yang bener >.<");
        }
      }

      /*---- 3. INVERS ----*/
      else if(menu == 3){
        System.out.println("1. Metode OBE");
        System.out.println("2. Metode Adjoin (Lebih baik digunakan ketika elemen matriks bukan pecahan)");
        System.out.printf("Masukkan input: ");
        menu = s.nextInt();
        if(menu == 1){
          /* OBE */
          solusi.append(a.invers().stringOfMatriks());
        }
        else if(menu == 2){ /* Lebih baik digunakan ketika elemen matriks bukan pecahan */
          solusi.append(a.invers().stringOfMatriks());
        }
        else{
          System.out.println("Salah input, ayo masukin yang bener >.<");
        }
      }

      /*----4. INTERPOLASI POLINOM----*/
      else if(menu == 4){
        a = a.interpolasi().gaussJordan();
        solusi.append("\n");
        solusi.append(a.tulisInterpolasi());
        solusi.append(String.format("%f\n", a.hitungInterpolasi(s)));
      }

      /*----5. REGRESI LINEAR----*/
      else if(menu == 5){
        a = a.regresi().gaussJordan();
        solusi.append("\n");
        solusi.append(a.tulisRegresi());
        solusi.append("\n");
        solusi.append(String.format("y = %f\n", a.hitungRegresi(s)));
      }

      System.out.println(solusi.toString());
      System.out.println("Mau dikeluarin di file juga? (1 = yes)");
      menu = s.nextInt();
      if(menu == 1){
        System.out.print("Nama file output: ");
        s.nextLine();
        String nama = s.nextLine();
        try{
          FileWriter w = new FileWriter(nama);
          w.write(solusi.toString());
          w.close();
        }
        catch(IOException e){
          System.out.println(solusi.toString());
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

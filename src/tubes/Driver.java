package tubes;
import java.util.Scanner;
class Driver{
  public static void main(String[] args){
    int x;
    Matriks a;
    String  f;
    Scanner s = new Scanner(System.in);
    System.out.printf("MENU");
    System.out.printf("\n1. Sistem Persamaan Linear\n2. Determinan \n3. Matriks balikan \n4. Interpolasi Polinom \n5. Regresi Linier Berganda \n6. Keluar\n");
    x = s.nextInt();
    a = null;
      if (x==1){ /* SPL */
        System.out.printf("\n1.Input File \n2.Input dari Keyboard \nMasukan Input ");
        x = s.nextInt();
        if (x==1){
          f = s.nextLine();
          /*
          try {
            a = Matriks.bacaMatriks(f);
          } catch (Exception e) {
            s.close();
            return  ;
          }
          */
        }
        else if (x==2){
          a = Matriks.bacaMatriks();
        }
        else{
          System.out.printf("\nSalah input,ayo masukin yang bener >.<\n");
        }
        System.out.printf("\n1.Metode Eliminasi Gauss \n2.Metode Eliminasi Gauss-Jordan  \n3.Metode Matriks balikan  \n4.Kaidah Crammer\nMasukan Input ");
        x = s.nextInt();
        if (x==1){
          /* Gauss */
        }
        else if (x==2){
          /* Gauss Jordan */
        }
        else if (x==3){/* METODE INVERS */
          a.solusiSPLinvers().tulisMatriks();
        }
        else if (x==4){
          /*crammer*/
        }
        else{
          System.out.printf("\nSalah input,ayo masukin yang bener >.<\n");
        }
      }
      else if (x==2){ /* DETERMINAN */
        System.out.printf("\n1.Input File \n2.Input dari Keyboard \nMasukkan Input ");
        x = s.nextInt();
        if (x==1){
          f = s.nextLine();
          /*
          try {
            a = Matriks.bacaMatriks(f);
          } catch (Exception e) {
            s.close();
            return ;
          }
          */
        }
        else if (x==2){
          a = Matriks.bacaMatriks();
        }

        System.out.printf("\n1.Metode Reduksi baris \n2.Metode Ekspansi Kofaktor\nMasukkan Input ");
        x = s.nextInt();
        if (x==1){
          /* Reduksi baris */
        }
        else if (x==2){
          System.out.printf("%.2f",a.determinan());
        }
        else{
          System.out.printf("\nSalah input,ayo masukin yang bener >.<\n");
        }
      }
      else if(x==3){ /* INVERS */
        System.out.printf("\n1.Input File \n2.Input dari Keyboard \nMasukkan Input ");
        x = s.nextInt();
        if (x==1){
          f = s.nextLine();
          /*
          try {
            a = Matriks.bacaMatriks(f);
          } catch (Exception e) {
            s.close();
            return ;
          }
          */
        }
        else if (x==2){
          a = Matriks.bacaMatriks();
        }
        else{
          System.out.printf("\nSalah input,ayo masukin yang bener >.<\n");
        }
        System.out.printf("\n1.Metode OBE \n2.Metode Adjoin\nMasukkan Input ");
        x = s.nextInt();
        if (x==1){
          /* OBE */
        }
        else if (x==2){
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
        s.close();
        System.exit(0);
      }
      else{
        System.out.printf("\nSalah input,ayo masukin yang bener >.<\n");
      }
  }
}

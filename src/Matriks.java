package Matriks;

import java.io.*;
import java.util.Scanner;

class Matriks {
  private double[][] mat;
  private int baris, kolom;

  /**
   * Membuat matriks baru dengan ukuran baris x kolom
   * @param baris jumlah baris
   * @param kolom jumlah kolom
   */
  public Matriks(int baris, int kolom){
    this.baris = baris;
    this.kolom = kolom;
    this.mat = new double[baris][kolom];
  }

  /* Operasi Baca-Tulis */
  /**
   * Membaca matriks dari stdin
   * @return Matriks hasil pembacaan
   */
  public static Matriks BacaMatriks(){
    /* Baca Matriks */
    int i,j, baris,kolom;
    Matriks m;

    Scanner s = new Scanner(System.in);
    baris = s.nextInt();
    kolom = s.nextInt();
    m = new Matriks(baris, kolom);
    for (i=0;i<baris;i++){
      for (j=0;j<kolom;j++){
        m.SetElemenKe(i, j, s.nextDouble());
      }
    }
    s.close();
    return m;
  }
  /**
   * Membaca matriks dari file
   * @param f nama file
   * @return Matriks hasil pembacaan
   */
  public static Matriks BacaMatriks(String f){
    int baris = 0, kolom = 0;
    Matriks m = null;

    try{
      File file = new File(f);
      Scanner s = new Scanner(file);
      String curr;
      while(s.hasNextLine()){
        curr = s.nextLine();
        kolom = curr.split(" ").length;
        baris++;
      }
      s.close();
      s = new Scanner(file);
      m = new Matriks(baris, kolom);
      for(int i = 0; i < m.Baris(); i++){
        for(int j = 0; j < m.Kolom(); j++){
          m.SetElemenKe(i, j, s.nextDouble());
        }
      }
      s.close();
    }
    catch(FileNotFoundException e){
      return null;
    }
    return m;
  }

  /**
   * Menuliskan matriks ke stdout
   */
  public void TulisMatriks(){
    /* Tulis Matriks */
    for(int i = 0; i < baris; i++){
      for(int j = 0; j < kolom; j++){
        System.out.printf("%.2f", mat[i][j]);
        if(j < kolom - 1) System.out.printf(" ");
      }
      if(i < baris - 1) System.out.println("");
    }
  }

  /* Getter atribut */
  /**
   * Mengembalikan jumlah baris dari matriks
   * @return Jumlah baris
   */
  public int Baris(){ 
    return baris;
  }

  /**
   * Mengembalikan jumlah kolom dari matriks
   * @return Jumlah kolom
   */
  public int Kolom(){
    return kolom;
  }

  /**
   * Mengembalikan elemen matriks di baris ke-i kolom ke-j
   * @ param i baris
   * @param j kolom
   * @return elemen matriks
   */
  public double ElemenKe(int baris, int kolom){
    return mat[baris][kolom];
  }

  /* Setter atribut */
  /**
   * Mengubah elemen ke-i,j menjadi val
   * @param i baris
   * @param j kolom
   * @param val isi baru
   */
  public void SetElemenKe(int i, int j, double val){
    mat[i][j] = val;
  }

  /* Operasi dasar */
  /**
   * Menjumlahkan dua matriks, a + b
   * Prekondisi: Ukuran kedua matriks sama
   * @param a Matriks pertama
   * @param b Matriks kedua
   * @param plus Tambah jika true, kurang jika false
   * @return Hasil penjumlahan matriks a + b
   */
  public static Matriks Jumlah(Matriks a, Matriks b, boolean plus){
    int sign = plus ? 1 : -1;
    Matriks m = new Matriks(a.baris, a.kolom);
    for(int i = 0; i < a.baris; i++){
      for(int j = 0; j < a.kolom; j++){
        m.SetElemenKe(i, j, a.ElemenKe(i,j) + (sign*b.ElemenKe(i,j)));
      }
    }
    return m;
  }

  /**
   * Mengalikan dua matriks, a * b
   * @param a Matriks pertama
   * @param b Matriks kedua
   * @return Hasil perkalian matriks a * b
   */
  public static Matriks Kali(Matriks a, Matriks b){
    Matriks m = new Matriks(b.baris, a.kolom);

    for(int i = 0; i < a.baris; i++){
      for(int j = 0; j < a.kolom; j++){
        int sum = 0;
        for(int k = 0; k < b.kolom; k++){
          sum += a.ElemenKe(i, k) * b.ElemenKe(k, i);
        }
        m.SetElemenKe(i, j, sum);
      }
    }
    return m;
  }

  /**
   * Menyalin matriks lain ke matriks ini
   * @param lain Matriks lain yang akan disalin
   */
  public void SalinMatriks(Matriks lain){
    for(int i = 0; i < lain.baris; i++){
      for(int j = 0; j < lain.kolom; j++){
        this.SetElemenKe(i, j, lain.ElemenKe(i, j));
      }
    }
  }

  /**
   * Mentranspose matriks
   */
  public Matriks Transpose(){
    double[][] temp = new double[kolom][baris];
    for(int i = 0; i < baris; i++){
      for(int j = 0; j < kolom; j++){
        temp[i][j] = this.ElemenKe(j, i);
      }
    }
    baris = kolom + baris;
    kolom = baris - kolom;
    baris = baris - kolom;
    this.mat = temp;
    return this;
  }

  public boolean IsKotak(){
    return (Baris()==Kolom()) ;

  }
  public double Determinan(){
    int j ;
    double c=0;
    
    if (IsKotak()){
      if (((this.Baris()) * (this.Kolom())) == 1){
        return (this.ElemenKe(0,0));
      }
      else if (((this.Baris()) * (this.Kolom())) == 4){
        return ((this.ElemenKe(0,0)*this.ElemenKe(1,1)) - (this.ElemenKe(0,1)* this.ElemenKe(1,0)));
      }
      else{
        for (j=0;j< this.Kolom() ;j++){
      c += (this.ElemenKe(0,j) * (this.EntriKofaktor(0,j)));
      }
      return c;
      }
    }
    else {
      return (0.0);
    }
  }
  public Matriks Kofaktor(){
    Matriks temp = new Matriks(this.Baris(),this.Kolom());
    int i, j;
    if (this.Baris()<2 || this.Kolom()<2) {
      return this;
    }
    else {
      for (i=0;i<this.Baris();i++){
        for (j=0;j<this.Kolom();j++){
            temp.SetElemenKe(i, j, this.EntriKofaktor(i, j));
        }
      }
    }
    return temp;
  }
  public Matriks Adjoin(){
    return (this.Kofaktor()).Transpose();
  }

  public double EntriKofaktor(int x, int y){
    Matriks temp = new Matriks(this.Baris()-1,this.Kolom()-1);
    int i,j,g=0,h=0;
    for (i=0;i<this.Baris();i++){
      for (j=0;j<this.Kolom();j++){
        if (i!=x && j!=y){
          temp.SetElemenKe(g, h, this.ElemenKe(i,j));
          h += 1;
          if(h==(temp.Kolom())){
            h=0;
            g++;
          }
        }
      }
    }
    return ((x+y) % 2 == 0 ? 1 : -1 ) * temp.Determinan();
  }

  public Matriks KaliSkalar(double x){
    int i,j;
    for(i=0;i<this.Baris();i++){
      for(j=0;j<this.Kolom();j++){
        SetElemenKe(i, j,x*ElemenKe(i,j));
      }    
   }
   return this;
  }

  public Matriks Invers(){
    return(this.Adjoin().KaliSkalar(1/this.Determinan()));
  }
}

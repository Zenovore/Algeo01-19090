package tubes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matriks {
  private double[][] mat;
  private int bar, kol;

  /**
   * Membuat matriks baru dengan ukuran bar x kol
   * @param bar jumlah bar
   * @param kol jumlah kol
   */
  public Matriks(int bar, int kol){
    this.bar = bar;
    this.kol = kol;
    this.mat = new double[bar][kol];
  }

  /* Operasi Baca-Tulis */
  /**
   * Membaca matriks dari stdin
   * @return Matriks hasil pembacaan
   */
  public static Matriks bacaMatriks(){
    /* Baca Matriks */
    int i,j, bar,kol;
    Matriks m;

    Scanner s = new Scanner(System.in);
    bar = s.nextInt();
    kol = s.nextInt();
    m = new Matriks(bar, kol);
    for (i=0;i<bar;i++){
      for (j=0;j<kol;j++){
        m.setElemenKe(i, j, s.nextDouble());
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
  public static Matriks bacaMatriks(String f) throws FileNotFoundException {
    int bar = 0, kol = 0;
    Matriks m = null;

    File file = new File(f);
    Scanner s = new Scanner(file);
    String curr;
    while(s.hasNextLine()){
      curr = s.nextLine();
      kol = curr.split(" ").length;
      bar++;
    }
    s.close();
    s = new Scanner(file);
    m = new Matriks(bar, kol);
    for(int i = 0; i < m.baris(); i++){
      for(int j = 0; j < m.kolom(); j++){
        m.setElemenKe(i, j, s.nextDouble());
      }
    }
    s.close();
    return m;
  }

  /**
   * Menuliskan matriks ke stdout
   */
  public void tulisMatriks(){
    /* Tulis Matriks */
    for(int i = 0; i < bar; i++){
      for(int j = 0; j < kol; j++){
        System.out.printf("%.2f", mat[i][j]);
        if(j < kol - 1) System.out.printf(" ");
      }
      if(i < bar - 1) System.out.println("");
    }
  }

  /* Getter atribut */
  /**
   * Mengembalikan jumlah bar dari matriks
   * @return Jumlah bar
   */
  public int baris(){ 
    return bar;
  }

  /**
   * Mengembalikan jumlah kol dari matriks
   * @return Jumlah kol
   */
  public int kolom(){
    return kol;
  }

  /**
   * Mengembalikan elemen matriks di bar ke-i kol ke-j
   * @ param i bar
   * @param j kol
   * @return elemen matriks
   */
  public double elemenKe(int bar, int kol){
    return mat[bar][kol];
  }

  /* Setter atribut */
  /**
   * Mengubah elemen ke-i,j menjadi val
   * @param i bar
   * @param j kol
   * @param val isi baru
   */
  public void setElemenKe(int i, int j, double val){
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
  public static Matriks jumlah(Matriks a, Matriks b, boolean plus){
    int sign = plus ? 1 : -1;
    Matriks m = new Matriks(a.bar, a.kol);
    for(int i = 0; i < a.bar; i++){
      for(int j = 0; j < a.kol; j++){
        m.setElemenKe(i, j, a.elemenKe(i,j) + (sign*b.elemenKe(i,j)));
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
  public static Matriks kali(Matriks a, Matriks b){
    Matriks m = new Matriks(a.bar, b.kol);

    for(int i = 0; i < a.bar; i++){
      for(int j = 0; j < b.kol; j++){
        double sum = 0;
        for(int k = 0; k < a.kol; k++){
          sum += a.elemenKe(i, k) * b.elemenKe(k, j);
        }
        m.setElemenKe(i, j, sum);
      }
    }
    return m;
  }

  /**
   * Menyalin matriks lain ke matriks ini
   * @param lain Matriks lain yang akan disalin
   */
  public void salinMatriks(Matriks lain){
    for(int i = 0; i < lain.bar; i++){
      for(int j = 0; j < lain.kol; j++){
        this.setElemenKe(i, j, lain.elemenKe(i, j));
      }
    }
  }

  /**
   * Mentranspose matriks
   */
  public Matriks transpose(){
    double[][] temp = new double[kol][bar];
    for(int i = 0; i < bar; i++){
      for(int j = 0; j < kol; j++){
        temp[i][j] = this.elemenKe(j, i);
      }
    }
    bar = kol + bar;
    kol = bar - kol;
    bar = bar - kol;
    this.mat = temp;
    return this;
  }

  /**
   * Mengembalikan true jika matriks persegi
   * @return baris == kolom
   */
  public boolean isKotak(){
    return (baris()==kolom()) ;

  }

  /**
   * Mencari determinan matriks menggunakan metode kofaktor
   * @return determinan
   */
  public double determinan(){
    int j ;
    double c=0;
    
    if (isKotak()){
      if (((this.baris()) * (this.kolom())) == 1){
        return (this.elemenKe(0,0));
      }
      else if (((this.baris()) * (this.kolom())) == 4){
        return ((this.elemenKe(0,0)*this.elemenKe(1,1)) - (this.elemenKe(0,1)* this.elemenKe(1,0)));
      }
      else{
        for (j=0;j< this.kolom() ;j++){
      c += (this.elemenKe(0,j) * (this.entriKofaktor(0,j)));
      }
      return c;
      }
    }
    else {
      return (0.0);
    }
  }

  /**
   * Mencari matriks kofaktor dari matriks awal
   * @return Matriks kofaktor
   */
  public Matriks kofaktor(){
    Matriks temp = new Matriks(this.baris(),this.kolom());
    int i, j;
    if (this.baris()<2 || this.kolom()<2) {
      return this;
    }
    else {
      for (i=0;i<this.baris();i++){
        for (j=0;j<this.kolom();j++){
            temp.setElemenKe(i, j, this.entriKofaktor(i, j));
        }
      }
    }
    return temp;
  }

  /**
   * Mencari Matriks adjoin dari matriks awal
   * @return Matriks adjoin
   */
  public Matriks adjoin(){
    return (this.kofaktor()).transpose();
  }

  /**
   * Mencari entri kofaktor baris ke-x kolom ke-y
   * @return entri kofaktor x,y
   */
  public double entriKofaktor(int x, int y){
    Matriks temp = new Matriks(this.baris()-1,this.kolom()-1);
    int i,j,g=0,h=0;
    for (i=0;i<this.baris();i++){
      for (j=0;j<this.kolom();j++){
        if (i!=x && j!=y){
          temp.setElemenKe(g, h, this.elemenKe(i,j));
          h += 1;
          if(h==(temp.kolom())){
            h=0;
            g++;
          }
        }
      }
    }
    return ((x+y) % 2 == 0 ? 1 : -1 ) * temp.determinan();
  }

  /**
   * Mengalikan matriks dengan suatu skalar x
   * @return Matriks yang sudah dikali x
   */
  public Matriks kaliSkalar(double x){
    int i,j;
    for(i=0;i<this.baris();i++){
      for(j=0;j<this.kolom();j++){
        setElemenKe(i, j,x*elemenKe(i,j));
      }    
   }
   return this;
  }

  /**
   * Menginverse matriks
   * @return Matriks yang sudah diinverse
   */
  public Matriks invers(){
    return(this.adjoin().kaliSkalar(1/this.determinan()));
  }

  /**
   * Menghapus kolom terakhir dari matriks
   * @return Matriks kolom terakhir
   */
  public Matriks hapusLastkolom(){
    int i;
    Matriks temp = new Matriks(this.baris(),1);
    for(i=0;i<this.baris();i++){
      temp.setElemenKe(i,0,elemenKe(i,this.kolom()-1));
      this.setElemenKe(i,this.kolom()-1,0);
    }
    this.kol = this.kol-1;
    return temp;
  }

  /**
   * Membuat matriks eselon baris menggunakan metode Gauss
   * @return Matriks eselon baris
   */
  public Matriks Gauss(){
    /** TODO: STUB! */
    return null;
  }

  /**
   * Membuat matriks eselon baris tereduksi menggunakan Gauss-Jordan
   * @return Matriks eselon baris tereduksi
   */
  public Matriks gaussJordan(){
    /** TODO: STUB! */
    return null;
  }

  /**
   * Menukar satu kolom dengan kolom yang diberikan
   * @param mat Matriks nx1 yang akan dimasukkan
   * @param idx indeks kolom yang akan dikeluarkan, mulai dari 0
   * @return Matriks baru yang sudah ditukar
   */
  public Matriks tukarKolom(Matriks mat, int idx){
    /** TODO: STUB! */
    return null;
  }

  /**
   * Mencari solusi SPL menggunakan invers matriks
   * dari matriks augmented
   * @return Matriks solusi SPL
   */
  public Matriks solusiSPLinvers(){
    Matriks c,Hsl;
    c = this.hapusLastkolom();
    Hsl = Matriks.kali(this.invers(),c);
    return Hsl;
  }
}

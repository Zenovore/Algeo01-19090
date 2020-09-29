package tubes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matriks {
  private double[][] mat;
  private int bar, kol;
  private int swaps = 0;

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
  public static Matriks bacaMatriks(Scanner s){
    /* Baca Matriks */
    int i,j, bar,kol;
    Matriks m;

    System.out.printf("Masukan Baris ");
    bar = s.nextInt();
    System.out.printf("Masukan Kolom ");
    kol = s.nextInt();
    m = new Matriks(bar, kol);
    for (i=0;i<bar;i++){
      for (j=0;j<kol;j++){
        m.setElemenKe(i, j, s.nextDouble());
      }
    }
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
   * Mengembalikan jumlah pertukaran baris yang sudah
   * dilakukan pada matriks
   * @return jumlah pertukaran baris
   */
  public int jumlahTukar(){
    return swaps;
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
    this.bar = lain.bar;
    this.kol = lain.kol;
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
    else if ((this.baris()) == ((this.kolom())-1)) {
      this.hapusLastkolom();
      return (this.determinan());
    }
    else{
      return(0.0);
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
  public Matriks identitas(int a){
    int i,j;
    Matriks temp = new Matriks(a,a);
    for(i=0;i<temp.baris();i++){
      for(j=0;j<temp.kolom();j++){
        if (i==j){
          temp.setElemenKe(i, j,1);
        }
        else {
          temp.setElemenKe(i, j,0);
        }
      }    
    }
    return temp;
  }
  // public Matriks invers2(){

  // }
  public Matriks tambahkolom(int a,Matriks b){
    int i,j;
    Matriks temp = new Matriks(this.baris(),this.kolom()+a);
    for(i=0;i<this.baris();i++){
      for(j=0;j<this.kolom()+a;j++){
        if (j<this.kolom()){
          temp.setElemenKe(i, j, this.elemenKe(i, j));
        }
        else {
          temp.setElemenKe(i, j, b.elemenKe(i, j-a));
        }
      }
    }
    return temp;
  }
  public Matriks hapuskolom(int a){
    int i,j;
    Matriks temp = new Matriks(this.baris(),this.kolom()-a);
    for(i=0;i<this.baris();i++){
      for(j=0;j<this.kolom()-a;j++){
        temp.setElemenKe(i, j, elemenKe(i, j+a));
      } 
    }
    return temp;
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
  public Matriks gauss(){
    int R=this.baris();
    int C=this.kolom();
    double x=0;
    double y=0;
    double z=0;
    int i,j,k;
    double temp,temp2;

    int t=0;
    z=this.elemenKe(0,0);
    while(z==0){
      t=t+1;
      for(j=0;j<C;j++){
        temp=this.elemenKe(0,j);
        this.setElemenKe(0,j,this.elemenKe(t,j));
        this.setElemenKe(t,j,temp);
      z=this.elemenKe(0,0);
      }
    }

    for(j=0;j<C;j++){
      this.setElemenKe(0,j,(this.elemenKe(0,j)/z));
    }
    for(k=0;k<(C-1);k++){
      for(i=(k+1);i<R;i++){
        x=this.elemenKe(i,k);
        for(j=0;j<C;j++){
          this.setElemenKe(i,j,(this.elemenKe(i,j)-this.elemenKe(k,j)*x));
        }
      }
      
      for(i=0;i<R;i++){
        int count0=0;
        j=0;
        while(this.elemenKe(i,j)==0 && j<(C-1)){
          if(this.elemenKe(i,j)==0){
            count0=count0+1;
          }
          j=j+1;
        }

        if(count0>i && count0!=R){
          for(j=0;j<C;j++){
            temp2=this.elemenKe(i,j);
            this.setElemenKe(i,j,this.elemenKe(count0,j));
            this.setElemenKe(count0,j,temp2);
          }
        }
      }

      for(i=0;i<R;i++){
        boolean found=false;
        j=0;
        while(!found && j<R){
          if(this.elemenKe(i,j)!=0){
            y=this.elemenKe(i,j);
            found=true;
          }
          else{
            j=j+1;
          }
        }

        for(j=0;j<C;j++){
          this.setElemenKe(i,j,(this.elemenKe(i,j)/y));
        }
      }
    }

    for (i=0;i<R;i++){
      for(j=0;j<C;j++){
        if (this.elemenKe(i,j)==-0.00){
          this.setElemenKe(i,j,0.00);
        }
      }
    }
    return this;
  }

  /**
   * Membuat matriks eselon baris tereduksi menggunakan Gauss-Jordan
   * @return Matriks eselon baris tereduksi
   */
  public Matriks gaussJordan(){
    /* Implementasi: metode Gauss, menggunakan pivot */
    /* Cari elemen x terbesar tiap kolom, misal i, lalu tukar baris ke-i dengan
     * baris dengan baris berisi x */
    int i, j, k;
    for(i = 0; i < this.baris(); i++){
      k = i;
      for(j = i+1; j < this.baris(); j++){
        if(elemenKe(j, i) > elemenKe(k, i)) k = j;
      }
      if(i != k) tukarBaris(i, k);
      /* Hapus elemen diatas dan dibawah lead */
      double mult;
      for(j = 0; j < this.baris(); j++){
        mult = elemenKe(j, i)/elemenKe(i, i);
        if(!Double.isNaN(mult) && Double.isFinite(mult)){
          for(k = 0; k < this.kolom(); k++){
            if(i != j) setElemenKe(j, k, elemenKe(j, k) - (elemenKe(i, k)*mult));
            else setElemenKe(j, k, elemenKe(j, k)/elemenKe(i, i));
          }
        }
      }
    }
    return this;
  }

  /**
   * Menukar satu baris indeks i dengan baris indeks j
   * @param i indeks baris pertama yang akan dipertukarkan
   * @param j indeks baris kedua yang akan dipertukarkan
   * @return Matriks yang baris i dan j sudah ditukar
   */
  public Matriks tukarBaris(int i, int j){
    double temp;
    for(int k = 0; k < kolom(); k++){
      temp = elemenKe(i, k);
      setElemenKe(i, k, elemenKe(j, k));
      setElemenKe(j, k, temp);
    }
    swaps++;
    return this;
  }

  /**
   * Menukar satu kolom indeks i dengan kolom indeks j
   * @param i indeks kolom pertama yang akan ditukar
   * @param j indeks kolom kedua yang akan ditukar
   * @return Matriks dengan kolom i dan j sudah tertukar
   */
  public Matriks tukarKolom(int i, int j){
    double temp;
    for(int k = 0; k < baris(); k++){
      temp = elemenKe(k, i);
      setElemenKe(k, i, elemenKe(k, j));
      setElemenKe(k, j, temp);
    }
    return this;
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

  /**
   * Mencari solusi SPL menggunakan metode Gauss-Jordan
   * @return Matriks augmented solusi SPL
   */
  public Matriks solusiSPLGaussJordan(){
    this.gaussJordan();
    return this;
  }

  /**
   * Menuliskan solusi SPL ke layar, jika memungkinkan maka dalam jawaban pasti
   * jika tidak maka menggunakan solusi parametrik
   * Jika tidak ada solusi, mengeluarkan "Tidak ada solusi untuk SPL"
   */
  public void tulisSolusiSPL(){
    if(this.kolom() == 1){
      /* Menggunakan Invers */
      if(Double.isNaN(elemenKe(0, 0))){
        /* Tidak ada solusi */
        System.out.println("Tidak ada solusi untuk SPL");
      }
      else{
        for(int i = 0; i < baris(); i++){
          /* Tulis Solusi SPL */
          System.out.printf("x%d = %.2f\n", i+1, elemenKe(i, 0));
        }
      }
    }
    else{
      /* Gauss atau Gauss-Jordan */
      boolean punyaSolusi = true;
      if(Math.abs(elemenKe(baris()-1, baris()-1)) < 1e-10){
        /* Punya solusi banyak, atau tidak punya solusi sama sekali */
        if(Math.abs(elemenKe(baris()-1, baris())) > 1e-10){
          /* tidak punya solusi */
          punyaSolusi = false;
        }
      }
      if(punyaSolusi){
        /* Cek tiap kolom, untuk yang punya lebih dari satu,
         * jadikan bentuk parametrik */
        int count, last;
        for(int i = 0; i < this.kolom()-1; i++){
          /* cari "first occurence" */
          count = 0;
          last = i;
          for(int j = 0; j < this.baris(); j++){
            if(Math.abs(elemenKe(j, i)) > 1e-10){
              last = j;
              count++;
            }
          }
          System.out.printf("x%d = ", i+1);
          if(count == 1){
            System.out.printf("%.2f", elemenKe(i, this.kolom()-1));
            for(int j = 0; j < this.kolom()-1; j++){
              if(Math.abs(elemenKe(last, j)) > 1e-10 && i != j){
                if(elemenKe(last, j) > 0) System.out.print(" + ");
                else System.out.print(" - ");
                System.out.printf("%.2f%c", Math.abs(elemenKe(last, j)), (char)('s'+j));
              }
            }
          }
          if(count > 1){
            System.out.print((char)('s'+i));
          }
          System.out.println();
        }
      }
      else{
        System.out.println("Tidak ada solusi untuk SPL");
      }
    }
  }
}

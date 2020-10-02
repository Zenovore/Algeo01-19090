package tubes;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class Matriks {
  private double[][] mat;
  private int bar, kol;
  private int swaps = 0;
  private final double epsilon = 1e-30;

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
   * @param r jumlah baris
   * @param c jumlah kolom
   * @param s scanner sumber pembacaan
   * @return Matriks hasil pembacaan
   */
  public static Matriks bacaMatriks(int r, int c, Scanner s){
    /* Baca Matriks */
    int i,j;
    Matriks m;

    m = new Matriks(r, c);
    for (i=0;i<r;i++){
      for (j=0;j<c;j++){
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
  public static Matriks bacaInterpolasi(Scanner s){
    /* Baca Matriks */
    int i,j, bar;
    Matriks m;

    System.out.printf("Masukan Jumlah Titik: ");
    bar = s.nextInt();
    m = new Matriks(bar, 2);
    for (i=0;i<bar;i++){
      for (j=0;j<2;j++){
        m.setElemenKe(i, j, s.nextDouble());
      }
    }
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

  /**
   * Membuat representasi string dari matriks
   */
  public String stringOfMatriks(){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < bar; i++){
      for(int j = 0; j < kol; j++){
        sb.append(String.format("%.2f", elemenKe(i, j)));
        if(j < kol - 1) sb.append(" ");
      }
      if(i < bar - 1) sb.append("\n");
    }
    return sb.toString();
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
    for(int i = 0; i < kol; i++){
      for(int j = 0; j < bar; j++){
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

  public Matriks tambahkolom(int a,Matriks b){
    int i,j;
    Matriks temp = new Matriks(this.baris(),this.kolom()+a);
    for(i=0;i<this.baris();i++){
      for(j=0;j<this.kolom()+a;j++){
        if (j<this.kolom()){
          temp.setElemenKe(i, j, this.elemenKe(i, j));
        }
        else {
          temp.setElemenKe(i, j, b.elemenKe(i, j-this.kolom()));
        }
      }
    }
    return temp;
  }
  public Matriks tambahkolomdepan(int a,Matriks b){
    int i,j;
    Matriks temp = new Matriks(this.baris(),this.kolom()+a);
    for(i=0;i<this.baris();i++){
      for(j=0;j<this.kolom()+a;j++){
        if (j<a){
          temp.setElemenKe(i, j, b.elemenKe(i, j));
        }
        else {
          temp.setElemenKe(i, j, this.elemenKe(i, j-a));
        } 
      }
    }
    return temp; 
  }
  public Matriks kolomElemenSama(int a,int b){
    Matriks temp = new Matriks(a,1);
    int i;
    for(i=0;i<a;i++){
      temp.setElemenKe(i,0,b);
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

  public Matriks tambahKolomDepan(int a){
    int i,j;
    Matriks sln=new Matriks(this.baris(),this.kolom());
    sln.salinMatriks(this);

    this.kol=this.kolom()+1;
    for(i=0;i<this.baris();i++){
      for(j=0;j<this.kolom();j++){
        if(j==0){
          this.setElemenKe(i,j,a);
        }
        else{
          this.setElemenKe(i,j,sln.elemenKe(i,j-1));
        }
      }
    }


    return this;

  }

  public Matriks hapusKolomMe(){
    int i,j;

    for(i=0;i<this.baris();i++){
      for(j=0;j<(this.kolom()-1);j++){
        this.setElemenKe(i,j,this.elemenKe(i,j+1));
      }
    }
    this.kol=this.kolom()-1;
    return this;
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
    double temp;
    boolean foundnot0;
    boolean token=false;

    z=this.elemenKe(0,0);

    if(z==0){
      foundnot0=false;
      int h=0;
      while(foundnot0==false && h<R){
        if(this.elemenKe(h,0)!=0){
          foundnot0=true;
        }
        else{
          h=h+1;
        }
      }
  
      if(foundnot0==true){
        for(j=0;j<C;j++){
          temp=this.elemenKe(0,j);
          this.setElemenKe(0,j,this.elemenKe(h,j));
          this.setElemenKe(h,j,temp);
        }
        z=this.elemenKe(0,0);
      }
      else if(foundnot0==false){
        this.hapusKolomMe();
        C=C-1;
        token=true;
        z=this.elemenKe(0,0);
        
      }
    }

    for(j=0;j<C;j++){
      this.setElemenKe(0,j,(this.elemenKe(0,j)/z));
    }

    for(k=0;k<(R);k++){
      for(i=(k+1);i<R;i++){
        x=this.elemenKe(i,k);
        for(j=0;j<C;j++){
          this.setElemenKe(i,j,(this.elemenKe(i,j)-this.elemenKe(k,j)*x));
        }
      }

      for(i=0;i<R;i++){
        boolean found=false;
        j=0;
        while(!found && j<C){
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

    int countsama=0;
    for(k=0;k<R;k++){
      for(i=k+1;i<R;i++){
       for(j=0;j<C;j++){
         if(this.elemenKe(i,j)==this.elemenKe(k,j)){
            countsama=countsama+1;
         }
       }
       if(countsama==C){
         for(j=0;j<C;j++){
           this.setElemenKe(i,j,0);
         }
       }
       countsama=0;
      } 
    }

    // sorting 0

    int min;
    int idxmin=0;
    foundnot0=false;
    int count0=0;
    for(k=0;k<(this.baris()-1);k++){
      min=99;
      for(i=(k+1);i<this.baris();i++){
        j=0;
        while(!foundnot0 && j<this.kolom()){
          if(this.elemenKe(i,j)==0){
            count0=count0+1;
            j=j+1;
          }
          else{
            foundnot0=true;
          }
        }
        if(count0<min){
          min=count0;
          idxmin=i;
        }
        count0=0;
        foundnot0=false;
      }
      this.tukarBaris(idxmin,(k+1));
    }

    if(token==true){
      this.tambahKolomDepan(0);
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
    int kolomOperasi, barisOperasi, lastFinished;
    kolomOperasi = 0;
    barisOperasi = 0;
    lastFinished = 0;
    while(lastFinished < baris() && kolomOperasi < kolom()){
      k = barisOperasi;
      for(j = lastFinished; j < baris(); j++){
        if(Math.abs(elemenKe(j, barisOperasi)) > Math.abs(elemenKe(k, barisOperasi))) k = j;
      }
      if(Math.abs(elemenKe(k, kolomOperasi)) > epsilon){
        if(barisOperasi != k) tukarBaris(barisOperasi, k);
        double mult = elemenKe(barisOperasi, kolomOperasi);
        for(i = 0; i < kolom(); i++){
          setElemenKe(barisOperasi, i, elemenKe(barisOperasi, i)/mult);
        }
        for(i = 0; i < baris(); i++){
          if(barisOperasi != i){
            mult = elemenKe(i, kolomOperasi)/elemenKe(barisOperasi, kolomOperasi);
            if(Double.isFinite(mult)){
              for(j = 0; j < kolom(); j++){
                setElemenKe(i, j, elemenKe(i, j) - elemenKe(barisOperasi, j)*mult);
              }
            }
          }
        }
        lastFinished++;
        kolomOperasi++;
      }
      barisOperasi++;
      if(barisOperasi >= baris()){
        barisOperasi = lastFinished;
        kolomOperasi++;
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
   * @param var string variabel / peubah x
   * @return String solusi SPL
   */
  public String stringSolusiSPL(String var, int offset){
    StringBuilder sb = new StringBuilder();
    DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    df.setMaximumFractionDigits(340);
    if(this.kolom() == 1){
      /* Menggunakan Invers */
      if(Double.isNaN(elemenKe(0, 0))){
        /* Tidak ada solusi */
        sb.append("Tidak ada solusi untuk SPL");
      }
      else{
        for(int i = 0; i < baris(); i++){
          /* Tulis Solusi SPL */
          sb.append(String.format("%s%d = ", var, i+offset));
          sb.append(df.format(elemenKe(i, 0)));
          if(i != baris()-1) sb.append(", ");
        }
      }
    }
    else{
      /* Gauss atau Gauss-Jordan */
      boolean punyaSolusi = true;
      boolean barisNol = true;
      int count, first;
      for(int i = 0; i < kolom()-1 && barisNol; i++){
        barisNol = barisNol && Math.abs(elemenKe(baris()-1, i)) < epsilon;
      }
      punyaSolusi = (barisNol && Math.abs(elemenKe(baris()-1, kolom()-1)) < epsilon) || (!barisNol);
      if(punyaSolusi){
        /* loop dari baris akhir, back-substitution */
        double sols[] = new double[kolom()-1];
        int useBaris[] = new int[kolom()-1];
        for(int i = 0; i < kolom()-1; i++){
          sols[i] = Double.NaN;
          useBaris[i] = -1;
        }
        for(int i = baris()-1; i >= 0; i--){
          // cari tidak-nol pertama
          // kalau masih ada, parametrik
          count = 0;
          first = 0;
          for(int j = kolom()-2; j >= 0; j--){
            if(Math.abs(elemenKe(i, j)) > epsilon){
              count++;
              first = j;
            }
          }
          if(count == 1){
            sols[first] = elemenKe(i, kolom()-1);
            useBaris[first] = i;
          }
          if(count > 1){
            sols[first] = elemenKe(i, kolom()-1);
            for(int j = first+1; j < kolom()-1; j++){
              if(Double.isFinite(sols[j])) sols[first] -= sols[j] * elemenKe(i, j);
            }
            useBaris[first] = i;
          }
        }
        for(int i = 0; i < kolom()-1; i++){
          sb.append(String.format("%s%d = ", var, i+offset));
          if(Double.isFinite(sols[i])) sb.append(df.format(sols[i]));
          else if(useBaris[i] == -1) sb.append("bebas");
          for(int j = i+1; j < kolom()-1 && useBaris[i] != -1; j++){
            if(Math.abs(elemenKe(useBaris[i], j)) > epsilon){
              if(elemenKe(useBaris[i], j) > 0) sb.append(" - ");
              else sb.append(" + ");
              if(Math.abs(elemenKe(useBaris[i], j))-1 > epsilon)
                sb.append(df.format(Math.abs(elemenKe(useBaris[i], j))));
              sb.append(String.format("%s%d", var, j+offset));
            }
          }
          if(i != kolom()-2) sb.append("\n");
        }
      }
      else{
        sb.append("Tidak ada solusi untuk SPL\n");
      }
    }
    return sb.toString();
  }
  public Matriks regresi(){
    Matriks a,y;
    Matriks b = new Matriks(this.baris(),this.kolom());
    Matriks c = new Matriks(this.baris(),1);
    System.out.println("\n");
    c.salinMatriks(this.hapusLastkolom());
    b.salinMatriks(this);
    b.tambahKolomDepan(1);
    b.transpose();
    a = kali(b,this.tambahKolomDepan(1));
    y = kali(b,c);
    a = a.tambahkolom(1, y);
    return(a);
  }

  public Matriks interpolasi(){
    Matriks inter=new Matriks(this.baris(),this.baris()+1);
    int i,j,m,n;

    m=0;
    n=0;

    for (i=0;i<inter.baris();i++){
      for (j=0;j<inter.kolom();j++){
        if(j!=(inter.kolom()-1)){
          inter.setElemenKe(i,j,Math.pow(this.elemenKe(m,n),j));
        }
        else if(j==(inter.kolom()-1)){
          n=1;
          inter.setElemenKe(i,j,this.elemenKe(m,n));
        }
      }
      m=m+1;
      n=0;
    }
    return inter;
  }
  public Matriks cramer(){
    int i,j,k;
    Matriks sln= new Matriks(this.baris(),this.kolom());
    Matriks sln2= new Matriks(this.baris(),this.kolom());
    Matriks nan= new Matriks(this.baris(),this.kolom());
    sln.salinMatriks(this);
    sln2.salinMatriks(this);
    sln.hapusLastkolom();
    sln2.hapusLastkolom();


    Matriks hsl=new Matriks(this.baris(),this.kolom());
    for(i=0;i<hsl.baris();i++){
      for(j=0;j<hsl.kolom();j++){
        if(i==j){
          hsl.setElemenKe(i,j,1);
        }
        else{
          hsl.setElemenKe(i,j,0);
        }
      }
    }

    for(i=0;i<hsl.baris();i++){
      for(j=0;j<hsl.kolom();j++){
       nan.setElemenKe(i,j,Double.NaN);
      }
    }


    double detTot=sln.determinan();
    double detCram;
    double hslElemen;

    if(detTot !=0 && sln.isKotak() ){
      for(k=0;k<sln.baris();k++){
        for(i=0;i<sln.baris();i++){
          for(j=0;j<sln.kolom();j++){
            if(j==k){
              sln.setElemenKe(i,j,this.elemenKe(i,(this.kolom()-1)));
            }
          }
        }
        detCram=sln.determinanReduksi();
        hslElemen=detCram/detTot;
        hsl.setElemenKe(k,(this.kolom()-1),hslElemen);
        sln.salinMatriks(sln2);
      }
      return hsl;
    }
    else{
      return nan;
    }
  }
  
  public double determinanReduksi(){
    int R=this.baris();
    int C=this.kolom();
    double x=0;
    double y=0;
    double z=0;
    int i,j,k;
    double temp,temp2;
    double det=1;

    int t=0;
    z=this.elemenKe(0,0);
    if(z==0){
      det=det*(-1);
    }
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
    det=det*z;

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
          det=det*(-1);
        }
      }

      for(i=0;i<R;i++){
        boolean found=false;
        j=0;
        while(!found && j<R){
          if(this.elemenKe(i,j)!=0){
            y=this.elemenKe(i,j);
            det=det*y;
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

    for (i=0;i<R;i++){
      for(j=0;j<C;j++){
        if (this.elemenKe(i,i)==0.00){
          det=0;
        }
      }
    }
    return det;
  }

  public String tulisInterpolasi(){
    int count = 0,i;
    String output="";
    boolean negatif=false;
    output += "y = ";
    for(i=0;i<this.baris();i++){
      if (i != this.baris()+1 && i !=0){
        if ((this.elemenKe(i,this.kolom()-1))<0){
          negatif = true;
          output += " - ";
        }
        else {output += " + ";}
      }
      if (negatif){
        output += String.format("%f",this.elemenKe(i,this.kolom()-1)*-1);
      }
      else {output += String.format("%f",this.elemenKe(i,this.kolom()-1));}
      if (count != 0){
        output += String.format("x^%d",count);
      }
      count++;
    }
    return output;
  }

  public Double hitungInterpolasi(Scanner s){
    double sum=0;
    int i;
    System.out.printf("Masukkan input x: ");
    bar = s.nextInt();
    for(i=0;i<this.baris();i++){
      sum += ((Math.pow(bar,i)) * (this.elemenKe(i, this.kolom()-1)));
      System.out.printf("y = %f\n",this.elemenKe(i, this.kolom()-1));
    }
    return sum;
  }
}

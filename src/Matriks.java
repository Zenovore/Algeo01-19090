package Matriks;

import java.io.*;
import java.util.Scanner;

class Matriks {
  private int[][] mat;
  private int baris, kolom;

  /**
   * Membuat matriks baru dengan ukuran baris x kolom
   * @param baris jumlah baris
   * @param kolom jumlah kolom
   */
  public Matriks(int baris, int kolom){
    this.baris = baris;
    this.kolom = kolom;
    this.mat = new int[baris][kolom];
  }

  /* Operasi Baca-Tulis */
  /**
   * Membaca matriks
   */
  public void BacaMatriks(){
    /* Baca Matriks */
  }

  /**
   * Menuliskan matriks ke stdout
   */
  public void TulisMatriks(){
    /* Tulis Matriks */
    for(int i = 0; i < baris; i++){
      for(int j = 0; j < kolom; j++){
        System.out.printf("%d", mat[i][j]);
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
  public int ElemenKe(int baris, int kolom){
    return mat[baris][kolom];
  }

  /* Setter atribut */
  /**
   * Mengubah elemen ke-i,j menjadi val
   * @param i baris
   * @param j kolom
   * @param val isi baru
   */
  public void SetElemenKe(int i, int j, int val){
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
    int[][] temp = new int[kolom][baris];
    for(int i = 0; i < baris; i++){
      for(int j = i; j < kolom; j++){
        temp[i][j] = this.ElemenKe(i, j);
      }
    }
    baris = kolom + baris;
    kolom = baris - kolom;
    baris = baris - kolom;
    this.mat = temp;
    return this;
  }
}

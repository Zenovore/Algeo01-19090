package tubes;

public class Polinom{
  private int derajat;
  private double koef[];
  
  /**
   * Buat Polinom berderajat n
   * @param n derajat polinom, bilangan bulat positif tidak nol
   */
  public Polinom(int n){
    this.derajat = n;
    this.koef = new double[n+1];
  }


  /* Getter Atribut */
  /**
   * Mengembalikan derajat polinom
   * @return derajat polinom
   */
  public int derajatPolinom(){
    return this.derajat;
  }

  /**
   * Mengembalikan koefisien polinom untuk x pangkat n
   * @param n suku polinom
   * @return koefisien untuk suku n
   */
  public double koefisienXN(int n){
    return this.koef[n];
  }

  /* Setter Atribut */

  /**
   * Membuat koefisien suku ke-n menjadi koef
   * @param n suku yang akan diset koefisiennya
   * @param koef koefisien baru
   */
  public void setKoefisienXN(int n, double koef){
    this.koef[n] = koef;
  }

  /* Fungsi Dasar */
  /**
   * Menginterpolasi fungsi polinom di titik x
   * @param x titik yang akan dicari nilai fungsinya
   * @return hasil interpolasi 
   */
  public double nilaiPolinom(double x){
    double res = 0;
    for(int i = 0; i < derajatPolinom()+1; i++){
      res += koefisienXN(i) * Math.pow(x, i);
    }
    return res;
  }
}

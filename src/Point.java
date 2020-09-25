package tubes;

import java.io.*;
import java.util.Scanner;
class Point{
  private double x, y;

  Point(double x, double y){
    this.x = x;
    this.y = y;
  }

  /**
   * Getter untuk absis
   * @return absis
   */
  public double getAbsis(){
    return this.x;
  }

  /**
   * Getter untuk ordinat
   * @return ordinat
   */
  public double getOrdinat(){
    return this.y;
  }

  /**
   * Membaca point dari stdin
   * @return Array of point
   */
  public static Point[] bacaPoint(){
    double x, y;
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    Point[] p = new Point[n];
    for(int i = 0; i < n; i++){
      x = s.nextDouble();
      y = s.nextDouble();
      p[i] = new Point(x, y);
    }

    return p;
  }

  /**
   * Membaca point dari File
   * @param file nama file
   * @return Array of point
   */
  /*
  public static Point[] bacaPoint(String file){
    double x, y;
    File f;
    int n;
    Point[] p;
    Scanner s;

    f = new File(file);
    s = new Scanner(f);
    n = 0;
    while(s.hasNextLine()){
      n++;
      s.nextLine();
    }
    s.close();
    s = new Scanner(f);
    p = new Point[n];
    for(int i = 0; i < n; i++){
      x = s.nextDouble();
      y = s.nextDouble();
      p[i] = new Point(x, y);
    }

    return p;
  }
  */
}


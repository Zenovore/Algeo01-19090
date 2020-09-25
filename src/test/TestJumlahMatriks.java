import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;
public class TestJumlahMatriks{
  Matriks a, b;
  int bar, kol;
  double epsilon = 1e-10;

  @Before
  public void setUp(){
    bar = 5;
    kol = 5;
    a = new Matriks(bar, kol);
    b = new Matriks(bar, kol);
    for(int i = 0; i < bar; i++){
      for(int j = 0; j < kol; j++){
        a.setElemenKe(i, j, i+j);
        b.setElemenKe(i, j, i-j);
      }
    }
  }

  @Test
  public void ujiJumlahMatriksPlus(){
    Matriks plus = Matriks.jumlah(a, b, true);
    for(int i = 0; i < bar; i++){
      for(int j = 0; j < kol; j++){
        assertEquals(2*i, plus.elemenKe(i, j), epsilon);
      }
    }
  }

  @Test
  public void ujiJumlahMatriksMinus(){
    Matriks minus = Matriks.jumlah(a, b, false);
    for(int i = 0; i < bar; i++){
      for(int j = 0; j < kol; j++){
        assertEquals(2*j, minus.elemenKe(i, j), epsilon);
      }
    }
  }
}

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;
public class TestAksesElemen{
  private Matriks matriks1;
  private Matriks matriks2;
  private int bar, kol;
  private double epsilon = 1e-10;

  @Before
  public void setUp(){
    bar = 10;
    kol = 10;
    matriks1 = new Matriks(bar, kol);
    matriks2 = new Matriks(bar, kol);
    for(int i = 0; i < bar; i++){
      for(int j = 0; j < kol; j++){
        matriks1.setElemenKe(i, j, 0);
        matriks2.setElemenKe(i, j, i+j);
      }
    }
  }

  @Test
  public void ujiAksesElemen(){
    for(int i = 0; i < bar; i++){
      for(int j = 0; j < kol; j++){
        assertEquals(0, matriks1.elemenKe(i, j), epsilon);
        assertEquals(i+j, matriks2.elemenKe(i, j), epsilon);
      }
    }
  }
}

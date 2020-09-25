import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestElemenKofaktorMatriks{
  private double epsilon = 1e-10;
  Matriks m;
  int bar, kol;

  @Before
  public void setUp(){
    bar = 3;
    kol = 3;
    m = new Matriks(bar, kol);
    m.setElemenKe(0, 0, 12);
    m.setElemenKe(0, 1, 13);
    m.setElemenKe(0, 2, 15);
    m.setElemenKe(1, 0, 7);
    m.setElemenKe(1, 1, 10);
    m.setElemenKe(1, 2, 9);
    m.setElemenKe(2, 0, 4);
    m.setElemenKe(2, 1, 8);
    m.setElemenKe(2, 2, 19);
  }

  @Test
  public void ujiElemenKofaktorMatriks(){
    assertEquals(118, m.entriKofaktor(0, 0), epsilon);
    assertEquals(168, m.entriKofaktor(1, 1), epsilon);
    assertEquals(29, m.entriKofaktor(2, 2), epsilon);
    assertEquals(-33, m.entriKofaktor(2, 0), epsilon);
  }
}

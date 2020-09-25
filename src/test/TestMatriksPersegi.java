import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestMatriksPersegi{
  Matriks a, b, c;

  @Before
  public void setUp(){
    a = new Matriks(3, 3);
    b = new Matriks(3, 2);
    c = new Matriks(6, 1);
  }

  @Test
  public void ujiMatriksPersegi(){
    assertTrue(a.isKotak());
    assertFalse(b.isKotak());
    assertFalse(c.isKotak());
  }
}

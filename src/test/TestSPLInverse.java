import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestSPLInverse{
  double epsilon = 1e-10;
  Matriks augment;

  @Before
  public void setUp(){
    augment = new Matriks(3, 4);
    augment.setElemenKe(0, 0, 2);
    augment.setElemenKe(0, 1, 3);
    augment.setElemenKe(0, 2, -1);
    augment.setElemenKe(0, 3, 5);
    augment.setElemenKe(1, 0, 4);
    augment.setElemenKe(1, 1, 4);
    augment.setElemenKe(1, 2, -3);
    augment.setElemenKe(1, 3, 3);
    augment.setElemenKe(2, 0, -2);
    augment.setElemenKe(2, 1, 3);
    augment.setElemenKe(2, 2, -1);
    augment.setElemenKe(2, 3, -1);
  }

  @Test
  public void ujiSolusiSPLInverse(){
    Matriks solusiExpect, solusiAlgo;
    solusiExpect = new Matriks(3, 1);
    solusiExpect.setElemenKe(0, 0, 1);
    solusiExpect.setElemenKe(1, 0, 2);
    solusiExpect.setElemenKe(2, 0, 3);

    solusiAlgo = augment.solusiSPLinvers();
    assertEquals(solusiExpect.kolom(), solusiAlgo.kolom());
    for(int i = 0; i < 3; i++){
      assertEquals(solusiExpect.elemenKe(i, 0), solusiAlgo.elemenKe(i, 0), epsilon);
    }
  }
}

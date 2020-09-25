import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestDeterminanMatriks{
  private double epsilon = 1e-10;
  Matriks a, b, c;

  @Before
  public void setUp(){
    a = new Matriks(2, 2);
    b = new Matriks(3, 3);
    c = new Matriks(4, 3);
    a.setElemenKe(0,0,1);
    a.setElemenKe(1,1,1);
    a.setElemenKe(0,1,0);
    a.setElemenKe(1,0,0);
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        if(i == j) b.setElemenKe(i, j, 1);
        else b.setElemenKe(i, j, 0);
      }
    }
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 3; j++){
        if(i == j) c.setElemenKe(i, j, 1);
        else c.setElemenKe(i, j, 0);
      }
    }
  }

  @Test
  public void ujiDeterminanMatriks(){
    assertEquals(1, a.determinan(), epsilon);
    assertEquals(1, b.determinan(), epsilon);
    assertTrue(Double.isNaN(c.determinan()));
  }
}

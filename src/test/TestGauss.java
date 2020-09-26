import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestGauss{
  double epsilon = 1e-10;
  Matriks m;

  @Before
  public void setUp(){
    m = new Matriks(3, 3);
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        if(i == j) m.setElemenKe(i, j, 1);
        else m.setElemenKe(i, j, 0);
      }
    }
  }

  @Test
  public void ujiMetodeGauss(){
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        if(i == j) assertEquals(1, m.elemenKe(i, j), epsilon);
        else assertEquals(0, m.elemenKe(i, j), epsilon);
      }
    }
  }
}

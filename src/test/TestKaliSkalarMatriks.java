import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestKaliSkalarMatriks{
  private double epsilon = 1e-10;
  Matriks a;

  @Before
  public void setUp(){
    a = new Matriks(3, 3);
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        a.setElemenKe(i, j, i+j);
      }
    }
  }

  @Test
  public void ujiKaliSkalarMatriks(){
    a.kaliSkalar(3);
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        assertEquals(3*(i+j), a.elemenKe(i, j), epsilon);
      }
    }
  }
}

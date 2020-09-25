import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestTransposeMatriks{
  private double epsilon = 1e-10;
  Matriks a;
  @Before
  public void setUp(){
    a = new Matriks(3, 3);
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        if(i < j)
          a.setElemenKe(i, j, 32);
        else
          a.setElemenKe(i, j, 0);
      }
    }
  }

  @Test
  public void ujiTransposeMatriks(){
    a.transpose();
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        if(i > j)
          assertEquals(32, a.elemenKe(i,j), 1e-10);
        else
          assertEquals(0, a.elemenKe(i, j), 1e-10);
      }
    }
  }
}

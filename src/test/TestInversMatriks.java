import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestInversMatriks{
  private double epsilon = 1e-10;
  Matriks awal, hasil;

  @Before
  public void setUp(){
    awal = new Matriks(3, 3);
    hasil = new Matriks(3, 3);
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        if(i == j){
          awal.setElemenKe(i, j, 1);
          hasil.setElemenKe(i, j, 1);
        }
        else{
          awal.setElemenKe(i, j, 0);
          hasil.setElemenKe(i, j, 0);
        } 
      }
    }
  }

  @Test
  public void ujiInverseMatriks(){
    awal = awal.invers();
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        assertEquals(hasil.elemenKe(i, j), awal.elemenKe(i, j), epsilon);
      }
    }
  }
}

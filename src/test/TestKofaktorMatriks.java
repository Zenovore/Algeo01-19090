import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestKofaktorMatriks{
  Matriks test, hasil;
  int bar, kol;
  private double epsilon = 1e-10;
  @Before
  public void setUp(){
    bar = 3;
    kol = 3;
    test = new Matriks(bar, kol);
    hasil = new Matriks(bar, kol);
    /*
     * 1 3  9
     * 4 5  10
     * 5 13 15
     * */
    test.setElemenKe(0, 0, 1);
    test.setElemenKe(0, 1, 3);
    test.setElemenKe(0, 2, 9);
    test.setElemenKe(1, 0, 4);
    test.setElemenKe(1, 1, 5);
    test.setElemenKe(1, 2, 10);
    test.setElemenKe(2, 0, 5);
    test.setElemenKe(2, 1, 13);
    test.setElemenKe(2, 2, 15);
    hasil.setElemenKe(0, 0, -55);
    hasil.setElemenKe(0, 1, -10);
    hasil.setElemenKe(0, 2, 27);
    hasil.setElemenKe(1, 0, 72);
    hasil.setElemenKe(1, 1, -30);
    hasil.setElemenKe(1, 2, 2);
    hasil.setElemenKe(2, 0, -15);
    hasil.setElemenKe(2, 1, 26);
    hasil.setElemenKe(2, 2, -7);
  }

  @Test
  public void ujiKofaktorMatriks(){
    test = test.kofaktor();
    for(int i = 0; i < bar; i++){
      for(int j = 0; j < kol; j++){
        assertEquals(hasil.elemenKe(i, j), test.elemenKe(i, j), epsilon);
      }
    }
  }

}

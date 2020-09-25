import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestKaliMatriks{
  Matriks a, b;
  @Before
  public void setUp(){
    a = new Matriks(1, 3);
    b = new Matriks(3, 1);
    for(int i = 0; i < 3; i++){
      a.setElemenKe(0, i, i+1);
      b.setElemenKe(i, 0, i+1);
    }
  }

  @Test
  public void ujiKaliMatriks(){
    Matriks hasil = Matriks.kali(a, b);
    assertEquals(hasil.kolom(), 1);
    assertEquals(hasil.baris(), 1);
    assertEquals(hasil.elemenKe(0,0), 14, 1e-10);
  }
}

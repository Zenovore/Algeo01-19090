import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestHapusLastKolom{
  private double epsilon = 1e-10;
  Matriks augment;

  @Before
  public void setUp(){
    augment = new Matriks(3, 4);
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 4; j++){
        augment.setElemenKe(i, j, i*(j+10));
      }
    }
  }

  @Test
  public void ujiHapusLastKolom(){
    Matriks kolom = augment.hapusLastkolom();
    assertEquals(augment.baris(), 3);
    assertEquals(augment.kolom(), 3);
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        assertEquals(i*(j+10), augment.elemenKe(i, j), epsilon);
      }
    }
    assertEquals(kolom.baris(), 3);
    assertEquals(kolom.kolom(), 1);
    for(int i = 0; i < 3; i++){
      assertEquals(i*13, kolom.elemenKe(i, 0), epsilon);
    }
  }
}

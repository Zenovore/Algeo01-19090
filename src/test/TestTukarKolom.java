import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestTukarKolom{
  private double epsilon = 1e-10;
  Matriks mawal, kolom, hasil, expected;

  @Before
  public void setUp(){
    mawal = new Matriks(3, 3);
    kolom = new Matriks(3, 1);
    expected = new Matriks(3, 3);

    mawal.setElemenKe(0, 0, 10);
    mawal.setElemenKe(0, 1, 20);
    mawal.setElemenKe(0, 2, 30);
    mawal.setElemenKe(1, 0, 40);
    mawal.setElemenKe(1, 1, 50);
    mawal.setElemenKe(1, 2, 60);
    mawal.setElemenKe(2, 0, 70);
    mawal.setElemenKe(2, 1, 80);
    mawal.setElemenKe(2, 2, 90);

    kolom.setElemenKe(0, 0, 420);
    kolom.setElemenKe(0, 1, 69);
    kolom.setElemenKe(0, 2, 69420);

    expected.salinMatriks(mawal);
    expected.setElemenKe(0, 0, 420);
    expected.setElemenKe(0, 1, 69);
    expected.setElemenKe(0, 2, 69420);
  }

  @Test
  public void ujiTukarKolom(){
    hasil = mawal.tukarKolom(kolom, 1);
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        assertEquals(expected.elemenKe(i, j), hasil.elemenKe(i, j), epsilon);
      }
    }
  }
}

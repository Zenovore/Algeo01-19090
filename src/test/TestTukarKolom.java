import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestTukarKolom{
  private double epsilon = 1e-10;
  Matriks mawal1, mawal2, kolom, hasil, expected;

  @Before
  public void setUp(){
    mawal1 = new Matriks(3, 3);
    mawal2 = new Matriks(3, 3);
    kolom = new Matriks(3, 1);
    expected = new Matriks(3, 3);
    hasil = new Matriks(3, 3);

    mawal1.setElemenKe(0, 0, 10);
    mawal1.setElemenKe(0, 1, 20);
    mawal1.setElemenKe(0, 2, 30);
    mawal1.setElemenKe(1, 0, 40);
    mawal1.setElemenKe(1, 1, 50);
    mawal1.setElemenKe(1, 2, 60);
    mawal1.setElemenKe(2, 0, 70);
    mawal1.setElemenKe(2, 1, 80);
    mawal1.setElemenKe(2, 2, 90);

    mawal2.salinMatriks(mawal1);

    kolom.setElemenKe(0, 0, 420);
    kolom.setElemenKe(1, 0, 69);
    kolom.setElemenKe(2, 0, 69420);

    expected.salinMatriks(mawal1);
    expected.setElemenKe(0, 1, 420);
    expected.setElemenKe(1, 1, 69);
    expected.setElemenKe(2, 1, 69420);
  }

  @Test
  public void ujiTukarKolomDenganMatriks(){
    hasil = mawal1.tukarKolom(kolom, 1);
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        assertEquals(expected.elemenKe(i, j), hasil.elemenKe(i, j), epsilon);
      }
    }
  }

  @Test
  public void ujiTukarKolomDenganIndeks(){
    mawal2.tukarKolom(0, 1);
    assertEquals(20, mawal2.elemenKe(0, 0), epsilon);
    assertEquals(10, mawal2.elemenKe(0, 1), epsilon);
    assertEquals(50, mawal2.elemenKe(1, 0), epsilon);
    assertEquals(40, mawal2.elemenKe(1, 1), epsilon);
    assertEquals(80, mawal2.elemenKe(2, 0), epsilon);
    assertEquals(70, mawal2.elemenKe(2, 1), epsilon);
  }
}

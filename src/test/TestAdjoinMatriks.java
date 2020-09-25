import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tubes.Matriks;

public class TestAdjoinMatriks{
  private double epsilon = 1e-10;
  Matriks m;
  @Before
  public void setUp(){
    m = new Matriks(3, 3);
    m.setElemenKe(0, 0, 12);
    m.setElemenKe(0, 1, 5);
    m.setElemenKe(0, 2, 13);
    m.setElemenKe(1, 0, 15);
    m.setElemenKe(1, 1, 20);
    m.setElemenKe(1, 2, 7);
    m.setElemenKe(2, 0, 3);
    m.setElemenKe(2, 1, 8);
    m.setElemenKe(2, 2, 6);
  }

  @Test
  public void ujiAdjoinMatriks(){
    m = m.adjoin();
    assertEquals(64, m.elemenKe(0, 0), epsilon);
    assertEquals(-69, m.elemenKe(1, 0), epsilon);
    assertEquals(60, m.elemenKe(2, 0), epsilon);
    assertEquals(74, m.elemenKe(0, 1), epsilon);
    assertEquals(33, m.elemenKe(1, 1), epsilon);
    assertEquals(-81, m.elemenKe(2, 1), epsilon);
    assertEquals(-225, m.elemenKe(0, 2), epsilon);
    assertEquals(111, m.elemenKe(1, 2), epsilon);
    assertEquals(165, m.elemenKe(2, 2), epsilon);
  }
}

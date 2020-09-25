import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tubes.Point;

public class PointTest{
  double epsilon = 1e-10;
  Point p1, p2;

  @Test
  public void ujiADTPoint(){
    p1 = new Point(3, 4);
    p2 = new Point(5, 12);
    assertEquals(p1.getAbsis(), 3, epsilon);
    assertEquals(p1.getOrdinat(), 4, epsilon);
    assertEquals(p2.getAbsis(), 5, epsilon);
    assertEquals(p2.getOrdinat(), 12, epsilon);
  }
}

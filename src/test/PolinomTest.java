import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tubes.Polinom;

public class PolinomTest{
  private double epsilon = 1e-10;

  @Test
  public void ujiADTPolinom(){
    Polinom p = new Polinom(5);
    for(int i = 0; i < 6; i++){
      p.setKoefisienXN(i, 6-i);
    }

    assertEquals(5, p.derajatPolinom());
    assertEquals(3, p.koefisienXN(3), epsilon);
    assertEquals(543, p.nilaiPolinom(3), epsilon);
  }
}

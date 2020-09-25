import static org.junit.Assert.assertEquals;
import tubes.Matriks;
import org.junit.Before;
import org.junit.Test;
public class TestBuatMatriks{
  private Matriks matriks1;
  private Matriks matriks2;

  @Before
  public void setUp(){
    matriks1 = new Matriks(10, 10);
    matriks2 = new Matriks(3, 4);
  }

  @Test
  public void UjiBuatMatriks(){
    assertEquals(10, matriks1.baris());
    assertEquals(10, matriks1.kolom());
    assertEquals(3, matriks2.baris());
    assertEquals(4, matriks2.kolom());
  }
}

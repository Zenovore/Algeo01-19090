import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  TestBuatMatriks.class,
  TestAksesElemen.class,
  TestJumlahMatriks.class,
  TestKaliMatriks.class,
  TestSalinMatriks.class,
  TestTransposeMatriks.class,
  TestMatriksPersegi.class,
  TestDeterminanMatriks.class,
  TestKofaktorMatriks.class,
  TestElemenKofaktorMatriks.class,
  TestAdjoinMatriks.class,
  TestKaliSkalarMatriks.class,
  TestInversMatriks.class,
  TestHapusLastKolom.class,
  TestGaussJordan.class,
  TestGauss.class,
  TestTukarKolom.class
})
public class MatriksTest {
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InverseTest {

    @Test
    public void PasitiveArguments() {
        Inverse inverse = new Inverse();
        double result = inverse.calculateInverse(2, 3, 4);
        double expected=1.0/24.0;
        assertEquals(expected, result, "iverse to the 24 should be equal to 1/24");
    }

    @Test
    public void ZeroProduct() {
        Inverse inverse = new Inverse();
        double result = inverse.calculateInverse(0, 2, 3);
        double expected=1.0/5.0;
        assertEquals(expected, result, "iverse to the 5 should be equal to 1/5");
    }

    @Test
    public void SumProductZero() {
        Inverse inverse = new Inverse();
        double result = inverse.calculateInverse(0,0,0);
        double expected=-1.0;
        assertEquals(expected, result, "0 + (0+1)*(0-1) should be equal to -1");
    }

    @Test
    public void NegativeValues() {
        Inverse inverse = new Inverse();
        double result = inverse.calculateInverse(-1,-1,-1);
        double expected=-1.0;
        assertEquals(expected, result, "iverse to the -1 should be equal to -1");
    }
}
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InverseTest {

    @Test
    public void PasitiveArguments() { // The purpuse of this test is to check normal work with positive arguments.
        //build
        Inverse inverse = new Inverse();
        //operate 
        double result = inverse.calculateInverse(2, 3, 4);
        // check
        double expected=0.041666666666666664;
        assertEquals(expected, result);
    }

    @Test
    public void ZeroProduct() { // The purpuse of this test is to check work where product of numbers is zero
        //build
        Inverse inverse = new Inverse();
        //operate 
        double result = inverse.calculateInverse(0, 2, 3);
        // check
        double expected=0.2;
        assertEquals(expected, result);
    }

    @Test
    public void SumProductZero() { // The purpuse of this test is to check work when sum and product of numbers are zero
        //build
        Inverse inverse = new Inverse();
        //operate 
        double result = inverse.calculateInverse(0,0,0);
         // check
        double expected=-1.0;
        assertEquals(expected, result);
    }

    @Test
    public void NegativeValues() { // The purpose of this test is to check work when product is negative
        //build
        Inverse inverse = new Inverse();
        //operate 
        double result = inverse.calculateInverse(-1,-1,-1);
        // check
        double expected=-1.0;
        assertEquals(expected, result);
    }
}
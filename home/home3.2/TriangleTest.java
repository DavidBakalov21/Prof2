import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TriangleTest {

    @Test
    public void normalUseCase() {
        //build
        double first = 3;
        double second = 4;
        double third = 5;
        Triangle triangle = new Triangle(first,second,third);
        //operate 
        String result = triangle.getArea();
        // check
        String expected="6,00";
        assertEquals(expected, result);
    }

    @Test
    public void NumbersAfterComma() {
        //build
        double first = 2;
        double second = 2;
        double third = 2;
        Triangle triangle = new Triangle(first,second,third);
        //operate 
        String result = triangle.getArea();
        // check
        String expected="1,73";
        assertEquals(expected, result);
    }

    @Test
    public void NegativeSide() {
        //build
        double first = -1;
        double second = 2;
        double third = 2;
        Triangle triangle = new Triangle(first,second,third);
        //operate 
        String result = triangle.getArea();
        // check
        String expected="triangle is invalid";
        assertEquals(expected, result);
    }

    @Test
    public void NormalHeight() {
        //build
        double first = 2;
        double second = 2;
        double third = 2;
        Triangle triangle = new Triangle(first,second,third);
        //operate 
        String result = triangle.getHeight('a');
        // check
        String expected="1,73";
        assertEquals(expected, result);
    }

    @Test
    public void InvalidTriangle() {
        //build
        double first = -2;
        double second = 2;
        double third = 2;
        Triangle triangle = new Triangle(first,second,third);
        //operate 
        String result = triangle.getHeight('a');
        // check
        String expected="triangle is invalid";
        assertEquals(expected, result);
    }

    @Test
    public void InvalidSide() {
        //build
        double first = 2;
        double second = 2;
        double third = 2;
        Triangle triangle = new Triangle(first,second,third);
        //operate 
        String result = triangle.getHeight('n');
        // check
        String expected="Invalid side";
        assertEquals(expected, result);
    }

    @Test
    public void normalTestC() {
        //build
        double first = 3;
        double second = 4;
        double third = 2;
        Triangle triangle = new Triangle(first,second,third);
        //operate 
        String result = triangle.getHeight('c');
        // check
        String expected="2,90";
        assertEquals(expected, result);
    }
}

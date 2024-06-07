import org.junit.jupiter.api.Test;  
import static org.junit.jupiter.api.Assertions.*;  
  
import java.util.Optional;  
  
public class TriangleTest {  
  
    @Test  
    public void normalUseCase() {  
        //build  
        double first = 3;  
        double second = 4;  
        double third = 5;  
        Triangle triangle = new Triangle(first, second, third);  
  
        //operate  
        Optional<Double> result = triangle.calculateArea();  
  
        //check  
        assertTrue(result.isPresent());  
        assertEquals(6.0, result.get());  
    }  
  
    @Test  
    public void NumbersAfterComma() {  
        //build  
        double first = 6;  
        double second = 2;  
        double third = 2;  
        Triangle triangle = new Triangle(first, second, third);  
  
        //operate  
        Optional<Double> result = triangle.calculateArea();  
  
        //check  
        assertFalse(result.isPresent());  
      
    }  
  
    @Test  
    public void NegativeSide() {  
        //build  
        double first = -1;  
        double second = 2;  
        double third = 2;  
        Triangle triangle = new Triangle(first, second, third);  
  
        //operate  
        Optional<Double> result = triangle.calculateArea();  
  
        //check  
        assertFalse(result.isPresent());  
    }  
  
    @Test  
    public void NormalHeight() {  
        //build  
        double first = 2;  
        double second = 2;  
        double third = 2;  
        Triangle triangle = new Triangle(first, second, third);  
        Optional<Side> side = Side.fromChar('a'); 
 
        //operate  
        Optional<Double> result = triangle.getHeight(side.get());  
  
        //check  
        assertTrue(result.isPresent());  
        assertEquals(1.7320508075688772, result.get(), 0.0001);  
    }  
  
    @Test  
    public void InvalidTriangle() {  
        //build  
        double first = -2;  
        double second = 2;  
        double third = 2;  
        Triangle triangle = new Triangle(first, second, third);  
        Optional<Side> side = Side.fromChar('a'); 
 
        //operate  
        Optional<Double> result = triangle.getHeight(side.get());  
  
        //check  
        assertFalse(result.isPresent());  
    }  
  
    @Test  
    public void InvalidSide() {  
        //build  
        double first = 2;  
        double second = 2;  
        double third = 2;  
        Triangle triangle = new Triangle(first, second, third);  
        Optional<Side> side = Side.fromChar('n'); 
        Optional<Double> result = Optional.empty(); 
 
         //operate 
        if (side.isPresent()) { 
            result = triangle.getHeight(side.get()); 
        } 
 
        // check 
        assertFalse(result.isPresent());  
    }  
  
    @Test  
    public void normalTestC() {  
        //build  
        double first = 3;  
        double second = 4;  
        double third = 2;  
        Triangle triangle = new Triangle(first, second, third);  
        Optional<Side> side = Side.fromChar('c'); 
         
        //operate  
        Optional<Double> result = triangle.getHeight(side.get());  
  
        //check  
        assertTrue(result.isPresent());  
        assertEquals(2.9047375096555625, result.get());  
    }  
}
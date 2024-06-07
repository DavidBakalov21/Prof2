import java.util.Optional;  
  
  
enum Side { 
    A('a'), 
    B('b'), 
    C('c'); 
 
    private final char sideChar; 
 
    Side(char sideChar) { 
        this.sideChar = sideChar; 
    } 
 
    public char getSideChar() { 
        return sideChar; 
    } 
 
    public static Optional<Side> fromChar(char sideChar) { 
        for (Side side : values()) { 
            if (side.getSideChar() == sideChar) { 
                return Optional.of(side); 
            } 
        } 
        return Optional.empty(); 
    } 
} 
  
public class Triangle {  
    private double a;  
    private double b;  
    private double c;  
    public Triangle(double a, double b, double c) {  
        this.a = a;  
        this.b = b;  
        this.c = c;  
    }  
  
  
    public Optional<Double> getHeight(Side side) { 
        if (!isValid()) { 
            return Optional.empty(); 
        } 
 
        Optional<Double> areaOpt = calculateArea(); 
        if (!areaOpt.isPresent()) { 
            return Optional.empty(); 
        } 
        double area = areaOpt.get(); 
 
        switch (side) { 
            case A: 
                return Optional.of((2 * area) / a); 
            case B: 
                return Optional.of((2 * area) / b); 
            case C: 
                return Optional.of((2 * area) / c); 
            default: 
                return Optional.empty(); 
        } 
    }   
  
    private boolean isValid() {  
        return ( a > 0 && b > 0 && c > 0 ) && ( a + b > c && a + c > b && b + c > a );  
    }  
  
    public Optional<Double> calculateArea(){  
        if (isValid()){  
            double s = (a + b + c) / 2;  
            double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));  
            return Optional.of(area);  
  
        }else{  
            return Optional.empty();  
        }  
    }  
      
}
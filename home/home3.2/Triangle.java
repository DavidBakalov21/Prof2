class Constants {
        public static final char a ='a';
        public static final char b = 'b';
        public static final char c = 'c';
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

    public String getArea() {
        if (isValid()) {
            return String.format("%.2f", calculateArea());
        } else {
            return "triangle is invalid";
        }
    }

    public String getHeight(char side) {
        if (!isValid()) {
            return "triangle is invalid";
        }
        if (side == Constants.a) {
            return String.format("%.2f", (2*calculateArea()) / a);
        }
        if (side == Constants.b) {
            return String.format("%.2f", (2*calculateArea()) / b);
        }
        if (side == Constants.c) {
            return String.format("%.2f", (2*calculateArea()) / c);
        }
        return "Invalid side";

    }

    private boolean isValid() {
        return ( a > 0 && b > 0 && c > 0 ) && ( a + b > c && a + c > b && b + c > a );
    }

    private double calculateArea(){
        double s = (a + b + c) / 2;
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        return area;
    }
    
}
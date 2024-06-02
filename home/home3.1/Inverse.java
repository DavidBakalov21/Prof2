public class Inverse {
    public double calculateInverse(double x, double y, double z) {
        double multiplied = x * y * z;
        if (multiplied!=0) {
            return 1/multiplied;
        }

        double sum = x + y + z;
        if (sum!=0) {
            return 1/sum;
        }
        return x + (y+1)*(z-1);
    }
}
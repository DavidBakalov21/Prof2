package home4_2;

public class MyCoolCalculator extends BaseMegaCalculator {
    private double coef1;
    private double coef2;
    private double coef3;
    private static final double VAL1 = 12.0;
    private static final double VAL2 = 3.0;
    private static final double VAL3 = 0.05;

    public MyCoolCalculator(double inCoef1, double inCoef2, double inCoef3) {
        this.coef1 = inCoef1;
        this.coef2 = inCoef2;
        this.coef3 = inCoef3;
    }

    @Override
    public double getPrice() {
        return coef1 * VAL1 + coef2 * VAL2 - coef3 * VAL3;
    }

    @Override
    public double getMinimalValue() {
        return Math.min(coef1 * VAL1, coef2 * VAL2);
    }

    @Override
    public String getReport() {
        return String.format("Some1 %.2f, another2 %.3f, and3 %.2f", coef1, coef2, coef3 * VAL2);
    }
}


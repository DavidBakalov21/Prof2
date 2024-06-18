package home4_2;

public class Main {
    public static void printResults(BaseMegaCalculator calculator) {
        System.out.println("Current price: " + calculator.getPrice());
        System.out.println("Current minimal value: " + calculator.getMinimalValue());
        System.out.println("Current report: " + calculator.getReport());
    }

    public static void main(String[] args) {

        BaseMegaCalculator calculator=new MyCoolCalculator(6.0, 12.1, 3.2);
        printResults(calculator);

        BaseMegaCalculator calculatorConstant=new ConstantCalculator();
        printResults(calculatorConstant);

        BaseMegaCalculator calculatorAdapted=new MegaAdapter(new LegacyCalculator(1.34, 5.4));
        printResults(calculatorAdapted);
    }
}
package final_project;

public class PaymentMethod{
    private String methodName;

    public PaymentMethod(String methodName){
        this.methodName=methodName;
    }

    public void setPaymentMethod(String newMethod){
        this.methodName=methodName;
    }
    public void viewAvailableMethods(){
        System.out.println("Availabe methods:");
        System.out.println("Visa");
        System.out.println("Master Card");
        System.out.println("Human souls");
    }
    public String getMethod(){
        return methodName;
    }
}
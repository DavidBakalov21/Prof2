package final_project;
import java.util.Arrays;

public class PaymentMethod{
    private String methodName;

    public PaymentMethod(String methodName){
        if (Arrays.asList(Constants.AvailabeMethods).contains(methodName)){
            this.methodName=methodName;
        }else{
            this.methodName=Constants.AvailabeMethods[0];
        }
    }

    public Boolean setPaymentMethod(String newMethod){
        if (Arrays.asList(Constants.AvailabeMethods).contains(newMethod)){
            this.methodName=newMethod;
            return true;
        }
        
        return false;
    }
    public void viewAvailableMethods(){
        System.out.println("Availabe methods:");
        for(String method: Constants.AvailabeMethods){
            System.out.println(method);
        }
    }
    public String getMethod(){
        return methodName;
    }
}
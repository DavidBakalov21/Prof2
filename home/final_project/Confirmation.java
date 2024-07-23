package final_project;
class Confirmation{
    public double ConfirmOrder(Client client){
        if (client.isUserAdmin()){
            client.addBonuses(client.getCart().getCartSize()*Constants.ADMIN_BONUS);
        }else{
            client.addBonuses(client.getCart().getCartSize()*Constants.SIMPLE_BONUS);
        }
        if (client.getCart().getCartSize()>0){
            double priceTotal=client.getCart().getCartPrice();
            priceTotal-=client.getBonuses()/100;
            client.getCart().clearCart();
            return priceTotal;
        } else{
            return 0.0;
        }
    }
}
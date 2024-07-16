package final_project;
class Confirmation{
    public double ConfirmOrder(Client client){
        if (client.isUserAdmin()){
            client.addBonuses(client.getCart().getCartSize()*Constants.ADMIN_BONUS);
        }else{
            client.addBonuses(client.getCart().getCartSize()*Constants.SIMPLE_BONUS);
        }
        double priceTotal=client.getCart().getCartPrice();
        priceTotal-=client.getBonuses()/100;
        client.getCart().clearCart();
        return priceTotal;
    }
}
package final_project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Tests {
    // Cart tests
    @Test
    public void TestCartSize() {
        // build
        Cart cart = new Cart("Apple-6.0,Apple-6.0,Apple-6.0");
        // operate
        int result = cart.getCartSize();
        // check
        int expected = 3;
        assertEquals(expected, result);
    }

    @Test
    public void ClearCartSizeTest() {
        // build
        Cart cart = new Cart("Apple-6.0,Apple-6.0,Apple-6.0");
        // operate
        cart.clearCart();
        int result = cart.getCartSize();
        // check
        int expected = 0;
        assertEquals(expected, result);
    }

    @Test
    public void AddProductTest() {
        // build
        Database db = new Database();
        Cart cart = new Cart("Apple-6.0,Apple-6.0,Apple-6.0");
        // operate
        cart.addToCart("Apple", db);
        int result = cart.getCartSize();
        // check
        int expected = 4;
        assertEquals(expected, result);
    }

    @Test
    public void RemoveProductTest() {
        // build
        Cart cart = new Cart("Apple-6.0,Apple-6.0,Apple-6.0");
        // operate
        cart.removeFromCart("Apple");
        int result = cart.getCartSize();
        // check
        int expected = 2;
        assertEquals(expected, result);
    }

    @Test
    public void RemoveProductUnExectingTest() {
        // build
        Cart cart = new Cart("Apple-6.0,Apple-6.0,Apple-6.0");
        // operate
        cart.removeFromCart("Appdddle");
        int result = cart.getCartSize();
        // check
        int expected = 3;
        assertEquals(expected, result);
    }

    @Test
    public void getCartPriceTest() {
        // build
        Cart cart = new Cart("Apple-6.0,Apple-6.0,Apple-6.0");
        // operate
        double result = cart.getCartPrice();
        // check
        double expected = 18.0;
        assertEquals(expected, result);
    }

    @Test
    public void getCartPriceZeroTest() {
        // build
        Cart cart = new Cart();
        // operate
        double result = cart.getCartPrice();
        // check
        double expected = 0.0;
        assertEquals(expected, result);
    }
    // Cart tests

    // Client tests
    @Test
    public void editNameNormal() {
        // build
        Client client = new Client("name", "storedPassword", "storedEmail", false,
                new Cart("Apple-6.0,Apple-6.0,Apple-6.0"),
                new PaymentMethod("paymentMethod"), 0);
        // operate
        boolean result = client.editName("newName");
        // check
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    public void editNameBad() {
        // build
        Client client = new Client("name", "storedPassword", "storedEmail", false,
                new Cart("Apple-6.0,Apple-6.0,Apple-6.0"),
                new PaymentMethod("paymentMethod"), 0);
        // operate
        boolean result = client.editName("");
        // check
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void editPasswordNormalTest() {
        // build
        Client client = new Client("name", "storedPassword", "storedEmail", false,
                new Cart("Apple-6.0,Apple-6.0,Apple-6.0"),
                new PaymentMethod("paymentMethod"), 0);
        // operate
        boolean result = client.editPassword("sdvsv");
        // check
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    public void editPasswordBadTest() {
        // build
        Client client = new Client("name", "storedPassword", "storedEmail", false,
                new Cart("Apple-6.0,Apple-6.0,Apple-6.0"),
                new PaymentMethod("paymentMethod"), 0);
        // operate
        boolean result = client.editPassword("");
        // check
        boolean expected = false;
        assertEquals(expected, result);
    }
    // Client tests

    // Confirmation
    @Test
    public void confirmationNormalNotAdminTest() {
        // build
        Confirmation confirmation = new Confirmation();
        Client client = new Client("name", "storedPassword", "storedEmail", false,
                new Cart("Apple-6.0,Apple-6.0,Apple-6.0"),
                new PaymentMethod("paymentMethod"), 294);
        // operate
        double result = confirmation.ConfirmOrder(client);
        // check
        double expected = 15.0;
        assertEquals(expected, result);
    }

    @Test
    public void confirmationEmptyNotAdminTest() {
        // build
        Confirmation confirmation = new Confirmation();
        Client client = new Client("name", "storedPassword", "storedEmail", false,
                new Cart(""),
                new PaymentMethod("paymentMethod"), 294);
        // operate
        double result = confirmation.ConfirmOrder(client);
        // check
        double expected = 0.0;
        assertEquals(expected, result);
    }

    @Test
    public void confirmationNormalAdminTest() {
        // build
        Confirmation confirmation = new Confirmation();
        Client client = new Client("name", "storedPassword", "storedEmail", true,
                new Cart("Apple-6.0,Apple-6.0,Apple-6.0"),
                new PaymentMethod("paymentMethod"), 288);
        // operate
        double result = confirmation.ConfirmOrder(client);
        // check
        double expected = 15;
        assertEquals(expected, result);
    }

    @Test
    public void confirmationEmptyAdminTest() {
        // build
        Confirmation confirmation = new Confirmation();
        Client client = new Client("name", "storedPassword", "storedEmail", true,
                new Cart(""),
                new PaymentMethod("paymentMethod"), 288);
        // operate
        double result = confirmation.ConfirmOrder(client);
        // check
        double expected = 0;
        assertEquals(expected, result);
    }
    // Confirmation

    // Database
    @Test
    public void readProductsTest() {
        // build
        Database db = new Database();
        // operate
        int result = db.readProductsFromFile("products.txt").size();
        // check
        int expected = 3;
        assertEquals(expected, result);
    }

    @Test
    public void findProductsTest() {
        // build
        Database db = new Database();
        // operate
        String result = db.findProduct("Apple").getName();
        // check
        String expected = "Apple";
        assertEquals(expected, result);
    }

    @Test
    public void findUnexistingProductsTest() {
        // build
        Database db = new Database();
        // operate
        String result = db.findProduct("Applee").getName();
        // check
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void logInTest() throws Exception {
        // build
        Database db = new Database();
        // operate
        String result = db.logIn("bd", "gfbgb@dfb.com").getName();
        // check
        String expected = "fg";
        assertEquals(expected, result);
    }

    @Test
    public void logInFailTest() throws Exception {
        // build
        Database db = new Database();
        // operate
        Client result = db.logIn("bddsv", "gfbgasb@dfb.com");
        // check
        String expected = null;
        assertEquals(expected, result);
    }

    // Database

    // PaymentMethod
    @Test
    public void PaymentSetTest() {
        // build
        PaymentMethod payment = new PaymentMethod("Visa");
        // operate
        boolean result = payment.setPaymentMethod("Master Card");
        // check
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    public void PaymentSetFailTest() {
        // build
        PaymentMethod payment = new PaymentMethod("Visa");
        // operate
        boolean result = payment.setPaymentMethod("Msdvsd");
        // check
        boolean expected = false;
        assertEquals(expected, result);
    }
    // PaymentMethod
}

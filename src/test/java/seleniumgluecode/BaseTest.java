package seleniumgluecode;

import org.openqa.selenium.WebDriver;
import pom.CartPage;
import pom.CheckoutPage;
import pom.InventoryPage;
import pom.LoginPage;

public class BaseTest {

    protected WebDriver driver = Hooks.getDriver();
    protected LoginPage loginPage = new LoginPage();
    protected InventoryPage inventoryPage = new InventoryPage();
    protected CheckoutPage checkoutPage = new CheckoutPage();
    protected CartPage cartPage = new CartPage();


}

package seleniumgluecode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pom.CartPage;
import pom.CheckoutPage;
import pom.InventoryPage;
import pom.LoginPage;

public class BaseTest {

    protected WebDriver driver = Hooks.getDriver();
    protected LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
    protected InventoryPage inventoryPage = new InventoryPage(driver);
    protected CheckoutPage checkoutPage = new CheckoutPage();
    protected CartPage cartPage = new CartPage();


}

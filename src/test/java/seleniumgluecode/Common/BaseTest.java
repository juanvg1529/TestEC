package seleniumgluecode.Common;

import APIresources.APIRepositories;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pom.*;

public class BaseTest {

    protected WebDriver driver = Hooks.getDriver();
       protected LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
    protected InventoryPage inventoryPage = new InventoryPage(driver);
    protected CartPage cartPage = new CartPage(driver);
    protected CheckoutPageStepOne checkoutPageStepOne = new CheckoutPageStepOne(driver);
    protected CheckoutPageStepTwo checkoutPageStepTwo = new CheckoutPageStepTwo(driver);
    protected CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
    protected APIRepositories apiRepositories = new APIRepositories();
    protected ScenarioContext scenarioContext = Hooks.getScenarioContext();
    public void printConsole(String text){
        System.out.println(text);

    }


}

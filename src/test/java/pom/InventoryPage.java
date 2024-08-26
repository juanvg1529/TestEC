package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver){
        super(driver);
    }


    private By inventoryContainer =  By.className("inventory_container");

    private By menuProductsButton = By.id("react-burger-menu-btn");
    private By cartElement = By.className("shopping_cart_link");
    @FindBy(xpath ="/html/body/div/div/div/div[1]/div[2]/span" )
    private WebElement inventoryPageValidation;
     private By logoutField = By.id("logout_sidebar_link");

    public boolean inventoryDisplayed() throws Exception{
        return this.isDisplayed(inventoryContainer);
    }

    public boolean getValidationInventaryPage() throws Exception{
        return  this.isDisplayed(cartElement);
    }

    public LoginPage logOut() throws Exception{
       this .isDisplayed(menuProductsButton);
       this.click(menuProductsButton);
       this.click(logoutField);
        return  new LoginPage(driver);
    }
}

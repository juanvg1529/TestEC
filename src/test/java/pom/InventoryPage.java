package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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
     private By inventoryItemMarket= By.className("inventory_item");
     private By inventoryItemTitleMarket = By.className("inventory_item_name");
     private By inventoryItemDescription=  By.className("inventory_item_desc");
     private By addButon= By.xpath(".//button[contains(@data-test, 'add-to-cart')]");
     private By shoppingBadge = By.className("shopping_cart_badge");
     private By cartItem= By.className("cart_item");
     private By filterOptionElement= By.cssSelector(".product_sort_container");


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
   public String addItemToTheCart(String itemToBuy) throws Exception{
       List<WebElement> itemsMarket= driver.findElements(inventoryItemMarket);
        String productNameSelected= null;
        for(WebElement element:itemsMarket){
         String productName=element.findElement(inventoryItemTitleMarket).getText();
            if( itemToBuy.contains(productName)){
                this.addItemToCart(productName);
                this.printConsole("Product Added "+productName);
                productNameSelected = productName;
          }
        }
        return productNameSelected;
    }
    public void addItemToCart(String itemName)  throws  Exception{
        WebElement itemContainer = driver.findElement(By.xpath("//div[@data-test='inventory-item-description' and .//div[@data-test='inventory-item-name' and text()='" + itemName + "']]"));
        WebElement addToCartButton = itemContainer.findElement(addButon);
        this.click(addToCartButton);
    }
    public boolean isItemAddedTotheCart() throws  Exception{
         return this.isDisplayed(shoppingBadge);
    }
    public CartPage goToCheckoutItems() throws Exception{
        this.click(cartElement);
        return new CartPage(driver);
    }
    public void selectOptionFromDropdown(String optionText) {
        WebElement dropdownElement = driver.findElement(filterOptionElement);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated( filterOptionElement)).isDisplayed();
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(optionText);
    }
    public List<String> displayItemInformation() throws Throwable{
        List<String> namesItems = new ArrayList<>();
        List<WebElement> itemsMarket= driver.findElements(inventoryItemMarket);
        String productNameSelected= null;
        for(WebElement element:itemsMarket) {
            String productName = element.findElement(inventoryItemTitleMarket).getText();
            String productDescription = element.findElement(inventoryItemDescription).getText();
            namesItems.add(productName);
            this.printConsole("Product Name =>"+productName);
            this.printConsole("----------------------------------------");
            this.printConsole("Desc \n "+productDescription);
            this.printConsole("----------------------------------------");
        }
        return  namesItems;
    }



}

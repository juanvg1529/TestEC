package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage{

    private By yourCartText = By.xpath("/html/body/div/div/div/div[1]/div[2]/span");
    private By cartItem= By.className("cart_item");
    private By inventoryTitleMarket= By.className("inventory_item_name");
    private  By checkoutButton = By.id("checkout");
    private By removeItemButton = By.xpath(".//button[contains(@data-test, 'remove')]");
    private By shoppingBadge = By.className("shopping_cart_badge");
    public CartPage(WebDriver driver){
        super(driver);
    }

    public String validationCartPage() throws Exception{
        this.isDisplayed(yourCartText);
       return this.getText(yourCartText);
    }

    public List<String> checkoutItemsToBuy(String itemToBuy) throws Exception{
        List<String> itemNameMarketList= new ArrayList<>();
        List<WebElement> itemsMarket= driver.findElements(cartItem);
        String productName= null;
        for(WebElement element:itemsMarket){
            productName=element.findElement(inventoryTitleMarket).getText();
            if( itemToBuy.contains(productName)){
                itemNameMarketList.add(productName);
                this.printConsole("Product Added "+productName);
            }
        }
        return itemNameMarketList;
    }

    public boolean areAllTrue(List<Boolean> booleanList) {
        for (Boolean value : booleanList) {
            if (!value) {
                return false;
            }
        }
        return true;
    }
    public CheckoutPageStepOne clickCheckoutbutton() throws Exception{
        this.click(checkoutButton);
        return  new CheckoutPageStepOne(driver);
    }
    public void removeItem( String itemName) throws Exception{
        String itemXPath = "//div[@data-test='inventory-item']//div[@data-test='inventory-item-name' and text()='" + itemName + "']/ancestor::div[@data-test='inventory-item']";
        WebElement itemContainer = driver.findElement(By.xpath(itemXPath));
        WebElement addToCartButton = itemContainer.findElement(removeItemButton);
        this.click(addToCartButton);
    }
    public boolean checkNumberOfItemsToBuy(String number) throws Exception{
        boolean value= false;
        this.isDisplayed(shoppingBadge);
        var valueOfShoppingCart = this.getText(shoppingBadge);
        if(valueOfShoppingCart.contains(number)){
            value =true;
        }
        return value;
    }

}

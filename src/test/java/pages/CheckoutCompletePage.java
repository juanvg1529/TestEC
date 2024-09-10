package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage{
   private By completeOrderText = By.className("complete-header");
   private By backToProductsButton = By.id("back-to-products");
    private By shoppingBadge = By.className("shopping_cart_badge");
    public CheckoutCompletePage(WebDriver driver){
        super(driver);
    }

    public String getCompleteOrderText()throws Exception{
        return this.getText(completeOrderText);
    }
    public InventoryPage clickBackHomeButton () throws Exception{
        this.click(backToProductsButton);
        return  new InventoryPage(driver);
    }
    public boolean elementsNotAddedToCart() throws Exception{
       return this.isNotDisplayed(shoppingBadge);
    }
}

package pom;

import io.cucumber.java.bs.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPageStepTwo extends BasePage{

    private By checkoutOverViewField = By.xpath("/html/body/div/div/div/div[1]/div[2]/span");
    private By summaryValueElement = By.className("summary_value_label");
    private By summaryInfoElement = By.className("summary_info_label");
    private By finishButtonElement = By.id("finish");
    public CheckoutPageStepTwo(WebDriver driver){
        super(driver);
    }


    public String validationCheckoutInformation() throws Exception{
        this.isDisplayed( checkoutOverViewField);
        return this.getText(checkoutOverViewField);
    }

    public List<List<String>> checkoutPaymentInfo() throws Exception{
        List<List<String>> itemNameMarketList= new ArrayList<>();
        List <String> valueInfo= new ArrayList<>();
        List<String> valuePayment = new ArrayList<>();
        List<WebElement> valueInfoListElements= driver.findElements(summaryInfoElement);
        List<WebElement> valuePaymentListElements= driver.findElements(summaryValueElement);
        for (WebElement element1:valuePaymentListElements){
            valueInfo.add(element1.getText());
            itemNameMarketList.add(valueInfo);
        }
        for(WebElement element:valueInfoListElements) {
            valuePayment.add(element.getText());
            itemNameMarketList.add(valuePayment);
        }

        return itemNameMarketList;
    }

    public CheckoutCompletePage clickFinishButton() throws Exception{
        this.click(finishButtonElement);
        return  new CheckoutCompletePage(driver);
    }
}

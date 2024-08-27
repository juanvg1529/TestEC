package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPageStepOne extends BasePage{
    public CheckoutPageStepOne(WebDriver driver){
        super(driver);
    }
    private By yourinformationElement = By.xpath("/html/body/div/div/div/div[1]/div[2]/span");
    private By firtsNameFieldCheckout = By.id("first-name");
    private By lastNameFieldCheckout = By.id("last-name");
    private By zipcodeFieldCheckout = By.id("postal-code");

    private By continueButton = By.id("continue");

    public String validationCheckoutInformation() throws Exception{
        this.isDisplayed( yourinformationElement);
        return this.getText(yourinformationElement);
    }

    public void setPersonalInfo(String name, String lastname,String zipcode) throws Exception{
        this.putText(firtsNameFieldCheckout,name);
        this.putText(lastNameFieldCheckout,lastname);
        this.putText(zipcodeFieldCheckout,zipcode);
    }

    public boolean areCredentialsCorrect() throws Exception{
        return this.isDisplayed(firtsNameFieldCheckout)&&this.isDisplayed(lastNameFieldCheckout) && this.isDisplayed(zipcodeFieldCheckout);
    }

    public CheckoutPageStepTwo clickCheckoutPage()throws  Exception{
          this.click(continueButton);
          return  new CheckoutPageStepTwo(driver);
    }
}

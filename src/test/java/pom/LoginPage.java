package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
    String titleLoginPage= "Swag Labs";
   private By loginCredentials =By.id("login_credentials");
   private By loginPassword =By.id("login_password");

    public By getLoginCredentials() {
        return loginCredentials;
    }
    public By getLoginPassword(){
        return loginPassword;
    }
    public String getTitleLoginPage(){
        return titleLoginPage;
    }
}

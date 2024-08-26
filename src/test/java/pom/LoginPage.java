package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){
        super(driver);
    }

    String titleLoginPage= "Swag Labs";
    @FindBy(className = "login_logo")
    public WebElement loginTitle ;
    @FindBy(id ="login_credentials" )
    private WebElement loginCredentials;
    @FindBy(className = "login_password")
    private WebElement loginPassword ;

    private By loginButton = By.id("login-button");

    private By userNameField = By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[1]/input");

    private By passwordField = By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[2]/input");


    public WebElement getLoginCredentials() {
        return loginCredentials;
    }
    public WebElement getLoginPassword(){
        return loginPassword;
    }
    public String getTitleLoginPage(){
        return titleLoginPage;
    }
    public boolean loginPageIsDisplayed() throws Exception{
        return  this.getTitle().equals(titleLoginPage) && this.getText(loginTitle).equals(titleLoginPage) ;
    }

    public void clickButtonLogin() throws Exception{
        this.click(loginButton);
    }
    public List<String> getAllUserNames() throws  Exception{
        List<String> listUsernames= new ArrayList<>();
        var userNameField= this.getText(getLoginCredentials());
        String[] users = userNameField.split("\n");
        for (String user : users) {
            if(user.contains("_user")) {
                listUsernames.add(user);
            }
        }
        return  listUsernames;
    }

    public String getPassword() throws  Exception{
        String password= null;
        var passwordField= this.getText(getLoginPassword());
        String[] usersPassword = passwordField.split("\n");
        for (String i : usersPassword) {
            if(i.contains("_")) {
                password= i;
            }
        }
        return  password;
    }

    public void setUser(String userSelected)throws  Exception{
        this.putText(userNameField,userSelected);
    }
    public void setPassword(String password)throws  Exception{
        this.putText(passwordField,password);
    }
    public boolean areCredentialsCorrect() throws Exception{
        return this.isDisplayed(userNameField)&&this.isDisplayed(passwordField);
    }
    public InventoryPage clickLoginButton() throws Exception{
        this.click(loginButton);
        return  new InventoryPage(driver);
    }
    public boolean validatesLoginButton() throws  Exception{
        return this.isDisplayed(loginButton);
    }
}

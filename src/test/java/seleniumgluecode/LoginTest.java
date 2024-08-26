package seleniumgluecode;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class LoginTest extends BaseTest{



    @Given("the user is at the SwagLabs home page")
    public void theUserIsAtTheSwagLabsHomePage() throws Throwable {

        Assert.assertEquals(loginPage.getTitleLoginPage(),driver.getTitle());
        Assert.assertTrue("",loginPage.loginPageIsDisplayed());
    }
    @Given("the user logs in")
    public void theUserLogsIn() throws  Throwable {

        List<String> usernames =loginPage.getAllUserNames();
        String usernameSelected = usernames.stream().filter(username->username.contains("user")).findAny().orElseThrow(() ->new RuntimeException("Not user found"));
        Assert.assertNotNull(usernameSelected,"Username not found");
        loginPage.printConsole("the username selected is ="+usernameSelected);
        var password= loginPage.getPassword();
        loginPage.printConsole("The password is = "+password);

        loginPage.setUser(usernameSelected);
        loginPage.setPassword(password);

        if(loginPage.areCredentialsCorrect()){
            inventoryPage=  loginPage.clickLoginButton();
            Assert.assertTrue("InventoryPage is not displayed",inventoryPage.inventoryDisplayed());
        }else{
            loginPage.printConsole("Wasn't possible to log in");
        }
    }
    @When("And the user validates that is logged in")
    public void whenTheUserValidatesThatIsLoggedIn() throws Throwable {
        loginPage.printConsole("Element " + loginPage.loginTitle);
        inventoryPage.inventoryDisplayed();
        Assert.assertTrue("InventoryPage is not displayed", inventoryPage.inventoryDisplayed());
        Assert.assertTrue("Cart not displayed", inventoryPage.getValidationInventaryPage());
    }
    @Then("the user validates that is logged in")
    public void thenTheUserValidatesThatIsLoggedIn() throws Throwable{
        loginPage.printConsole("Element "+loginPage.loginTitle);
        inventoryPage.inventoryDisplayed();
        Assert.assertTrue("InventoryPage is not displayed",inventoryPage.inventoryDisplayed());
        Assert.assertTrue("Cart not displayed",inventoryPage.getValidationInventaryPage());
    }
    @Then("the user logs out")
    public void theUserLogsOut() throws Throwable {
        loginPage= inventoryPage.logOut();
        Assert.assertTrue("Login not present",loginPage.validatesLoginButton());

    }


}

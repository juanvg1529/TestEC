package seleniumgluecode;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class LogintTest extends BaseTest{


    @Given("the user is at the SwagLabs home page")
    public void the_user_is_at_the_SwagLabs_home_page() throws Throwable {

        Assert.assertEquals(loginPage.getTitleLoginPage(),driver.getTitle());
        Assert.assertTrue("",loginPage.loginPageIsDisplayed());
        // Write code here that turns the phrase above into concrete actions
    }
    @Given("the user logs in")
    public void the_user_logs_in() throws  Throwable {

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
    @Then("the user validates that is logged in")
    @When("the user validates that is logged in")
    public void the_user_validates_that_is_logged_in() throws Throwable{
        loginPage.printConsole("Element "+loginPage.loginTitle);

        inventoryPage.inventoryDisplayed();
        Assert.assertTrue("InventoryPage is not displayed",inventoryPage.inventoryDisplayed());
        Assert.assertTrue("Cart not displayed",inventoryPage.getValidationInventaryPage());


        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the user logs out")
    public void the_user_logs_out() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        loginPage= inventoryPage.logOut();
        Assert.assertTrue("Login not present",loginPage.validatesLoginButton());



    }


}

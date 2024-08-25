package seleniumgluecode;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.LoginPage;

import java.util.ArrayList;
import java.util.List;

public class UI_Testing_EC_Cargo_Test extends BaseTest{


    @Given("the user is at the SwagLabs home page")
    public void the_user_is_at_the_SwagLabs_home_page() {

        Assert.assertEquals(loginPage.getTitleLoginPage(),driver.getTitle());
        // Write code here that turns the phrase above into concrete actions
    }
    @Given("the user select the option to log in")
    public void the_user_select_the_option_to_log_in() {
        WebElement loginCredentials = driver.findElement(loginPage.getLoginCredentials());
        var password =loginCredentials.getText();
        String[] parts = password.split("\n");
        List<String> usernames = new ArrayList<>();

        // Add all usernames to the list
        for (String part : parts) {
            if(part.contains("_user")) {
                usernames.add(part);
            }
        }
        String usernameSelected = usernames.stream().filter(username->username.contains("user")).findAny().orElseThrow(() ->new RuntimeException("Not user found"));
        Assert.assertNotNull(usernameSelected,"Username not found");

        System.out.println(usernameSelected);
    }
    @When("the user provide the user credentials")
    public void the_user_provide_the_user_credentials() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the user validates that is logged in")
    public void the_user_validates_that_is_logged_in() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the user logs out")
    public void the_user_logs_out() {
        // Write code here that turns the phrase above into concrete actions

    }
}

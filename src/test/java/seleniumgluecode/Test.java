package seleniumgluecode;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Test {

    private ChromeDriver driver;
    @Given("the user is at the SwagLabs home page")
    public void the_user_is_at_the_SwagLabs_home_page() {
        // Write code here that turns the phrase above into concrete actions
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();

    }
    @Given("the user select the option to log in")
    public void the_user_select_the_option_to_log_in() {
        WebElement loginCredentials = driver.findElement(By.id("login_credentials"));
        var password =loginCredentials.getText();
        String[] parts = password.split("\n");
        List<String> usernames = new ArrayList<>();

        // Add all usernames to the list
        for (String part : parts) {
            if(part.contains("_user")) {
                usernames.add(part);
            }
        }
        Optional<String> usernameSelected = usernames.stream().filter(username->username.contains("user")).findAny();

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

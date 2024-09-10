package testrunners.browserManager;
import org.openqa.selenium.firefox.FirefoxDriver;
public class FirefoxDriverManager extends DriverManager {

    @Override
    public  void createDriver(){
        System.setProperty("webdriver.gecko.driver","./src/test/resources/geckodriver/geckodriver.exe");
        driver = new FirefoxDriver();
    }
}

package seleniumgluecode;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;


public class Hooks {
    private static ChromeDriver driver;
    private  static  int numberOfCase= 0;
    @Before
    public static void setup(){
        numberOfCase++;
        System.out.println("Se esta ejecutando el escenario nro ="+numberOfCase);
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
    }
    @After
    public static void tearDown()
    {
        System.out.println("El escenario nro = "+numberOfCase+" se ejecuto correctamente");
        driver.quit();
    }
    public static ChromeDriver getDriver(){
        return driver;
    }
}

package seleniumgluecode;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import io.cucumber.plugin.event.PickleStepTestStep;
import runner.browserManager.DriverManager;
import runner.browserManager.DriverManagerFactory;
import runner.browserManager.DriverType;


public class Hooks {
    private static WebDriver driver;
    private DriverManager driverManager;
    private  static  int numberOfCase= 0;
    private static ScenarioContext scenarioContext;
    @Before
    public void setup(){
        numberOfCase++;
        scenarioContext = new ScenarioContext();
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        driver = driverManager.getDriver();
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();

    }
    @After
    public void tearDown()
    {
        System.out.println("El escenario nro = "+numberOfCase+" se ejecuto correctamente");
        driverManager.quitDriver();
    }
    public static WebDriver getDriver(){
        return driver;
    }

    public static ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}

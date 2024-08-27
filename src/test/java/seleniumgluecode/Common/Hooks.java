package seleniumgluecode.Common;
import APIresources.APIRepositories;
import io.cucumber.java.*;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import runner.browserManager.DriverManager;
import runner.browserManager.DriverManagerFactory;
import runner.browserManager.DriverType;
import seleniumgluecode.Common.ScenarioContext;


public class Hooks {
    private static WebDriver driver;
    private DriverManager driverManager;
    private  static  int numberOfCase= 0;
    private static ScenarioContext scenarioContext;
    @Before
    public void setup(Scenario scenario){
        scenarioContext = new ScenarioContext();
        APIRepositories apiRepositories = new APIRepositories();
        RestAssured.baseURI = apiRepositories.getURL();
        if (scenario.getSourceTagNames().contains("@UI")) {
            numberOfCase++;
            driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
            driver = driverManager.getDriver();
            driver.get("https://www.saucedemo.com");
            driver.manage().window().maximize();
        }
    }
    @After
    public void tearDown(Scenario scenario)
    {
        if (scenario.getSourceTagNames().contains("@UI")) {
            System.out.println("El escenario nro = " + numberOfCase + " se ejecuto correctamente");
            driverManager.quitDriver();
        }
    }
    public static WebDriver getDriver(){
        return driver;
    }

    public static ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}

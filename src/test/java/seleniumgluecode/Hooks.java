package seleniumgluecode;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import runner.browserManager.DriverManager;
import runner.browserManager.DriverManagerFactory;
import runner.browserManager.DriverType;


public class Hooks {
    private static WebDriver driver;
    private DriverManager driverManager;
    private  static  int numberOfCase= 0;
    @Before
    public void setup(){
        numberOfCase++;
        System.out.println("Se esta ejecutando el escenario nro ="+numberOfCase);
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

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        // Obtiene el nombre del step actual
        String stepName = scenario.getName();
        System.out.println("Step: " + stepName);
    }
}

package runner.browserManager;

import org.openqa.selenium.WebDriver;

public  abstract class DriverManager {

    protected WebDriver driver;
    protected abstract  void createDriver();

    /***
     * This method quit the driver
     */
    public void quitDriver(){
        if (driver!=null){
            driver.quit();
            driver=null;
        }
    }

    /***
     * This method retunr the driver if not exist
     * @return a driver object
     */
    public WebDriver getDriver(){
        if(driver==null){
            createDriver();
        }
        return driver;
    }
}


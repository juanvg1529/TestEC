package runner.browserManager;

import java.sql.DriverManager;

/***
 * Class used to handle the browser for the automation
 */
public class DriverManagerFactory {
    /***
     * Receives the browser that is wanted to automate
     * @param receives an enum
     * @return a driver object
     *
     */

    public static runner.browserManager.DriverManager getManager(DriverType type){
        runner.browserManager.DriverManager driverManager= null;

        switch (type){
            case CHROME:
                driverManager= new ChromeDriverManager();
                break;
            case FIREFOX:
                    driverManager= new FirefoxDriverManager();
            break;
            default:
                throw new UnsupportedOperationException("Browser option not defined");

        }
        return driverManager;
    }
}

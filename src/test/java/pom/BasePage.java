package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/***
 * This page is the one used to interact with all of methods that are going to be needed to interact
 * with selenium
 */
public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    /***
     * This method is used to click any element provided
     * @param element = Receives a WebEelement
     * @throws Exception Throws an exception if the element is not clickable
     */
    public void click(By element)throws Exception{
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        }catch (Exception ex){
            throw  new Exception("Element wasn't possible to click" + element);
        }
    }
    public void click(WebElement element)throws Exception{
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        }catch (Exception ex){
            throw  new Exception("Element wasn't possible to click" + element);
        }
    }

    /***
     *
     * @param element = Receives a WebEelement
     * @return Returns a boolean, true if finds the element, false if doesn't
     * @throws Exception Throws an exception if the element is not displayed
     */

    public boolean isDisplayed(By element)throws Exception{
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(element)).isDisplayed();
            return true;
        }catch (Exception ex){
            throw  new Exception("Element isn't displayed" + element);
        }
    }

    /***
     *
     * @param element = Receives a WebEelement
     * @return Return a String if it gets the text from the element selected
     * @throws Exception Throw an exception if the text isn't gotten
     */
    public String getText (By element)throws Exception{
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(driver.findElement(element))).getText();
        }catch (Exception ex){
            throw  new Exception("Element isn't wasn't possible to get the text" + element);
        }
    }
    public String getText(WebElement element)throws Exception{
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(element)).getText();
        }catch (Exception ex){
            throw  new Exception("Element isn't wasn't possible to get the text" + element);
        }
    }

    /***
     *
     * @return return the title of the page where the driver is working on
     * @throws Exception throws an exception if the driver doesn't get the element
     */
    public String getTitle ()throws Exception{
        try {
            return driver.getTitle();

        }catch (Exception ex){
            throw  new Exception("Wasn't possible to obtain the title of the Page");
        }
    }

    /***
     * this method is used to send text, eg, send text to form
     * @param element receives a web element to find
     * @param textToSend receives the text to send
     * @throws Exception Throws an exeception if the elemente is not able to send the text
     */

    public void putText(By element, String textToSend)throws Exception{
        try {
            driver.findElement(element).sendKeys(textToSend);
        }catch (Exception ex){
            throw  new Exception("Wasn't possible to send the text = "+textToSend+" to the element = "+element);
        }

    }

    public void putText(WebElement element, String textToSend)throws Exception{
        try {
            element.sendKeys(textToSend);
        }catch (Exception ex){
            throw  new Exception("Wasn't possible to send the text = "+textToSend+" to the element = "+element);
        }

    }
    public void printConsole(String text){
        System.out.println(text);

    }

    public boolean isElementClickable(By element, int time) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.elementToBeClickable(element)).findElement(element).click();
        } catch (Exception exception){
            throw  new Exception("Wasn't possible to wait until the element was clickable= "+element);
        }
        return true;
    }
    public boolean isElementClickable(WebElement element, int time)throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception exception){
            throw  new Exception("Wasn't possible to wait until the element was clickable= "+element);
        }

        return true;
    }

    public void waitUntilElement(WebElement element ,int seconds ){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }
    public void waitUntilElement(By  element ,int seconds ){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element))).isDisplayed();
    }


}

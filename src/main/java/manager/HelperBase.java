package manager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }
    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);
        if(text != null){
            element.sendKeys(text);
        }
    }
    public void clearNew(WebElement element) {
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);

    }
    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void click(By locator){
        WebElement element = wd.findElement(locator);
        element.click();
    }
    public boolean isElementPresent(By locator){
        List<WebElement> list = wd.findElements(locator);
        return list.size() > 0;
    }

    public boolean isAlertPresent(String message) {
        Alert alert = new WebDriverWait(wd,10)
                .until(ExpectedConditions.alertIsPresent());
        if (alert != null && alert.getText().contains(message)){
            pause(2000);
            alert.accept();
            //alert.accept(); -> click OK
            //alert.dismiss(); -> click cancel
            //alert.sendKeys("hello") -> type
            return true;
        }
        return false;
    }
    public void submit(){
        click(By.cssSelector("button[type='submit']"));
    }
    public String getMessage() {
        WebElement element = wd.findElement(By.cssSelector(".dialog-container>h2"));
        String text = element.getText();
        pause(2000);
        return text;

        //return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }
}

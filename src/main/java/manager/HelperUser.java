package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }
    public void openLoginForm(){
        click(By.cssSelector("a[href='/login?url=%2Fsearch']"));
    }
    public void  fillLoginForm(String email, String password){
        type(By.cssSelector("label[for='email']"), email);
        type(By.cssSelector("label[for='password']"), password);
    }
    public void submitLogin(){
        click(By.cssSelector("input[type='submit']"));
    }
    public void logout() {
        click(By.cssSelector("a[href='/logout?url=%2Fsearch']"));
    }
    public boolean isLogged() {
        return isElementPresent(By.cssSelector("a[href='/logout?url=%2Fsearch']"));
    }

}
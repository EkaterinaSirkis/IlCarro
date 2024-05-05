package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    public class LoginTests extends TestBase{
        @BeforeMethod
        public void preCondition(){
            if(app.getHelperUser().isLogged(){
                app.getHelperUser().logout();
            }
        }

        @Test
    public void loginSuccess(){
            app.getHelperUser().openLoginForm();
            app.getHelperUser().fillLoginForm("katia212277@gmail.com", "Kotikkk666!");
            app.getHelperUser().submitLogin();

            Assert.assertTrue(app.getHelperUser().isLogged());
        }


    }
}
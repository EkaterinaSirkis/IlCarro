package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

        @BeforeMethod
        public void preCondition(){
            if(app.getHelperUser().isLogged()){
                app.getHelperUser().logout();
            }
        }

        @Test
    public void loginSuccess() {
            app.getHelperUser().openLoginForm();
            app.getHelperUser().fillLoginForm("katia212277@gmail.com", "Kotikkk666!");
            app.getHelperUser().submit();
            app.getHelperUser().clickOKButton();

            Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

            Assert.assertTrue(app.getHelperUser().isLogged());
        }
    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("katia212277@gmail.com", "Kotikkk666!");
        app.getHelperUser().submit();
        // app.getHelperUser().clickOKButton();

        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

        Assert.assertTrue(app.getHelperUser().isLogged());
    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOKButton();
      }
      
      @Test
      public void loginWrongEmail(){
          app.getHelperUser().openLoginForm();
          app.getHelperUser().fillLoginForm("katia212277gmail.com", "Kotikkk666!");
          app.getHelperUser().submit();
          Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
          Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

      }
      @Test
      public void loginWrongPassword(){
          app.getHelperUser().openLoginForm();
          app.getHelperUser().fillLoginForm("katia212277@gmail.com", "kotikkk666");
          app.getHelperUser().submit();
          Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");

      }
    @Test
    public void loginUnregistered(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("katia212277@gmail.com", "kotikkk666");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");

    }

    }

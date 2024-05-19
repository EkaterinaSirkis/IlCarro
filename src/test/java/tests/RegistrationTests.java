package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSucces(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        System.out.println(i);
        //*********************************************
        System.out.println(System.currentTimeMillis());
        int z = (int) (System.currentTimeMillis()/1000) % 3600;
        System.out.println(z);

        User user = new User()
                .setFirstName("firstName")
                .setLastName("lastName")
                .setEmail("email" + z + "@gmail.com")
                .setPassword("Password1!");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
//      app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");

    }
    @Test
    public void registrationWrongEmail(){
        User user = new User()
                .setLastName("Sirkis")
                .setFirstName("Ekaterina")
                .setEmail("emailgmail.com")
                .setPassword("Password1!");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
//      app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Wrong email format");

    }
    @Test
    public void registrationWrongPassword(){
        User user = new User()
                .setLastName("Sirkis")
                .setFirstName("Ekaterina")
                .setEmail("email@gmail.com")
                .setPassword("password1");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);

    }
    @Test
    public void registrationExistUser(){
        User user = new User()
                .setLastName("Sirkis")
                .setFirstName("Ekaterina")
                .setEmail("email@gmail.com")
                .setPassword("Password1!");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submit();
        app.getHelperUser().checkPolicyXY();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exists"));

    }

}

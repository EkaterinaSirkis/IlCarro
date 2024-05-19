package tests;

import models.Car;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCar extends TestBase{
    @BeforeClass
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("margo@gmail.com").setPassword("Mmar123456$"));
        }
    }

    @Test
    public void addNewCarSuccess(){
        int i = new Random().nextInt(1000)+1000;
        Car car = Car.builder().
                location("Tel Aviv, Israel").
                manuFacture("Mazda").
                model("M3").
                year("2023").
                fuel("Petrol").
                seats(4).
                carClass("C").
                carRegNumber("678-900-" + i).
                price(50).
                about("Very nice car").
                build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fellCarForm(car);
        app.getHelperCar(),submitCarForm();

    }
}

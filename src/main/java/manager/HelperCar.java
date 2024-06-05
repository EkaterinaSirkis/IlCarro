package manager;

import models.Car;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase{

    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//a[text()= ' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManuFacture());
        type(By.cssSelector("#model"), car.getModel());
        type(By.xpath("//input[@id ='year']"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"),String.valueOf(car.getSeats()));
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        type(By.id("price"), car.getPrice()+"");


    }

    private void select(By locator, String options) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(options);

//        gas
//        select.selectByIndex(5);
//        select.selectByVisibleText(" Gas ");
//        select.selectByValue("Gas");


    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }

    public void returnToHome() {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void attachPhoto(String link) {
        wd.findElement(By.cssSelector("#photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
        // "5/30/2024", "5/31/2024"  30  31
        String[] from = dateFrom.split("/");//["5"]["30"]["2024"]

        String locatorFrom = "//div[text()=' "+from[1]+" ']";
        click(By.xpath(locatorFrom));


        String[]to = dateTo.split("/");
        String locatorTo = "//div[text()=' "+to[1]+" ']";

        click(By.xpath(locatorTo));

    }

    private void typeCity(String city) {
        type(By.id("city"),city);
        click(By.cssSelector("div.pac-item"));
    }
    public boolean isListOfCarsAppeared(){
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
        //"5/31/2024", "7/15/2024"
        LocalDate now = LocalDate.now();// 2024-06-3
        System.out.println(now);
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/dd/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/dd/yyyy"));
//      System.out.println(from);
//      LocalDate from1 = LocalDate.parse("2024:23/5", DateTimeFormatter.ofPattern("yyyy:dd/M"));
//      System.out.println(from1);
        int diffMonth = from.getMonthValue()-month;
        if(diffMonth>0) {
            clickNextMonthBtn(diffMonth);
        };
        click(By.xpath("//div[text()=' "+from.getDayOfMonth()+" ']"));

        diffMonth = to.getMonthValue()-from.getMonthValue();
        if(diffMonth>0){
            clickNextMonthBtn(diffMonth);
        }

        click(By.xpath("//div[text()=' "+from.getDayOfMonth()+" ']"));
    }

    private void clickNextMonthBtn(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/dd/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/dd/yyyy"));

        int diffMonth = from.getMonthValue()-month;
        if(diffMonth>0) {
            clickNextMonthBtn(diffMonth);
        };

        int diffYear = from.getYear()-year;
        if(diffYear>0) {
            clickNextYearBtn(diffYear);
        };

        click(By.xpath("//div[text()=' "+from.getDayOfMonth()+" ']"));

        diffMonth = to.getMonthValue()-from.getMonthValue();
        if(diffMonth>0){
            clickNextMonthBtn(diffMonth);
        }

        diffYear = to.getYear()-from.getYear();
        if(diffYear>0){
            clickNextMonthBtn(diffYear);
        }
        click(By.xpath("//div[text()=' "+from.getDayOfMonth()+" ']"));
    }

    private void clickNextYearBtn(int diffYear) {
        for (int i = 0; i < diffYear; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }
    }
}

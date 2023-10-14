package testrunner;

import com.github.javafaker.Faker;
import config.EmployeeModel;
import config.Setup;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class DashboardTestRunner extends Setup {
    DashboardPage dashboardPage;

    @BeforeTest(groups = "smoke")
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
        dashboardPage = new DashboardPage(driver);
        dashboardPage.menuItems.get(1).click(); //click PIM
    }

    @Test(priority = 1, groups = "smoke", description = "Check if search button is working")
    public void clickonSearchButton() {
//        dashboardPage = new DashboardPage(driver);
//        dashboardPage.menuItems.get(1).click(); //click PIM
        driver.findElement(By.cssSelector("[type='submit']")).click();
//        String textActual = driver.findElements(By.className("oxd-text--span")).get(12).getText();
//        System.out.println(textActual);
//        String textExpected = "Records Found";
//        Assert.assertTrue(textActual.contains(textExpected));
    }

    @Test(priority = 2, groups = "smoke", description = "Check if reset button is working")
    public void clickonResetButton() {
        driver.findElement(By.cssSelector("[type='reset']")).click();
    }

    @Test(priority = 3, description = "Check if new user is created successfully")
    public void createUser() throws IOException, ParseException, InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String username = faker.name().username();
        String password = faker.internet().password();

        EmployeeModel model = new EmployeeModel();
        model.setFirstname(firstname);
        model.setLastname(lastname);
        model.setUsername(username);
        model.setPassword(password);

        dashboardPage.createUser(model);
        String textTitleExpected = driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();

        Thread.sleep(5000);

        if (textTitleExpected.contains("Personal Details")) {
            Utils.saveEmployeeInfo(model);
        }
        Allure.description("User created successfully");
    }
}

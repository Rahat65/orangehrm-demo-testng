package testrunner;

import config.Setup;
import io.qameta.allure.Allure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;

    @Test(priority = 1, description = "Admin tries to login with wrong creds")
    public void doLoginWithWrongCreds(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("admin", "Wrongpass");
        String actualText = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String expectedText = "Invalid credentials";
        Assert.assertEquals(actualText,expectedText);
    }
    @Test(priority = 2, description = "Admin tries to do login with valid creds")
    public void doLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONArray jsonArray = Utils.readJSONList("./src/test/resources/employees.json");
        JSONObject empObj = (JSONObject) jsonArray.get(0);
        if (System.getProperty("username")!=null && (System.getProperty("password")!=null)){
            loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
        }
        else {
            loginPage.doLogin(empObj.get("username").toString(), empObj.get("password").toString());
        }

        SoftAssert softAssert = new SoftAssert();   //Soft assert diye multiple assert kora jabe even if 1 fails it will continue

        softAssert.assertTrue(driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed());
        softAssert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        softAssert.assertAll();
        Allure.description("Admin login successfully");
    }


    @Test(priority = 3, description = "Admin can successfully log out")
    public void logout(){
        loginPage = new LoginPage(driver);
        loginPage.doLogout();
        String textActual = driver.findElement(By.className("orangehrm-login-title")).getText();
        String textExpected = "Login";
        Assert.assertEquals(textActual,textExpected);
    }
}

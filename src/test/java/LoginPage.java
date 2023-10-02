import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class LoginPage {
    @FindBy(name = "username")
    WebElement txtUsername;
    @FindBy(name = "password")
    WebElement txtPassword;
    @FindBy(tagName = "button")
    WebElement btnLogin;
    @FindBy(className = "oxd-userdropdown-name")
    WebElement btnUserProfile;
    @FindBy(className = "oxd-userdropdown-link")
    List<WebElement> linkSubitems;

    public LoginPage(WebDriver driver){                                         //Page Factory
        PageFactory.initElements(driver,this);          // Webdriver theke data pass korar jonno page factoty webelement gula ke initiate kore
    }

    public void doLogin(String username, String password){                  //Login er steps
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }

    public void doLogout(){
        btnUserProfile.click();
        linkSubitems.get(3).click();
    }
}

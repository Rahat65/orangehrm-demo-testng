import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage {
    @FindBy(className = "oxd-main-menu-item--name")
    List <WebElement> menuItems;
    @FindBy(className = "oxd-button")
    List<WebElement> buttons;
    @FindBy(className = "oxd-input")
    List<WebElement> fromTextFields;
    @FindBy(className = "oxd-switch-input")
    WebElement btnSwitch;

    public DashboardPage(WebDriver driver){                                         //Page Factory
        PageFactory.initElements(driver,this);          // Webdriver theke data pass korar jonno page factoty webelement gula ke initiate kore
    }

    public void createUser(String firstname, String lastname, String username, String password){
        menuItems.get(1).click(); //click PIM
        buttons.get(2).click();
        fromTextFields.get(1).sendKeys(firstname);
        fromTextFields.get(3).sendKeys(lastname);
        btnSwitch.click();
        fromTextFields.get(5).sendKeys(username);
        fromTextFields.get(6).sendKeys(password);
        fromTextFields.get(7).sendKeys(password); //Confirm Password
        buttons.get(1).click(); //Save button
    }
}

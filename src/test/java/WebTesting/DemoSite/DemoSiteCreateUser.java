package WebTesting.DemoSite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoSiteCreateUser extends NavigationLinks {

    @FindBy (css = "body > table > tbody > tr > td.auto-style1 > form > div > center > table > tbody > tr > td:nth-child(1) > div > center > table > tbody > tr:nth-child(1) > td:nth-child(2) > p > input")
    private WebElement usernameField;
    @FindBy (css = "body > table > tbody > tr > td.auto-style1 > form > div > center > table > tbody > tr > td:nth-child(1) > div > center > table > tbody > tr:nth-child(2) > td:nth-child(2) > p > input[type=\"password\"]")
    private WebElement passwordField;
    @FindBy (css = "body > table > tbody > tr > td.auto-style1 > form > div > center > table > tbody > tr > td:nth-child(1) > div > center > table > tbody > tr:nth-child(3) > td:nth-child(2) > p > input[type=\"button\"]")
    private WebElement saveLoginButton;


    public DemoSiteCreateUser(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    public void setTextUsernameField(String input) {
        usernameField.sendKeys(input);
    }

    public void setTextPasswordField(String input) {
        passwordField.sendKeys(input);
    }

    public void clickSaveLoginButton(){
        saveLoginButton.click();
    }

    public String checkUsername() {
        return usernameField.getAttribute("value");
    }

    public String checkPassword() {
        return passwordField.getAttribute("value");
    }
}

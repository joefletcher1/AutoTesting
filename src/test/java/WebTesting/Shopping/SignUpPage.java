package WebTesting.Shopping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage {

    @FindBy(css = "#email_create")
    private WebElement registerEmailField;

    @FindBy (css = "#SubmitCreate")
    private WebElement createUserButton;

    @FindBy (css = "#email")
    private WebElement loginEmailField;

    @FindBy (css = "#passwd")
    private WebElement passwordField;

    @FindBy (css = "#SubmitLogin")
    private WebElement loginExistingUserButton;

    public void setRegisterEmailField(String registerEmail) {
        this.registerEmailField.sendKeys(registerEmail);
    }
    public void clickCreateUserButton() {
        createUserButton.click();
    }
    public void setLoginEmailField(String userEmail) {
        this.loginEmailField.sendKeys(userEmail);
    }
    public void setPasswordField(String password) {
        this.passwordField.sendKeys(password);
    }
    public void clickLoginExistingUserButton() {
        loginExistingUserButton.click();
    }
}

package WebTesting.Shopping;

import WebTesting.SpreadSheetReader;
import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CreateNewUserInfoPage {

    @FindBy (css = "#customer_firstname")
    private WebElement firstNameField;
    @FindBy (css = "#customer_lastname")
    private WebElement lastNameField;
    @FindBy (css = "#passwd")
    private WebElement passwordField;
    @FindBy (css = "#days")
    private WebElement daysField;
    @FindBy (css = "#months")
    private WebElement monthsField;
    @FindBy (css = "#years")
    private WebElement yearsField;
    @FindBy (css = "#firstname")
    private WebElement firstNameAdressField;
    @FindBy (css = "#lastname")
    private WebElement lastNameAdressField;
    @FindBy (css = "#address1")
    private WebElement addressField;
    @FindBy (css = "#city")
    private WebElement cityField;
    @FindBy (css = "#id_state")
    private WebElement stateField;
    @FindBy (css = "#postcode")
    private WebElement zipField;
    @FindBy (css = "#phone_mobile")
    private WebElement mobileNoField;
    @FindBy (css = "#submitAccount")
    private WebElement submitAccountButton;

    private SpreadSheetReader reader;

    public void populateFields(List<String> fields, WebDriver driver) {
        Select dropdown;

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class);
        firstNameField = wait.until(new Function<WebDriver, WebElement>()
        {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.cssSelector("#customer_firstname"));
            }
        });

        firstNameField.sendKeys(fields.get(3));
        lastNameField.sendKeys(fields.get(4));
        passwordField.sendKeys(fields.get(2));

        addressField.sendKeys(fields.get(5));
        cityField.sendKeys(fields.get(6));

        dropdown = new Select(stateField);
        dropdown.selectByVisibleText(fields.get(7));
        zipField.sendKeys(fields.get(8));    //.substring(0, fields.get(8).length() - 2));
        mobileNoField.sendKeys(fields.get(9));

    }

    public void clickRegisterButton() {
        submitAccountButton.click();
    }
}

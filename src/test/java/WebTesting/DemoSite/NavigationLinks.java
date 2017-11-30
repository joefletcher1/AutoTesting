package WebTesting.DemoSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class  NavigationLinks {

    WebDriver webDriver;

    @FindBy(css = "body > div > center > table > tbody > tr:nth-child(2) > td > div > center > table > tbody > tr > td:nth-child(2) > p > small > a:nth-child(6)")
    private WebElement makeUserButton;
    @FindBy(css = "body > div > center > table > tbody > tr:nth-child(2) > td > div > center > table > tbody > tr > td:nth-child(2) > p > small > a:nth-child(7)")
    private WebElement loginButton;

    public void clickMakeUserButton(){
        makeUserButton.click();
    }

    public void clickLoginPageButton(){
        loginButton.click();
    }

    public String getTitle(){
        return webDriver.getTitle();
    }


}

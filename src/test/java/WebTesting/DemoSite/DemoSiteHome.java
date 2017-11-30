package WebTesting.DemoSite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoSiteHome extends NavigationLinks {

    @FindBy (css = "body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b")
    private WebElement successMessage;

    public DemoSiteHome(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    public String getSuccessMessageText() {
        return successMessage.getText();
    }


}

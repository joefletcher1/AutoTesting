package WebTesting.Shopping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Homepage {

    @FindBy (css = ".login")
    private WebElement loginPageButton;

    @FindBy (css = "#homefeatured > li:nth-child(2) > div > div.left-block > div > a.product_img_link")
    private WebElement selectedDress;

    @FindBy (css = ".shopping_cart > a:nth-child(1)")
    private WebElement basketButton;


    public void clickSignUpButton(WebDriver driver) {
        loginPageButton.click();
    }
    public void clickSelectedDress(WebDriver driver){
        Actions builder = new Actions(driver);
        builder.moveToElement(selectedDress, 15, 15).click().perform();

    }

    public void navigateToBasket() {
    }
}

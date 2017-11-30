package WebTesting.Shopping;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage {

    @FindBy (css = ".standard-checkout")
    private WebElement purchaseButton;

    public void clickPurchaseButton() {
        purchaseButton.click();
    }
}

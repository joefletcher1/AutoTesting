package WebTesting.Shopping;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DressPage {

    @FindBy (css = "#add_to_cart")
    private WebElement addToCartButton;

    @FindBy (css = "#group_1")
    private WebElement sizeSelector;

    @FindBy (css = "#color_8")
    private WebElement colourSelector;

    @FindBy (css = "#layer_cart > div.clearfix > div.layer_cart_product.col-xs-12.col-md-6 > h2")
    private WebElement successfulAddToCart;

    public void changeSizeOfDress(String size) {
        Select dropdown = new Select(sizeSelector);
        dropdown.selectByVisibleText(size);
    }

    public void changeColourOfDress() {
        colourSelector.click();
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }

    public String getSuccessfulElementMessage() {
        return successfulAddToCart.getText();
    }
}

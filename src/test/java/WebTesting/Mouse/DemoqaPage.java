package WebTesting.Mouse;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DemoqaPage {
    //Sidebar tabs
    @FindBy (css = "#menu-item-140 > a")
    private WebElement draggableTab;
    @FindBy (css = "#menu-item-141 > a")
    private WebElement droppableTab;
    @FindBy (css = "#menu-item-143 > a")
    private WebElement resizableTab;
    @FindBy (css = "#menu-item-142 > a")
    private WebElement selectableTab;
    @FindBy (css = "#menu-item-151 > a")
    private WebElement sortableTab;

    //draggable elements
    @FindBy (css = "#draggable")
    private WebElement draggable;

    //droppable elements
    @FindBy (css = "#draggableview > p:nth-child(1)")
    private WebElement droppable;
    @FindBy (css = "#droppableview")
    private WebElement dropLocation;

    //resize elements
    @FindBy (css = "#resizable")
    private WebElement resizeBox;

    //selectable elements
    @FindBy (css = "#tabs-1 > div")
    private WebElement selectableList;
    @FindBy (css = "#selectable > li:nth-child(1)")
    private WebElement selectable1;
    @FindBy (css = "#selectable > li:nth-child(2)")
    private WebElement selectable2;
    @FindBy (css = "#selectable > li:nth-child(3)")
    private WebElement selectable3;
    @FindBy (css = "#selectable > li:nth-child(4)")
    private WebElement selectable4;
    @FindBy (css = "#selectable > li:nth-child(5)")
    private WebElement selectable5;
    @FindBy (css = "#selectable > li:nth-child(6)")
    private WebElement selectable6;
    @FindBy (css = "#selectable > li:nth-child(7)")
    private WebElement selectable7;

    //Sortable elements
    @FindBy (css = "#tabs-1 > div")
    private WebElement sortableArea;
    @FindBy (css = "#sortable > li:nth-child(1)")
    private WebElement sortable1;
    @FindBy (css = "#sortable > li:nth-child(2)")
    private WebElement sortable2;
    @FindBy (css = "#sortable > li:nth-child(3)")
    private WebElement sortable3;
    @FindBy (css = "#sortable > li:nth-child(4)")
    private WebElement sortable4;
    @FindBy (css = "#sortable > li:nth-child(5)")
    private WebElement sortable5;
    @FindBy (css = "#sortable > li:nth-child(6)")
    private WebElement sortable6;
    @FindBy (css = "#sortable > li:nth-child(7)")
    private WebElement sortable7;

    Wait<WebDriver> wait;


    public void moveDraggable(WebDriver driver) {

        draggableTab.click();

        Point p = draggable.getLocation();
        int xLoc = p.x;

        Actions builder = new Actions(driver);

        builder.moveToElement(draggable);

        builder.moveByOffset(5, 5) .clickAndHold().moveByOffset(120, 120).perform();
        int endLoc = draggable.getLocation().x;
        assertEquals(xLoc + 120, endLoc);
    }

    public void moveToDropLocation(WebDriver driver) {

        droppableTab.click();

        Point start = droppable.getLocation();
        Point end = dropLocation.getLocation();

        Actions builder = new Actions(driver);
        builder.moveToElement(droppable).perform();
        builder.moveByOffset(5, 5).clickAndHold().moveByOffset(end.x - start.x + 5, end.y - start.y + 5).perform();

        assertEquals(end.x + 5, droppable.getLocation().x);
    }

    public void resize(WebDriver driver) {

        resizableTab.click();

        Actions builder = new Actions(driver);
        //builder.moveToElement(resizeBox).perform();

        int x = resizeBox.getSize().width;
        int y = resizeBox.getSize().height;

        builder.moveToElement(resizeBox, 140, 140).perform();
        builder.clickAndHold().moveByOffset(100, 100).perform();
        builder.release().perform();

        Dimension newSize = resizeBox.getSize();

        assertEquals(y + 83 ,newSize.height);
    }

    public void selectMany(WebDriver driver) {

        selectableTab.click();

        Actions builder = new Actions(driver);
        builder.moveToElement(selectableList, 10, 0);
        builder.keyDown(Keys.LEFT_CONTROL)
                .click(selectable1)
                .click(selectable2)
                .click(selectable3)
                .click(selectable4)
                .click(selectable5)
                .click(selectable6)
                .click(selectable7)
                .keyUp(Keys.LEFT_CONTROL)
                .build()
                .perform();

       //builder.moveToElement(selectableList, 5, 15).clickAndHold().moveByOffset(0, 7*30).release().perform();

       String res = selectable1.getCssValue("background-color");
       assertTrue(res.equals("rgba(243, 152, 20, 1)"));
       res = selectable7.getCssValue("background-color");
       assertTrue(res.equals("rgba(243, 152, 20, 1)"));
   }

    public void sortMeReverse(WebDriver driver) {

        sortableTab.click();

        //Dimension listArea = .getSize();
        int barHeight = sortable1.getSize().height;
        int moveHeight = (barHeight * 7) + (6 * 3);

        Actions builder = new Actions(driver);
        builder.moveToElement(sortable1,15,10).clickAndHold().moveByOffset(50, moveHeight).release().perform();

        builder.moveToElement(sortable2,15,10).clickAndHold().moveByOffset(50, moveHeight).release().perform();

        builder.moveToElement(sortable3,15,10).clickAndHold().moveByOffset(50, moveHeight).release().perform();

        builder.moveToElement(sortable4,15,10).clickAndHold().moveByOffset(50, moveHeight).release().perform();

        builder.perform();

    }

}
//        JavascriptExecutor js;
//        if (driver instanceof JavascriptExecutor){
//            js = (JavascriptExecutor)driver;
//        }
//        else {
//            throw new IllegalStateException();
//        }
//        js.executeScript();

//        wait = new FluentWait<WebDriver>(driver)
//        .withTimeout(30, SECONDS)
//        .pollingEvery(5, SECONDS)
//        .ignoring(NoSuchElementException.class);
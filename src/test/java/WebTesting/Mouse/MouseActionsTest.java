package WebTesting.Mouse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class MouseActionsTest {

    private String url = "http://demoqa.com/";
    private WebDriver driver;
    private DemoqaPage demoqa;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }

    @After
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void dragMeAround() {
        demoqa = PageFactory.initElements(driver, DemoqaPage.class);
        demoqa.moveDraggable(driver);
    }

    @Test
    public void dragMeToDrop() {
        demoqa = PageFactory.initElements(driver, DemoqaPage.class);
        demoqa.moveToDropLocation(driver);
    }

    @Test
    public void resizeMe() {
        demoqa = PageFactory.initElements(driver, DemoqaPage.class);
        demoqa.resize(driver);
    }

    @Test
    public void selectMe() {
        demoqa = PageFactory.initElements(driver, DemoqaPage.class);
        demoqa.selectMany(driver);
    }

    @Test
    public void sortMe() {
        demoqa = PageFactory.initElements(driver, DemoqaPage.class);
        demoqa.sortMeReverse(driver);
    }
}

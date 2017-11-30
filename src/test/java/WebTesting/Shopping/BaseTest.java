package WebTesting.Shopping;

import WebTesting.DriverSelection;
import WebTesting.SpreadSheetReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class BaseTest {

    private String url = "http://automationpractice.com/index.php";
    private WebDriver driver;
    private static ExtentReports report;
    private SpreadSheetReader reader;
    List<String> fields;

    @BeforeClass
    public static void setUpBeforeTestClass() {
        report = new ExtentReports();
        String fileName = "AutomationPractice_ExtentReport" + ".html";
        String filePath = System.getProperty("user.dir")
                + File.separatorChar + fileName;
        report.attachReporter(new ExtentHtmlReporter(filePath));

    }

    @Before
    public void setUpBeforeTestMethod() {
        reader = new SpreadSheetReader(System.getProperty("user.dir") + File.separatorChar + "testinputs.xlsx");
        fields = reader.readRow(1, "Sheet1");
        DriverSelection selDriver = new DriverSelection();
        driver = selDriver.getDriver(fields.get(0));
        driver.manage().window().maximize();
        driver.navigate().to(url);

    }

    @After
    public void tearDownAfterTestMethod() {
        driver.quit();
    }

    @AfterClass
    public static void tearDownAfterTestClass() {
        report.flush();
    }

    @Test //Register user account
    public void createNewAccount() {
        ExtentTest newAccountTest = report.createTest("New Account Test");
        Homepage homepage = PageFactory.initElements(driver, Homepage.class);
        try {
            homepage.clickSignUpButton(driver);
            newAccountTest.log(Status.INFO, "Sign up button clicked");
        } catch (NoSuchElementException nse) {
            newAccountTest.log(Status.ERROR, "Sign up button not found");
            throw nse;
        }

        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        try {
            signUpPage.setRegisterEmailField(fields.get(1));
            newAccountTest.log(Status.INFO, "Register email field populated");
        } catch (NoSuchElementException nse) {
            newAccountTest.log(Status.ERROR, "Register email field not found");
            throw nse;
        }

        try {
            signUpPage.clickCreateUserButton();
            newAccountTest.log(Status.INFO, "Create user button clicked");
        } catch (NoSuchElementException nse) {
            newAccountTest.log(Status.ERROR, "Create user button not found");
            throw nse;
        }


        CreateNewUserInfoPage addUser = PageFactory.initElements(driver, CreateNewUserInfoPage.class);

        try {
            addUser.populateFields(fields, driver);
            newAccountTest.log(Status.INFO, "All mandatory fields populated");
        } catch (NoSuchElementException nse) {
            newAccountTest.log(Status.ERROR, "Sign up field not found");
            throw nse;
        }
        try {
            addUser.clickRegisterButton();
            newAccountTest.log(Status.INFO, "Register user button clicked");
        } catch (NoSuchElementException nse) {
            newAccountTest.log(Status.ERROR, "Register user button not found");
            throw nse;
        }

        assertEquals("My account - My Store", driver.getTitle());
    }

    @Test //Sign in existing user
    public void signInUserAccount() {
        Homepage homepage = PageFactory.initElements(driver, Homepage.class);

        homepage.clickSignUpButton(driver);



        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);

        signUpPage.setLoginEmailField(fields.get(1));

        signUpPage.setPasswordField(fields.get(2));

        signUpPage.clickLoginExistingUserButton();

        assertEquals("My account - My Store", driver.getTitle());
    }

    @Test
    public void placeItemInBasket() {
        Homepage homepage = PageFactory.initElements(driver, Homepage.class);
        homepage.clickSelectedDress(driver);

        DressPage dressPage = PageFactory.initElements(driver, DressPage.class);
        dressPage.changeSizeOfDress(fields.get(10).toUpperCase());
        //dressPage.changeColourOfDress();
        dressPage.clickAddToCart();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException inter) {
            inter.printStackTrace();

        }
        assertEquals("Product successfully added to your shopping cart", dressPage.getSuccessfulElementMessage());
    }

    @Test
    public void performPurchaseOfBasket() {
        Homepage homepage = PageFactory.initElements(driver, Homepage.class);
        homepage.navigateToBasket();

        BasketPage basket = PageFactory.initElements(driver, BasketPage.class);
        basket.clickPurchaseButton();

    }
}

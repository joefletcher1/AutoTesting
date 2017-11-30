package WebTesting.DemoSite;

import WebTesting.ScreenShot;
import WebTesting.SpreadSheetReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import junit.framework.AssertionFailedError;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AutomatedWebTest {

    private WebDriver driver;
    private String url = "http://thedemosite.co.uk/";
    private static ExtentReports report;
    private SpreadSheetReader reader;

    @BeforeClass
    public static void init() {
        report = new ExtentReports();
        String fileName = "DemoSite_ExtentReport" + ".html";
        String filePath = System.getProperty("user.dir")
                + File.separatorChar + fileName;
        report.attachReporter(new ExtentHtmlReporter(filePath));

    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void loginToStringURL() {

        ExtentTest test = report.createTest("AutomatedWebTest");

        //String excelFileLoc = System.getProperty("user.dir") + File.separatorChar + "testinputs.xlsx";
        reader = new SpreadSheetReader(System.getProperty("user.dir") + File.separatorChar + "testinputs.xlsx");

        List<String> inputs = reader.readRow(0, "Sheet1");

        String uname = inputs.get(0);
        String pword = inputs.get(1);
        driver.navigate().to(url);

        test.log(Status.INFO, "AutomatedWebTest is Starting ");

        DemoSiteHome homepage = new DemoSiteHome(driver);
        DemoSiteCreateUser createUserPage = new DemoSiteCreateUser(driver);
        DemoSiteLogin login = new DemoSiteLogin(driver);

        homepage.clickMakeUserButton();
        String nextPage = driver.getTitle();
        try {
            assertTrue(nextPage.contains("Add a user"));
            test.log(Status.DEBUG, "Successfully navigated to Add a user page");
        } catch (AssertionFailedError asfe) {
            test.log(Status.ERROR, "Current page not Add a User");
            try {
                ScreenShot.take(driver, "failedPage");
            } catch (IOException ioe) {
                ioe.fillInStackTrace();
            }
            throw asfe;
        }

        createUserPage.setTextUsernameField(uname);
        String temp = createUserPage.checkUsername();
        if (temp.equalsIgnoreCase(uname)) {
            test.log(Status.DEBUG, "Username populated successfully");
        }
        else {
            test.log(Status.ERROR, "Unexpected username");
            try {
                ScreenShot.take(driver, "username");
            } catch (IOException ioe) {
                ioe.fillInStackTrace();
            }

        }

        createUserPage.setTextPasswordField(pword);
        createUserPage.clickSaveLoginButton();

        createUserPage.clickLoginPageButton();
        nextPage = driver.getTitle();
        try {
            assertTrue(nextPage.contains("Login"));
            test.log(Status.DEBUG, "Successfully navigated to Login page");
        } catch (AssertionFailedError asfe) {
            test.log(Status.ERROR, "Current page not Login Page");
            try {
                ScreenShot.take(driver, "failedPage");
            } catch (IOException ioe) {
                ioe.fillInStackTrace();
            }
            throw asfe;
        }

        login.sendTextUsernameField(uname);
        login.sendTextPasswordField(pword);
        login.clickLoginButton();
        try {
            assertEquals("**Successful Login**", homepage.getSuccessMessageText());
        } catch (AssertionFailedError asfe) {
            test.log(Status.ERROR, "Incorrect username/password");
            try {
                ScreenShot.take(driver, "failedPage");
            } catch (IOException ioe) {
                ioe.fillInStackTrace();
            }
            throw asfe;
        }


    }
    @AfterClass
    public static void cleanUpAll() {
        report.flush();
    }
}

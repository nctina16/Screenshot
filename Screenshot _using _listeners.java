import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class LoginPage {

    private static ChromeDriver driver;


    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }


    @Test
    public void loginTest() {

        //define the url
        driver.get("http://automationpractice.com/index.php");

        //maximize the window
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        //get the title of the webpage
        String pageTitle = driver.getTitle();
        System.out.println("The title of this page is ===> " +pageTitle);

        //enter the locator of username and clear the input field before entering any value
        driver.findElement(By.linkText("Sign in")).click();

        //enter the username
        driver.findElement(By.id("email_create")).sendKeys("nivetha100@yahoo.comm");
        driver.findElement(By.xpath("//button[@name=\"SubmitCreate\"]")).click();

        //downcast the driver to access TakesScreenshot method
        TakesScreenshot ts = (TakesScreenshot)driver;

        //capture screenshot as output type FILE
        File file = ts.getScreenshotAs(OutputType.FILE);

        try {
            //save the screenshot taken in destination path
            FileUtils.copyFile(file, new File("Nivetha.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The screenshot is taken");

        //wait for the page to load
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}

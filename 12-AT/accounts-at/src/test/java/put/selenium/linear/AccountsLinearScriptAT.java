package put.selenium.linear;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import put.selenium.utils.ScreenshotAndQuitOnFailureRule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class AccountsLinearScriptAT {

    private WebDriver driver;

    @Rule
    public ScreenshotAndQuitOnFailureRule screenshotOnFailureAndWebDriverQuitRule =
            new ScreenshotAndQuitOnFailureRule();


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        this.driver = new ChromeDriver();
        screenshotOnFailureAndWebDriverQuitRule.setWebDriver(driver);
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
    }

    @Test
    public void successfulUserRegistration() throws Exception {
        driver.get("http://localhost:8080/accounts/controller");
        driver.manage().window().setSize(new Dimension(796, 817));
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("Louan");
        driver.findElement(By.name("password")).sendKeys("louan");
        driver.findElement(By.name("repeat_password")).sendKeys("louan");
        driver.findElement(By.name("name")).sendKeys("Louan Hamon");
        driver.findElement(By.name("addressData")).sendKeys("54 rue de Saint Martin");
        driver.findElement(By.name("addressData")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("contentSingle")).click();
        assertThat(driver.findElement(By.cssSelector("h3")).getText(), is("Login"));
        driver.findElement(By.linkText("Register")).click();
        driver.close();
    }


}

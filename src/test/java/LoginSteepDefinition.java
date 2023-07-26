
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class LoginSteepDefinition

{
    WebDriver driver;
    WebDriverWait wait ;

    @Before
    public void OpenBrowser() {
        WebDriverManager .chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-disable-notifications");
        options.addArguments("-remote-allow-origins");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    @After
    public void closeBrowser()
    {
        driver.quit();
    }
    @Given("I open login page")
    public void openLoginPage()
    {
        driver.get("https://qa.koel.app");
    }
    @When("I entre email")
    public void entreEmail(String email){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type=email]"))).sendKeys();

    }
    @And("I entre password")
    public void entrePassword(String password)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type=password]"))).sendKeys();
    }
    @And("I submit")
    public void submit()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type=button]"))).click();
    }
    @Then("i logged in ")
    public void LoggedIn()
    {
       Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar"))).isDisplayed());
    }
}

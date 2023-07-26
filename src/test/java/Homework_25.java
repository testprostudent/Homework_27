import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Homework_25 extends BaseTest {

   public WebDriver pickBrowser(String browser) throws MalformedURLException{
       DesiredCapabilities caps =new DesiredCapabilities();
       String gridURL = "http://192.168.153.1:4444\n";
       switch (browser){
           case "grid-chrome":
               caps.setCapability("browserName" , "chrome");
               return driver =new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
           case "grid-edge":
                   caps.setCapability("browserName","MicrosoftEdge");
               return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
           case "cloud":
               return LambdaTest();
           default:
               WebDriverManager .chromedriver().setup();
               ChromeOptions chromeOptions =new ChromeOptions();
               chromeOptions.addArguments("--remote-allow-origin");
               return driver = new ChromeDriver(chromeOptions);
       }
   }


}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BaseTest {

    public static WebDriver driver = null;
    public static WebDriverWait wait = null;
    public static Actions actions = null;
    public static String url = "https://qa.koel.app/";
     private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
     public static WebDriver getDriver(){
         return threadDriver.get();
     }
     public WebDriver LambdaTest () throws MalformedURLException{
         String hubURL ="https://hub.lambdatest.com/wd/hub";
         ChromeOptions browserOptions = new ChromeOptions();
         browserOptions.setPlatformName("Windows 10");
         browserOptions.setBrowserVersion("114.0");
         HashMap<String, Object> ltOptions = new HashMap<String, Object>();
         ltOptions.put("username", "kamal.serhani");
         ltOptions.put("accessKey", "yEFUtqN9kERXZmHxIcYmiGpoWVKsZrTZoFjKag2U9U4fgAIEyC");
         ltOptions.put("project", "Untitled");
         ltOptions.put("selenium_version", "4.0.0");
         ltOptions.put("w3c", true);
         browserOptions.setCapability("LT:Options", ltOptions);
         return new RemoteWebDriver(new URL(hubURL),browserOptions);
     }
   /* @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();


    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL) {
        //      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        url = BaseURL;
        driver.get(url);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @DataProvider(name="IncorrectLoginProviders")
    public static Object[][] getDataFromDataProviders() {
        return new Object[][]{
                {"notExisting@email.com", "NotExistingPassword"},
                {"demo@class.com", ""},
                {"", ""}
        };
    }

    @DataProvider(name="CorrectLoginProviders")
    public static Object[][] getLoginData() {
        return new Object[][]{
                {"demo@class.com", "te$t$tudent"}
        };
    }
    }*/
    protected static void clickSubmit() {
        WebElement submitLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
        submitLogin.click();
    }

    protected static void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='password']")));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    protected static void enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='email']")));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    protected static void openLoginUrl() {
        String url = "https://qa.koel.app/";
        driver.get(url);
    }

    // Profile Tests Helper Functions
    protected static void clickAvatarIcon() {
        WebElement avatarIcon = driver.findElement(By.cssSelector("img.avatar"));
        avatarIcon.click();
    }

    protected static String generateRandomName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    protected static void providePassword(String password) {
        WebElement currentPassword = driver.findElement(By.cssSelector("[name='current_password']"));
        currentPassword.clear();
        currentPassword.sendKeys(password);
    }

    protected static void provideProfileName(String name) {
        WebElement profileName = driver.findElement(By.cssSelector("[name='name']"));
        profileName.clear();
        profileName.sendKeys(name);
    }

    protected static void  ClickSaveButton () {
        WebElement saveButton = driver.findElement(By.cssSelector(("button.btn-submit")));
        saveButton.click();
    }

    // Helper functions for the Homework task
    public void searchSong(String songTitleKeyword) throws InterruptedException {
        WebElement searchField = driver.findElement(By.cssSelector(("div#searchForm input[type=search]")));
        searchField.clear();
        searchField.sendKeys(songTitleKeyword);
        Thread.sleep(5000);
    }

    public void clickViewAllBtn() throws InterruptedException {
        WebElement viewAllSearchResult = driver.findElement(By.cssSelector("#searchExcerptsWrapper > div > div > section.songs > h1 > button"));
        viewAllSearchResult.click();
        Thread.sleep(5000);
    }

    public void selectFirstSongResult() throws InterruptedException {
        WebElement firstSongResult = driver.findElement(By.cssSelector("#songResultsWrapper > div > div > div.item-container > table > tr"));
        firstSongResult.click();
        Thread.sleep(5000);
    }

    public void clickAddToBtn() throws InterruptedException {
        WebElement addToBtn = driver.findElement(By.cssSelector("#songResultsWrapper > header > div.song-list-controls > span > button.btn-add-to"));
        addToBtn.click();
        Thread.sleep(5000);
    }

    public void choosePlayList () throws InterruptedException {
        WebElement playListElement = driver.findElement(By.xpath("//*[@id='songResultsWrapper']/header/div[3]/div/section[1]/ul/li[74]"));
        playListElement.click();
    }

    public String getNotificationText () {
        WebElement notificationElement = driver.findElement(By.cssSelector("div.alertify-logs.top.right"));
        return notificationElement.getText();
    }

    // Methods for Playing Songs
    public void clickPlay() {
        WebElement playNextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainFooter']/div[1]/i[2]")));
        WebElement playButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainFooter']/div[1]/span/span[2]")));

        playNextButton.click();
        playButton.click();

    }

    public Boolean isSongPlaying() {
        WebElement soundBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainFooter']/div[2]/div[2]/div/button[1]/div")));
        return soundBar.isDisplayed();
    }

    // Helper functions for delete playlist
    public void openPlaylist() {
        WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        playlist.click();
    }

    public void clickDeletePlaylistBtn() {
        WebElement deletePlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-delete-playlist")));
        deletePlaylist.click();

        // If there are songs on the playlist
        // if yes need to click the confirm
        // if no songs on the playlist (continue)
    }

    public void confirmDelete() {
        WebElement confirmBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.ok")));
        confirmBtn.click();
    }

    public String getDeletedPlaylistMsg() {
        WebElement notificationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notificationMsg.getText();
    }

    // Double Click Method
    public void doubleClickChoosePlaylist() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        actions.doubleClick(playlistElement).perform();
    }

    // Play song helper functions
    public void chooseAllSongsList() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".overlay.loading")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li a.songs"))).click();
    }

    public void contextClickFirstSong() {
        WebElement firstSongElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".all-songs tr.song-item:nth-child(1)")));
        actions.contextClick(firstSongElement).perform();
    }

    public void choosePlayOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.playback"))).click();
    }

    // Hover helper functions
    public WebElement hoverPlay() {
        WebElement play = driver.findElement(By.cssSelector("[data-testid='play-btn']"));
        actions.moveToElement(play).perform();
        return driver.findElement(By.cssSelector("[data-testid='play-btn']"));
    }

    // Count Songs Helper Functions
    public void choosePlaylistByName(String playlistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + playlistName + "')]"))).click();
    }

    public int countSongs() {
        return driver.findElements(By.cssSelector("section#playlistWarpper td.title")).size();
    }

    public String getPlaylistDetails() {
        return driver.findElement(By.cssSelector("span.meta.text-secondary span.meta")).getText();
    }

    public void displayAllSongs() {
        List<WebElement> songList = driver.findElements(By.cssSelector("section#playlistWrapper td.title"));
        System.out.println("Number of Songs Found:" + countSongs());
        for (WebElement e: songList) {
            System.out.println(e.getText());
        }

    }


    // Helper functions for Rename
    String newPlaylistName = "new name";
    public void doubleClickPlaylist () {
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        actions.doubleClick(playlistElement).perform();
    }
    public void enterNewPlaylistName() {
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));

        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public boolean doesPlaylistExist() {
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + newPlaylistName + "' ]")));
        return playlistElement.isDisplayed();
    }


}
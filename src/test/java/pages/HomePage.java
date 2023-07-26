package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }
    By userAvatarIcon = By.cssSelector("img.avatar");
    public WebElement getUserAvatar() {
        return findElement(userAvatarIcon);
    }

    By firstPlaylist =By .cssSelector("* .playlist:nth-child(3)");
    By playlistNameField =By .cssSelector("[name='name']");
    public void doubleClickPlaylist()
    {
    double_click(firstPlaylist);
    }

    public void entrePlaylistName(String playlistName)
    {
   findElement(playlistNameField).sendKeys(playlistName);
        findElement(playlistNameField).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
        findElement(playlistNameField).sendKeys(playlistName);
        findElement(playlistNameField).sendKeys(Keys.ENTER);
    }
    public boolean doesPlaylistExit(String playlistName){
         By newPlayList = By.xpath("//a [text()='"+playlistName+"']");
     return findElement(newPlayList).isDisplayed();
    }


}
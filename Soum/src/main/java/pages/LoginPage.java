package pages;

import com.google.common.base.Stopwatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.InventoryPage;

import java.time.Duration;


public class LoginPage {
    private WebDriver driver;
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By lockederrormsg = By.xpath("//h3[@data-test=\"error\"]");
    private InventoryPage inv;
    private long starttime = 0;
    private Stopwatch stopwatch;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public String getLockedErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(lockederrormsg));
        return driver.findElement(lockederrormsg).getText();
    }

    public void logOut() {
        driver.get("https://www.saucedemo.com/");
    }

    public InventoryPage clickLoginButton() {
        driver.findElement(loginButton).click();
        System.out.println("" + driver.manage().getCookies());
        return new InventoryPage(driver);
    }
}

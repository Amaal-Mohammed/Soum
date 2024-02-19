package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class InventoryPage {
    private WebDriver driver;
    private By pagesubtitle = By.xpath("//span[@class='title']");
    private By inventoryitems = By.className("inventory_item_name");
    private By cartcontainerbadge = By.xpath("//span[@class=\"shopping_cart_badge\"]");
    private By menubtn = By.id("react-burger-menu-btn");
    private By resetappstate = By.id("reset_sidebar_link");
    private By sortcontainer = By.xpath("//select[@class='product_sort_container']");
    private By cartcontainer = By.xpath("//div[@id=\"shopping_cart_container\"]");
    private String sortby;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;

    }

    public void clearCookie() {
        driver.navigate().refresh();
    }

    public void goToInventoryPage(String session, String username) {
        Cookie c = new Cookie(session, username);
        this.driver.manage().addCookie(c);
        this.driver.navigate().to("https://www.saucedemo.com/inventory.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public List<String> getInventoryItems() {
        List<WebElement> list = driver.findElements(inventoryitems);
        List<String> intentoryitemsnames = new ArrayList<String>();
        for (WebElement e : list) {
            intentoryitemsnames.add(e.getText());
        }
        return intentoryitemsnames;
    }

    public String getpageSubTitle() {
        ;
        String strr = driver.findElement(pagesubtitle).getText();
        return strr;
    }

    public void clickAddToCard(String item) {
        String testitem = "'" + item + "'";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[(text()= " + testitem + ")]//ancestor::div/following-sibling::div[1]/button")));
        WebElement addToCart = driver.findElement(By.xpath("//div[(text()= " + testitem + ")]//ancestor::div/following-sibling::div[1]/button"));
        if (addToCart.getText().equals("Add to cart"))
            addToCart.click();
    }

    public void clickToRemove(String item) {
        String testitem = "'" + item + "'";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[(text()= " + testitem + ")]//ancestor::div/following-sibling::div[1]/button")));
        WebElement removeCart = driver.findElement(By.xpath("//div[(text()= " + testitem + ")]//ancestor::div/following-sibling::div[1]/button"));
        if (removeCart.getText().equals("Remove"))
            removeCart.click();
    }

    public String getCartContainer() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartcontainerbadge));
        return driver.findElement(cartcontainerbadge).getText();
    }

    public void clickMenu() {
        driver.findElement(menubtn).click();
    }

    public void clickResetAppState() {
        driver.findElement(resetappstate).click();
    }

    public String getAddRemoveButtonText(String item) {
        String test = "'" + item + "'";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[(text()= " + test + ")]//ancestor::div/following-sibling::div[1]/button")));
        return driver.findElement(By.xpath("//div[(text()= " + test + ")]//ancestor::div/following-sibling::div[1]/button")).getText();
    }

    public void sortFilter(String sortby) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(sortcontainer));
            Select d = new Select(driver.findElement(sortcontainer));
            d.selectByVisibleText(sortby);
            this.sortby = sortby;
        } catch (Exception ex) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            throw new Exception("Sort is broken");
        }

    }

    public Boolean isSorted() {
        List<String> sortitems = this.getInventoryItems();

        switch (sortby) {
            case "Name (Z to A)":
                Collections.sort(sortitems, Collections.reverseOrder());
                break;
            case "Name (A to Z)":
                Collections.sort(sortitems);
                break;
            default:
                Collections.sort(sortitems);
        }
        boolean bool = sortitems.equals(this.getInventoryItems());
        return bool;
    }

    public String getStyle(String property) {
        WebElement cart = driver.findElement(cartcontainer);
        return cart.getCssValue(property);

    }
}


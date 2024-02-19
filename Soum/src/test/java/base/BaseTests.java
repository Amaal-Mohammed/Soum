package base;


import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.InventoryPage;
import pages.LoginPage;
import testdata.ReadJson;

import java.io.IOException;
import java.util.Map;


public class BaseTests {
    private WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventortyPage;
    protected Map<String, String> testdata;
    protected long actualtime;
    protected long expectedtime;

    @BeforeClass
    public void setUp() throws IOException, ParseException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        testdata = ReadJson.readJson("data.json");
        driver.get(testdata.get("url"));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventortyPage = new InventoryPage(driver);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
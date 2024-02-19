package login;

import base.BaseTests;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import testdata.DataProviderClass;

import java.lang.reflect.Method;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class LoginTests extends BaseTests {

    @BeforeMethod
    public void testlogout() {
        loginPage.logOut();
    }

    @Test(dataProvider = "InvalidUsers", dataProviderClass = DataProviderClass.class)
    public void testLockedUserLogin(String invaliduser) {
        loginPage.setUsername(invaliduser);
        loginPage.setPassword(System.getenv("Password"));
        inventortyPage = loginPage.clickLoginButton();
        assertEquals("you are not locked", testdata.get("lockedusermsg"), loginPage.getLockedErrorMessage());

    }

    @Test(dataProvider = "Users", dataProviderClass = DataProviderClass.class)
    public void testUserLogin(String username) throws InterruptedException {

        loginPage.setUsername(username);
        loginPage.setPassword(System.getenv("Password"));
        inventortyPage = loginPage.clickLoginButton();
        assertEquals("you are not logged in", testdata.get("pagesubtitle"), inventortyPage.getpageSubTitle());

    }

    @AfterMethod
    public void checkPerformance(ITestResult result, Method method) {

        try {
            actualtime = result.getEndMillis() - result.getStartMillis();
            expectedtime = Long.parseLong(testdata.get("expectedtime"));
            assertTrue(expectedtime > actualtime, "Time taken to run test is :" + actualtime + " miliiseconds");
        } catch (AssertionError e) {
            throw new AssertionError("Time taken for " + method.getName() + " to run test is: " + actualtime + " miliiseconds" + " while expected time should be " + expectedtime + " miliiseconds ");
        }
    }


}

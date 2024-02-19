package inventory;

import base.BaseTests;
import org.testng.annotations.*;
import testdata.DataProviderClass;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class InventoryTests extends BaseTests {

    @Test(dataProvider = "InventorySession", dataProviderClass = DataProviderClass.class, priority = 1)
    public void testUserIsAbleToClickAddToCard(String session, String username) {
        try {
            inventortyPage.goToInventoryPage(session, username);
            inventortyPage.clickAddToCard(testdata.get("item"));
            assertEquals("Remove", inventortyPage.getAddRemoveButtonText(testdata.get("item")));
            inventortyPage.clickToRemove(testdata.get("item"));
            assertEquals("Add to cart", inventortyPage.getAddRemoveButtonText(testdata.get("item")));
        } catch (AssertionError e) {
            throw new AssertionError("Add/Remove Button is not working");
        }

    }

    @Test(dataProvider = "InventorySession", dataProviderClass = DataProviderClass.class, priority = 2)
    public void testItemAddedFromCartContainer(String session, String username) {
        try {

            inventortyPage.goToInventoryPage(session, username);
            inventortyPage.clickToRemove(testdata.get("item"));
            inventortyPage.clickAddToCard(testdata.get("item"));
            assertEquals("Item is not add to cart badge", testdata.get("expecteditemnumber"), inventortyPage.getCartContainer());

        } catch (AssertionError e) {
            throw new AssertionError("Item is not added");

        }
    }

    @Test(dataProvider = "InventorySession", dataProviderClass = DataProviderClass.class, priority = 3)
    public void testResetApp(String session, String username) {
        try {
            inventortyPage.goToInventoryPage(session, username);
            inventortyPage.clickToRemove(testdata.get("item"));
            inventortyPage.clickAddToCard(testdata.get("item"));
            inventortyPage.clickMenu();
            inventortyPage.clickResetAppState();
            assertEquals("Add to cart", inventortyPage.getAddRemoveButtonText(testdata.get("item")));
        } catch (AssertionError e) {
            throw new AssertionError("App is not reset to its original state");
        }
    }


    @Test(dataProvider = "InventorySession", dataProviderClass = DataProviderClass.class, priority = 4)
    public void testSort(String session, String username) {
        try {
            inventortyPage.goToInventoryPage(session, username);
            inventortyPage.sortFilter(testdata.get("za"));
            assertTrue(inventortyPage.isSorted());
        } catch (AssertionError e) {
            throw new AssertionError("Sort Filter is not working");
        } catch (Exception e) {
            throw new AssertionError("Sort dropdown is broken");
        }
    }

    @Test(dataProvider = "InventorySession", dataProviderClass = DataProviderClass.class, priority = 5)
    public void testCartStyle(String session, String username) {
        try {
            inventortyPage.goToInventoryPage(session, username);
            assertEquals(testdata.get("expectedright"), inventortyPage.getStyle(testdata.get("right")));
            assertEquals(testdata.get("expectedtop"), inventortyPage.getStyle(testdata.get("top")));
        } catch (AssertionError e) {
            throw new AssertionError("Css style is not correct");
        }


    }
}

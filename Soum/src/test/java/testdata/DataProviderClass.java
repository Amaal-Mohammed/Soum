package testdata;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class DataProviderClass {
    @DataProvider(name="Users")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { "standard_user" },
                        {"visual_user"},
                        { "problem_user" },
                        {"error_user"},
                        {"performance_glitch_user"},


                };

    }

    @DataProvider(name="InvalidUsers")
    public Object[][] getDataFromDataprovider2(){
        return new Object[][]
                {
                        { "locked_out_user" },

                };

    }
    @DataProvider(name="InventorySession")
    public Object[][] getDataFromDataproviderInventory(){
        return new Object[][]
                {
                        { "session-username" , "standard_user" },
                        { "session-username"  ,"visual_user"},
                        { "session-username"  ,"problem_user" },
                        { "session-username"  ,"error_user"},



                };

    }

    @DataProvider(name="ProductFilters")
    public Object[][] getPageAndSize(){
        return new Object[][]
                {
                        { "2" , "2" },
                        {"2","3"},
                        {"3","2"},
                        {"1","469695"},

                };

    }

}

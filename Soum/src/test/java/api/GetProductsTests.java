package api;

import base.BaseTests;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import testdata.DataProviderClass;
import io.restassured.specification.RequestSpecification;
import testdata.ReadJson;

import java.io.IOException;

public class GetProductsTests  {

    private static RequestSpecification httpRequest;
    private static Response response;

    @BeforeClass
    public void setUp() {


    }
    @Test(dataProvider = "ProductFilters", dataProviderClass = DataProviderClass.class)
    public void request_Product_Page_Size(String page, String size) {
        try {

            httpRequest = RestAssured.given();
            response = httpRequest.get("https://api.qa.soum.sa/api-v2/rest/api/v1/product/?page%20number=" + page + "&" + "size" + "=" + size);
            Assert.assertEquals(response.getStatusCode(), 200);

        } catch (AssertionError e) {
            throw new AssertionError("Status code is not correct " + response.getStatusCode() + " " + response.toString());
        }

    }


}

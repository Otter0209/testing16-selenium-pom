package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.CsvReader;

import java.io.IOException;
import java.util.List;

// Epic > Feature > Story > Test case

@Epic("OrangeHRM web")
@Feature("Authentication")
public class LoginTest extends BaseTest {

    @Story("Login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login success with Admin username and **** password and redirect to Dashboard page")
    @Test(description = "Test login success")
    public void testLoginSuccess() throws InterruptedException {
//        khoi tao doi tuong LoginPage
        LoginPage loginPage = new LoginPage(getDriver(), getWait());

        String username = ConfigReader.get("admin.username");
        String password = ConfigReader.get("admin.password");

        loginPage.login(username, password);

//        kiem tra expected - actual result
        String currentUrl = getDriver().getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("dashboard"));
    }

    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login fail with Admin username and **** password")
    @Test(description = "Test login fail")
    public void testLoginFail() throws  InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        loginPage.login("Admin", "admin1234");
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("auth/login"));
        Assert.assertFalse(currentUrl.contains("dashboard"));
    }

    //    tao data provider
    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() throws IOException {
        String filePath = "src/test/resources/loginData.csv";

        List<String[]> data = CsvReader.readCsv(filePath);

        return CsvReader.toDataProviderArray(data);
    }

    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Login with data provider")
    @Test(description = "Test login data provider", dataProvider = "loginData")
    public void testDataLogin(String username, String password, String expectedResult) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        loginPage.login(username, password);

        if(expectedResult.equalsIgnoreCase("success")) {
            String currentUrl = getDriver().getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("dashboard"));
        } else {
            Assert.assertTrue(getDriver().getCurrentUrl().contains("auth/login"));
        }
    }
}
package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;

@Epic("OrangeHRM web")
@Feature("Admin module")
public class AdminTest extends BaseTest{
    //    viet before method de cac test case deu phai login truoc
    private AdminPage adminPage;

    @BeforeMethod
    public void loginAndOpenAdminPage() throws InterruptedException {
//        case 1: login -> access url cua admin page
        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        loginPage.login("Admin", "admin123");

        adminPage = new AdminPage(getDriver(), getWait());
        adminPage.open();
//        case 2: login -> click menu admin
    }

    @Story("Manage system user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Filter by admin user")
    @Test(description = "Test filter by admin user")
    public void testFilterByAdminUser() throws InterruptedException {
        adminPage.filterByUser("Admin", "Admin");
        Assert.assertTrue(adminPage.checkNumberOfRecords());
    }
}
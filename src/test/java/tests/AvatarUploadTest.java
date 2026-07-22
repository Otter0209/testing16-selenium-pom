package tests;

import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PersonalPage;
import utils.ConfigReader;

@Epic("OrangeHRM web")
@Feature("PIM - Employee photo")
public class AvatarUploadTest  extends BaseTest {
    private static final String EMP_NUMBER="7";
    private static final String AVATAR_TEST="avatar_test.png";
    private static final Logger log = LoggerFactory.getLogger(AvatarUploadTest.class);
    private PersonalPage personalPage;

    @BeforeMethod
    public void loginAndOpenPesonalPage() throws InterruptedException{
        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        String username = ConfigReader.get("get.username");
        String password = ConfigReader.get("get.password");
        loginPage.login(username, password);



        personalPage = new PersonalPage(getDriver(), getWait());
        personalPage.open(EMP_NUMBER);
    }

    @Story("upload Avatar")
    @Severity(SeverityLevel.NORMAL)
    @Description("Upload avatar success")
    @Test(description = "upload avatar succsess")
    public void testUploadAvatarSuccess() throws InterruptedException{
        personalPage.uploadAvatar(AVATAR_TEST);
        boolean isUploaded = personalPage.isAvatarUploadSuccessfully();
        Assert.assertTrue(isUploaded, "avatar upload fail");
    }
}

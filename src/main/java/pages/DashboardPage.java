package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage{
    private static final By USER_DROPDOWN = By.xpath("//li[@class='oxd-userdropdown']");
    private static final By LOGOUT_LINK = By.linkText("Logout");
    //By.xpath("//a[@text()='logout']")


    public DashboardPage(WebDriver driver, WebDriverWait wait){
            super(driver, wait);
    }

    public void logout(){
//        WebElement userDropDown = driver.findElement(USER_DROPDOWN);
//        userDropDown.click();
        wait.until(ExpectedConditions.elementToBeClickable(USER_DROPDOWN)).click();

//        WebElement logoutLink = driver.findElement(LOGOUT_LINK);
//        logoutLink.click();

        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK)).click();

        wait.until(ExpectedConditions.urlContains("auth/login"));
    }
}

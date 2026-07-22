package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{


    private static final By USERNAME_INPUT = By.xpath("//input[@name='username']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@name='password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }

    //define các step
    public void open(){
        Allure.step("Open LoginPage", ()->{
        String url ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
        });
    }
    public void enterUsername(String username) throws InterruptedException {
        Allure.step("Enter Username: "+username,()->{
            WebElement usernameInput = driver.findElement(USERNAME_INPUT);
            highlight(usernameInput);

            usernameInput.sendKeys(username);
            Thread.sleep(2000);
            unhighlight(usernameInput);
        });
    }

    public void enterPassword(String password) throws InterruptedException {
        Allure.step("Enter Password",()-> {
            WebElement passwordInput = driver.findElement(PASSWORD_INPUT);
            highlight(passwordInput);

            passwordInput.sendKeys(password);
            Thread.sleep(2000);
            unhighlight(passwordInput);
        });
    }

    public void clickLoginBtn() throws InterruptedException{
        Allure.step("Click LoginButton", ()-> {
            WebElement loginBtn = driver.findElement(LOGIN_BUTTON);
            highlight(loginBtn);

            loginBtn.click();
            Thread.sleep(2000);
            unhighlight(loginBtn);
        });
    }
    public void login(String username, String password) throws InterruptedException{
        open();
        enterUsername(username);
        enterPassword(password);
        clickLoginBtn();
    }
}

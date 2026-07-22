package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class PersonalPage extends BasePage{
//flow: click vào avatar -> click nút + -> upload hình
    //kiểm tra: success toast, button save
    private static final By AVATAR_IMG = By.xpath("//flow: click vào avatar -> click nút + -> upload hình\n" + "//kiểm tra: success toast, button save");
    private static final By UPLOAD_BTN = By.xpath("//button[contains(@class,'employee-image-action')]\n");
    private static  final By FILE_INPUT = By.xpath("//input[@type='file']");
    private static  final  By SAVE_BTN = By.xpath("/input[@type='submit']");
    private  static  final By SUCCSESS_TOAST = By.xpath("\n" + "//div[contains(@class,'oxd-toast')]\n");
    //

    public PersonalPage(WebDriver driver, WebDriverWait wait){

        super(driver, wait);
    }

    public void open(String empNumber){
        Allure.step("Open personal details page", ()->{
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7"+empNumber);

            // đợi đến khi xuất hiện AVARTAR_IMG
            wait.until(ExpectedConditions.visibilityOfElementLocated(AVATAR_IMG));
        });


        }
    public void uploadAvatar(String fileName) throws InterruptedException{
        Allure.step("upload avatar file:" + fileName,() ->{
//b1
            WebElement avatarImg = wait.until(ExpectedConditions.elementToBeClickable(AVATAR_IMG));
            highlight(avatarImg);
            avatarImg.click();
            unhighlight(avatarImg);
            //b2

            WebElement uploadBtn = wait.until(ExpectedConditions.elementToBeClickable(UPLOAD_BTN));
            highlight(uploadBtn);
            uploadBtn.click();
            unhighlight(uploadBtn);

            //b3

            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(FILE_INPUT));

            //ghep path tuyet doi -> src/test/resource/images.
            String filePath = new File("src/test/recources/images/" +  fileName).getAbsolutePath();
            fileInput.sendKeys(filePath);
            Thread.sleep(2000);


            //b4

            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(SAVE_BTN));
            highlight(saveBtn);
            saveBtn.click();
            unhighlight(saveBtn);

            Thread.sleep(2000);
        });


    }
    public boolean isAvatarUploadSuccessfully(){
        return Allure.step("Check avatar uploaded Successfully", ()->{
            WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCSESS_TOAST));
            return successToast.isDisplayed();
        });
    }
}

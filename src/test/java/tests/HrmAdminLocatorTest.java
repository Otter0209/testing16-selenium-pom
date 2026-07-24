package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.hrmAdminPage;

import java.lang.reflect.Field;
import java.util.List;

public class HrmAdminLocatorTest extends BaseTest {

    @Test(description = "Login, chuyen sang trang Admin, kiem tra tat ca locator trong hrmAdminPage")
    public void checkAdminLocators() throws Exception {
        WebDriver driver = getDriver();

        // 1. Dang nhap
        LoginPage loginPage = new LoginPage(driver, getWait());
        loginPage.login("Admin", "admin123");
        getWait().until(ExpectedConditions.urlContains("dashboard"));

        // 2. Chuyen sang trang Admin
        By adminLinkLocator = By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']");
        getWait().until(ExpectedConditions.presenceOfElementLocated(adminLinkLocator));
        Thread.sleep(1500); // Cho render xong sidebar

        List<WebElement> adminLinkCandidates = driver.findElements(adminLinkLocator);
        WebElement adminLinkVisible = adminLinkCandidates.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Khong tim thay link Admin nao dang hien thi!"));

        JavascriptExecutor jsClick = (JavascriptExecutor) driver;
        jsClick.executeScript("arguments[0].scrollIntoView({block:'center'});", adminLinkVisible);
        jsClick.executeScript("arguments[0].click();", adminLinkVisible);

        getWait().until(ExpectedConditions.urlContains("admin"));
        getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='User Management' or text()='System Users']")));
        Thread.sleep(1000);

        // Click mo dropdown "User Role" truoc de render cac the an trong DOM
        try {
            By userRoleDropdownLocator = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[1]");
            WebElement userRoleDropdown = getWait().until(ExpectedConditions.elementToBeClickable(userRoleDropdownLocator));
            userRoleDropdown.click();
            Thread.sleep(800);
        } catch (Exception ignored) {
            // Bo qua neu khong the mo dropdown
        }

        // 3. Duyet va kiem tra toan bo locator trong hrmAdminPage
        hrmAdminPage page = new hrmAdminPage();
        Field[] fields = hrmAdminPage.class.getDeclaredFields();

        int total = 0, found = 0, notFound = 0;

        System.out.println("=========================================");
        System.out.println("KẾT QUẢ KIỂM TRA LOCATOR");
        System.out.println("=========================================");

        for (Field field : fields) {
            if (field.getType().equals(By.class)) {
                field.setAccessible(true);
                By locator = (By) field.get(page);
                String name = field.getName();
                total++; // Đếm số thứ tự

                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    found++;
                    WebElement el = elements.get(0);
                    highlightElement(driver, el);
                    System.out.printf("%d. [FOUND]     %s  ->  %s\n", total, name, locator);
                } else {
                    notFound++;
                    System.out.printf("%d. [NOT FOUND] %s  ->  %s\n", total, name, locator);
                }
            }
        }

        System.out.println("=========================================");
        System.out.println("Tong so locator kiem tra : " + total);
        System.out.println("Tim thay                 : " + found);
        System.out.println("Khong tim thay           : " + notFound);
        System.out.println("=========================================");

        Thread.sleep(3000); // Giu tab 3 giay truoc khi dong
    }

    // Highlight phan tu bang vien do + nen vang
    private void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', arguments[1] + '; border: 3px solid red; background: yellow;');",
                element, originalStyle == null ? "" : originalStyle);
        try {
            Thread.sleep(800);
        } catch (InterruptedException ignored) {
        }
        unhighlightElement(driver, element, originalStyle);
    }

    // Tra lai style ban dau cho phan tu
    private void unhighlightElement(WebDriver driver, WebElement element, String originalStyle) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle == null ? "" : originalStyle);
    }
}
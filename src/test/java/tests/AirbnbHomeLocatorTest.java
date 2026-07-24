package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.airbnbHomePage;

import java.lang.reflect.Field;
import java.util.List;

public class AirbnbHomeLocatorTest extends BaseTest {

    @Test(description = "Truy cap CyberSoft Airbnb va kiem tra tat ca locator trong airbnbHomePage")
    public void checkAirbnbLocators() throws Exception {
        WebDriver driver = getDriver();

        // 1. Truy cập vào trang web
        driver.get("https://demo5.cybersoft.edu.vn/");
        Thread.sleep(3000); // Đợi trang load xong
        try {
            // Click vào ô "Thêm khách" để hiện popup có nút + và -
            driver.findElement(By.xpath("//p[text()='Thêm khách']/parent::div")).click();
            Thread.sleep(1000); // Đợi 1 giây cho hiệu ứng popup mở ra hoàn tất
            System.out.println(">>> Đã mở popup Thêm khách");
        } catch (Exception e) {
            System.out.println(">>> Lỗi: Không thể click ô Thêm khách");
        }
        // ---------------------------------------------------

        // Code cũ của bạn giữ nguyên từ đoạn này...
        airbnbHomePage page = new airbnbHomePage();
        Field[] fields = airbnbHomePage.class.getDeclaredFields();

        int total = 0, found = 0, notFound = 0;

        System.out.println("=========================================");
        System.out.println("KẾT QUẢ KIỂM TRA LOCATOR AIRBNB");
        System.out.println("=========================================");

        for (Field field : fields) {
            if (field.getType().equals(By.class)) {
                field.setAccessible(true);
                By locator = (By) field.get(page);
                String name = field.getName();
                total++;

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
        System.out.println("Tổng số locator kiểm tra : " + total);
        System.out.println("Tìm thấy                 : " + found);
        System.out.println("Không tìm thấy           : " + notFound);
        System.out.println("=========================================");

        Thread.sleep(3000); // Giữ tab 3 giây trước khi đóng
    }

    // Highlight phần tử bằng viền đỏ + nền vàng
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

    private void unhighlightElement(WebDriver driver, WebElement element, String originalStyle) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle == null ? "" : originalStyle);
    }
}
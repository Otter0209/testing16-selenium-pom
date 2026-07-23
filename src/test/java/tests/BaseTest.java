package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.DriverFactory;
import utils.ScreenshotUtils;
import utils.VideoRecorderUtil;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();

    protected WebDriver getDriver(){
        return driverThreadLocal.get();
    }


    protected WebDriverWait getWait(){
        return waitThreadLocal.get();
    }

    //setup moi truong
    @Parameters({"browser", "device"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome")String browser, @Optional("")String device, Method method) throws IOException {
        //TODO:tạo folder chứa screenshot, video record

        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();


        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String folderName = String.format("%s_%S_%s_%s_%s",device,browser, className, methodName, timestamp);

        String testFolderPath = "taget/test-output/" + folderName;
        Files.createDirectories(Paths.get(testFolderPath));

        ScreenshotUtils.setTestFolder(testFolderPath);

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

//        WebDriver driver = new ChromeDriver(options);
        WebDriver driver = DriverFactory.createDriver(browser, device);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driverThreadLocal.set(driver);
        waitThreadLocal.set(wait);

        //TODO: start recording

        VideoRecorderUtil.start(testFolderPath);
    }



    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result){
        // chỉ luu record với testcase lỗi
        // với testcase pass => clear record đó
        WebDriver driver = getDriver();

        //TODO: kiểm tra testcase pass hay fail để xem xét lưu record

        if(driver!=null&&result.getStatus()==ITestResult.FAILURE){
            ScreenshotUtils.takeScreenshot(driver, "Failed"+result.getName());
        }

        if(driver!=null){
            driver.quit();
        }

        VideoRecorderUtil.stop(true);
        ScreenshotUtils.clear();
        driverThreadLocal.remove();
        waitThreadLocal.remove();
    }
}

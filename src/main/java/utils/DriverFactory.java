package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    public static WebDriver createDriver(String browser, String deviceName) {
        // trướng hợp không truyền tên browser hay device  thì setup giá trị  mặc định
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        // dung switch-case
        switch (browser){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximine");

                return new FirefoxDriver(firefoxOptions);

            case"egde":

                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximine");

                return new EdgeDriver(edgeOptions);

            default:
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                if (deviceName != null && !deviceName.isEmpty()){
                    //chrome yêu cầu có 1 map chứa key để bắt đầu giả lập
                    Map<String, String> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", deviceName.trim());
                    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                }else{
                    chromeOptions.addArguments("--start-maximine");
                }
                return new ChromeDriver(chromeOptions);
        }

    }
}

package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        this.js = (JavascriptExecutor) driver;
    }
    public void highlight(WebElement element){
        js.executeScript("arguments[0].style.border ='2px solid red';", element);
        js.executeScript("arguments[0].style.backgroundColor ='yellow';",element);
    }
    public void unhighlight(WebElement element){
        try{
            js.executeScript("arguments[0].style.border = '';", element);
            js.executeScript("argument[0].style.backgroundColor = '';",element);
        }catch (Exception e){

        }
    }
}

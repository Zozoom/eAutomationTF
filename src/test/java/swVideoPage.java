import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class swVideoPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final int TIMEOUT = 5;
    private static final int POLLING = 100;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/browsers/chromedriver.exe");
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null){
            driver.close();
            driver.quit();
        }
    }

    /**
     * Waiting for element to be visible.
     * */
    private WebElement waitForElementToAppear(By locator) {
        System.out.println(ExpectedConditions.visibilityOfElementLocated(locator));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Selenium test case
     * */
    @Test
    public void swVideoPage() {

        String myUrl = "https://www.starwars.com/video";
        String elementName = "//input[@placeholder='Search Videos']";

        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);

        driver.get(myUrl);
        WebElement element = waitForElementToAppear(By.xpath(elementName));

        Assert.assertEquals(element.getAttribute("placeholder"),"Search Videos");

    }

}

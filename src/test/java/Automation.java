import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Automation {
    private static WebDriver driver;

    @BeforeAll
    static void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        options.addArguments("--headless"); // Enable headless mode
        options.addArguments("--window-size=1920,1080"); // Set window size
        options.addArguments("force-device-scale-factor=0.8"); // Set zoom level

        driver = new ChromeDriver(options); // Pass the ChromeOptions to the ChromeDriver
        driver.manage().window().maximize(); // Maximize the window (though in headless mode, this might not have an effect)
        driver.get("https://ytuongsangtaohcm.vn/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("document.body.style.zoom='80%'");
//        Thread.sleep(10000);
    }

    @ParameterizedTest
    @MethodSource("ExcelUtils#excelDataProvider")
    void testSubmitIdeas(String topic, String idea, String summary) throws InterruptedException, AWTException, IOException {
        WebElement authorField = driver.findElement(By.id("yt_tacgia"));
        authorField.sendKeys("Nguyen Ngoc Phuong Uyen");

        WebElement emailField = driver.findElement(By.id("yt_email"));
        emailField.sendKeys("phuongbinhthuan.dtn@gmail.com");

        WebElement phoneField = driver.findElement(By.id("yt_phone"));
        phoneField.sendKeys("0979635890");

        WebElement orgSelect = driver.findElement(By.cssSelector(".css-1hwfws3"));
        orgSelect.click();
        WebElement orgField = driver.findElement(By.id("react-select-2-input"));
        orgField.sendKeys("Binh Thuan" + Keys.ENTER);

        WebElement topicSelect = driver.findElement(By.cssSelector(".css-1hwfws3"));
        topicSelect.click();
        WebElement topicField = driver.findElement(By.id("react-select-5-input"));
        topicField.sendKeys(topic + Keys.ENTER);

        WebElement ideaField = driver.findElement(By.id("yt_ten"));
        ideaField.sendKeys(idea);

        WebElement summaryField = driver.findElement(By.id("tom_tat"));
        summaryField.sendKeys(summary);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500);

        WebElement submitButton = driver.findElement(By.xpath("(//button)[3]"));

        try {
            submitButton.click();
        } catch (ElementClickInterceptedException e){
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image, "png", new File("D://CurrentScreenshot.png"));
            driver.quit();
        }
        Thread.sleep(1000);

        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
        }
        Thread.sleep(2000);
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

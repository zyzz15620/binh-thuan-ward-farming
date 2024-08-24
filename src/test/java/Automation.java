import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Automation {
    private static WebDriver driver;

    @BeforeAll
    static void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ytuongsangtaohcm.vn/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(10000);
    }

    @ParameterizedTest
    @MethodSource("ExcelUtils#excelDataProvider")
    void testSubmitIdeas(String topic, String idea, String summary) throws InterruptedException {
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

        WebElement submitButton = driver.findElement(By.cssSelector(".btn-success"));
        submitButton.click();

        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
        }

        Thread.sleep(3000);
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

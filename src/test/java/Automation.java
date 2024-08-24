import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Automation {

    @ParameterizedTest
    @MethodSource("ExcelUtils#excelDataProvider")
    void testSubmitIdeas(String topic, String idea, String summary) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ytuongsangtaohcm.vn/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Tương tác với các trường trên trang web
        WebElement authorField = driver.findElement(By.id("yt_tacgia"));
        authorField.sendKeys("Nguyen Ngoc Phuong Uyen");

        WebElement emailField = driver.findElement(By.id("yt_email"));
        emailField.sendKeys("phuongbinhthuan.dtn@gmail.com");

        WebElement phoneField = driver.findElement(By.id("yt_phone"));
        phoneField.sendKeys("0979635890");

        // Điền dữ liệu từ Excel
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

        // Xử lý cảnh báo nếu có
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            // Không có cảnh báo
        }

        Thread.sleep(2000);
        driver.quit();
    }
}

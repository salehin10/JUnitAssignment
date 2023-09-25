import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJunitTesting{
    WebDriver driver;
    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

    }
    @DisplayName("Submitting Form")
    @Test
    public void submit() throws Exception{
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        String atitle = driver.getTitle();
        String etitle = "Practice webform for learners | Digital Unite";
        Assertions.assertEquals(atitle,etitle);
        System.out.println(atitle);
        Thread.sleep(10000);
        boolean ck = driver.findElement(By.xpath("//*[@id=\"onetrust-banner-sdk\"]/div/div/div[1]/button")).isDisplayed();
        if(ck == true){
            WebElement cookiee = driver.findElement(By.xpath("//*[@id=\"onetrust-banner-sdk\"]/div/div/div[1]/button"));

            cookiee.click();
        }
        Thread.sleep(1000);

        driver.findElement(By.className("form-text")).sendKeys("Mr Tester");
        System.out.println("Flag 1");
        driver.findElement(By.className("form-number")).sendKeys("0171557619478946");
        List<WebElement>  ageLabel = driver.findElements(By.className("ui-checkboxradio-radio-label"));
        ageLabel.get(2).click();
        driver.findElement(By.className("form-date")).sendKeys("05/25/2023");
        driver.findElement(By.className("form-email")).sendKeys("test@test.com");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,500)");
        driver.findElement(By.className("form-textarea")).sendKeys("Lorem Ipsum is simply dummy ## >! ?? of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an deocde?!# $$code. anotehr plot<< >>  printer took a galley of type and scrambled it to make a type specimen book.");
        String filePath = System.getProperty("user.dir") +"\\src\\test\\resources\\data_sheet.xlsx";
        //driver.findElement(By.className("form-file")).sendKeys("D:\\OneDrive - ReliSource Inc\\RTL Onedrive\\OneDrive - ReliSource Inc\\vi-eur\\Video\\SQA Automation\\SDET Batch 7(Job Holder Batch)\\data_sheet.xlsx");
        driver.findElement(By.className("form-file")).sendKeys(filePath);
        Thread.sleep(10000);
        driver.findElement(By.className("form-checkbox")).click();

        Thread.sleep(10000);
        driver.findElement(By.id("edit-submit")).click();
        System.out.println(driver.getTitle());
        String titleText = driver.findElement(By.id("block-pagetitle-2")).getText();
        System.out.println("Title to next page : "+titleText);
        Assertions.assertEquals(titleText, "Thank you for your submission!");

    }


    @AfterAll
    public void closeDriver() {
        driver.quit();
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ContactUsPage;
import pages.HomePage;
import pages.SearchResultPage;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class TestFinal {
    private WebDriver driver;
    private HomePage homePage;
    private ContactUsPage contactUsPage;
    private SearchResultPage searchResultPage;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"Browser", "Device", "Width", "Height"})
    public void start(@Optional String browserName,
                      @Optional String deviceName,
                      @Optional Integer width,
                      @Optional Integer height){
        if (browserName == null || browserName.isEmpty()) {
            driver = new ChromeDriver();
//            driver = new FirefoxDriver();
        } else {
            if (browserName.contentEquals("Chrome")) {
                if (deviceName != null && !deviceName.isEmpty()) {
                    Map<String, String> mobileEmulation = new HashMap<String, String>();
                    mobileEmulation.put("deviceName", deviceName);

                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

                    driver = new ChromeDriver(chromeOptions);

                } else {
                    driver = new ChromeDriver();
                }
            } else if (browserName.contentEquals("ff")) {
                driver = new FirefoxDriver();
            } else {
                driver = new ChromeDriver();
            }
        }

        if (deviceName != null && !deviceName.isEmpty()) {
            if (width!=null && width!=0 && height!=null && height!=0) {
                Dimension dimension = new Dimension(width, height);
                driver.manage().window().setSize(dimension);
            }
        }

//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com/index.php");
        homePage = new HomePage(driver);
        contactUsPage = new ContactUsPage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void finish(){
        driver.quit();
    }


    @Test()
    public void contactUsFormSendsSuccessfully(){

         homePage.contactUsPage();

//Fill in all the fields attach txt file
//        Verify that the success message is displayed.

        WebElement contactLink = driver.findElement(By.id("contact-link"));
        contactLink.click();

        WebElement uniformIdContact = driver.findElement(By.id("uniform-id_contact"));
        uniformIdContact.click();

        WebElement descContact2 = driver.findElement(By.xpath("//*[@id=\"id_contact\"]//option[2]"));
        descContact2.click();

        WebElement formControl = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        formControl.sendKeys("mikhailkirdzik@gmail.com");

        WebElement fileUpload = driver.findElement(By.xpath("//*[@id=\"fileUpload\"]"));
        fileUpload.sendKeys("D:\\Учеба\\АТ\\Final\\Test.txt");


        WebElement message = driver.findElement(By.xpath("//*[@id=\"message\"]"));
        message.sendKeys("Test");

        WebElement submitMessage = driver.findElement(By.id("submitMessage"));
        submitMessage.click();

        WebElement successfullySent = driver.findElement(By.xpath("//*[@id=\"center_column\"]//p"));
        String searchValue = successfullySent.getText();

        System.out.println(searchValue);

//            assertTrue(result.getFirstLink().contains("automated"));
        Assert.assertEquals("Your message has been successfully sent to our team.", searchValue);

    }

    @Test()

    public void errorMessageAppearsIfMessageAreaIsEmpty(){
        homePage.contactUsPage();


        WebElement uniformIdContact = driver.findElement(By.id("uniform-id_contact"));
        uniformIdContact.click();

        WebElement descContact2 = driver.findElement(By.xpath("//*[@id=\"id_contact\"]//option[2]"));
        descContact2.click();

        WebElement formControl = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        formControl.sendKeys("mikhailkirdzik@gmail.com");

        WebElement fileUpload = driver.findElement(By.xpath("//*[@id=\"fileUpload\"]"));
        fileUpload.sendKeys("D:\\Учеба\\АТ\\Final\\Test.txt");


//        WebElement message = driver.findElement(By.xpath("//*[@id=\"message\"]"));
//        message.sendKeys("Test");

        WebElement submitMessage = driver.findElement(By.id("submitMessage"));
        submitMessage.click();

        WebElement cannotBeBlank = driver.findElement(By.xpath("//*[@id=\"center_column\"]//li"));

                String searchValueCannotBeBlank = cannotBeBlank.getText();

        System.out.println(searchValueCannotBeBlank);

        Assert.assertEquals("The message cannot be blank.", searchValueCannotBeBlank);

    }




}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;

    }

    public ContactUsPage contactUsPage() {
       driver.findElement(By.id("contact-link")).click();

        return new ContactUsPage(driver);
    }

}

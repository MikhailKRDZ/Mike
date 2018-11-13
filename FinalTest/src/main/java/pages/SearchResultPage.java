package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage {
    private WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

//    public String getFirstLink() {
//        return driver.findElement(By.xpath("//*[@id='rso']//a[1]")).getText();
//    }
}

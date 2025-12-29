package org.SeleniumJavaPOM.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    public String dropdownOptionAtIndex(WebDriver driver, int index) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selected']/parent::div")));

        List<WebElement> stations = driver.findElements(By.xpath("//div[contains(@id,'Autocomplete')]//div[@title]"));

        for (int i = 0; i < stations.size(); i++) {
            String station = stations.get(i).getAttribute("title");
            if (i == index) {
//                System.out.println(station);
                break;
            }
        }

        String stationName = stations.get(index).getAttribute("title");

        return stationName;
    }

    public List<String> getAllDropdownStations(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selected']/parent::div")));

        List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@id,'Autocomplete')]//div[@title]"));

        List<String> stationNames = new ArrayList<>();

        for (WebElement element : elements) {
            stationNames.add(element.getAttribute("title"));
        }

        return stationNames;
    }


    public boolean waitForElement(WebDriver driver, String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return true;
    }


}

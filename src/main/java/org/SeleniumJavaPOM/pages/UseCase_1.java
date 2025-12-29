package org.SeleniumJavaPOM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.SeleniumJavaPOM.utilities.CommonUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class UseCase_1 {

    WebDriver driver;
    CommonUtils utils = new CommonUtils();


    public UseCase_1(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="txtStationFrom")
    private WebElement fromStationField;

    @FindBy(id="txtStationTo")
    private WebElement toStationField;

    @FindBy(xpath = "//input[@title='Select Departure date for availability']")
    private WebElement sortOnDateField;

    @FindBy(xpath = "//a[@class='icon-right-big']")
    private WebElement nextDateBtn;

    @FindBy(xpath = "//a[@class='icon-left-big']")
    private WebElement prevDateBtn;


    public void enterFromStation(String station) {
        fromStationField.click();
        fromStationField.clear();
        fromStationField.sendKeys(station);
    }
    public void onDateField() {
        sortOnDateField.click();
    }


    public List<String> getDropdownStations(WebDriver driver) {
        return utils.getAllDropdownStations(driver);
    }

    public void clickFutureDate(int days) {
        LocalDate futureDate = LocalDate.now().plusDays(days);
        for (int i=0; i<=days;i++) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(nextDateBtn));
            nextBtn.click();
        }

        System.out.println("Future Date: " + futureDate);
    }


}

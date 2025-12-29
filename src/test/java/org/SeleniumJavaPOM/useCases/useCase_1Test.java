package org.SeleniumJavaPOM.useCases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.SeleniumJavaPOM.base.baseTest;
import org.SeleniumJavaPOM.pages.UseCase_1;
import org.SeleniumJavaPOM.utilities.CommonUtils;
import org.SeleniumJavaPOM.utilities.ExcelUtils;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class useCase_1Test extends baseTest {

    private UseCase_1 useCase1;
    private ExcelUtils excelUtils;
    private CommonUtils utils;
    private SoftAssert softAssert;
    private Actions actions;


    @BeforeClass
    public void navToUrl() {
        driver.get("https://erail.in/");
        actions = new Actions(driver);
    }

    @BeforeMethod
    public void initConstructor() {
        useCase1 = new UseCase_1(driver);
        utils = new CommonUtils();
        excelUtils = new ExcelUtils();
        softAssert = new SoftAssert();
    }


    @Test(priority = 1)
    public void dropdownTest() {
        int index = 3;

        useCase1.enterFromStation("DEL");
        String stnName = utils.dropdownOptionAtIndex(driver, index);
        System.out.println("Station name at index "+index+" is "+stnName);
    }

    @Test(priority = 2)
    public void writeIntoExcel() {
        String inputPath = "testData/stationList.xlsx";

        List<String> expectedStations = excelUtils.readColumn(inputPath, "Sheet1");
        List<String> outputStations = useCase1.getDropdownStations(driver);

        String outputPath = System.getProperty("user.dir")+"/test-output/OutputStations.xlsx";
        ExcelUtils.writeList(outputPath, "OutputStations", outputStations);

        softAssert.assertTrue(outputStations.containsAll(expectedStations), "Expected stations not matched!");
        actions.sendKeys(Keys.ESCAPE).perform();
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void calendarDateTest() {

        int noOfDays = 30;

        useCase1.onDateField();
        useCase1.clickFutureDate(noOfDays);
        actions.sendKeys(Keys.ESCAPE).perform();
        System.out.println("Date selected successfully for +"+noOfDays+" days.");
    }


}


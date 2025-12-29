package org.SeleniumJavaPOM.useCases;

import org.openqa.selenium.By;
import org.SeleniumJavaPOM.base.baseTest;
import org.SeleniumJavaPOM.pages.UseCase_2;
import org.SeleniumJavaPOM.utilities.CommonUtils;
import org.SeleniumJavaPOM.utilities.ExcelUtils;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import static org.SeleniumJavaPOM.reports.ExtentLogger.*;

import java.io.IOException;


public class useCase_2Test extends baseTest {

    private UseCase_2 useCase2;
    private CommonUtils utils;
    private SoftAssert softAssert;

    @BeforeClass
    public void navToUrl() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @BeforeMethod
    public void initConstructor() {
        useCase2 = new UseCase_2(driver);
        utils = new CommonUtils();
        softAssert = new SoftAssert();
    }


    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {
        return ExcelUtils.getSheetData(System.getProperty("user.dir")+"/src/test/resources/testData/logindata.xlsx", "Sheet1");
    }

    @Test(dataProvider = "loginData")
    public void LoginTest(String username, String password, String expectedResult) {
        info("Step 1: Enter username: " + username);
        useCase2.enterUsername(username);
        info("Step 2: Enter password: " + password);
        useCase2.enterPassword(password);
        info("Step 3: Click Login button");
        useCase2.clickLoginBtn();

        if (expectedResult.equalsIgnoreCase("PASS")) {
            info("Step 4: Validate dashboard is visible");
            softAssert.assertTrue(utils.waitForElement(driver,"//h6[normalize-space()='Dashboard']"));
            driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
            info("Step 5: Logout");
            driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
        } else {
            info("Step 4: Validate error alert is visible");
            softAssert.assertTrue(utils.waitForElement(driver,"//div[@role='alert']"));
        }
        softAssert.assertAll();
    }

}

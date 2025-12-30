package org.SeleniumJavaPOM.utilities.reportUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;


public class ExtentReportsManager {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {

        if (extent == null) {
            String path = System.getProperty("user.dir") + "/reports/ExtentReport.html";

            new File(System.getProperty("user.dir") + "/reports").mkdirs();

            ExtentSparkReporter spark = new ExtentSparkReporter(path);
            spark.config().setReportName("Rapifuzz Automation Report");
            spark.config().setDocumentTitle("Rapifuzz Automation Execution");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("Author", "Sidharth Khurana");
        }

        return extent;
    }


}

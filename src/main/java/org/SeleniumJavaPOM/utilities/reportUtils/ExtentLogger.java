package org.SeleniumJavaPOM.utilities.reportUtils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentLogger {

    private ExtentLogger() {}

    private static ExtentTest currentTest() {
        ExtentTest t = ExtentListener.getTest();
//        if (t == null) {
//            System.out.println("[WARN] ExtentTest is null.");
//        }
        return t;
    }

    public static void info(String message) {
        ExtentTest t = currentTest();
        if (t != null) t.log(Status.INFO, message);
    }

    public static void pass(String message) {
        ExtentTest t = currentTest();
        if (t != null) t.log(Status.PASS, message);
    }

    public static void fail(String message) {
        ExtentTest t = currentTest();
        if (t != null) t.log(Status.FAIL, message);
    }

    public static void warn(String message) {
        ExtentTest t = currentTest();
        if (t != null) t.log(Status.WARNING, message);
    }

}
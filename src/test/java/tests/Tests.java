package tests;

import base.Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.Page;

public class Tests extends Base {
    Page page;
    ExtentHtmlReporter reporter = new ExtentHtmlReporter("report.html");
    ExtentReports extentReports = new ExtentReports();

    @Test(priority = 6)
    public void validInput() throws InterruptedException {
        ExtentTest extentTest = extentReports.createTest("Valid inputs");
        extentReports.attachReporter(reporter);
        page = new Page(driver, extentTest);

        page.selectOption3();
        page.setAddressField("Avan 10");
        page.setCommentField("Sport lover");
        page.setName("Ruben");
        page.setEmailField("rubgeg@gmail.com");
        page.submitClick();
        page.isSubmitted(driver, getBaseUrl());

        Thread.sleep(4000);
        extentReports.flush();

        if (extentTest.getStatus().toString().equals("fail")) {
            Assert.fail();
        }
    }

    @Test(priority = 2)
    public void invalidAddressTest() throws InterruptedException {
        extentReports.attachReporter(reporter);
        ExtentTest extentTest = extentReports.createTest("Checking without address");
        page = new Page(driver, extentTest);

        page.selectOption3();
        page.setAddressField("");// Setting invalid  address  for test
        page.setCommentField("Sport lover");
        page.setName("Ruben");
        page.setEmailField("rubgeg@gmail.com");
        page.submitClick();
        page.setPhoneNumberField("+37495612262");
        page.isSubmitted(driver, getBaseUrl());

        extentReports.flush();

        if (extentTest.getStatus().toString().equals("fail")) {
            Assert.fail();
        }
    }

    @Test(priority = 3)
    public void invalidEmailTest() throws InterruptedException {

        extentReports.attachReporter(reporter);
        ExtentTest extentTest = extentReports.createTest("Checking without e-mail");
        Page page = new Page(driver, extentTest);

        page.selectOption3();
        page.setAddressField("Avan 10");
        page.setCommentField("Sport lover");
        page.setName("Ruben");
        page.setEmailField("rubgeg"); //setting invalid format email
        page.submitClick();
        page.isSubmitted(driver, getBaseUrl());

        extentReports.flush();

        if (extentTest.getStatus().toString().equals("fail")) {
            Assert.fail();
        }
    }

    @Test(priority = 4)
    public void invalidNameInput() throws InterruptedException {

        extentReports.attachReporter(reporter);
        ExtentTest extentTest = extentReports.createTest("Checking without name");
        page = new Page(driver, extentTest);
        page.selectOption3();

        page.setAddressField("Avan 10");
        page.setCommentField("Sport lover");
        page.setName("");// setting invalid name
        extentTest.pass("Name: Ruben");
        page.setEmailField("rubgeg@gmail.com");
        page.submitClick();
        page.isSubmitted(driver, getBaseUrl());

        extentReports.flush();

        if (extentTest.getStatus().toString().equals("fail")) {
            Assert.fail();
        }
    }


    @Test(priority = 5)
    public void checkingClearForm() throws InterruptedException {
        extentReports.attachReporter(reporter);
        ExtentTest extentTest = extentReports.createTest("Checking to clear the document");
        page = new Page(driver, extentTest);

        page.selectOption3();
        page.setAddressField(""); //setting invalid  address
        page.setCommentField("Sport lover");
        page.setName("Ruben");
        page.submitClick();
        page.setPhoneNumberField("+37495612262");
        page.isSubmitted(driver, getBaseUrl());
        page.clearForm();
        page.checkClearForm();

        extentReports.flush();

        if (extentTest.getStatus().toString().equals("fail")) {
            Assert.fail();
        }
    }


    @Test(priority = 1)
    public void minimumValidInputs() throws InterruptedException {
        extentReports.attachReporter(reporter);
        ExtentTest extentTest = extentReports.createTest("Checking with minimum required fields");
        page = new Page(driver, extentTest);

        page.setAddressField("Avan 10");
        page.setName("Ruben");
        page.setEmailField("rubgeg@gmail.com");
        page.submitClick();
        page.isSubmitted(driver, getBaseUrl());

        extentReports.flush();

        if (extentTest.getStatus().toString().equals("fail")) {
            Assert.fail();
        }
    }
}

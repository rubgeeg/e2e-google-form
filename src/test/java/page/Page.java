package page;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Page {

    WebDriver webDriver;
    ExtentTest extentTest;

    public Page(WebDriver webDriver,ExtentTest extentTest) {
        this.webDriver = webDriver;
        this.extentTest = extentTest;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "i11")
    WebElement radioButton;

    @FindBy(css = "[aria-labelledby='i23']")
    WebElement nameField;

    @FindBy(xpath = "//input[@type='email']")
    WebElement emailField;

    @FindBy(xpath = "//textarea[@aria-labelledby='i31']")
    WebElement addressField;

    @FindBy(xpath = "//input[@aria-labelledby='i35']")
    WebElement phoneNumberField;

    @FindBy(xpath = "//textarea[@aria-labelledby='i39']")
    WebElement commentField;

    @FindBy(css = "[role='button']")
    List<WebElement> submitButtonList;

    @FindBy(css = "[id='i29']")
    WebElement emailErrorField;

    @FindBy(css = "[id='i25']")
    WebElement nameErrorField;

    @FindBy(css = "[id='i33']")
    WebElement addressErrorField;

    @FindBy(css = "[role='button']")
    List<WebElement> allButton;

    @FindBy(xpath = "//input[@type='text'] ")
    List<WebElement> inputs;


    public void selectOption3() {
        extentTest.pass("Selected 3rd option");
        radioButton.click();

    }

    public void clearForm( ) {
        extentTest.pass("Trying to clear the document");
        submitButtonList.get(submitButtonList.size() - 2).click();
        allButton.get(allButton.size() - 1).click();
    }

    public void checkClearForm( ) {
        for (WebElement element : inputs) {
            String text = element.getAttribute("data-initial-value");
            if (text.length() > 0) {
                extentTest.log(Status.FAIL, "Failed to clear the document");
                return;
            }

        }
        extentTest.pass("The document was successfully cleared");
    }

    public void checkEmailValidation( ) {
        boolean isValid = emailErrorField.getText().length() <= 0;
        if (!isValid) {
            extentTest.log(Status.FAIL, "Type right e-mail");
        }
    }

    public void isSubmitted( WebDriver driver, String path) {
        if (driver.getCurrentUrl().equals(path)) {
            checkAdressValidation();
            checkNameValidation();
            checkEmailValidation();
            return;
        }
        System.out.println("Test result: passed");
        extentTest.log(Status.PASS, "Submitted  successfull");
    }

    public void checkNameValidation( ) {
        boolean isValid = nameErrorField.getText().length() == 0;
        if (!isValid) {
            extentTest.log(Status.FAIL, "Type your name");
        }
    }

    public void checkAdressValidation() {
        boolean isValid = addressErrorField.getText().length() == 0;
        if (!isValid) {
            extentTest.log(Status.FAIL, "Type your address");
        }
    }

    public void setName(String name) {
        extentTest.pass("Name: " + name);
        nameField.sendKeys(name);
    }

    public void setEmailField(String email ) {
        extentTest.pass("E-mail: " + email);
        emailField.sendKeys(email);
    }

    public void setAddressField(String address ) {
        extentTest.pass("Address: " + address);
        addressField.sendKeys(address);
    }

    public void setPhoneNumberField(String phoneNumber ) {
        extentTest.pass("Phone: " + phoneNumber);
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void setCommentField(String comment ) {
        extentTest.pass("Comment: " + comment);

        commentField.sendKeys(comment);
    }

    public void submitClick() {
        extentTest.pass("Clicked on Submit ");
        submitButtonList.get(submitButtonList.size() - 3).click();
    }
}

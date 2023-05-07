package techproed.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import techproed.utilities.Driver;

public class TestCenterTechproPage {

    public TestCenterTechproPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//*[@id='exampleInputEmail1']")
    public WebElement userName;

    @FindBy(xpath = "//*[@role='alert']")
    public WebElement text;
    @FindBy(xpath = "//*[text()=' Logout']")
    public WebElement logout;

    @FindBy(xpath = "//*[text()='Login Page']")
    public WebElement giris;


    public static class BlueRentACarPage {
        public BlueRentACarPage(){
            PageFactory.initElements(Driver.getDriver(),this);
        }
        @FindBy(xpath = "(//*[@role='button'])[1]")
        public WebElement login;

        @FindBy(xpath = "//*[@id='formBasicEmail']")
        public WebElement email;

        @FindBy(id = "dropdown-basic-button")
        public WebElement verify;

        @FindBy(xpath ="//*[@role='alert']")
        public WebElement hataMesaji;

        @FindBy(xpath ="//*[@type='submit']")
        public WebElement login2;


    }
}

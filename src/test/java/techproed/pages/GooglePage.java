package techproed.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import techproed.tests.day26_ExcelDataProvider.C01_DataProvider;

public class GooglePage {

    public GooglePage(){

        PageFactory.initElements(C01_DataProvider.Driver.getDriver(),this);

    }
    @FindBy(xpath ="//*[@name='q']")
    public WebElement aramaKutusu1;
    @FindBy(xpath = "//*[@text()='Alles accepteren']")
    public WebElement accepterkutu;

}

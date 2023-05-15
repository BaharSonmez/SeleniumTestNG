package techproed.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import techproed.tests.day26_ExcelDataProvider.C01_DataProvider;

public class AmazonPage {
    public AmazonPage() {

        PageFactory.initElements(C01_DataProvider.Driver.getDriver(),this);



    }

    //  C01_PageClassKullanimi LOCATELERİ

    @FindBy(xpath = "//select[@id='searchDropdownBox']")
    public WebElement ddm;




    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    public WebElement aramaKutusu;





    @FindBy(xpath = "(//img[@class='s-image'])[2]")
    public WebElement ikinciUrun;






    // C02_Raporlama LOCATE





    @FindBy(xpath = "//div[@class='a-section a-spacing-small a-spacing-top-small']")
    public WebElement sonucYazisi1;




    @FindBy(xpath = "(//div[@class='a-section a-spacing-small a-spacing-top-small'])[1]")
    public WebElement sonucYazisi2;




}
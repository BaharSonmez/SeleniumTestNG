package techproed.tests.day25_ExcelUtils;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import techproed.pages.BlueRentalPage;
import techproed.tests.day26_ExcelDataProvider.C01_DataProvider;
import techproed.utilities.ConfigReader;

import java.io.FileNotFoundException;

public class C02_BlueRentalExcelTest {
    @Test
    public void excelTest() throws FileNotFoundException {
       /*
    Eger bir veriyi (datayi) .properties dosyasindan almak istiyorsak , ConfigReader classindan getProperty()
    methodu kullanarak  .properties dosyamiza girdigimiz key degerini belirtiriz ve bize buy key degerinin
    value sunu dondurur.

     Eger bir veriyi(datayi) excel dosyasindan almak istiyorsak, olusturmus oldugumuz ExcelUtils class'sindaki
     methodlari kullanarak istedigimiz veriyi alabiliriz...
     */

        C01_DataProvider.ExcelUtils excelUtils = new C01_DataProvider.ExcelUtils("src/test/java/resources/mysmoketestdata.xlsx","customer_info");
        String email = excelUtils.getCellData(1,0);
        String password = excelUtils.getCellData(1,1);
        System.out.println(email+"  "+password);


        //Bluerantal car adresine gidelim.
        C01_DataProvider.Driver.getDriver().get(ConfigReader.getProperty("blueRentACarUrl"));


        //Excel dosyamızdan aldıgımız ılk emaıl ve password ıle sayfaya login olalım.
        BlueRentalPage blueRentalPage = new BlueRentalPage();
        blueRentalPage.login.click();
        blueRentalPage.email.sendKeys(email, Keys.TAB,password,Keys.ENTER);
        //excelden aldigimiz verileri sendKeys methodu ile gonderdik

        //Login oldugumuzu dogrulyalım.
        Assert.assertTrue(blueRentalPage.verify.isDisplayed());

    }
}

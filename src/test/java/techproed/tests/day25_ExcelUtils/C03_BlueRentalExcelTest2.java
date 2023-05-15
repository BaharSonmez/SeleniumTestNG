package techproed.tests.day25_ExcelUtils;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import techproed.pages.BlueRentalPage;
import techproed.tests.day26_ExcelDataProvider.C01_DataProvider;
import techproed.utilities.ConfigReader;

import java.io.FileNotFoundException;

public class C03_BlueRentalExcelTest2 {
    @Test
    public void test1() throws FileNotFoundException {
        /*
        Excel dosyamisdaki tum email ve passwordler ile
        BlueRentalCar sayfasina gidip login olalim

         */

        C01_DataProvider.ExcelUtils excelUtils = new C01_DataProvider.ExcelUtils("src/test/java/resources/mysmoketestdata.xlsx","customer_info");
        //once sayfaya gidelim
        C01_DataProvider.Driver.getDriver().get(ConfigReader.getProperty("blueRentACarUrl"));
        //Bir loop olusturup excel dosyasindaki tum verileri girdirelim
        BlueRentalPage blueRentalPage= new BlueRentalPage();
        for (int i = 1; i <= excelUtils.rowCount() ; i++) { //i' yi birden baslattik cunku veriler1. satidan basliyor
            String mail = excelUtils.getCellData(i,0);
            String password = excelUtils.getCellData(i,1);
            System.out.println(mail + " "+password);

         blueRentalPage.login.click();
            C01_DataProvider.ReusableMethods.bekle(3);
         blueRentalPage.email.sendKeys(mail, Keys.ENTER,password,Keys.ENTER);
         assert  blueRentalPage.verify.isDisplayed();
            C01_DataProvider.ReusableMethods.bekle(3);
         blueRentalPage.login3.click();
         blueRentalPage.logout.click();
         C01_DataProvider.ReusableMethods.bekle(3);
         blueRentalPage.ok.click();

        }
        C01_DataProvider.Driver.closeDriver();

    }
}

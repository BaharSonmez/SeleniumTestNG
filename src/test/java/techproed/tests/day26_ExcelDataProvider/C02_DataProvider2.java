package techproed.tests.day26_ExcelDataProvider;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import techproed.pages.BlueRentalPage;
import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;

public class C02_DataProvider2 {
    @DataProvider
    public static Object[][] kullanicilar() {
        return new Object[][]{{ConfigReader.getProperty("email1"),ConfigReader.getProperty("password1")},
                {ConfigReader.getProperty("email2"),ConfigReader.getProperty("password2")}};


    }

    @Test(dataProvider = "kullanicilar")
    public void test1(String email, String password ) {
        Driver.getDriver().get(ConfigReader.getProperty("blueRentACarUrl"));

        BlueRentalPage blueRentalPage = new BlueRentalPage();
        blueRentalPage.login.click();//login butonuna basti
        blueRentalPage.email.sendKeys(email, Keys.TAB,password,Keys.ENTER);
        techproed.tests.day26_ExcelDataProvider.C01_DataProvider.ReusableMethods.bekle(3);
        techproed.tests.day26_ExcelDataProvider.C01_DataProvider.Driver.closeDriver();


    }
}

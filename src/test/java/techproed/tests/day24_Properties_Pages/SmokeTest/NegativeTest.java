package techproed.tests.day24_Properties_Pages.SmokeTest;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import techproed.pages.TestCenterTechproPage;
import techproed.tests.day26_ExcelDataProvider.C01_DataProvider;
import techproed.utilities.ConfigReader;

public class NegativeTest {
    @Test
    public void test1(){
        /*
        Description:
        Kullanimda olmayan kullanıcı adi ve şifre ile giriş yapilamamali
        Acceptance Criteria
        Customer email: fake@bluerentalcars.com
        Customer password: fakepass
        Error:
        User with email fake@bluerentalcars.com not found
         */
        C01_DataProvider.Driver.getDriver().get(ConfigReader.getProperty("blueRentACarUrl"));
        TestCenterTechproPage.BlueRentACarPage blueRentalPage = new TestCenterTechproPage.BlueRentACarPage();
        blueRentalPage.login.click();
        blueRentalPage.email.sendKeys(ConfigReader.getProperty("fakeEmail"),
                Keys.TAB,ConfigReader.getProperty("fakepass"));
        blueRentalPage.login2.click();

        C01_DataProvider.ReusableMethods.bekle(3);
        C01_DataProvider.ReusableMethods.tumSayfaResmi();
        Assert.assertTrue(blueRentalPage.hataMesaji.isDisplayed());
        C01_DataProvider.Driver.closeDriver();
    }
}
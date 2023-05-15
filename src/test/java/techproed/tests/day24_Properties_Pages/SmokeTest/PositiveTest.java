package techproed.tests.day24_Properties_Pages.SmokeTest;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import techproed.pages.TestCenterTechproPage;
import techproed.tests.day26_ExcelDataProvider.C01_DataProvider;
import techproed.utilities.ConfigReader;

public class PositiveTest {
    @Test
    public void test1() {
         /*
        Acceptance Criteria:
        Admin olarak, uygulamaya giriş yapabilmeliyim
        https://www.bluerentalcars.com/
        Admin email: jack@gmail.com
        Admin password: 12345
         */
        C01_DataProvider.Driver.getDriver().get(ConfigReader.getProperty("blueRentACarUrl"));
        TestCenterTechproPage.BlueRentACarPage blueRentalPage = new TestCenterTechproPage.BlueRentACarPage();
        blueRentalPage.login.click();

        blueRentalPage.email.sendKeys(ConfigReader.getProperty("email"),
                Keys.TAB,ConfigReader.getProperty("pass"),Keys.ENTER);

        Assert.assertEquals(blueRentalPage.verify.getText(),equals("Jack Nicholson"));
    }
}

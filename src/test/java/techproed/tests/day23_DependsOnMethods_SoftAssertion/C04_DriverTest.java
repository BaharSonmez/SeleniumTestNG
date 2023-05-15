package techproed.tests.day23_DependsOnMethods_SoftAssertion;

import org.testng.annotations.Test;
import techproed.tests.day26_ExcelDataProvider.C01_DataProvider;
import techproed.utilities.ConfigReader;

public class C04_DriverTest {
    @Test
    public void driverTest1() {
        C01_DataProvider.Driver.getDriver().get("https://techproeducation.com");
        C01_DataProvider.Driver.getDriver().get("https://amazon.com");
        C01_DataProvider.Driver.getDriver().get("https://facebook.com");
        C01_DataProvider.Driver.getDriver().get(ConfigReader.getProperty("techproed_Url"));
        System.out.println(ConfigReader.getProperty("username"));
        System.out.println(ConfigReader.getProperty("password"));
        
    }
}

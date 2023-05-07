package techproed.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class Driver {
    /*
    Drver class'indaki temel mantik extends yontemiyle degil yani TestBase class'ina extent etmke yerine
    Driver class'indan static methodlar kullanarak driver olustururuz. Static oldugu icin class
    ismi ile methoda heryerden ulasabilecegiz.

    --------
    Singleton Pattern: Tekli kullanim kalibidir..
      Yani bir classtan obje olusturulmasinin onune gecilmesi icin kullanilan ifade
      Bir classtan obje olusturmanin onune gecmek icin default Constructor'in kullanimini engellemek icin
      Private access modifire kullanarak bir constructor olustururuz


     */
    private Driver(){

    }


    static WebDriver driver;

    public static WebDriver getDriver() {
        /*
        Driver'i her cagirdiginda yeni bir pencere acilmasinin onuna gecmek icin
        IF blogu icinde eger driver'a deger atanmamissa (driver dolu ise)deger ata, eger deger atanmisssa driver'i ayni
        sayfada return et. Bunun icin sadece yapmamiz gereken if(driver==null) kullanmak

         */
        if (driver == null) {
            switch (ConfigReader.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-orgins=*"));
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(new EdgeOptions().addArguments("--remote-allow-origins=*"));
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }

        return driver;

    }

    public static void closeDriver() {
        if (driver != null) {//Drivera deger atanmissa

            driver.close();
            driver = null;
        }


    }

    public static void quitDriver() {
        if (driver != null) {//Drivera deger atanmissa

            driver.quit();
            driver = null;
        }

    }
}
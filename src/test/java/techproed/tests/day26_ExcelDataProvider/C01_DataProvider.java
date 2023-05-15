package techproed.tests.day26_ExcelDataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import techproed.utilities.ConfigReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class C01_DataProvider {
    public static class Driver {
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

    public static class ExcelUtils {
         private Workbook workbook;
         private Sheet sheet;
        private String path;
        //Constrictor: Excel path'ine ve Excel'deki sayfaya ulasmak icin 2 parametreli constructor olusturduk

        public ExcelUtils(String path, String sheetName) throws FileNotFoundException {
            this.path = path;
            FileInputStream fis = new FileInputStream(path);
            try {
                workbook = WorkbookFactory.create(fis);
                sheet = workbook.getSheet(sheetName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
        //Satir ve sutun sayilari girildiginde , o hucredeki veriyi return eder

        public String getCellData(int rowNum, int colNum){
            Cell cell = sheet.getRow(rowNum).getCell(colNum);
            return cell.toString();


        }
       //Exceldeki satir sayisini return eder
        public int rowCount(){
            return sheet.getLastRowNum();

        }
        //Exceldeki sutun sayisini return eder
        public int columnCount(){

            return sheet.getRow(0).getLastCellNum();
        }
        //Exceldeki datalari alabilmek icin 2 boyutlu bir array method olusturalim
        public String [][] getDataArray(){

            String [][] data = new String [rowCount()][columnCount()];
            for (int i =1; i<=rowCount(); i++){
                for (int j= 0; j<columnCount(); j++){
                    String value = getCellData(i,j);
                    data[i-1][j]=value;

                }

            }
            return data;
        }
    }

    public static class ReusableMethods {

        protected static ExtentReports extentReports;
        protected static ExtentHtmlReporter extentHtmlReporter;
        protected static ExtentTest extentTest;


        //HARD WAIT METHOD
        public static void bekle(int saniye) {
            try {
                Thread.sleep(saniye * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //Alert ACCEPT
        public static void alertAccept() {
            Driver.getDriver().switchTo().alert().accept();
        }

        //Alert DISMISS
        public static void alertDismiss() {
            Driver.getDriver().switchTo().alert().dismiss();
        }

        //Alert getText()
        public static void alertText() {
            Driver.getDriver().switchTo().alert().getText();
        }

        //Alert promptBox
        public static void alertprompt(String text) {
            Driver.getDriver().switchTo().alert().sendKeys(text);
        }

        //DropDown VisibleText
        /*
            Select select2 = new Select(gun);
            select2.selectByVisibleText("7");

            //ddmVisibleText(gun,"7"); --> Yukarıdaki kullanım yerine sadece method ile handle edebilirim
         */
        public static void ddmVisibleText(WebElement ddm, String secenek) {
            Select select = new Select(ddm);
            select.selectByVisibleText(secenek);
        }

        //DropDown Index
        public static void ddmIndex(WebElement ddm, int index) {
            Select select = new Select(ddm);
            select.selectByIndex(index);
        }

        //DropDown Value
        public static void ddmValue(WebElement ddm, String secenek) {
            Select select = new Select(ddm);
            select.selectByValue(secenek);
        }

        //SwitchToWindow1
        public static void switchToWindow(int sayi) {
            List<String> tumWindowHandles = new ArrayList<String>(Driver.getDriver().getWindowHandles());
            Driver.getDriver().switchTo().window(tumWindowHandles.get(sayi));
        }

        //SwitchToWindow2
        public static void window(int sayi) {
            Driver.getDriver().switchTo().window(Driver.getDriver().getWindowHandles().toArray()[sayi].toString());
        }
        //EXPLICIT WAIT METHODS

        //Visible Wait
        public static void visibleWait(WebElement element, int sayi) {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
            wait.until(ExpectedConditions.visibilityOf(element));

        }

        //VisibleElementLocator Wait
        public static WebElement visibleWait(By locator, int sayi) {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        }

        //Alert Wait
        public static void alertWait(int sayi) {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
            wait.until(ExpectedConditions.alertIsPresent());

        }

        //Tüm Sayfa ScreenShot
        public static void tumSayfaResmi() {
            String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
            String dosyaYolu = "TestOutput/screenshot/screenshot" + tarih + ".png";
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            try {
                FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //WebElement ScreenShot
        public static void webElementResmi(WebElement element) {
            String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
            String dosyaYolu = "TestOutput/screenshot/webElementScreenshot" + tarih + ".png";

            try {
                FileUtils.copyFile(element.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //ExtentReport
        public static void extentReport() {
            extentReports = new ExtentReports();
            String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
            String dosyaYolu = "TestOutput/reports/extentReport_" + tarih + ".html";
            extentHtmlReporter = new ExtentHtmlReporter(dosyaYolu);
            extentReports.attachReporter(extentHtmlReporter);

            //Raporda gözükmesini istediğimiz bilgiler için
            extentReports.setSystemInfo("Browser", "Chrome");
            extentReports.setSystemInfo("Tester", "Erol");
            extentHtmlReporter.config().setDocumentTitle("Extent Report");
            extentHtmlReporter.config().setReportName("Smoke Test Raporu");
        }

        //WebTable
        public static void printData(int satir, int sutun) {
            WebElement satirSutun = Driver.getDriver().findElement(By.xpath("(//tbody)[1]//tr[" + satir + "]//td[" + sutun + "]"));
            System.out.println(satirSutun.getText());
        }

        //Click Method
        public static void click(WebElement element) {
            try {
                element.click();
            } catch (Exception e) {
                JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
                js.executeScript("arguments[0].click();", element);
            }
        }

        //JS Scroll
        public static void scroll(WebElement element) {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        }

        //JS Sayfa Sonu Scroll
        public static void scrollEnd() {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        }

        //JS Sayfa Başı Scroll
        public static void scrollHome() {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
        }

        //JS SendKeys
        public static void sendKeysJS(WebElement element, String text) {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].value='" + text + "'", element);
        }

        //JS SendAttributeValue
        public static void sendAttributeJS(WebElement element, String text) {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
        }

        //JS GetAttributeValue
        public static void getValueByJS(String id, String attributeName) {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            String attribute_Value = (String) js.executeScript("return document.getElementById('" + id + "')." + attributeName);
            System.out.println("Attribute Value: = " + attribute_Value);
        }
        public static String getScreenshot(String name) throws IOException {
            // naming the screenshot with the current date to avoid duplication
            String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            // TakesScreenshot is an interface of selenium that takes the screenshot
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            // full path to the screenshot location
            String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
            File finalDestination = new File(target);
            // save the screenshot to the path given
            FileUtils.copyFile(source, finalDestination);
            return target;
        }

    }
}

package techproed.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    //Once Properties dosyasindaki verileri okuyabilmek icin JAVA'dan properties class'indan bir obje olustururuz
    public static Properties properties;

    //Herseyden once calismasi icin Static Blok icerisinde olusturmus oldugum Properties dosyasini tanimlar yani yolunu alir ve
    //atamasini yapariz. FileInputStream ile dosya yolunu akisa aliriz..

    static {
        String dosyaYolu="configuration.properties";
        try {
            FileInputStream fis = new FileInputStream(dosyaYolu);
            properties = new Properties(); //Objeyi olusturduk ve atamasini gerceklestirdik
            properties.load(fis);//fis'in okudugu bilgileri properties'e yukler...
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //properties dosyasindaki key degerini alip test methodumla kullanabilmek icin bir method
    //olusturmaliyiz...
    public static String getProperty(String Key){//String bir deger dondurmesi icin String bir parametre atariz
        /*
        Test methodundan gonderdigimiz String key degerini alip ConfigReader class'indan getProperty() methodunu
        kullanarak bu key'e ait value'yu bize getirir..
         */
        return properties.getProperty(Key);

    }

}

package com.tasks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class Post2 {
    private static WebDriver driver;

    public static void initTest() {

        //Config del driver
        WebDriverManager.chromedriver().setup();

        //Instancia del driver
        driver = new ChromeDriver();

        //Maximizar ventana
        driver.manage().window().maximize();

        //Config de tiempo de espera (implícito): Tiempo general. Tiempo que va a esperar por cada elemento con el que se interactue. Es la espera max
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //Explícita: Es propia de un elemento y en base a una condición. Por ejemplo para un elemento como el logo de youtube, esperar a que esté visible (a un tipo de acción)

        //Espera de java. Es absoluta. No recomendada
        //Thread.sleep(5000);
    }


    public static void interactions (String pathFile, String subid) {

        //Abrir página
        driver.get("file:///Users/lfigueras/Documents/proyect-apirest/src/main/java/com/tasks/uploadform/index.html");

        //Mapear elemento 1
        WebElement inputfile = driver.findElement(By.id("file"));

        //Mapear elemento 2
        WebElement inputsubid = driver.findElement(By.id("sub_id"));

        //Mapear elemento 3
        WebElement uploadBotton = driver.findElement(By.id("uploadBtn"));

        //Mapear elemento 4
        WebElement response = driver.findElement(By.id("response"));

        //Interacción 1: Enviar valor
        inputfile.sendKeys(String.valueOf(pathFile));

        //Interacción 2: Enviar valor
        inputsubid.sendKeys(String.valueOf(subid));

        //Acá se hace el Post con Java Script
        //Hacer clic sobre el botón para la acción: Submit
        uploadBotton.click();

        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Obtener respuesta
        String resp = response.getAttribute("value");

        //Imprimir respuesta de operación
        System.out.println("Respuesta: "+ resp);

        System.out.println("\n");

        driver.close();
        //return answer;
    }

    //Hacer uso de un metodo al que le pida la operación y resultado esperado. Refactorizar
    //@Test
    //public void test1() {
//
    //
    //}
//
    //@After
    //public void finishTest() throws InterruptedException {
//
    //    //Espera de java. Es absoluta. No recomendada
    //    Thread.sleep(5000);
//
    //    //Cerrar el navegador
    //    driver.close();
    //}
}

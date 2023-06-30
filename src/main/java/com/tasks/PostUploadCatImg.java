package com.tasks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.utils.SelectRandomIdImg.randomIdImgMethod;
import static com.utils.ReadParamProperties.findParam;

public class Post2 {

    static WebDriver driver;

    public static void openDataForm(String urlPage) {

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

        //Abrir página
        driver.get(urlPage);

    }


    public static void interactions (String pathFile, String subid) {

        //Mapear elemento 1
        WebElement inputfile = driver.findElement(By.id("file"));

        //Mapear elemento 2
        WebElement inputsubid = driver.findElement(By.id("sub_id"));

        //Mapear elemento 3
        WebElement uploadBotton = driver.findElement(By.id("uploadBtn"));

        //Mapear elemento 4
        WebElement response = driver.findElement(By.id("response"));

        //Mapear elemento 5
        WebElement statusCode = driver.findElement(By.id("statuscode"));

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

        //Obtener status code
        String statusCod = statusCode.getAttribute("value");

        driver.close();

        if ("201".equals(statusCod)) {

            JSONObject jsonObject = new JSONObject(resp);
            String id = jsonObject.getString("id");
            System.out.println("id: " + id);


            // Refactorizar de acá hacia abajo

            List<Map<String, Object>> listResponseBodyUpImg = new ArrayList<>();

            Map<String, Object> mapJsonObj = new HashMap<String, Object>();
            mapJsonObj.put("id", id);

            listResponseBodyUpImg.add(mapJsonObj);


            //Se llama a un método randomIdImgMethod de la clase SelectRandomIdImg para enviarle la lista con el único response de la imagen cargada
            //y seleccionar el id de la imagen cargada
            randomIdImgMethod(listResponseBodyUpImg, 2);

            for (Map<String, Object> images : listResponseBodyUpImg) {
                System.out.println(images.get("id"));
            }
        }
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

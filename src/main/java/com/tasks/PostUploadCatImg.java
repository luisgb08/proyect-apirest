package com.tasks;

import com.drivers.DriverChrome;
import com.userinterfaces.Cats;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.drivers.DriverChrome.*;
import com.userinterfaces.Cats.*;

import static com.drivers.DriverChrome.closeDriver;
import static com.utils.ReadParamProperties.findParam;
import static com.utils.SelectRandomIdImg.randomIdImgMethod;

public class PostUploadCatImg {

    static String statusCod;

    public static String getUploadCatImgStatusCod() {
        return statusCod;
    }

    public static void setUploadCatImgStatusCod(String statusCod) {
        PostUploadCatImg.statusCod = statusCod;
    }

    public static void dataFormWebInteractions(String pathFile, String subid, String urlreq, String apikey) {
        System.out.println("prueba");
        Cats objIntFields = new Cats();

        objIntFields.URLPOSTREQ.sendKeys(String.valueOf(urlreq));

        objIntFields.APIKEY.sendKeys(String.valueOf(apikey));

        //Interacción 1: Enviar valor
        objIntFields.INPUT_FILE.sendKeys(String.valueOf(pathFile));

        //Interacción 2: Enviar valor
        objIntFields.INPUT_SUBID.sendKeys(String.valueOf(subid));

        //Acá se hace el Post con JavaScript
        //Hacer clic sobre el botón para la acción: Submit
        objIntFields.UPLOAD_BUTTON.click();

        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Obtener respuesta
        String resp = objIntFields.RESPONSE.getAttribute("value");

        //Imprimir respuesta de operación
        System.out.println("Respuesta: "+ resp);

        System.out.println("\n");

        //Obtener status code
        statusCod = objIntFields.STATUS_CODE.getAttribute("value");

        //Cerrar navegador
        closeDriver();

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
}

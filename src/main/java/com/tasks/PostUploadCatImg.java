package com.tasks;

import com.userinterfaces.Cats;
import static com.drivers.DriverChrome.closeDriver;
import static com.utils.ExtractTransferPostImgId.extractTPostImgId;
import static com.utils.SelectSaveIdImg.assignRandomUpIdImg;

public class PostUploadCatImg {

    static String statusCod;

    static String response;

    public static String getUploadCatImgStatusCod() {
        return statusCod;
    }

    public static void setUploadCatImgStatusCod(String statusCod) {
        PostUploadCatImg.statusCod = statusCod;
    }

    public static String getResponseUploadImg() {
        return response;
    }

    public static void setResponseUploadImg(String response) {
        PostUploadCatImg.response = response;
    }

    public static void dataFormWebInteractions(String pathFile, String subid, String urlreq, String apikey) {
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
            Thread.sleep(98000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Obtener respuesta
        response = objIntFields.RESPONSE.getAttribute("value");

        //Imprimir respuesta de operación
        System.out.println("Response POST: "+ response);

        System.out.println("\n");

        //Obtener status code
        statusCod = objIntFields.STATUS_CODE.getAttribute("value");

        //Cerrar navegador
        closeDriver();

        //Si la carga de la imagen es satisfactoria, para cuando se ejecuta el POST con imagen correcta de gato
        if ("201".equals(statusCod)) {

            //Se llama a un método randomIdImgMethod de la clase SelectRandomIdImg para enviarle la lista con el id del response de la única imágen cargada

            //Se invoca método 'extractTPostImgId' al que se le pasa la respuesta obtenida en String, se encapsula en objeto Json, se extrae el id de la imagen cargada
            //Se crea lista con mapa con el id de la imagen, luego dicha lista se comparte con el otra clase para tenerla disponible en el escenario de consulta con GET de la imagen cargada
            assignRandomUpIdImg(extractTPostImgId(response), 2);
        }
    }

}

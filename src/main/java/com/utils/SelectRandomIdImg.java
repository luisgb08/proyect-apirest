package com.utils;

import java.util.List;
import java.util.Map;

import static com.factory.CreateCatImageDataFactory.IDRPOSITION;

public class SelectRandomIdImg {

    static String randomIdImg;

    public String getRandomIdImg() {
        return randomIdImg;
    }

    public void setRandomIdImg(String randomIdImg) {
        SelectRandomIdImg.randomIdImg = randomIdImg;
    }


    //Aquí se hace una selección aleatoria de un id de imagen en la lista de response recibida por la ejecución del get del primer escenario
    public static void randomIdImgMethod(List<Map<String, Object>> idList, int indImgUpload) {

        //Acá se resta uno en caso de que el limit o cantidad de imagenes a consultar sea mayor a cero
        //Ya que las listas inician en cero
        int randomNum = 0;
        if (Integer.parseInt(IDRPOSITION) > 0) {
            randomNum = Integer.parseInt(IDRPOSITION) - 1;
        }

        if (indImgUpload ==1) {
            randomIdImg = idList.get(randomNum).get("id").toString();
            System.out.println("Random Id Selec: " + randomIdImg);
            System.out.println(IDRPOSITION);
        }
        else {
                randomIdImg = idList.get(0).get("id").toString();
                System.out.println("Id Img cargada: " + randomIdImg);
             }
        }
}

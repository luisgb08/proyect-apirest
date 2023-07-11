package com.utils;

import java.util.List;
import java.util.Map;
import static com.factory.CreateCatImageDataFactory.IDRPOSITION;

public class SelectSaveIdImg {

    static String idImg;

    public static String getIdImg() {
        return idImg;
    }

    public static void setIdImg(String idImg) {
        SelectSaveIdImg.idImg = idImg;
    }


    //Aquí se hace una selección aleatoria de un id de imagen en la lista de response recibida por la ejecución del get del primer escenario
    public static void assignRandomUpIdImg(List<Map<String, Object>> idList, int indImgUpload) {

        //Acá se resta uno en caso de que el limit o cantidad de imagenes a consultar sea mayor a cero
        //Ya que las listas inician en cero
        int randomNum = 0;
        if (Integer.parseInt(IDRPOSITION) > 0) {
            randomNum = Integer.parseInt(IDRPOSITION) - 1;
        }

        //Se selecciona un id random de imagen de los obtenidos del escenario 1
        if (indImgUpload ==1) {
            idImg = idList.get(randomNum).get("id").toString();
            System.out.println("Random Id Selec: " + idImg);
            System.out.println(randomNum);
        }

        //Con indImgUpload=2 se recibe lista con única posición de la lista que contiene el id de la imagen cargada según respuesta del post
        //Se consulta la posición fija cero ya que solo se tiene un id de la única imagen cargada
        else {
                idImg = idList.get(0).get("id").toString();
                System.out.println("Id Img cargada: " + idImg);
             }
        }

}

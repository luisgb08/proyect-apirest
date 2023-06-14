package com.utils;

import java.util.List;
import java.util.Map;

import static com.factory.CreateEmployeeDataFactory.ID_EMPLOYEE;

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
        if (indImgUpload ==1) {
            randomIdImg = idList.get(Integer.parseInt(ID_EMPLOYEE)).get("id").toString();
            System.out.println("Random Id Selec: " + randomIdImg);
            System.out.println(ID_EMPLOYEE);
        }
        else {
                randomIdImg = idList.get(0).get("id").toString();
                System.out.println("Id Img cargada: " + randomIdImg);
             }
        }
}

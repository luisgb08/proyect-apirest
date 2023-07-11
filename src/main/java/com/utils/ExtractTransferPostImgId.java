package com.utils;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractTransferPostImgId {

    public static List<Map<String, Object>> extractTPostImgId(String responsePOST) {

        //Se encapsula la respuesta en un objeto Json para extraer el id de la imagen cargada con el método POST
        JSONObject jsonObject = new JSONObject(responsePOST);
        String id = jsonObject.getString("id");
        System.out.println("id: " + id);

        //Se crea mapa para insertar id en la lista
        Map<String, Object> mapIdImg = new HashMap<String, Object>();

        //Se agrega el id obtenido en el mapa
        mapIdImg.put("id", id);

        //Se crea lista con mapa de combinación String, Object para almacenar el id de la imagen cargada con el método POST
        List<Map<String, Object>> listResponseBodyUpImg = new ArrayList<>();

        //Se agrega el mapa con el id en la lista
        listResponseBodyUpImg.add(mapIdImg);

        return listResponseBodyUpImg;
    }

}

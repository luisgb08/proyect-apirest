package com.utils;

import java.util.List;
import java.util.Map;

public class TransferResponseListS1 {

    static List<Map<String, Object>> responseList;

    public static List<Map<String, Object>> getResponseList() {
        return responseList;
    }

    public static void setResponseList(List<Map<String, Object>> responseList) {
        TransferResponseListS1.responseList = responseList;
    }

    //Se recibe la lista de response del escenario 1
    public static void assignIdImgList(List<Map<String, Object>> responseListS1) {
        //Se asigna la lista recibida al atributo responseList para tenerla disponible para su consulta
        responseList = responseListS1;
    }

}

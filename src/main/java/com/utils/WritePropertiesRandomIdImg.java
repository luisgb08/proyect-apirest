package com.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.factory.CreateEmployeeDataFactory.ID_EMPLOYEE;

//Se crea archivo con los id's de las 10 imágenes random consultadas con el servicio
public class WritePropertiesRandomIdImg {

    public static void writeRandomIdInFile(List<Map<String, Object>> idList) throws Exception {

        int cont = -1;

        Properties p = new Properties();
        OutputStream os = new FileOutputStream("src/test/resources/dataRandomImg2.properties");

        for(Map<String,Object> images : idList)
        {
            cont = cont + 1;
            p.setProperty("id"+cont, images.get("id").toString());

        }
        p.store(os, null);
        os.close();

    }
}

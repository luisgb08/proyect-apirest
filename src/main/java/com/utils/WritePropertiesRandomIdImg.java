package com.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class WritePropertiesRandomIdImg {
    public static void writeRandomIdInFile(String idImg) throws Exception {
        Properties p = new Properties();

            OutputStream os = new FileOutputStream("src/test/resources/dataRandomImg.properties");
            p.setProperty("id", idImg);
            p.store(os, null);

    }
}

package com.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ReadParamProperties {

    public static String findParam (String idParam) {
        ResourceBundle rb = ResourceBundle.getBundle("param", Locale.getDefault());
        return rb.getString(idParam);
    }
}

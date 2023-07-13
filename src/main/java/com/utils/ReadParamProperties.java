package com.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ReadParamProperties {

    public static String findParam (String idParam) {
        ResourceBundle rb = ResourceBundle.getBundle("others/param", Locale.getDefault());
        return rb.getString(idParam);
    }

    public static String findIdImgFile (String numberID) {
        ResourceBundle rb = ResourceBundle.getBundle(findParam("NAME_FID_IMG"), Locale.getDefault());
        return rb.getString(numberID);
    }

}

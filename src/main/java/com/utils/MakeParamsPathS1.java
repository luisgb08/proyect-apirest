package com.utils;

import static com.utils.ReadParamProperties.findParam;

public class MakeParamsPathS1 {

    static String size = findParam("SIZE");
    static String mime_type = findParam("MIME_TYPE");
    static String format = findParam("FORMAT");
    static String limit = findParam("LIMIT");

    static String paramPath = "";

    public static String paramPathS1 () {

        int ind = 0;

        if (!("-").equals(size)) {
            ind = ind + 1;
            paramPath = "size=" + size;
        }

        if (!("-").equals(mime_type)) {
            if (ind > 0) {
                paramPath = paramPath + "&";
            }
            ind = ind + 1;
            paramPath = paramPath + "mime_types=" + mime_type;
        }

        if (!("-").equals(format)) {
            if (ind > 0) {
                paramPath = paramPath + "&";
            }
            ind = ind + 1;
            paramPath = paramPath + "format=" + format;
        }

        if (!("0").equals(limit)) {
            if (ind > 0) {
                paramPath = paramPath + "&";
            }
            ind = ind + 1;
            paramPath = paramPath + "limit=" + limit;
        }

        if (ind != 0) {
            paramPath = "?"+paramPath;
        }
        else {
            paramPath = "";
        }

        return paramPath;
    }

}

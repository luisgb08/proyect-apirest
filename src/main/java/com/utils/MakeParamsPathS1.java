package com.utils;

import static com.utils.ReadParamProperties.findParam;

public class MakeParamsPathS1 {

    static String size = findParam("SIZE");
    static String mime_type = findParam("MIME_TYPE");
    static String format = findParam("FORMAT");
    static String limit = findParam("LIMIT");
    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        MakeParamsPathS1.limit = limit;
    }
    static String paramPath = "";

    public static String paramPathS1 () {

        int ind = 0;

        if (!size.equals("-")) {
            ind = ind + 1;
            paramPath = "size=" + size;
        }

        if (!mime_type.equals("-")) {
            if (ind > 0) {
                paramPath = paramPath + "&";
            }
            ind = ind + 1;
            paramPath = paramPath + "mime_types=" + mime_type;
        }

        if (!format.equals("-")) {
            if (ind > 0) {
                paramPath = paramPath + "&";
            }
            ind = ind + 1;
            paramPath = paramPath + "format=" + format;
        }

        if (!limit.equals("0")) {
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

        System.out.println("params: "+ paramPath);
        return paramPath;

    }
}

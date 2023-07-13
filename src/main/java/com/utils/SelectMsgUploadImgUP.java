package com.utils;

import static com.exceptions.ErrorAssertion.ERROR_UPLOADING_IMG;
import static com.exceptions.ErrorAssertion.NOCATIMG_RESPONSE;

public class SelectMsgUploadImgUP {

    public static String MsgUploadImgUP (int typeMsg) {

        if (1==typeMsg)
        {
            return NOCATIMG_RESPONSE;
        }
        else
        {
            return ERROR_UPLOADING_IMG;
        }
    }
}

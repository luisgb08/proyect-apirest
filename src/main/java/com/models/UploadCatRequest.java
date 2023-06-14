package com.models;

import java.io.File;

public class UploadCatRequest {

    private File file;
    private String sub_id;

    public File getFile() {
        return file;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

}

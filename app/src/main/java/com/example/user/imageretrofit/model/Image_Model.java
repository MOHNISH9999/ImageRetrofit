package com.example.user.imageretrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image_Model {

    @SerializedName("Filename")
    @Expose
    private String filename;
    @SerializedName("Value")
    @Expose
    private String value;
    @SerializedName("Mimetype")
    @Expose
    private String mimetype;
    @SerializedName("Sydate")
    @Expose
    private String sydate;
    @SerializedName("Sytime")
    @Expose
    private String sytime;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getSydate() {
        return sydate;
    }

    public void setSydate(String sydate) {
        this.sydate = sydate;
    }

    public String getSytime() {
        return sytime;
    }

    public void setSytime(String sytime) {
        this.sytime = sytime;
    }

}
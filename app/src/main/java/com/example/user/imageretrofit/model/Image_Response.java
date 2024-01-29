package com.example.user.imageretrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Image_Response {

    @SerializedName("d")
    @Expose
    private Image_Response.D d;

    public Image_Response.D getD() {
        return d;
    }

    public void setD(Image_Response.D d) {
        this.d = d;
    }


    public class D {

        @SerializedName("results")
        @Expose
        private List<Image_Model> results;

        public List<Image_Model> getResults() {
            return results;
        }

        public void setResults(List<Image_Model> results) {
            this.results = results;
        }

    }}
package com.example.user.imageretrofit.retrofit;

import com.example.user.imageretrofit.model.Image_Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public
interface ApiService {
    @GET("ZPROJECT_SRV/sampleSet")
    Call<Image_Response> getMaterialTagPair(
            @Header("Accept") String acceptHeader, // Add the Accept header
            @Header("Content-Type") String contentTypeHeader
    );

}

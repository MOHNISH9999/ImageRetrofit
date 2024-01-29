package com.example.user.imageretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.imageretrofit.model.Image_Model;
import com.example.user.imageretrofit.model.Image_Response;
import com.example.user.imageretrofit.retrofit.ApiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.user.imageretrofit.retrofit.BasicAuthInterceptor;

public
class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ApiService apiService;
    String username = "ashwin", password = "Crave@2022",
            baseUrl = "https://aincfapim.test.apimanagement.eu10.hana.ondemand.com:443/";
    ImageAdaptor adapter;
    ProgressBar prog;
    Button fetch;

    @Override
    protected
    void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        recyclerView=findViewById ( R.id.recyclerView );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );
        prog=findViewById ( R.id.prog );
        prog.setVisibility ( View.INVISIBLE );
        fetch=findViewById ( R.id.fetch );
        OkHttpClient client = new OkHttpClient.Builder ( ).addInterceptor ( new BasicAuthInterceptor ( username ,
                password ) ).build ( );
        Retrofit retrofit = new Retrofit.Builder ( ).baseUrl ( baseUrl ).client ( client ).addConverterFactory ( GsonConverterFactory.create ( ) ).build ( );
        apiService = retrofit.create ( ApiService.class );


        fetch.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public
            void onClick ( View v ) {
                prog.setVisibility ( View.VISIBLE );
                fetchImage ();
            }
        } );

    }
    void fetchImage(){
        Call<Image_Response> call = apiService.getMaterialTagPair (
                "application/json" ,
                "application/json"
                 );
        call.enqueue ( new Callback<Image_Response> ( ) {
            @Override
            public
            void onResponse ( Call<Image_Response> call , Response<Image_Response> response ) {

                Image_Response materialTagPairResponse = response.body ( );
                List<Image_Model> arr = materialTagPairResponse.getD ( ).getResults ( );
                ArrayList<Image_Model> result = new ArrayList<> ( arr );
                adapter = new ImageAdaptor ( result );
                recyclerView.setAdapter ( adapter );
                prog.setVisibility ( View.INVISIBLE );
            }

            @Override
            public
            void onFailure ( Call<Image_Response> call , Throwable t ) {
                prog.setVisibility ( View.INVISIBLE );

            }
        } );
    }

    class ImageAdaptor extends RecyclerView.Adapter<ImageAdaptor.ViewHolder> {
        private final List<Image_Model> data;
        ImageAdaptor ( List<Image_Model> data ) {
            this.data = data;
        }

        @NonNull
        @Override
        public
        ImageAdaptor.ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
            View view = LayoutInflater.from ( parent.getContext ( ) ).inflate ( R.layout.item_recycler ,
                    parent ,
                    false );
            return new ImageAdaptor.ViewHolder ( view );
        }

        @Override
        public
        void onBindViewHolder ( @NonNull ImageAdaptor.ViewHolder holder , int position ) {
            Image_Model woTask = data.get ( position );
            holder.bindData ( woTask );
        }

        @Override
        public
        int getItemCount () {
            return data.size ( );
//            return Math.min(data.size(), 5);
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //            private ImageView icon;
//            private final TextView assetID;
//            private final TextView maktx;
//            private final TextView currentDate;
//            private final TextView lastDate;

            ImageView imageView;

            ViewHolder ( View itemView ) {
                super ( itemView );
//                assetID = itemView.findViewById ( R.id.assetID );
//                maktx = itemView.findViewById ( R.id.maktx );
//                currentDate = itemView.findViewById ( R.id.dateTimeCurrent );
//                lastDate = itemView.findViewById ( R.id.lastDate );

                imageView=itemView.findViewById( R.id.imageView);
            }

            void bindData ( Image_Model woTask ) {

                // Decode the base64 string to a byte array
                byte[] decodedString = Base64.decode(woTask.getValue (), Base64.DEFAULT);

                // Convert the byte array to a Bitmap
                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                imageView.setImageBitmap(decodedBitmap);

                itemView.setOnClickListener ( new View.OnClickListener ( ) {
                    @Override
                    public
                    void onClick ( View view ) {

                    }
                } );
            }
        }
    }
}
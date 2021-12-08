package com.example.tarea2_api_retrofil_y_google_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {
    Button button;
    private TextView lblMensajeRetrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //UI
        initUI();
        //me envía a la segunda ventana para mostrar la respuesta! VOLLEY
        button.setOnClickListener((view -> {
            startActivity(new Intent(MainActivity.this, actVolley.class));
        }));

        //RETROFIT GOOGLE
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gorest.co.in/public/v1/")
                .addConverterFactory((GsonConverterFactory.create()))
                .build();
        Api_JSON jsonPlaceHolderApi = retrofit.create(Api_JSON.class);
        Call<List<EnviarNEXT>> call = jsonPlaceHolderApi.getEnviarNEXT();
        call.enqueue(new Callback<List<EnviarNEXT>>() {
            @Override
            public void onResponse(Call<List<EnviarNEXT>> call, Response<List<EnviarNEXT>> response) {
                if(!response.isSuccessful())
                {
                    lblMensajeRetrofit.setText("Code: " + response.code());
                    return;
                }
                List<EnviarNEXT> EnviarNEXTs = response.body();
                for(EnviarNEXT EnviarNEXT :EnviarNEXTs )
                {
                    String content = "";
                    content += "id:" +EnviarNEXT.getId() + "\n";
                    content += "name" +EnviarNEXT.getName() + "\n";
                    content += "email" +EnviarNEXT.getEmail() + "\n";
                    content += "gender" +EnviarNEXT.getGender() + "\n";
                    content += "status" +EnviarNEXT.getStatus() + "\n";
                    lblMensajeRetrofit.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<EnviarNEXT>> call, Throwable t) {

            }
        });
    }


    private void initUI(){
        button=findViewById(R.id.btnVolley);
    }
}
package com.example.schooleproject1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText email,pass;
    Button loginbt;

    String getmailstring,getmainpassstring;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

      /*  email=findViewById(R.id.phonenum);
        pass=findViewById(R.id.password);*/
        loginbt=findViewById(R.id.login);



        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                JSONObject jsonObject=new JSONObject();
                try {

                    jsonObject.put("email","9764939565");
                    jsonObject.put("password","9764939565");



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Retrofit retrofit=new Retrofit.Builder().baseUrl("http://manaidea-001-site45.dtempurl.com/2019n20/index.php/api/").addConverterFactory(GsonConverterFactory.create()).build();

                NetworkRequestInterface networkRequestInterface=retrofit.create(NetworkRequestInterface.class);


                JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

                Call<LoginPojo> loginPojoCall=networkRequestInterface.LOGIN_POJO_CALL(object);


                loginPojoCall.enqueue(new Callback<LoginPojo>() {
                    @Override
                    public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {

                        if(response.body().getStatus().toString().equalsIgnoreCase("true")) {
                           // Toast.makeText(LoginActivity.this, "" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                          //  Toast.makeText(LoginActivity.this, "parent id"+response.body().getParent().getParentId(), Toast.LENGTH_SHORT).show();
                            String userid=response.body().getParent().getParentId();
                          Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);


                            SharedPreferences sharedPreferences=getSharedPreferences("parentinfo",MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("email",response.body().getParent().getEmail());
                            editor.putString("user",response.body().getParent().getUser());
                            editor.putString("login_type",response.body().getParent().getLoginType());
                            editor.putString("parent_id",response.body().getParent().getParentId());
                            editor.putString("name",response.body().getParent().getName());
                            editor.putString("phone",response.body().getParent().getPhone());

                            editor.commit();
                            finish();
                            /*Toast.makeText(LoginActivity.this, "userid"+userid, Toast.LENGTH_SHORT).show();
                            Toast.makeText(LoginActivity.this, "" + response.body().getParent().getName(), Toast.LENGTH_SHORT).show();*/
                        }
                        else if(response.body().getStatus().toString().trim().equalsIgnoreCase("false")) {
                            Toast.makeText(LoginActivity.this, "invalid user", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<LoginPojo> call, Throwable t) {

                        Toast.makeText(LoginActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });





    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);    }
}

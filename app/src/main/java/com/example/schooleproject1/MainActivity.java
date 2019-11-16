package com.example.schooleproject1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
   // private List<Spinnermodel> spinerlist = new List<Spinnermodel>();
    private Spinner spinner;
    List<String> profileUrl;

    LinearLayout studentprofile,homework,timetable,marks;
    private List<Student> mStudents;
    ArrayAdapter<String> mAdapter;
    List<String> nameIDS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Maharshi Vidhyamandhir");
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        studentprofile=findViewById(R.id.studentprofile);
        homework=findViewById(R.id.homework);
        timetable=findViewById(R.id.timetable);
        marks=findViewById(R.id.marks);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        mStudents = new ArrayList<>();

        profileUrl=new ArrayList<>();

        profileUrl.add("nill");

        nameIDS = new ArrayList<>();
        nameIDS.add("Select Student");
        mAdapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_item,nameIDS);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mAdapter);

        SharedPreferences sharedPreferences=getSharedPreferences("parentinfo",MODE_PRIVATE);
        final String userid=sharedPreferences.getString("parent_id",null);

     //   Toast.makeText(this, ""+userid, Toast.LENGTH_SHORT).show();


        /*
        Intent intent =getIntent();
        String userid =intent.getStringExtra("userid");
        Toast.makeText(this, ""+userid, Toast.LENGTH_SHORT).show();



*/

        if (isNetwork(getApplicationContext())){

            Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show();
        }

        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("","");
            jsonObject.put("","");



        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Userid",userid ).build();
                return chain.proceed(request);
            }
        });


        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://manaidea-001-site45.dtempurl.com/2019n20/index.php/api/ ").addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();


        NetworkRequestInterface networkRequestInterface=retrofit.create(NetworkRequestInterface.class);

        Call<StudentsPojo1> studentsPojo1Call=networkRequestInterface.STUDENTS_POJO_1_CALL();

        studentsPojo1Call.enqueue(new Callback<StudentsPojo1>() {
            @Override
            public void onResponse(Call<StudentsPojo1> call, Response<StudentsPojo1> response) {
                //Toast.makeText(MainActivity.this, "students check" + response.body().getStudents(), Toast.LENGTH_SHORT).show();
                StudentsPojo1 bodyResponse = response.body();
                mStudents.clear();
                mStudents.addAll(bodyResponse.getStudents());

                for(Student student:mStudents){
                    nameIDS.add(student.getName()+"-"+student.getStudentId());
                }
                mAdapter.notifyDataSetChanged();



                for(Student student:mStudents)
                {
                    profileUrl.add(student.getPhoto());
                }



            }

            @Override
            public void onFailure(Call<StudentsPojo1> call, Throwable t) {

            }
        });

    }

    private boolean isNetwork(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, final int position, long l) {


        if(position>0) {
         //   Toast.makeText(MainActivity.this, "ID:" + mStudents.get(position - 1).getStudentId(), Toast.LENGTH_SHORT).show();

             final String  iid= String.valueOf(mStudents.get(position-1));


             final String url=mStudents.get(position-1).getPhoto();

            studentprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(MainActivity.this, "student ID:" + mStudents.get(position - 1).getStudentId(), Toast.LENGTH_SHORT).show();

                   // Toast.makeText(MainActivity.this, ""+url, Toast.LENGTH_SHORT).show();
    Intent intent=new Intent(MainActivity.this,StudentProfileActivity.class);

                     intent.putExtra("photourl",url);
                    intent.putExtra("studentid",mStudents.get(position-1).getStudentId());
                    startActivity(intent);

                    finish();
                 //   Toast.makeText(MainActivity.this, "student id" +mStudents.get(position-1).getStudentId(), Toast.LENGTH_SHORT).show();

                }
            });

            homework.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MainActivity.this,HomeworkActivity.class);

                    intent.putExtra("studentid",mStudents.get(position-1).getStudentId());
                    startActivity(intent);
                    finish();
                }
            });

            timetable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MainActivity.this,TimeTableActivity.class);
                    startActivity(intent);
                    finish();
                }
            });


            marks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MainActivity.this,MarksActivity.class);
                    intent.putExtra("studentid",mStudents.get(position-1).getStudentId());

                    startActivity(intent);
                    finish();
                }
            });



        }

        if (position==0)
        {
            studentprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "please select child", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

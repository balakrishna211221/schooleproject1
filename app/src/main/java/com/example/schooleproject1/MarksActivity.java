package com.example.schooleproject1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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

public class MarksActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;

    private List<ExamResult> mexamResults;


    ArrayAdapter<String> mAdapter;
    List<String> examsname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Marks Sheet");



        spinner = (Spinner) findViewById(R.id.spinnermarks);
        spinner.setOnItemSelectedListener(MarksActivity.this);
        mexamResults = new ArrayList<>();

        examsname = new ArrayList<>();
        examsname.add("Please Select Exams");

        mAdapter = new ArrayAdapter<>(MarksActivity.this, android.R.layout.simple_spinner_item, examsname);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mAdapter);

        Intent intent = getIntent();

        final String sid = intent.getStringExtra("studentid");
        final Bundle bundle = new Bundle();
        bundle.putString("message", sid);


        SharedPreferences sharedPreferences = getSharedPreferences("parentinfo", MODE_PRIVATE);
        final String userid = sharedPreferences.getString("parent_id", null);

        //Toast.makeText(this, "marks Activity getting userid:"+userid+"student id:"+sid, Toast.LENGTH_SHORT).show();




        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Userid", userid).build();
                return chain.proceed(request);
            }
        });
        //    Toast.makeText(this, "Marks"+sid, Toast.LENGTH_SHORT).show();
        JSONObject jsonObject = new JSONObject();
        try {


        } catch (Exception e) {

            e.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://manaidea-001-site45.dtempurl.com/2019n20/index.php/api/academic_result/").addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();


        NetworkRequestInterface networkRequestInterface = retrofit.create(NetworkRequestInterface.class);

        Call<MarksActivityPojo1> marksActivityPojo1Call = networkRequestInterface.MARKS_ACTIVITY_POJO_1_CALL(sid);

        marksActivityPojo1Call.enqueue(new Callback<MarksActivityPojo1>() {
            @Override
            public void onResponse(Call<MarksActivityPojo1> call, Response<MarksActivityPojo1> response) {
                Toast.makeText(MarksActivity.this, "" + response.body().getStatus(), Toast.LENGTH_SHORT).show();

                MarksActivityPojo1 bodyresponce = response.body();
                mexamResults.clear();
                mexamResults.addAll(bodyresponce.getExamResult());

                for (ExamResult examResult : mexamResults) {
                    examsname.add(examResult.getExamName());
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MarksActivityPojo1> call, Throwable t) {


                Toast.makeText(MarksActivity.this, ""+t, Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, final int position, long l) {

        if(position>0) {
            Toast.makeText(MarksActivity.this, "sellected exam" + mexamResults.get(position - 1).getExamName(), Toast.LENGTH_SHORT).show();

            Intent intent = getIntent();

            final String sid = intent.getStringExtra("studentid");

             final String iid = String.valueOf(
                    mexamResults.get(position - 1).getSno());

            final Bundle bundle=new Bundle();
              bundle.putString("selectedexam", iid);
              bundle.putString("markssid",sid);


            Toast.makeText(this, "iid"+bundle, Toast.LENGTH_SHORT).show();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();


            MarksDetailsFamre marksDetailsFamre=new  MarksDetailsFamre();
             marksDetailsFamre.setArguments(bundle);
            fragmentTransaction.addToBackStack("FrameTransaction");
            fragmentTransaction.replace(R.id.examselectframe,marksDetailsFamre );
            fragmentTransaction.commit();


          //  Toast.makeText(this, "position"+iid, Toast.LENGTH_SHORT).show();

            SharedPreferences sharedPreferences = getSharedPreferences("parentinfo", MODE_PRIVATE);
            final String userid = sharedPreferences.getString("parent_id", null);


            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().addHeader("Userid", "2").build();
                    return chain.proceed(request);
                }
            });
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://manaidea-001-site45.dtempurl.com/2019n20/index.php/api/academic_result/").addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();


            NetworkRequestInterface networkRequestInterface = retrofit.create(NetworkRequestInterface.class);

            Call<MarksActivityPojo1> marksActivityPojo1Call = networkRequestInterface.MARKS_ACTIVITY_POJO_1_CALL("10");


            marksActivityPojo1Call.enqueue(new Callback<MarksActivityPojo1>() {
                @SuppressLint("ResourceType")
                @Override
                public void onResponse(Call<MarksActivityPojo1> call, Response<MarksActivityPojo1> response) {
                    Toast.makeText(MarksActivity.this, "" + "2nd"+response.body().getExamResult().get(Integer.parseInt(iid)-1).getExamName(), Toast.LENGTH_SHORT).show();

                    TextView Examname,gpa,total,percentage;
                    Examname=findViewById(R.id.examnameheading);

                    gpa=findViewById(R.id.gpa);
                    percentage=findViewById(R.id.percentage);
                    total=findViewById(R.id.total);



                    Examname.setText(response.body().getExamResult().get(Integer.parseInt(iid)-1).getExamName());
                    Toast.makeText(MarksActivity.this, "gpa"+response.body().getExamResult().get(Integer.parseInt(iid)-1).getTotals().getGpa(), Toast.LENGTH_SHORT).show();


                           gpa.setText("GPA            :"+response.body().getExamResult().get(Integer.parseInt(iid)-1).getTotals().getGpa());

                    percentage.setText("Percentage:"+response.body().getExamResult().get(Integer.parseInt(iid)-1).getTotals().getPercentage()+"%");

                         total.setText("Total           :"+response.body().getExamResult().get(Integer.parseInt(iid)-1).getTotals().getTotalMarks());


                //    final Bundle bundle=new Bundle();
                  //  bundle.putString("selectedexam", iid);











                }

                @Override
                public void onFailure(Call<MarksActivityPojo1> call, Throwable t) {

                    Toast.makeText(MarksActivity.this, "marks exceptions"+t, Toast.LENGTH_SHORT).show();
                }
            });


        }
        }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(MarksActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}

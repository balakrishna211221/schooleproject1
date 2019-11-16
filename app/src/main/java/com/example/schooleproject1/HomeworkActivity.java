package com.example.schooleproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeworkActivity extends AppCompatActivity {
    TableLayout tableLayout;
    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);


       // Toast.makeText(this, "Homework userid"+userid, Toast.LENGTH_SHORT).show();
        Intent intent=getIntent();
        String sid=intent.getStringExtra("studentid");



        JSONObject jsonObject=new JSONObject();
        try{


        }
        catch (Exception e){

            e.printStackTrace();
        }
        SharedPreferences sharedPreferences=getSharedPreferences("parentinfo",MODE_PRIVATE);
        final String userid=sharedPreferences.getString("parent_id",null);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Userid", userid).build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://manaidea-001-site45.dtempurl.com/2019n20/index.php/api/").addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();


        NetworkRequestInterface networkRequestInterface=retrofit.create(NetworkRequestInterface.class);


        Call<HomeWorkPojo1> homeWorkPojo1Call=networkRequestInterface.HOME_WORK_POJO_1_CALL();

        homeWorkPojo1Call.enqueue(new Callback<HomeWorkPojo1>() {
            @Override
            public void onResponse(Call<HomeWorkPojo1> call,  Response<HomeWorkPojo1> response) {



                tableLayout = findViewById(R.id.main_table);
                TableRow tr_head = new TableRow(HomeworkActivity.this);
                tr_head.setId(19);


                TableLayout.LayoutParams tableRowParams=
                        new TableLayout.LayoutParams
                                (TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);


                // tr_head.setBackgroundColor(Color.GRAY);
                tr_head.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                tr_head.setBackgroundColor(Color.WHITE);







                TextView sno = new TextView(HomeworkActivity.this);
                sno.setId(21);// define id that must be unique
                sno.setText("S.no"); // set the text for the header
                sno.setTextSize(20);
                sno.setGravity(1);




                sno.setTextColor(Color.BLACK); // set the color
                sno.setPadding(10, 5, 10, 5); // set the padding (if required)
                tr_head.addView(sno);

                TextView id = new TextView(HomeworkActivity.this);
                id.setId(20);// define id that must be unique
                id.setText("Class"); // set the text for the header
                id.setTextSize(20);
                id.setGravity(1);

                id.setTextColor(Color.BLACK); // set the color
                id.setPadding(10, 5, 10, 5); // set the padding (if required)
                tr_head.addView(id);// add the column to the table row here



                TextView date = new TextView(HomeworkActivity.this);
                date.setId(22);// define id that must be unique
                date.setText("Divission"); // set the text for the header
                date.setTextSize(20);
                date.setGravity(1);

                date.setTextColor(Color.BLACK); // set the color
                date.setPadding(10, 5, 10, 5); // set the padding (if required)
                tr_head.addView(date);

             /*   TextView amount = new TextView(HomeworkActivity.this);
                amount.setId(23);// define id that must be unique
                amount.setText("Students"); // set the text for the header
                amount.setGravity(1);
                amount.setTextSize(25);
                amount.setTextColor(Color.BLACK); // set the color
                amount.setPadding(10, 5, 10, 5); // set the padding (if required)
                tr_head.addView(amount);
*/
                TextView category = new TextView(HomeworkActivity.this);
                category.setId(24);// define id that must be unique
                category.setText("Date"); // set the text for the header
                category.setGravity(1);
                category.setTextSize(20);
                category.setTextColor(Color.BLACK); // set the color
                category.setPadding(10, 5, 10, 5); // set the padding (if required)
                tr_head.addView(category);



                TextView employee = new TextView(HomeworkActivity.this);
                employee.setId(25);// define id that must be unique
                employee.setGravity(1);
                employee.setTextSize(20);
                employee.setText("Work"); // set the text for the header
                employee.setTextColor(Color.BLACK); // set the color
                employee.setPadding(10, 5, 10, 5); // set the padding (if required)
                tr_head.addView(employee);




                tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.FILL_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));


//Curent Date Home Display
                Calendar c = Calendar.getInstance();
                System.out.println("Current time => "+c.getTime());
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());
                // formattedDate have current date/time


               /* try {


                    for(int j=0;j<=response.body().getHomeWorks().size();j++) {
                        if (response.body().getHomeWorks().get(j).getDate().toString().trim() == df.toString()) {
                          //  Toast.makeText(HomeworkActivity.this, "DateContition", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(HomeworkActivity.this, formattedDate, Toast.LENGTH_SHORT).show();

                            int k = Integer.parseInt(response.body().getHomeWorks().get(j).toString());


                            if(j == k) {
                                Toast.makeText(HomeworkActivity.this, "" + j, Toast.LENGTH_SHORT).show();

                            }


                            for (int i = 0; i <= response.body().getHomeWorks().size(); i++) {
                                TableRow tableRow=new TableRow(HomeworkActivity.this);
                                tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));





                                TextView snor=new TextView(HomeworkActivity.this);
                                snor.setText(response.body().getHomeWorks().get(i).getSno().toString().trim());
                                snor.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
                                ///   snor.setBackgroundColor(R.color.white);
                                snor.setPadding(10,5,10,5);
                                snor.setGravity(1);
                                snor.setBackgroundColor(Color.WHITE);
                                tableRow.addView(snor);

                                // String j=response.body().getExpenses().get(i).getId().toString().trim();
                                TextView textView=new TextView(HomeworkActivity.this);
                                textView.setText(response.body().getHomeWorks().get(i).getClass_().toString().trim());
                                // textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                                textView.setPadding(10,5,10,5);

                                tableRow.addView(textView);

                                TextView dater=new TextView(HomeworkActivity.this);
                                dater.setText(response.body().getHomeWorks().get(i).getDivision().toString().trim());
                                dater.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
                                dater.setBackgroundColor(Color.WHITE);
                                dater.setPadding(10,5,10,5);

                                tableRow.addView(dater);







                                TextView noter=new TextView(HomeworkActivity.this);
                                noter.setText(response.body().getHomeWorks().get(i).getDate().toString().trim());
                                noter.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));

                                noter.setBackgroundColor(Color.WHITE);
                                noter.setPadding(10,5,10,5);


                                noter.setGravity(1);

                                tableRow.addView(noter);



                                TextView usernamer=new TextView(HomeworkActivity.this);
                                usernamer.setText(response.body().getHomeWorks().get(i).getWork().toString().trim());
                                usernamer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
                                //usernamer.setBackgroundColor(R.color.white);

                                usernamer.setPadding(10,5,10,5);


                                usernamer.setGravity(0);

                                tableRow.addView(usernamer);




                                tableLayout.addView(tableRow);


                                Toast.makeText(HomeworkActivity.this, ""+response.body().getHomeWorks().get(j).getDate()+""+df, Toast.LENGTH_SHORT).show();

                            }


                        } else {
                          //  Toast.makeText(HomeworkActivity.this, "not current date", Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch (Exception e){

                }
*/


                //Show show Button Dislay All HomeWork

                try {
                    for (int i = 0; i <= response.body().getHomeWorks().size(); i++) {
                        TableRow tableRow=new TableRow(HomeworkActivity.this);
                        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));





                        TextView snor=new TextView(HomeworkActivity.this);
                        snor.setText(response.body().getHomeWorks().get(i).getSno().toString().trim());
                        snor.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
                        ///   snor.setBackgroundColor(R.color.white);
                        snor.setPadding(10,5,10,5);
                        snor.setGravity(1);
                        snor.setBackgroundColor(Color.WHITE);
                        tableRow.addView(snor);

                       // String j=response.body().getExpenses().get(i).getId().toString().trim();
                        TextView textView=new TextView(HomeworkActivity.this);
                        textView.setText(response.body().getHomeWorks().get(i).getClass_().toString().trim());
                        // textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                        textView.setPadding(10,5,10,5);

                        tableRow.addView(textView);

                        TextView dater=new TextView(HomeworkActivity.this);
                        dater.setText(response.body().getHomeWorks().get(i).getDivision().toString().trim());
                        dater.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
                        dater.setBackgroundColor(Color.WHITE);
                        dater.setPadding(10,5,10,5);

                        tableRow.addView(dater);









                        TextView noter=new TextView(HomeworkActivity.this);
                        noter.setText(response.body().getHomeWorks().get(i).getDate().toString().trim());
                        noter.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));

                        noter.setBackgroundColor(Color.WHITE);
                        noter.setPadding(10,5,10,5);


                        noter.setGravity(1);

                        tableRow.addView(noter);



                        TextView usernamer=new TextView(HomeworkActivity.this);
                        usernamer.setText(response.body().getHomeWorks().get(i).getWork().toString().trim());
                        usernamer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
                      //usernamer.setBackgroundColor(R.color.white);

                        usernamer.setPadding(10,5,10,5);


                        usernamer.setGravity(0);

                        tableRow.addView(usernamer);




                        tableLayout.addView(tableRow);

                    //int k = Integer.parseInt(response.body().getHomeWorks().get(i).toString());


                       // Toast.makeText(HomeworkActivity.this, "" +k, Toast.LENGTH_SHORT).show();



                    }


                }
                catch (Exception e){

                }


            }

            @Override
            public void onFailure(Call<HomeWorkPojo1> call, Throwable t) {

                Toast.makeText(HomeworkActivity.this, ""+t, Toast.LENGTH_LONG).show();
            }
        });









    }
}

package com.example.schooleproject1;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

class MarksDetailsFamre extends Fragment {
TextView textView;
    TableLayout tableLayout;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.marksdetailsframelayout,container,false);

        final String iid=getArguments().getString("selectedexam");
        String sid=getArguments().getString("markssid");
        Toast.makeText(getContext(), ""+sid, Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("parentinfo", MODE_PRIVATE);
        final String userid = sharedPreferences.getString("parent_id", null);


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Userid", userid).build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://manaidea-001-site45.dtempurl.com/2019n20/index.php/api/academic_result/").addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();


        NetworkRequestInterface networkRequestInterface = retrofit.create(NetworkRequestInterface.class);

        Call<MarksActivityPojo1> marksActivityPojo1Call = networkRequestInterface.MARKS_ACTIVITY_POJO_1_CALL(sid);


        marksActivityPojo1Call.enqueue(new Callback<MarksActivityPojo1>() {
            @SuppressLint("ResourceType")
            @Override
            public void onResponse(Call<MarksActivityPojo1> call, Response<MarksActivityPojo1> response) {
                Toast.makeText(getContext(), "markframe:"+response.body().getStatus(), Toast.LENGTH_SHORT).show();

                LinearLayout linearLayout;
                linearLayout=view.findViewById(R.id.llmarks);

                tableLayout = view.findViewById(R.id.markstable);
                TableRow tr_head = new TableRow(getActivity());
                tr_head.setId(19);







                TableLayout.LayoutParams tableRowParams=
                        new TableLayout.LayoutParams
                                (TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);


                // tr_head.setBackgroundColor(Color.GRAY);
                tr_head.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                tr_head.setBackgroundColor(Color.LTGRAY);



               /* TextView SNo = new TextView(getActivity());
                SNo.setId(21);// define id that must be unique
                SNo.setText("S.no."); // set the text for the header
                SNo.setTextSize(20);
                SNo.setGravity(1);
                //  Subject.setBackgroundColor(Color.LTGRAY);



                SNo.setTextColor(Color.BLACK); // set the color
                SNo.setPadding(10, 5, 10, 5); // set the padding (if required)
                tr_head.addView(SNo);*/

                View vt = new View(getContext());
                vt.setLayoutParams(new TableRow.LayoutParams( 1,TableRow.LayoutParams.MATCH_PARENT));
                vt.setBackgroundColor(Color.BLACK);
                tr_head.addView(vt);




                TextView Subject = new TextView(getActivity());
                Subject.setId(21);// define id that must be unique
                Subject.setText("Subjects"); // set the text for the header
                Subject.setTextSize(20);
                Subject.setGravity(1);
              //  Subject.setBackgroundColor(Color.LTGRAY);



                Subject.setTextColor(Color.BLACK); // set the color
                Subject.setPadding(10, 5, 10, 5); // set the padding (if required)
                tr_head.addView(Subject);

                View v = new View(getContext());
                v.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
                v.setBackgroundColor(Color.BLACK);
                tr_head.addView(v);

                TextView ObtainedMark = new TextView(getActivity());
                ObtainedMark.setId(20);// define id that must be unique
                ObtainedMark.setText("Obtained Marks"); // set the text for the header
                ObtainedMark.setTextSize(20);
                ObtainedMark.setGravity(1);

                ObtainedMark.setTextColor(Color.BLACK); // set the color
                ObtainedMark.setPadding(10, 5, 10, 5); // set the padding (if required)
                tr_head.addView(ObtainedMark);// add the column to the table row here


                View v2 = new View(getContext());
                v2.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
                v2.setBackgroundColor(Color.BLACK);
                tr_head.addView(v2);


                TextView Highestmark = new TextView(getActivity());
                Highestmark.setId(22);// define id that must be unique
                Highestmark.setText("Highest Marks"); // set the text for the header
                Highestmark.setTextSize(20);
                Highestmark.setGravity(1);

                Highestmark.setTextColor(Color.BLACK); // set the color
                Highestmark.setPadding(10, 5, 10, 5); // set the padding (if required)
                tr_head.addView(Highestmark);

                View v3 = new View(getContext());
                v3.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
                v3.setBackgroundColor(Color.BLACK);
                tr_head.addView(v3);

                TextView Grade = new TextView(getActivity());
                Grade.setId(24);// define id that must be unique
                Grade.setText("Grade"); // set the text for the header
                Grade.setGravity(1);
                Grade.setTextSize(20);
                Grade.setTextColor(Color.BLACK); // set the color
                Grade.setPadding(10, 5, 10, 5); // set the padding (if required)
                tr_head.addView(Grade);



                View v4 = new View(getContext());
                v4.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
                v4.setBackgroundColor(Color.BLACK);
                tr_head.addView(v4);


                TextView Comment = new TextView(getActivity());
                Comment.setId(25);// define id that must be unique
                Comment.setGravity(1);
                Comment.setTextSize(20);
                Comment.setText("Comment"); // set the text for the header
                Comment.setTextColor(Color.BLACK); // set the color
                Comment.setPadding(10, 5, 10, 5); // set the padding (if required)
                tr_head.addView(Comment);


                View vl = new View(getContext());
                vl.setLayoutParams(new TableRow.LayoutParams( 1,TableRow.LayoutParams.MATCH_PARENT));
                vl.setBackgroundColor(Color.BLACK);
                tr_head.addView(vl);




                tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.FILL_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

              /*  try {


                for (int i=0;i<=response.body().getExamResult().get(Integer.parseInt(iid)-1).getSubjects().size();i++){
                    Toast.makeText(getContext(), ""+response.body().getExamResult().get(i).getSubjects().get(i).getSubject().toString().trim(), Toast.LENGTH_SHORT).show();

                 }
                }catch (Exception e){

                }
*/


                try {
                  for (int i=0;i<=response.body().getExamResult().get(Integer.parseInt(iid)-1).getSubjects().size();i++) {


                        TableRow tableRow=new TableRow(getActivity());
                        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));



                      View vtr1 = new View(getContext());
                      vtr1.setLayoutParams(new TableRow.LayoutParams( 1,TableRow.LayoutParams.MATCH_PARENT));
                      vtr1.setBackgroundColor(Color.BLACK);
                      tableRow.addView(vtr1);


                        TextView subject=new TextView(getActivity());
                        subject.setText(response.body().getExamResult().get(Integer.parseInt(iid)-1).getSubjects().get(i).getSubject().toString().trim());
                        subject.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
                        ///   snor.setBackgroundColor(R.color.white);
                        subject.setPadding(10,5,10,5);
                        subject.setGravity(1);
                        subject.setBackgroundColor(Color.WHITE);
                        tableRow.addView(subject);


                        // String j=response.body().getExpenses().get(i).getId().toString().trim();

                      View vtr2 = new View(getContext());
                      vtr2.setLayoutParams(new TableRow.LayoutParams( 1,TableRow.LayoutParams.MATCH_PARENT));
                      vtr2.setBackgroundColor(Color.BLACK);
                      tableRow.addView(vtr2);



                    //  String om=response.body().getExamResult().get(Integer.parseInt(iid) - 1).getSubjects().get(i).getObtainedMarks().toString();
                      TextView obtainedm = new TextView(getActivity());
                      if (response.body().getExamResult().get(Integer.parseInt(iid)-1).getSubjects().get(i).getObtainedMarks()==null){
                          Toast.makeText(getContext(), "null value", Toast.LENGTH_SHORT).show();

                          continue;

                      }
                      else {

                          obtainedm.setText(" "+response.body().getExamResult().get(Integer.parseInt(iid) - 1).getSubjects().get(i).getObtainedMarks().toString().trim());
                          // textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                          obtainedm.setPadding(10, 5, 10, 5);

                          tableRow.addView(obtainedm);
                      }
                      View vtr3 = new View(getContext());
                      vtr3.setLayoutParams(new TableRow.LayoutParams( 1,TableRow.LayoutParams.MATCH_PARENT));
                      vtr3.setBackgroundColor(Color.BLACK);
                      tableRow.addView(vtr3);


                        TextView highestm=new TextView(getActivity());
                        highestm.setText(response.body().getExamResult().get(Integer.parseInt(iid)-1).getSubjects().get(i).getHighestMark().toString().trim());
                        highestm.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
                        highestm.setBackgroundColor(Color.WHITE);
                        highestm.setPadding(10,5,10,5);

                        tableRow.addView(highestm);



                      View vtr4 = new View(getContext());
                      vtr4.setLayoutParams(new TableRow.LayoutParams( 1,TableRow.LayoutParams.MATCH_PARENT));
                      vtr4.setBackgroundColor(Color.BLACK);
                      tableRow.addView(vtr4);





                        TextView grade=new TextView(getActivity());
                        grade.setText(response.body().getExamResult().get(Integer.parseInt(iid)-1).getSubjects().get(i).getGrade().toString().trim());
                        grade.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));

                        grade.setBackgroundColor(Color.WHITE);
                        grade.setPadding(10,5,10,5);


                        grade.setGravity(1);

                        tableRow.addView(grade);



                      View vtr5 = new View(getContext());
                      vtr5.setLayoutParams(new TableRow.LayoutParams( 1,TableRow.LayoutParams.MATCH_PARENT));
                      vtr5.setBackgroundColor(Color.BLACK);
                      tableRow.addView(vtr5);





                      TextView comment=new TextView(getActivity());
                        comment.setText(response.body().getExamResult().get(Integer.parseInt(iid)-1).getSubjects().get(i).getComment().toString().trim());
                        comment.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
                        //usernamer.setBackgroundColor(R.color.white);

                        comment.setPadding(10,5,10,5);


                        comment.setGravity(0);

                        tableRow.addView(comment);

                      View vtlr1 = new View(getContext());
                      vtlr1.setLayoutParams(new TableRow.LayoutParams( 1,TableRow.LayoutParams.MATCH_PARENT));
                      vtlr1.setBackgroundColor(Color.BLACK);
                      tableRow.addView(vtlr1);



                      View vtblr1 = new View(getContext());
                      vtblr1.setLayoutParams(new TableRow.LayoutParams( 1,TableRow.LayoutParams.MATCH_PARENT));
                      vtblr1.setBackgroundColor(Color.BLACK);
                      tableRow.addView(vtblr1);



                        tableLayout.addView(tableRow);}}catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<MarksActivityPojo1> call, Throwable t) {

            }
        });



        return view;
    }

}

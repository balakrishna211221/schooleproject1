package com.example.schooleproject1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.schooleproject1.databinding.ActivityStudentProfileBinding;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  StudentProfileActivity extends AppCompatActivity {



    ActivityStudentProfileBinding activityStudentProfileBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       activityStudentProfileBinding= DataBindingUtil.setContentView(StudentProfileActivity.this,R.layout.activity_student_profile);
        Intent intent=getIntent();
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        String sid=intent.getStringExtra("studentid");
        final Bundle bundle=new Bundle();
        bundle.putString("message", sid);

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();


        Studentdetailsframe studentdetailsframe=new  Studentdetailsframe();
        studentdetailsframe.setArguments(bundle);
        fragmentTransaction.addToBackStack("FrameTransaction");
        fragmentTransaction.replace(R.id.fragment, studentdetailsframe);
        fragmentTransaction.commit();

        activityStudentProfileBinding.studentdetailsbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();


                Studentdetailsframe studentdetailsframe=new  Studentdetailsframe();
                studentdetailsframe.setArguments(bundle);
                fragmentTransaction.addToBackStack("FrameTransaction");
                fragmentTransaction.replace(R.id.fragment, studentdetailsframe);
                fragmentTransaction.commit();

            }
        });

        activityStudentProfileBinding.parentdetailsbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();


                /*Bundle bundle=new Bundle();
                bundle.putString("message",sid);*/

                Parentdetailsframe parentdetailsframe=new  Parentdetailsframe();

                parentdetailsframe.setArguments(bundle);

                fragmentTransaction.addToBackStack("FrameTransaction");
                fragmentTransaction.replace(R.id.fragment,parentdetailsframe );
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        Glide.with(this)
                .load("http://manaidea-001-site45.dtempurl.com/2019n20/uploads/user.jpg")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(activityStudentProfileBinding.profilepic);


        final String photourl=intent.getStringExtra("photourl");
        Toast.makeText(this, ""+sid, Toast.LENGTH_SHORT).show();


        JSONObject jsonObject=new JSONObject();
        try{


        }
        catch (Exception e){

            e.printStackTrace();
        }

        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://manaidea-001-site45.dtempurl.com/2019n20/index.php/api/student_profile/").addConverterFactory(GsonConverterFactory.create()).build();


        NetworkRequestInterface networkRequestInterface=retrofit.create(NetworkRequestInterface.class);


        Call<StudentProfilePojo1> studentProfilePojo1Call=networkRequestInterface.STUDENT_PROFILE_POJO_1_CALL(sid);

        studentProfilePojo1Call.enqueue(new Callback<StudentProfilePojo1>() {
            @Override
            public void onResponse(Call<StudentProfilePojo1> call, Response<StudentProfilePojo1> response) {
                Toast.makeText(StudentProfileActivity.this, "Student Profile:"+response.body().getStatus(), Toast.LENGTH_SHORT).show();

                Toast.makeText(StudentProfileActivity.this, ""+photourl, Toast.LENGTH_SHORT).show();
                activityStudentProfileBinding.studentname.setText("  "+response.body().getStudent().getName());
                activityStudentProfileBinding.studentclass.setText(response.body().getStudent().getClass_());
              //  ActivityStudentProfileBinding.
/*


                activityStudentProfileBinding.admissionnumber.setText(" : "+response.body().getStudent().getAdmno());
                activityStudentProfileBinding.accesscardnumber.setText(" : "+response.body().getStudent().getAcesscardNumber());
                activityStudentProfileBinding.admissiondate.setText(" : "+response.body().getStudent().getAdmissionDate());
                activityStudentProfileBinding.studentclass.setText(" "+response.body().getStudent().getClass_());
                activityStudentProfileBinding.gender.setText(" : "+response.body().getStudent().getGender());
                activityStudentProfileBinding.dateofbirth.setText(" : "+response.body().getStudent().getDob());
                activityStudentProfileBinding.nationality.setText(" : "+response.body().getStudent().getNationality());
                activityStudentProfileBinding.religion.setText(" : "+response.body().getStudent().getRelegion());
                activityStudentProfileBinding.mothertonue.setText(" : "+response.body().getStudent().getMotherTongue());
                activityStudentProfileBinding.bloodgroup.setText(" : "+response.body().getStudent().getBloodGroup());
                activityStudentProfileBinding.adharnumber.setText(" : "+response.body().getStudent().getAadharNo());
                activityStudentProfileBinding.passport.setText(" : "+response.body().getStudent().getPassport());
                activityStudentProfileBinding.transport.setText(" : "+response.body().getStudent().getTransport());

                activityStudentProfileBinding.hostel.setText(" : "+response.body().getStudent().getHostel());
                activityStudentProfileBinding.mark1.setText(" : "+response.body().getStudent().getMark1());
                activityStudentProfileBinding.mark2.setText(" : "+response.body().getStudent().getMark2());
                activityStudentProfileBinding.fathername.setText(" : "+response.body().getStudent().getFatherName());
                activityStudentProfileBinding.fatheradhar.setText(" : "+response.body().getStudent().getFadhar());
                activityStudentProfileBinding.fathercontact.setText(" : "+response.body().getStudent().getPhone());
                activityStudentProfileBinding.mothercontact.setText(" : "+response.body().getStudent().getMphone());
                activityStudentProfileBinding.mothername.setText(" : "+response.body().getStudent().getMotherName());
                activityStudentProfileBinding.motheradhar.setText(" : "+response.body().getStudent().getMadhar());
                activityStudentProfileBinding.motheremail.setText(" : "+response.body().getStudent().getMemail());
                activityStudentProfileBinding.fatheremail.setText(" : "+response.body().getStudent().getEmail());
                activityStudentProfileBinding.fatherincome.setText(" : "+response.body().getStudent().getFincome());
                activityStudentProfileBinding.fatherpassport.setText(" : "+response.body().getStudent().getFpassport());
                activityStudentProfileBinding.fatherreligion.setText(" : "+response.body().getStudent().getFatherReligion());
                activityStudentProfileBinding.motherincome.setText(" : "+response.body().getStudent().getMincome());
                activityStudentProfileBinding.motherpassport.setText(" : "+response.body().getStudent().getMpassport());
                activityStudentProfileBinding.motherreligion.setText(" : "+response.body().getStudent().getMotherReligion());
                activityStudentProfileBinding.fOccupation.setText(" : "+response.body().getStudent().getFatherOccupation());
                activityStudentProfileBinding.mOccupation.setText(" : "+response.body().getStudent().getMotherOccupation());
                activityStudentProfileBinding.parentqualification.setText(" : "+response.body().getStudent().getQualification());
                activityStudentProfileBinding.cAddress.setText(" : "+response.body().getStudent().getCurrentAddress());
                activityStudentProfileBinding.pAddress.setText(" : "+response.body().getStudent().getPermanentAddress());



*/

            }

            @Override
            public void onFailure(Call<StudentProfilePojo1> call, Throwable t) {

            }
        });



    }

    @Override
    public void onBackPressed() {
       Intent intent=new Intent(StudentProfileActivity.this,MainActivity.class);
       startActivity(intent);


    }
}

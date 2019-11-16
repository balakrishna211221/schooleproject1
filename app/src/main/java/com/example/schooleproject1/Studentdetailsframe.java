package com.example.schooleproject1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.schooleproject1.databinding.StudentfragmentlayoutBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



class Studentdetailsframe extends Fragment {



    StudentfragmentlayoutBinding studentfragmentlayoutBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     //  View view=inflater.inflate(R.layout.studentfragmentlayout,container,false);



        studentfragmentlayoutBinding= DataBindingUtil.inflate(inflater,
                R.layout.studentfragmentlayout,null,false);

    String strtext=getArguments().getString("message");

        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://manaidea-001-site45.dtempurl.com/2019n20/index.php/api/student_profile/").addConverterFactory(GsonConverterFactory.create()).build();


        NetworkRequestInterface networkRequestInterface=retrofit.create(NetworkRequestInterface.class);


        Call<StudentProfilePojo1> studentProfilePojo1Call=networkRequestInterface.STUDENT_PROFILE_POJO_1_CALL(strtext);

         studentProfilePojo1Call.enqueue(new Callback<StudentProfilePojo1>() {
             @Override
             public void onResponse(Call<StudentProfilePojo1> call, Response<StudentProfilePojo1> response) {

                 studentfragmentlayoutBinding.admissionnumber.setText(" : "+response.body().getStudent().getAdmno());
                 studentfragmentlayoutBinding.accesscardnumber.setText(" : "+response.body().getStudent().getAcesscardNumber());
                 studentfragmentlayoutBinding.admissiondate.setText(" : "+response.body().getStudent().getAdmissionDate());
                 studentfragmentlayoutBinding.gender.setText(" : "+response.body().getStudent().getGender());
                 studentfragmentlayoutBinding.dateofbirth.setText(" : "+response.body().getStudent().getDob());
                 studentfragmentlayoutBinding.nationality.setText(" : "+response.body().getStudent().getNationality());
                 studentfragmentlayoutBinding.religion.setText(" : "+response.body().getStudent().getRelegion());
                 studentfragmentlayoutBinding.mothertonue.setText(" : "+response.body().getStudent().getMotherTongue());
                 studentfragmentlayoutBinding.bloodgroup.setText(" : "+response.body().getStudent().getBloodGroup());
                 studentfragmentlayoutBinding.adharnumber.setText(" : "+response.body().getStudent().getAadharNo());
                 studentfragmentlayoutBinding.passport.setText(" : "+response.body().getStudent().getPassport());
                 studentfragmentlayoutBinding.transport.setText(" : "+response.body().getStudent().getTransport());

                 studentfragmentlayoutBinding.hostel.setText(" : "+response.body().getStudent().getHostel());
                 studentfragmentlayoutBinding.mark1.setText(" : "+response.body().getStudent().getMark1());
                 studentfragmentlayoutBinding.mark2.setText(" : "+response.body().getStudent().getMark2());


              }

             @Override
             public void onFailure(Call<StudentProfilePojo1> call, Throwable t) {

             }
         });


        return studentfragmentlayoutBinding.getRoot();
    }

}

package com.example.schooleproject1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.schooleproject1.databinding.ParentfragmentlayoutBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class Parentdetailsframe extends Fragment {
    ParentfragmentlayoutBinding parentfragmentlayoutBinding;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


       parentfragmentlayoutBinding= DataBindingUtil.inflate(inflater,R.layout.parentfragmentlayout,null,false);

        String strtext=getArguments().getString("message");
        Toast.makeText(getActivity(), "parentframe"+strtext, Toast.LENGTH_SHORT).show();


        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://manaidea-001-site45.dtempurl.com/2019n20/index.php/api/student_profile/").addConverterFactory(GsonConverterFactory.create()).build();

        NetworkRequestInterface networkRequestInterface=retrofit.create(NetworkRequestInterface.class);

        Call<StudentProfilePojo1> studentProfilePojo1Call=networkRequestInterface.STUDENT_PROFILE_POJO_1_CALL(strtext);

        studentProfilePojo1Call.enqueue(new Callback<StudentProfilePojo1>() {
            @Override
            public void onResponse(Call<StudentProfilePojo1> call, Response<StudentProfilePojo1> response) {
                Toast.makeText(getActivity(), "parentframe"+response.body().getStatus(), Toast.LENGTH_SHORT).show();

                parentfragmentlayoutBinding.fathername.setText(" : "+response.body().getStudent().getFatherName());
                parentfragmentlayoutBinding.cAddress.setText(" : "+response.body().getStudent().getCurrentAddress());
                parentfragmentlayoutBinding.fatheradhar.setText(" : "+response.body().getStudent().getFadhar());
                parentfragmentlayoutBinding.fathercontact.setText(" : "+response.body().getStudent().getPhone());
                parentfragmentlayoutBinding.fatherreligion.setText(" : "+response.body().getStudent().getFatherReligion());
                parentfragmentlayoutBinding.fatheremail.setText(" : "+response.body().getStudent().getEmail());
                parentfragmentlayoutBinding.fatherincome.setText(" : "+response.body().getStudent().getMemail());
                parentfragmentlayoutBinding.fatherpassport.setText(" : "+response.body().getStudent().getFpassport());
                parentfragmentlayoutBinding.fOccupation.setText(" : "+response.body().getStudent().getFatherOccupation());
                parentfragmentlayoutBinding.mOccupation.setText(" : "+response.body().getStudent().getMotherOccupation());
                parentfragmentlayoutBinding.motheradhar.setText(" : "+response.body().getStudent().getMadhar());
                parentfragmentlayoutBinding.mothercontact.setText(" : "+response.body().getStudent().getMphone());
                parentfragmentlayoutBinding.motheremail.setText(" : "+response.body().getStudent().getMemail());
                parentfragmentlayoutBinding.mothername.setText(" : "+response.body().getStudent().getMotherName());
                parentfragmentlayoutBinding.motherpassport.setText(" : "+response.body().getStudent().getPassport());
                parentfragmentlayoutBinding.motherincome.setText(" : "+response.body().getStudent().getMincome());
                parentfragmentlayoutBinding.motherreligion.setText(" : "+response.body().getStudent().getMotherReligion());
                parentfragmentlayoutBinding.pAddress.setText(" : "+response.body().getStudent().getPermanentAddress());
                parentfragmentlayoutBinding.parentqualification.setText(" : "+response.body().getStudent().getQualification());




            }

            @Override
            public void onFailure(Call<StudentProfilePojo1> call, Throwable t) {

            }
        });
        return parentfragmentlayoutBinding.getRoot();
    }
}

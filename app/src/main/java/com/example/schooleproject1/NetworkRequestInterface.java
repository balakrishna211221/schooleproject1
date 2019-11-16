package com.example.schooleproject1;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface NetworkRequestInterface {
    @POST("parent_login")
    @Headers("Content-Type:application/json")
    Call<LoginPojo> LOGIN_POJO_CALL(@Body JsonObject jsonObject1);


    @GET("students")
    @Headers("Content-Type:application/json")
    Call<StudentsPojo1> STUDENTS_POJO_1_CALL();


    @GET("{Student_id}")
    Call<StudentProfilePojo1> STUDENT_PROFILE_POJO_1_CALL(@Path("Student_id") String Sid);



    @GET("homework")
    @Headers("Content-Type:application/json")
    Call<HomeWorkPojo1> HOME_WORK_POJO_1_CALL();


    @GET("{Student_id}")
    Call<MarksActivityPojo1> MARKS_ACTIVITY_POJO_1_CALL(@Path("Student_id") String Sid);




}

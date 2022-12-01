package com.kiraninfotech.kiraninfotech.Interface

import com.kiraninfotech.kiraninfotech.Model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {


    @GET("Products.php")
    fun getProducts(): Call<List<ProductModel>>

    @GET("Solutions.php")
    fun getSolutions():Call<List<SolutionModel>>

    @GET("AwsPlans.php")
    fun getAwsPlans(): Call<List<AwsPlanModel>>

    @GET("OnlineEvents.php")
    fun getOnlineEvents(): Call<List<OnlineEventModel>>

    @GET("OfflineEvents.php")
    fun getOfflineEvents(): Call<List<OfflineEventModel>>

    @GET("ContactUs.php")
    fun getContacts():Call<List<ContactModel>>

    @GET("Academy.php")
    fun getAcademy() : Call<List<AcademyModel>>

    @GET("AcademyCourse.php")
    fun getAcademyCourse(): Call<List<AcademyCourseModel>>

    @GET("AcademyClasses.php")
    fun getAcademyClasses() : Call<List<AcademyClassModel>>

    @Headers("Content-Type: application/json")
    @POST("AddToken.php")
    fun addNotifyToken(@Body tokenData : NotificationTokenModel) : Call<NotificationTokenModel>


    @Headers("Content-Type: application/json")
    @POST("EnquiryForm.php")
    fun addEnquiry(@Body enquiryData : EnquiryModel) : Call<EnquiryModel>

    @Headers("Content-Type: application/json")
    @POST("StudentRegistration.php")
    fun addStudentRegistration(@Body studentData : StudentRegistrationModel) : Call<StudentRegistrationModel>


    @Headers("Content-Type: application/json")
    @POST("EventRegistration.php")
    fun addEventRegistration(@Body eventCustomerData : EventCustomerRegistrationModel) : Call<EventCustomerRegistrationModel>





    companion object{

        private const val BASE_URL = "http://tallydata.co.in/KiraninfotechApp/API/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiInterface::class.java)
        }

    }

}
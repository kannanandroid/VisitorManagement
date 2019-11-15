package com.e.ifazig.visitormanagement.api;



import com.e.ifazig.visitormanagement.api_model.BuildingApiResponse;
import com.e.ifazig.visitormanagement.api_model.CompanyApiResponse;
import com.e.ifazig.visitormanagement.api_model.FileuploadApiResponse;
import com.e.ifazig.visitormanagement.api_model.GetEmployeePurposeDetailsApiResponse;
import com.e.ifazig.visitormanagement.api_model.InsertComplaintApiResponse;
import com.e.ifazig.visitormanagement.api_model.LocationApiResponse;
import com.e.ifazig.visitormanagement.api_model.LoginApiResponseModel;
import com.e.ifazig.visitormanagement.api_model.ReturnVisitorApiResponse;
import com.e.ifazig.visitormanagement.api_model.SMSApiResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiInterface {


    @GET(Urls.LOGIN)
    Call<LoginApiResponseModel> login(@Query("UserName") String email,
                                      @Query("Password") String password,
                                      @Query("ToolId") String ToolId,
                                      @Query("demoVal") String demoVal);

    @GET(Urls.FORGOTPASSWORD)
    Call<LoginApiResponseModel> forgotPassword(@Query("username") String email);


    @GET(Urls.GETCOMPANYDETAILS)
    Call<CompanyApiResponse> companyDetails(@Query("UserId") String UserId);

    @GET(Urls.GETVISITORSDETAILS)
    Call<ReturnVisitorApiResponse> getReturnVisitor(@Query("PhoneNo") String PhoneNo);


    @GET(Urls.LOCATIONDETAILSBYROLEID)
    Call<LocationApiResponse> locationDetails(@Query("UserId") String RoleId,
                                              @Query("CompanyId") String CompanyId);

    @GET(Urls.BUILDINGDETAILS)
    Call<BuildingApiResponse> buildingDetails(@Query("UserId") String CompanyId,
                                              @Query("LocationId") String LocationId);
    @GET(Urls.GETEMPLOYEEPURPOSEDETAILS)
    Call<GetEmployeePurposeDetailsApiResponse> getEmployeePurposeDetails(@Query("EmployeeId") String EmployeeId,
                                                                         @Query("PurposeId") String PurposeId,
                                                                         @Query("CompanyId") String CompanyId,
                                                                         @Query("LocationId")String LocationId,
                                                                         @Query("BuildingId")String BuildingId);




    @POST(Urls.INSERTVAMS)
    Call<InsertComplaintApiResponse> insertData(@Body RequestBody body);

    @Multipart
    @POST(Urls.DOCUMENTUPLOAD)
    Call<FileuploadApiResponse> uploadfile(@Part MultipartBody.Part profile_image);


    @GET(Urls.SMS_URL)
    Call<SMSApiResponse> smsData(@Query("api_key") String api_key,
                                 @Query("method")String method,
                                 @Query("message")String message,
                                 @Query("to")String to,
                                 @Query("sender")String sender);

}
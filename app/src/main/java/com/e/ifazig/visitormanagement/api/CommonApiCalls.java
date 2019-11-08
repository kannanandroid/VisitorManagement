package com.e.ifazig.visitormanagement.api;

import android.content.Context;


import com.e.ifazig.visitormanagement.api_model.BuildingApiResponse;
import com.e.ifazig.visitormanagement.api_model.CompanyApiResponse;
import com.e.ifazig.visitormanagement.api_model.FileuploadApiResponse;
import com.e.ifazig.visitormanagement.api_model.GetEmployeePurposeDetailsApiResponse;
import com.e.ifazig.visitormanagement.api_model.InsertComplaintApiResponse;
import com.e.ifazig.visitormanagement.api_model.LocationApiResponse;
import com.e.ifazig.visitormanagement.api_model.LoginApiResponseModel;
import com.e.ifazig.visitormanagement.api_model.ReturnVisitorApiResponse;
import com.e.ifazig.visitormanagement.api_model.SMSApiResponse;
import com.e.ifazig.visitormanagement.callback.CommonCallback;
import com.e.ifazig.visitormanagement.utility.CommonFunctions;
import com.e.ifazig.visitormanagement.utility.CustomProgressDialog;
import com.e.ifazig.visitormanagement.utility.MyApplication;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonApiCalls {
    private static CommonApiCalls ourInstance;

    public static CommonApiCalls getInstance() {
        ourInstance = new CommonApiCalls();
        return ourInstance;
    }

    /**
     * @param context
     * @param emailid
     * @param password
     * @param listener
     */
    public void getLoginDetails(final Context context, String emailid, String password, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<LoginApiResponseModel> call = apiInterface.login(emailid, password);
        call.enqueue(new Callback<LoginApiResponseModel>() {
            @Override
            public void onResponse(Call<LoginApiResponseModel> call, Response<LoginApiResponseModel> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<LoginApiResponseModel> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }

    /**
     * @param context
     * @param emailid
     * @param listener
     */
    public void getForgotPasswordDetails(final Context context, String emailid, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<LoginApiResponseModel> call = apiInterface.forgotPassword(emailid);
        call.enqueue(new Callback<LoginApiResponseModel>() {
            @Override
            public void onResponse(Call<LoginApiResponseModel> call, Response<LoginApiResponseModel> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<LoginApiResponseModel> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }


    /**
     * @param context
     * @param UserId
     * @param listener
     */

    public void getCompanyDetails(final Context context, String UserId, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<CompanyApiResponse> call = apiInterface.companyDetails(UserId);
        call.enqueue(new Callback<CompanyApiResponse>() {
            @Override
            public void onResponse(Call<CompanyApiResponse> call, Response<CompanyApiResponse> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<CompanyApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }

    /**
     * @param context
     * @param RoleId
     * @param listener
     */
    public void getLocationDetails(final Context context, String RoleId, String companyID, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<LocationApiResponse> call = apiInterface.locationDetails(RoleId, companyID);
        call.enqueue(new Callback<LocationApiResponse>() {
            @Override
            public void onResponse(Call<LocationApiResponse> call, Response<LocationApiResponse> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<LocationApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }

    /**
     * @param context
     * @param LocationId
     * @param listener
     */
    public void getBuildingDetails(final Context context, String userID, String LocationId, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<BuildingApiResponse> call = apiInterface.buildingDetails(userID, LocationId);
        call.enqueue(new Callback<BuildingApiResponse>() {
            @Override
            public void onResponse(Call<BuildingApiResponse> call, Response<BuildingApiResponse> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<BuildingApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }

    /**
     * 
     * @param context
     * @param phone
     * @param listener
     */
    public void getReturnVisitorDetails(final Context context, String phone, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<ReturnVisitorApiResponse> call = apiInterface.getReturnVisitor(phone);
        call.enqueue(new Callback<ReturnVisitorApiResponse>() {
            @Override
            public void onResponse(Call<ReturnVisitorApiResponse> call, Response<ReturnVisitorApiResponse> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<ReturnVisitorApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }


    /**
     *
     * @param context
     * @param EmployeeId
     * @param PurposeId
     * @param CompanyId
     * @param LocationId
     * @param BuildingId
     * @param listener
     */
    public void getEmployeePurposeeDetails(final Context context, String EmployeeId,String PurposeId, String CompanyId,String LocationId,String BuildingId, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<GetEmployeePurposeDetailsApiResponse> call = apiInterface.getEmployeePurposeDetails(EmployeeId,PurposeId,CompanyId, LocationId,BuildingId);
        call.enqueue(new Callback<GetEmployeePurposeDetailsApiResponse>() {
            @Override
            public void onResponse(Call<GetEmployeePurposeDetailsApiResponse> call, Response<GetEmployeePurposeDetailsApiResponse> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<GetEmployeePurposeDetailsApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }
    public void sendVisitorSMSDetails(final Context context, String api_key,String method, String message,String to,String sender, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        ApiInterface apiInterface = SMSApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<SMSApiResponse> call = apiInterface.smsData(api_key,method,message, to,sender);
        call.enqueue(new Callback<SMSApiResponse>() {
            @Override
            public void onResponse(Call<SMSApiResponse> call, Response<SMSApiResponse> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("OK")) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<SMSApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }

    public void insertVMSData(final Context context, String body, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        RequestBody body_ = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<InsertComplaintApiResponse> call = apiInterface.insertData(body_);
        call.enqueue(new Callback<InsertComplaintApiResponse>() {
            @Override
            public void onResponse(Call<InsertComplaintApiResponse> call, Response<InsertComplaintApiResponse> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<InsertComplaintApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }

    public void updateImage(final Context context, File profilepic, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        MultipartBody.Part body = null;
        if (profilepic != null) {
            RequestBody fbody = RequestBody.create(MediaType.parse("image/jpg"), profilepic);
            body = MultipartBody.Part.createFormData("", profilepic.getName(), fbody);
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<FileuploadApiResponse> call = apiInterface.uploadfile(body);
        call.enqueue(new Callback<FileuploadApiResponse>() {
            @Override
            public void onResponse(Call<FileuploadApiResponse> call, Response<FileuploadApiResponse> response) {
                CustomProgressDialog.getInstance().dismiss();
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<FileuploadApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });

    }

}

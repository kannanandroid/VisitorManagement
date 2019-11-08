package com.e.ifazig.visitormanagement.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.e.ifazig.visitormanagement.api.CommonApiCalls;
import com.e.ifazig.visitormanagement.api_model.FileuploadApiResponse;
import com.e.ifazig.visitormanagement.api_model.InsertComplaintApiResponse;
import com.e.ifazig.visitormanagement.api_model.InsertDataModel;
import com.e.ifazig.visitormanagement.callback.CommonCallback;
import com.e.ifazig.visitormanagement.utility.CommonFunctions;
import com.e.ifazig.visitormanagement.utility.LanguageConstants;
import com.e.ifazig.visitormanagement.utility.SessionManager;
import com.e.ifazig.visitormanagement.utility.SharedPrefConstants;
import com.e.visitormanagement.R;
import com.e.visitormanagement.databinding.ActivityImageUploadBinding;
import com.google.gson.Gson;
import com.mikelau.croperino.Croperino;
import com.mikelau.croperino.CroperinoConfig;
import com.mikelau.croperino.CroperinoFileUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import lolodev.permissionswrapper.callback.OnRequestPermissionsCallBack;
import lolodev.permissionswrapper.wrapper.PermissionWrapper;

public class ImageUploadActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityImageUploadBinding binding;
    private String filePath = "";
    private Uri uri;
    Bitmap profilePortfolioBitmap;
    private String encodedImage = "";
    private File mFileRelationPhoto;

    // intent datas
    Integer employeeId=0;
    Integer purposeID=0;
    String visitorname = "";
    String visitorlocation = "";
    String visitoremail = "";
    String visitormobilenumber = "";
    Integer visitorId=0;
    String  imageLocation="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_upload);
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("VISITORNAME") != null) {
                visitorname = getIntent().getExtras().getString("VISITORNAME");
            }
            if (getIntent().getExtras().getString("VISITORLOCATION") != null) {
                visitorlocation = getIntent().getExtras().getString("VISITORLOCATION");
            }
            if (getIntent().getExtras().getString("VISITOREMAIL") != null) {
                visitoremail = getIntent().getExtras().getString("VISITOREMAIL");
            }
            if (getIntent().getExtras().getString("VISITORMOBILENUMBER") != null) {
                visitormobilenumber = getIntent().getExtras().getString("VISITORMOBILENUMBER");
            } if (getIntent().getExtras().getInt("EMPLOYEEID")!=0) {
                employeeId = getIntent().getExtras().getInt("EMPLOYEEID");
            } if (getIntent().getExtras().getInt("PURPOSEID") != 0) {
                purposeID = getIntent().getExtras().getInt("PURPOSEID");
            }
            if(getIntent().getExtras().getString("IMAGELOCATION")!=null){
                imageLocation=getIntent().getExtras().getString("IMAGELOCATION");
            }if(getIntent().getExtras().getInt("VISITORID")!=0){
                visitorId=getIntent().getExtras().getInt("VISITORID");
            }
        }
        initView();
        new CroperinoConfig("IMG_123", "/Pictures", "/sdcard/Pictures");
        CroperinoFileUtil.setupDirectory(this);
    }

    private void initView() {
        binding.title.setText(LanguageConstants.smileplease);
        binding.ivbackArrow.setOnClickListener(this);
        binding.rlCamera.setOnClickListener(this);
        binding.rlOkay.setOnClickListener(this);
        binding.rlRetake.setOnClickListener(this);
        binding.rlOkay.setVisibility(View.GONE);
        binding.rlRetake.setVisibility(View.GONE);
        binding.rlCamera.setVisibility(View.VISIBLE);
        if(imageLocation!=null&&!imageLocation.isEmpty()){
            Glide.with(ImageUploadActivity.this).load(imageLocation).apply(RequestOptions.circleCropTransform()).into(binding.ivProImage);
            binding.rlOkay.setVisibility(View.VISIBLE);
            binding.rlRetake.setVisibility(View.VISIBLE);
            binding.rlCamera.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivbackArrow:
                finish();
                break;
            case R.id.rlCamera:
                callCamereGallary();
                break;
            case R.id.rl_Okay:
                if(imageLocation!=null&&!imageLocation.isEmpty()){
                    InsertDataModel insertDataModel = new InsertDataModel();

                    // visitor details

                    List<InsertDataModel.EVisitor> eVisitor = new ArrayList<>();
                    InsertDataModel.EVisitor eVisitor1 = new InsertDataModel.EVisitor();
                    eVisitor1.setVisitorsId(visitorId);// it always send empty auto generated by backend side
                    eVisitor1.setVisitorName(visitorname);
                    eVisitor1.setVisitorFrom(visitorlocation);
                    eVisitor1.setPhoneNo(visitormobilenumber);
                    eVisitor1.setEmailId(visitoremail);
                    eVisitor1.setCompanyName(visitorlocation);
                    eVisitor1.setIsActive(true);// it always sent true control by backend side
                    eVisitor1.setImageLocation(imageLocation); // image upload api response
                    eVisitor.add(eVisitor1);
                    insertDataModel.setEVisitors(eVisitor);

                    // employee details

                    List<InsertDataModel.TVisiting> tVisitings = new ArrayList<>();
                    InsertDataModel.TVisiting mTVisiting = new InsertDataModel.TVisiting();

                    mTVisiting.setVisitId(0);//it always send empty auto generated by backend side
                    mTVisiting.setVisitorId(0);
                    mTVisiting.setEmployeeId(employeeId);
                    mTVisiting.setPurposeId(purposeID);
                    mTVisiting.setCompanyId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.COMPANYID)));
                    mTVisiting.setLocationId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.LOCATIONID)));
                    mTVisiting.setBuildingId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.BUILDINGID)));
                    mTVisiting.setInTime("");
                    mTVisiting.setOutTime("");
                    tVisitings.add(mTVisiting);
                    insertDataModel.setTVisiting(tVisitings);


                    Gson gson = new Gson();
                    final String body = gson.toJson(insertDataModel);
                    System.out.println("Input ==> " + body);
                    CommonApiCalls.getInstance().insertVMSData(ImageUploadActivity.this, body, new CommonCallback.Listener() {
                        @Override
                        public void onSuccess(Object object) {
                            InsertComplaintApiResponse mInsertComplaintApiResponse = (InsertComplaintApiResponse) object;
                            if (mInsertComplaintApiResponse.getStatus()) {
                                CommonFunctions.getInstance().successResponseToast(ImageUploadActivity.this, mInsertComplaintApiResponse.getMessage());
                                SessionManager.getInstance().insertIntoPreference(ImageUploadActivity.this,SharedPrefConstants.VISITOR_NAME,visitorname);
                                SessionManager.getInstance().insertIntoPreference(ImageUploadActivity.this,SharedPrefConstants.VISITOR_NUMBER,visitormobilenumber);

                                //ActivityCompat.finishAffinity(ImageUploadActivity.this);
                                CommonFunctions.getInstance().newIntentForRelts(ImageUploadActivity.this, ThankYouActivity.class, Bundle.EMPTY, true);
                            } else {
                                CommonFunctions.getInstance().successResponseToast(ImageUploadActivity.this, mInsertComplaintApiResponse.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(String reason) {

                        }
                    });

                }else {
                    CommonApiCalls.getInstance().updateImage(ImageUploadActivity.this, mFileRelationPhoto, new CommonCallback.Listener() {
                        @Override
                        public void onSuccess(Object object) {
                            FileuploadApiResponse fileuploadApiResponse = (FileuploadApiResponse) object;
                            if (fileuploadApiResponse.getStatus()) {
                                InsertDataModel insertDataModel = new InsertDataModel();

                                // visitor details

                                List<InsertDataModel.EVisitor> eVisitor = new ArrayList<>();
                                InsertDataModel.EVisitor eVisitor1 = new InsertDataModel.EVisitor();
                                eVisitor1.setVisitorsId(visitorId);// it always send empty auto generated by backend side
                                eVisitor1.setVisitorName(visitorname);
                                eVisitor1.setVisitorFrom(visitorlocation);
                                eVisitor1.setPhoneNo(visitormobilenumber);
                                eVisitor1.setEmailId(visitoremail);
                                eVisitor1.setCompanyName(visitorlocation);
                                eVisitor1.setIsActive(true);// it always sent true control by backend side
                                eVisitor1.setImageLocation(fileuploadApiResponse.getReturnData()); // image upload api response
                                eVisitor.add(eVisitor1);
                                insertDataModel.setEVisitors(eVisitor);

                                // employee details

                                List<InsertDataModel.TVisiting> tVisitings = new ArrayList<>();
                                InsertDataModel.TVisiting mTVisiting = new InsertDataModel.TVisiting();

                                mTVisiting.setVisitId(0);//it always send empty auto generated by backend side
                                mTVisiting.setVisitorId(0);
                                mTVisiting.setEmployeeId(employeeId);
                                mTVisiting.setPurposeId(purposeID);
                                mTVisiting.setCompanyId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.COMPANYID)));
                                mTVisiting.setLocationId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.LOCATIONID)));
                                mTVisiting.setBuildingId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.BUILDINGID)));
                                mTVisiting.setInTime("");
                                mTVisiting.setOutTime("");
                                tVisitings.add(mTVisiting);
                                insertDataModel.setTVisiting(tVisitings);


                                Gson gson = new Gson();
                                final String body = gson.toJson(insertDataModel);
                                System.out.println("Input ==> " + body);
                                CommonApiCalls.getInstance().insertVMSData(ImageUploadActivity.this, body, new CommonCallback.Listener() {
                                    @Override
                                    public void onSuccess(Object object) {
                                        InsertComplaintApiResponse mInsertComplaintApiResponse = (InsertComplaintApiResponse) object;
                                        if (mInsertComplaintApiResponse.getStatus()) {
                                            CommonFunctions.getInstance().successResponseToast(ImageUploadActivity.this, mInsertComplaintApiResponse.getMessage());
                                            SessionManager.getInstance().insertIntoPreference(ImageUploadActivity.this,SharedPrefConstants.VISITOR_NAME,visitorname);
                                            SessionManager.getInstance().insertIntoPreference(ImageUploadActivity.this,SharedPrefConstants.VISITOR_NUMBER,visitormobilenumber);
                                            CommonFunctions.getInstance().newIntentForRelts(ImageUploadActivity.this, ThankYouActivity.class, Bundle.EMPTY, true);
                                        } else {
                                            CommonFunctions.getInstance().successResponseToast(ImageUploadActivity.this, mInsertComplaintApiResponse.getMessage());
                                        }
                                    }

                                    @Override
                                    public void onFailure(String reason) {

                                    }
                                });
                            } else {
                                CommonFunctions.getInstance().successResponseToast(ImageUploadActivity.this, fileuploadApiResponse.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(String reason) {

                        }
                    });
                }

                break;
            case R.id.rlRetake:
                callCamereGallary();
                break;
        }
    }

    private void callCamereGallary() {

        new PermissionWrapper.Builder(ImageUploadActivity.this)
                .addPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
                //enable rationale message with a custom message
                .addPermissionRationale("Permission needed")
                //show settings dialog,in this case with default message base on requested permission/s
                .addPermissionsGoSettings(true)
                //enable callback to know what option was choosed
                .addRequestPermissionsCallBack(new OnRequestPermissionsCallBack() {
                    @Override
                    public void onGrant() {
                        prepareChooser();
                    }

                    @Override
                    public void onDenied(String permission) {

                    }
                }).build().request();
    }

    // ---- Pick Image Crop
    private void prepareChooser() {
        try {
            Croperino.prepareCamera(ImageUploadActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareCamera() {
        Croperino.prepareCamera(ImageUploadActivity.this);
    }

    // ---  On-Request--Permissions--Result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CroperinoFileUtil.REQUEST_CAMERA) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.CAMERA)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        prepareCamera();
                    }
                }
            }
        } else if (requestCode == CroperinoFileUtil.REQUEST_EXTERNAL_STORAGE) {
            boolean wasReadGranted = false;
            boolean wasWriteGranted = false;

            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        wasReadGranted = true;
                    }
                }
                if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        wasWriteGranted = true;
                    }
                }
            }

            if (wasReadGranted && wasWriteGranted) {
                prepareChooser();
            }
        }
    }

    // -- On Activity Result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // ---- Image Crop
        switch (requestCode) {
            case CroperinoConfig.REQUEST_TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    Croperino.runCropImage(CroperinoFileUtil.getTempFile(), ImageUploadActivity.this, true, 1, 1, R.color.gray, R.color.gray_variant);
                }
                break;
            case CroperinoConfig.REQUEST_PICK_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    CroperinoFileUtil.newGalleryFile(data, ImageUploadActivity.this);
                    Croperino.runCropImage(CroperinoFileUtil.getTempFile(), ImageUploadActivity.this, true, 1, 1, R.color.gray, R.color.gray_variant);
                }
                break;
            case CroperinoConfig.REQUEST_CROP_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    uri = Uri.fromFile(CroperinoFileUtil.getTempFile());
                    binding.ivProImage.setImageURI(uri);
                    binding.ivProImage.setVisibility(View.VISIBLE);
                    mFileRelationPhoto = CroperinoFileUtil.getTempFile();
                    filePath = mFileRelationPhoto.getPath();
                    /*profilePortfolioBitmap = BitmapFactory.decodeFile(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    profilePortfolioBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                    byte[] b = baos.toByteArray();

                    try {
                        encodedImage = URLEncoder.encode(Base64.encodeToString(b, Base64.DEFAULT), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }*/
                    //binding.tvUploadImage.setText(filePath);

                    //Glide.with(ImageUploadActivity.this).asBitmap().load(filePath).into(binding.ivProImage);
                    Glide.with(ImageUploadActivity.this).load(filePath).apply(RequestOptions.circleCropTransform()).into(binding.ivProImage);
                    binding.rlOkay.setVisibility(View.VISIBLE);
                    binding.rlRetake.setVisibility(View.VISIBLE);
                    binding.rlCamera.setVisibility(View.GONE);
                    imageLocation="";


                    //callChangeProfilePicApi(mFileRelationPhoto);
                }
                break;
            default:
                break;
        }
    }
}

package com.e.ifazig.visitormanagement.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.e.ifazig.visitormanagement.adapters.EmployeeListAdapter;
import com.e.ifazig.visitormanagement.adapters.PurposeListAdapter;
import com.e.ifazig.visitormanagement.api.CommonApiCalls;
import com.e.ifazig.visitormanagement.api_model.GetEmployeePurposeDetailsApiResponse;
import com.e.ifazig.visitormanagement.callback.CommonCallback;
import com.e.ifazig.visitormanagement.interfacess.EmployeeOnClick;
import com.e.ifazig.visitormanagement.interfacess.PurposeOnClick;
import com.e.ifazig.visitormanagement.utility.CommonFunctions;
import com.e.ifazig.visitormanagement.utility.LanguageConstants;
import com.e.ifazig.visitormanagement.utility.SessionManager;
import com.e.ifazig.visitormanagement.utility.SharedPrefConstants;
import com.e.visitormanagement.R;
import com.e.visitormanagement.databinding.ActivityPurposeDetailsBinding;

import java.util.List;

public class PurposeDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityPurposeDetailsBinding binding;
    Integer employeeId=0;
    Integer purposeID=0;
    String visitorname="";
    String visitorlocation="";
    String visitoremail="";
    String visitormobilenumber="";
    Integer visitorId=0;
    String  imageLocation="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_purpose_details);
        binding.edemployee.setThreshold(1);
        binding.edpurpose.setThreshold(1);
        if(getIntent().getExtras()!=null){
            if(getIntent().getExtras().getString("VISITORNAME")!=null){
                visitorname=getIntent().getExtras().getString("VISITORNAME");
            }if(getIntent().getExtras().getString("VISITORLOCATION")!=null){
                visitorlocation=getIntent().getExtras().getString("VISITORLOCATION");
            }if(getIntent().getExtras().getString("VISITOREMAIL")!=null){
                visitoremail=getIntent().getExtras().getString("VISITOREMAIL");
            }if(getIntent().getExtras().getString("VISITORMOBILENUMBER")!=null){
                visitormobilenumber=getIntent().getExtras().getString("VISITORMOBILENUMBER");
            }if(getIntent().getExtras().getString("IMAGELOCATION")!=null){
                imageLocation=getIntent().getExtras().getString("IMAGELOCATION");
            }if(getIntent().getExtras().getInt("VISITORID")!=0){
                visitorId=getIntent().getExtras().getInt("VISITORID");
            }
        }
        initView();
        binding.ivbackArrow.setOnClickListener(this);
        binding.ivRightArrow.setOnClickListener(this);
    }

    private void initView() {
        getAllDetails();
    }

    private void getAllDetails() {
        CommonApiCalls.getInstance().getEmployeePurposeeDetails(PurposeDetailsActivity.this, "0", "0",
                SessionManager.getInstance().getFromPreference(SharedPrefConstants.COMPANYID),
                SessionManager.getInstance().getFromPreference(SharedPrefConstants.LOCATIONID),
                SessionManager.getInstance().getFromPreference(SharedPrefConstants.BUILDINGID), new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        GetEmployeePurposeDetailsApiResponse body = (GetEmployeePurposeDetailsApiResponse) object;
                        List<GetEmployeePurposeDetailsApiResponse.EmployeeDetail> employeeDetails = body.getReturnData().getEmployeeDetails();
                        List<GetEmployeePurposeDetailsApiResponse.PurposeofVisitDetail> purposeofVisitDetails= body.getReturnData().getPurposeofVisitDetails();
                        if (body.getStatus()) {
                            if(employeeDetails!=null&&employeeDetails.size()>0){
                                EmployeeListAdapter adapter = new EmployeeListAdapter(PurposeDetailsActivity.this, R.layout.activity_purpose_details, R.id.lbl_name, employeeDetails, new EmployeeOnClick() {
                                    @Override
                                    public void onClick(Integer empId,String empName,String phoneno) {
                                        //Toast.makeText(PurposeDetailsActivity.this, ""+empId, Toast.LENGTH_SHORT).show();
                                        employeeId=empId;
                                        SessionManager.getInstance().insertIntoPreference(PurposeDetailsActivity.this,SharedPrefConstants.MEETING_PERSON_NAME,empName);
                                        SessionManager.getInstance().insertIntoPreference(PurposeDetailsActivity.this,SharedPrefConstants.MEETER_PERSON_NUMBER,phoneno);
                                    }
                                });
                                binding.edemployee.setAdapter(adapter);
                            }else {
                                CommonFunctions.getInstance().validationWarningError(PurposeDetailsActivity.this,LanguageConstants.noRecordAvailable);
                            }
                            if(purposeofVisitDetails!=null&&purposeofVisitDetails.size()>0){
                                PurposeListAdapter adapter = new PurposeListAdapter(PurposeDetailsActivity.this, R.layout.activity_purpose_details, R.id.lbl_name, purposeofVisitDetails, new PurposeOnClick() {
                                    @Override
                                    public void onClick(Integer purpoID) {
                                        //Toast.makeText(PurposeDetailsActivity.this, ""+purposeID, Toast.LENGTH_SHORT).show();
                                        purposeID=purpoID;
                                    }
                                });
                                binding.edpurpose.setAdapter(adapter);
                            }else {
                                CommonFunctions.getInstance().validationWarningError(PurposeDetailsActivity.this,LanguageConstants.noRecordAvailable);
                            }

                        } else {
                            CommonFunctions.getInstance().successResponseToast(PurposeDetailsActivity.this, body.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(String reason) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivRightArrow:
                if(binding.edemployee.getText().toString().trim().isEmpty()){
                    CommonFunctions.getInstance().validationEmptyError(PurposeDetailsActivity.this, LanguageConstants.namecantblank);

                    return;
                }
                if(employeeId==0) {
                    CommonFunctions.getInstance().validationEmptyError(PurposeDetailsActivity.this, LanguageConstants.selectatlestone);
                    return;
                }
                if(binding.edpurpose.getText().toString().trim().isEmpty()){
                    CommonFunctions.getInstance().validationEmptyError(PurposeDetailsActivity.this, LanguageConstants.namecantblank);
                    return;
                }
                if(purposeID==0) {
                    CommonFunctions.getInstance().validationEmptyError(PurposeDetailsActivity.this, LanguageConstants.selectatlestone);
                    return;
                }
                    Bundle bundle = new Bundle();
                    bundle.putInt("EMPLOYEEID",employeeId);
                    bundle.putInt("PURPOSEID",purposeID);
                    bundle.putInt("VISITORID",visitorId);
                    bundle.putString("VISITORNAME",visitorname);
                    bundle.putString("VISITORLOCATION",visitorlocation);
                    bundle.putString("VISITOREMAIL",visitoremail);
                    bundle.putString("VISITORMOBILENUMBER",visitormobilenumber);
                    bundle.putString("IMAGELOCATION",imageLocation);
                    CommonFunctions.getInstance().newIntent(PurposeDetailsActivity.this, ImageUploadActivity.class, bundle, false);

                break;
            case R.id.ivbackArrow:
                finish();
                break;
            default:
                break;
        }

    }
}

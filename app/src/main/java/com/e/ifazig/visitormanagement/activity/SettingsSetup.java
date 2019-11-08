package com.e.ifazig.visitormanagement.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.e.ifazig.visitormanagement.api.CommonApiCalls;
import com.e.ifazig.visitormanagement.api_model.BuildingApiResponse;
import com.e.ifazig.visitormanagement.api_model.CompanyApiResponse;
import com.e.ifazig.visitormanagement.api_model.LocationApiResponse;
import com.e.ifazig.visitormanagement.callback.CommonCallback;
import com.e.ifazig.visitormanagement.spinnerdialog.OnSpinerItemClick;
import com.e.ifazig.visitormanagement.spinnerdialog.SpinnerDialog;
import com.e.ifazig.visitormanagement.spinnerdialog.SpinnerModel;
import com.e.ifazig.visitormanagement.utility.CommonFunctions;
import com.e.ifazig.visitormanagement.utility.LanguageConstants;
import com.e.visitormanagement.R;
import com.e.visitormanagement.databinding.SettingsRequestBinding;

import java.util.ArrayList;
import java.util.List;

import static com.e.ifazig.visitormanagement.activity.HomeActivity.tempHomeActivity;


public class SettingsSetup extends AppCompatActivity implements View.OnClickListener {
    SettingsRequestBinding binding;
    private String userID;
    private String locationID;
    private String companyID;
    private String buildingID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.settings_request);
        if (getIntent().getExtras() != null) {
            userID = getIntent().getExtras().getString("USERID");
        }
        initView();
    }

    private void initView() {
        binding.tvCompany.setHint(LanguageConstants.companytxt);
        binding.tvLoaction.setHint(LanguageConstants.locationtxt);
        binding.tvBuilding.setHint(LanguageConstants.buildingtxt);
        binding.btnSubmit.setText(LanguageConstants.submit);

        binding.llCompany.setOnClickListener(this);
        binding.llLoaction.setOnClickListener(this);
        binding.llBuilding.setOnClickListener(this);

        binding.rlySubmit.setOnClickListener(this);
        binding.ivbackArrow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llCompany:
                getCompanyDetails();
                break;
            case R.id.llLoaction:
                if (binding.tvCompany.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(SettingsSetup.this, LanguageConstants.selectCompanyfirst);
                    return;
                }
                getLocationDetails();
                break;
            case R.id.llBuilding:
                if (binding.tvLoaction.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(SettingsSetup.this, LanguageConstants.selectlocationfirst);
                    return;
                }
                getBuildingDetails();
                break;
            case R.id.rlySubmit:
                if (binding.tvCompany.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(SettingsSetup.this, LanguageConstants.selectCompanyfirst);
                    return;
                }
                if (binding.tvLoaction.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(SettingsSetup.this, LanguageConstants.selectlocationfirst);
                    return;
                }
                if (binding.tvBuilding.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(SettingsSetup.this, LanguageConstants.selectbuildingfirst);
                    return;
                }
                submitApi();
                break;
            case R.id.ivlogout:
                finish();
                break;
            case R.id.ivbackArrow:
                finish();
                break;
            default:
                break;
        }
    }

    private void submitApi() {
        if (tempHomeActivity != null) {
            tempHomeActivity.finish();
        }
        Bundle bundle = new Bundle();
        bundle.putString("USERID", userID);
        bundle.putString("COMPANYID", companyID);
        bundle.putString("LOCATIONID", locationID);
        bundle.putString("BUILDINGID", buildingID);
        bundle.putString("SETTINGSETUPPAGE", "1");
        CommonFunctions.getInstance().newIntentForRelts(SettingsSetup.this, HomeActivity.class, bundle, true);
    }
    private void getBuildingDetails() {
        CommonApiCalls.getInstance().getBuildingDetails(SettingsSetup.this, userID, locationID, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object object) {
                BuildingApiResponse body = (BuildingApiResponse) object;
                List<BuildingApiResponse.ReturnDatum> buildingdetails = body.getReturnData();
                if (body.getStatus()) {
                    if (buildingdetails == null)
                        return;

                    final ArrayList<SpinnerModel> mSpinnerModels = new ArrayList<>();
                    SpinnerModel mSpinnerModel;
                    for (int count = 0; count < buildingdetails.size(); count++) {
                        mSpinnerModel = new SpinnerModel();
                        mSpinnerModel.setId(String.valueOf(buildingdetails.get(count).getBuildingID()));
                        mSpinnerModel.setName(buildingdetails.get(count).getBuildingName());
                        mSpinnerModels.add(mSpinnerModel);
                    }
                    SpinnerDialog spinnerDialog;
                    spinnerDialog = new SpinnerDialog(SettingsSetup.this, mSpinnerModels, LanguageConstants.location);
                    spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                        @Override
                        public void onClick(String item, int position) {
                            binding.tvBuilding.setText(mSpinnerModels.get(position).getName());
                            binding.tvBuilding.setTag(mSpinnerModels.get(position).getId());
                            buildingID = mSpinnerModels.get(position).getId();

                        }
                    });
                    spinnerDialog.showSpinerDialog();
                } else {
                    CommonFunctions.getInstance().successResponseToast(SettingsSetup.this, body.getMessage());
                }

            }

            @Override
            public void onFailure(String reason) {

            }
        });
    }

    private void getLocationDetails() {
        CommonApiCalls.getInstance().getLocationDetails(SettingsSetup.this, userID, companyID, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object object) {
                LocationApiResponse body = (LocationApiResponse) object;
                List<LocationApiResponse.ReturnDatum> locationdetails = body.getReturnData();
                if (body.getStatus()) {
                    if (locationdetails == null)
                        return;

                    final ArrayList<SpinnerModel> mSpinnerModels = new ArrayList<>();
                    SpinnerModel mSpinnerModel;
                    for (int count = 0; count < locationdetails.size(); count++) {
                        mSpinnerModel = new SpinnerModel();
                        mSpinnerModel.setId(String.valueOf(locationdetails.get(count).getLocationID()));
                        mSpinnerModel.setName(locationdetails.get(count).getLocationName());
                        mSpinnerModels.add(mSpinnerModel);
                    }
                    SpinnerDialog spinnerDialog;
                    spinnerDialog = new SpinnerDialog(SettingsSetup.this, mSpinnerModels, LanguageConstants.location);
                    spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                        @Override
                        public void onClick(String item, int position) {
                            binding.tvLoaction.setText(mSpinnerModels.get(position).getName());
                            binding.tvLoaction.setTag(mSpinnerModels.get(position).getId());
                            locationID = mSpinnerModels.get(position).getId();

                        }
                    });
                    spinnerDialog.showSpinerDialog();
                } else {
                    CommonFunctions.getInstance().successResponseToast(SettingsSetup.this, body.getMessage());
                }

            }

            @Override
            public void onFailure(String reason) {

            }
        });
    }

    private void getCompanyDetails() {
        CommonApiCalls.getInstance().getCompanyDetails(SettingsSetup.this, userID, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object object) {
                CompanyApiResponse body = (CompanyApiResponse) object;
                List<CompanyApiResponse.ReturnDatum> companydetails = body.getReturnData();
                if (body.getStatus()) {
                    if (companydetails == null)
                        return;

                    final ArrayList<SpinnerModel> mSpinnerModels = new ArrayList<>();
                    SpinnerModel mSpinnerModel;
                    for (int count = 0; count < companydetails.size(); count++) {
                        mSpinnerModel = new SpinnerModel();
                        mSpinnerModel.setId(String.valueOf(companydetails.get(count).getCompanyID()));
                        mSpinnerModel.setName(companydetails.get(count).getCompanyName());
                        mSpinnerModels.add(mSpinnerModel);
                    }
                    SpinnerDialog spinnerDialog;
                    spinnerDialog = new SpinnerDialog(SettingsSetup.this, mSpinnerModels, LanguageConstants.company);
                    spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                        @Override
                        public void onClick(String item, int position) {
                            binding.tvCompany.setText(mSpinnerModels.get(position).getName());
                            binding.tvCompany.setTag(mSpinnerModels.get(position).getId());
                            companyID = mSpinnerModels.get(position).getId();

                        }
                    });
                    spinnerDialog.showSpinerDialog();
                } else {
                    CommonFunctions.getInstance().successResponseToast(SettingsSetup.this, body.getMessage());
                }

            }

            @Override
            public void onFailure(String reason) {

            }
        });

    }
}

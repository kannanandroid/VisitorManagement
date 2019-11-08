package com.e.ifazig.visitormanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.e.ifazig.visitormanagement.api_model.ReturnVisitorApiResponse;
import com.e.ifazig.visitormanagement.utility.CommonFunctions;
import com.e.ifazig.visitormanagement.utility.LanguageConstants;
import com.e.visitormanagement.R;
import com.e.visitormanagement.databinding.ActivityVisitorDetailsBinding;
import com.google.gson.Gson;

public class VisitorDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityVisitorDetailsBinding binding;
    String ITEMS = "";
    private ReturnVisitorApiResponse.ReturnData items;
    Integer visitorId = 0;
    String imageLocation = "";
    String TEMPHONE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_visitor_details);

        if (getIntent().getExtras() != null) {
            TEMPHONE = getIntent().getExtras().getString("TEMPHONE");
            if (getIntent().getExtras().getString("ITEMS") != null) {
                ITEMS = getIntent().getExtras().getString("ITEMS");
                Gson gson = new Gson();
                items = gson.fromJson(ITEMS, ReturnVisitorApiResponse.ReturnData.class);
            }
        }
        if (items != null) {
            binding.edMobileNumber.setText(items.getPhoneNo());
            binding.edEmail.setText(items.getEmailId());
            binding.edLocation.setText(items.getVisitorFrom());
            binding.edName.setText(items.getVisitorName());
            visitorId = items.getVisitorsId();
            imageLocation = items.getImageLocation();
        }

        binding.ivbackArrow.setOnClickListener(this);
        binding.ivRightArrow.setOnClickListener(this);
        binding.edMobileNumber.setText(TEMPHONE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivRightArrow:
                if (binding.edName.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(VisitorDetailsActivity.this, LanguageConstants.namecantblank);
                    return;
                }
                if (binding.edLocation.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(VisitorDetailsActivity.this, LanguageConstants.locationcantblank);
                    return;
                }
                if (binding.edEmail.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(VisitorDetailsActivity.this, LanguageConstants.emailcantblank);
                    return;
                }
                if (!CommonFunctions.getInstance().isValidEmail(binding.edEmail.getText().toString().trim())) {
                    CommonFunctions.getInstance().validationEmptyError(VisitorDetailsActivity.this, LanguageConstants.invaildEmailFormat);
                    return;
                }
                if (binding.edMobileNumber.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(VisitorDetailsActivity.this, LanguageConstants.phonenumbercantblank);
                    return;
                }
                if (!CommonFunctions.getInstance().isValidMobile(binding.edMobileNumber.getText().toString().trim())) {
                    CommonFunctions.getInstance().validationEmptyError(VisitorDetailsActivity.this, LanguageConstants.invailphoneFormat);
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("VISITORNAME", binding.edName.getText().toString().trim());
                bundle.putString("VISITORLOCATION", binding.edLocation.getText().toString().trim());
                bundle.putString("VISITOREMAIL", binding.edEmail.getText().toString().trim());
                bundle.putString("VISITORMOBILENUMBER", binding.edMobileNumber.getText().toString().trim());
                bundle.putString("IMAGELOCATION", imageLocation);
                bundle.putInt("VISITORID", visitorId);
                CommonFunctions.getInstance().newIntent(VisitorDetailsActivity.this, PurposeDetailsActivity.class, bundle, false);
                break;
            case R.id.ivbackArrow:
                finish();
                break;
            default:
                break;
        }

    }
}

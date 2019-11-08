package com.e.ifazig.visitormanagement.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.e.ifazig.visitormanagement.api.CommonApiCalls;
import com.e.ifazig.visitormanagement.api_model.LoginApiResponseModel;
import com.e.ifazig.visitormanagement.api_model.ReturnVisitorApiResponse;
import com.e.ifazig.visitormanagement.callback.CommonCallback;
import com.e.ifazig.visitormanagement.utility.CommonFunctions;
import com.e.ifazig.visitormanagement.utility.LanguageConstants;
import com.e.visitormanagement.R;
import com.e.visitormanagement.databinding.ActivitySelectVisitorBinding;
import com.e.visitormanagement.databinding.HomeVisitorLayoutBinding;
import com.google.gson.Gson;

public class HomeVisitorActivity extends AppCompatActivity implements View.OnClickListener {

    HomeVisitorLayoutBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.home_visitor_layout);
        initView();
        binding.ivbackArrow.setOnClickListener(this);
        binding.ivRightArrow.setOnClickListener(this);

    }

    private void initView() {

    }

    private void callReturnVistior() {
        CommonApiCalls.getInstance().getReturnVisitorDetails(HomeVisitorActivity.this, binding.edMobileNumber.getText().toString().trim()
                , new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        ReturnVisitorApiResponse body = (ReturnVisitorApiResponse) object;
                        ReturnVisitorApiResponse.ReturnData data = body.getReturnData();
                        if (body.getStatus()) {
                            Bundle bundle = new Bundle();
                            Gson gson= new Gson();
                            if (data != null&&data.getVisitorsId()!=null) {
                                String items=gson.toJson(data);
                                bundle.putString("ITEMS",items);
                            } else {
                                //CommonFunctions.getInstance().successResponseToast(HomeVisitorActivity.this, LanguageConstants.dataEmpty);
                            }
                            bundle.putString("TEMPHONE",binding.edMobileNumber.getText().toString().trim());
                            CommonFunctions.getInstance().newIntent(HomeVisitorActivity.this, VisitorDetailsActivity.class, bundle, false);
                        } else {
                            CommonFunctions.getInstance().successResponseToast(HomeVisitorActivity.this, body.getMessage());
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
            case R.id.ivbackArrow:
                finish();
                break;
            case R.id.ivRightArrow:
                if(binding.edMobileNumber.getText().toString().trim().isEmpty()){
                    CommonFunctions.getInstance().validationEmptyError(HomeVisitorActivity.this,LanguageConstants.phonenumbercantblank);
                    return;
                }
                if(!CommonFunctions.getInstance().isValidMobile(binding.edMobileNumber.getText().toString().trim())){
                    CommonFunctions.getInstance().validationEmptyError(HomeVisitorActivity.this,LanguageConstants.invailphoneFormat);
                    return;
                }
                callReturnVistior();
                break;
            default:
                break;
        }

    }
}

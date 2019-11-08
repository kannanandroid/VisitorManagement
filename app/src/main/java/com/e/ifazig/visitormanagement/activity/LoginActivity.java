package com.e.ifazig.visitormanagement.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.e.ifazig.visitormanagement.api.CommonApiCalls;
import com.e.ifazig.visitormanagement.api_model.LoginApiResponseModel;
import com.e.ifazig.visitormanagement.callback.CommonCallback;
import com.e.ifazig.visitormanagement.utility.CommonFunctions;
import com.e.ifazig.visitormanagement.utility.LanguageConstants;
import com.e.visitormanagement.BuildConfig;
import com.e.visitormanagement.R;
import com.e.visitormanagement.databinding.ActivityLoginBinding;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding binding;
    private String versionName = BuildConfig.VERSION_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initView();
    }

    private void initView() {
        // text set
        binding.edEmail.setHint(LanguageConstants.emailidUsername);
        binding.edPassword.setHint(LanguageConstants.password);
        binding.title.setText(LanguageConstants.adminlogin);
        binding.btnSignin.setText(LanguageConstants.login);
        //binding.tvLoginTitle.setText(LanguageConstants.adminlogin);


        // listeners
        binding.llSignin.setOnClickListener(this);
        binding.ivbackArrow.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        //String pwdby = "\u00A9" + year + ". All rights reserved.\n \t\t\t  www.ifazig.com \n \t\t\t\t Version " + versionName;
        String ss = "Version" + versionName + "\n \t Release Date: Oct22, 2019 \n \t  www.ifazig.com \n" + "\u00A9" + year + ". All rights reserved.";
        //binding.tvcopyrights.setText(ss);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llSignin:
                if (binding.edEmail.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(LoginActivity.this, LanguageConstants.emailcantblank);
                    return;
                }
                if (binding.edPassword.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(LoginActivity.this, LanguageConstants.passwordcantblank);
                    return;
                }
                // call login api
                callLoginApi(binding.edEmail.getText().toString().trim(), binding.edPassword.getText().toString().trim());
                break;
                case R.id.ivbackArrow:
                finish();
                break;

            default:
                break;
        }
    }

    /**
     * @param email
     * @param password
     */
    private void callLoginApi(String email, String password) {
        CommonApiCalls.getInstance().getLoginDetails(LoginActivity.this, email,
                password, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        LoginApiResponseModel body = (LoginApiResponseModel) object;
                        LoginApiResponseModel.ReturnData data = body.getReturnData();
                        if (body.getStatus()) {
                            if (body.getReturnData() != null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("USERID", String.valueOf(data.getUserID()));
                                CommonFunctions.getInstance().newIntentForRelts(LoginActivity.this, SettingsSetup.class, bundle, true);
                            } else {
                                CommonFunctions.getInstance().successResponseToast(LoginActivity.this, LanguageConstants.dataEmpty);
                            }
                        } else {
                            CommonFunctions.getInstance().successResponseToast(LoginActivity.this, body.getMessage());
                        }


                    }

                    @Override
                    public void onFailure(String reason) {

                    }
                });

    }
}

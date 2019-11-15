package com.e.ifazig.visitormanagement.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.e.ifazig.visitormanagement.api.CommonApiCalls;
import com.e.ifazig.visitormanagement.api_model.SMSApiResponse;
import com.e.ifazig.visitormanagement.callback.CommonCallback;
import com.e.ifazig.visitormanagement.utility.CommonFunctions;
import com.e.ifazig.visitormanagement.utility.SessionManager;
import com.e.ifazig.visitormanagement.utility.SharedPrefConstants;
import com.e.visitormanagement.R;
import com.e.visitormanagement.databinding.ThankLayoutBinding;

public class ThankYouActivity extends AppCompatActivity {

    ThankLayoutBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.thank_layout);
        initView();
    }

    private void initView() {
        binding.txtthankmsg.setText(SessionManager.getInstance().getFromPreference(SharedPrefConstants.MEETING_PERSON_NAME) + " ," + "will reach you asap. Have a nice day!");

        callVisitorApi();
        //LoadHome();
    }

    void LoadHome() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityCompat.finishAffinity(ThankYouActivity.this);
                CommonFunctions.getInstance().newIntent(ThankYouActivity.this, HomeActivity.class, Bundle.EMPTY, true);
            }
        }, 5000);
    }

    // visitor api call
    private void callVisitorApi() {
        CommonApiCalls.getInstance().sendVisitorSMSDetails(ThankYouActivity.this, "6y5s4psty07o79lw1k1149m7h12168", "sms",
                "Welcome to"+" "+SessionManager.getInstance().getFromPreference(SharedPrefConstants.COMPANYNAME)+","+" We have informed your host" +" "+ SessionManager.getInstance().getFromPreference(SharedPrefConstants.MEETING_PERSON_NAME)+"  about your arrival.\n" +
                        "Have a great day!",
                SessionManager.getInstance().getFromPreference(SharedPrefConstants.VISITOR_NUMBER),
                "IFAZIG", new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        SMSApiResponse body = (SMSApiResponse) object;
                        if (body.getStatus().equalsIgnoreCase("OK")) {
                            //meeting person sms api call
                            callVisitingApi();
                        } else {
                            CommonFunctions.getInstance().successResponseToast(ThankYouActivity.this, body.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(String reason) {

                    }
                });
    }

    // meeter
    private void callVisitingApi() {
        CommonApiCalls.getInstance().sendVisitorSMSDetails(ThankYouActivity.this, "6y5s4psty07o79lw1k1149m7h12168", "sms",
                SessionManager.getInstance().getFromPreference(SharedPrefConstants.VISITOR_NAME)+" is waiting for you at Reception.",
                SessionManager.getInstance().getFromPreference(SharedPrefConstants.MEETER_PERSON_NUMBER),
                "IFAZIG", new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        SMSApiResponse body = (SMSApiResponse) object;
                        if (body.getStatus().equalsIgnoreCase("OK")) {
                            ActivityCompat.finishAffinity(ThankYouActivity.this);
                            CommonFunctions.getInstance().newIntent(ThankYouActivity.this, HomeActivity.class, Bundle.EMPTY, true);
                        } else {
                            CommonFunctions.getInstance().successResponseToast(ThankYouActivity.this, body.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(String reason) {

                    }
                });
    }
}

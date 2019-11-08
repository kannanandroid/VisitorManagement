package com.e.ifazig.visitormanagement.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.e.ifazig.visitormanagement.utility.CommonFunctions;
import com.e.ifazig.visitormanagement.utility.SessionManager;
import com.e.ifazig.visitormanagement.utility.SharedPrefConstants;
import com.e.visitormanagement.R;
import com.e.visitormanagement.databinding.ActivityCategoryHomeBinding;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityCategoryHomeBinding binding;
    private String userID;
    private String companyID;
    private String locationID;
    private String buildingID;
    private String settingsetuppage = "0";
    public static HomeActivity tempHomeActivity;
    Boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_home);
         if (getIntent().getExtras() != null) {
            settingsetuppage = getIntent().getExtras().getString("SETTINGSETUPPAGE");
            if (settingsetuppage != null && !settingsetuppage.isEmpty()) {
                if (settingsetuppage.equalsIgnoreCase("1")) {
                    userID = getIntent().getExtras().getString("USERID");
                    companyID = getIntent().getExtras().getString("COMPANYID");
                    locationID = getIntent().getExtras().getString("LOCATIONID");
                    buildingID = getIntent().getExtras().getString("BUILDINGID");
                    SessionManager.getInstance().Logout();
                    SessionManager.getInstance().insertIntoPreference(HomeActivity.this, SharedPrefConstants.USERID, userID);
                    SessionManager.getInstance().insertIntoPreference(HomeActivity.this, SharedPrefConstants.COMPANYID, companyID);
                    SessionManager.getInstance().insertIntoPreference(HomeActivity.this, SharedPrefConstants.LOCATIONID, locationID);
                    SessionManager.getInstance().insertIntoPreference(HomeActivity.this, SharedPrefConstants.BUILDINGID, buildingID);

                }
            }
        }

        tempHomeActivity = HomeActivity.this;
        binding.ivbackArrow.setOnClickListener(this);
        binding.tvTouchtoStart.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivbackArrow:
                CommonFunctions.getInstance().newIntent(HomeActivity.this, LoginActivity.class, Bundle.EMPTY, false);
                break;
            case R.id.tvTouchtoStart:
                CommonFunctions.getInstance().newIntent(HomeActivity.this, HomeVisitorActivity.class, Bundle.EMPTY, false);
                break;
            default:
                break;
        }
    }
    @Override
    public void onBackPressed() {

        FragmentManager manager = getSupportFragmentManager();
        int count = manager.getBackStackEntryCount();
        if (count == 1) {
            super.onBackPressed();
        }
        if (count == 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            CommonFunctions.getInstance().successResponseToast(HomeActivity.this, "Please click BACK again to exit");
        }

    }
}

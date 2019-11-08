package com.e.ifazig.visitormanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.e.ifazig.visitormanagement.utility.CommonFunctions;
import com.e.ifazig.visitormanagement.utility.SessionManager;
import com.e.ifazig.visitormanagement.utility.SharedPrefConstants;
import com.e.visitormanagement.R;
import com.e.visitormanagement.databinding.ActivityLoginBinding;
import com.e.visitormanagement.databinding.ActivitySelectVisitorBinding;

public class SelectVisitorActivity extends AppCompatActivity implements View.OnClickListener {
    String[] data = {"a new visitor", "a returning visitor"};
    ActivitySelectVisitorBinding binding;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_visitor);
        adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(SelectVisitorActivity.this, parent.getSelectedItemPosition()+"", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.ivbackArrow.setOnClickListener(this);
        binding.ivRightArrow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivbackArrow:
                finish();
                break;
            case R.id.ivRightArrow:
                CommonFunctions.getInstance().newIntent(SelectVisitorActivity.this, VisitorDetailsActivity.class, Bundle.EMPTY, false);
                break;
            default:
                break;
        }

    }
}

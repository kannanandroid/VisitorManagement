package com.e.ifazig.visitormanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.e.ifazig.visitormanagement.activity.ImageUploadActivity;
import com.e.ifazig.visitormanagement.api_model.GetEmployeePurposeDetailsApiResponse;
import com.e.ifazig.visitormanagement.interfacess.EmployeeOnClick;
import com.e.visitormanagement.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListAdapter extends ArrayAdapter<GetEmployeePurposeDetailsApiResponse.EmployeeDetail> {

    private final EmployeeOnClick employeeOnClick;
    Context context;
    int resource, textViewResourceId;
    List<GetEmployeePurposeDetailsApiResponse.EmployeeDetail> items, tempItems, suggestions;

    public EmployeeListAdapter(Context context, int resource, int textViewResourceId, List<GetEmployeePurposeDetailsApiResponse.EmployeeDetail> items, EmployeeOnClick employeeOnClick) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<GetEmployeePurposeDetailsApiResponse.EmployeeDetail>(items); // this makes the difference.
        suggestions = new ArrayList<GetEmployeePurposeDetailsApiResponse.EmployeeDetail>();
        this.employeeOnClick = employeeOnClick;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_sub_category_item, parent, false);
        }
        final GetEmployeePurposeDetailsApiResponse.EmployeeDetail people = items.get(position);
        if (people != null) {
            TextView lblName = (TextView) view.findViewById(R.id.lbl_name);
            TextView txtDescrpi = (TextView) view.findViewById(R.id.txtDescrpi);
            ImageView imge = (ImageView) view.findViewById(R.id.empimage);
            if (lblName != null) {
                lblName.setText(people.getEmployeeName());

            }else {

            }if (txtDescrpi != null) {
                txtDescrpi.setText(people.getDesigination());

            }else {

            }
            if(people.getImageLocation()!=null&&!people.getImageLocation().isEmpty()){
                Glide.with(context).load(people.getImageLocation()).apply(RequestOptions.circleCropTransform()).into(imge);
            }else {

            }

        }
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                employeeOnClick.onClick(people.getEmployeeId(),people.getEmployeeName(),people.getPhoneNo());
                return false;
            }
        });
        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((GetEmployeePurposeDetailsApiResponse.EmployeeDetail) resultValue).getEmployeeName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (GetEmployeePurposeDetailsApiResponse.EmployeeDetail people : tempItems) {
                    if (people.getEmployeeName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(people);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<GetEmployeePurposeDetailsApiResponse.EmployeeDetail> filterList = (ArrayList<GetEmployeePurposeDetailsApiResponse.EmployeeDetail>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (GetEmployeePurposeDetailsApiResponse.EmployeeDetail people : filterList) {
                    add(people);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
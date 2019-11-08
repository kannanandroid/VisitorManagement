package com.e.ifazig.visitormanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.e.ifazig.visitormanagement.api_model.GetEmployeePurposeDetailsApiResponse;
import com.e.ifazig.visitormanagement.interfacess.PurposeOnClick;
import com.e.visitormanagement.R;

import java.util.ArrayList;
import java.util.List;

public class PurposeListAdapter extends ArrayAdapter<GetEmployeePurposeDetailsApiResponse.PurposeofVisitDetail> {

    private final PurposeOnClick purposeOnClick;
    Context context;
    int resource, textViewResourceId;
    List<GetEmployeePurposeDetailsApiResponse.PurposeofVisitDetail> items, tempItems, suggestions;

    public PurposeListAdapter(Context context, int resource, int textViewResourceId, List<GetEmployeePurposeDetailsApiResponse.PurposeofVisitDetail> items, PurposeOnClick purposeOnClick) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        this.purposeOnClick=purposeOnClick;
        tempItems = new ArrayList<GetEmployeePurposeDetailsApiResponse.PurposeofVisitDetail>(items); // this makes the difference.
        suggestions = new ArrayList<GetEmployeePurposeDetailsApiResponse.PurposeofVisitDetail>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_purpose_item, parent, false);
        }
        final GetEmployeePurposeDetailsApiResponse.PurposeofVisitDetail people = items.get(position);
        if (people != null) {
            TextView lblName = (TextView) view.findViewById(R.id.lbl_name);
            if (lblName != null)
                lblName.setText(people.getPurposeName());
        }
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                purposeOnClick.onClick(people.getPurposeId(),people.getCDate());
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
            String str = ((GetEmployeePurposeDetailsApiResponse.PurposeofVisitDetail) resultValue).getPurposeName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (GetEmployeePurposeDetailsApiResponse.PurposeofVisitDetail people : tempItems) {
                    if (people.getPurposeName().toLowerCase().contains(constraint.toString().toLowerCase())) {
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
            List<GetEmployeePurposeDetailsApiResponse.PurposeofVisitDetail> filterList = (ArrayList<GetEmployeePurposeDetailsApiResponse.PurposeofVisitDetail>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (GetEmployeePurposeDetailsApiResponse.PurposeofVisitDetail people : filterList) {
                    add(people);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
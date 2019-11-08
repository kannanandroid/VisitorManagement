package com.e.ifazig.visitormanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.visitormanagement.R;
import com.e.ifazig.visitormanagement.api_model.FieldsApiResponse;
import com.e.ifazig.visitormanagement.interfacess.FieldsSelection;


import java.util.List;

/**
 * Created by Kannan on 24,August,2019
 */
public class FieldsAdapter extends RecyclerView.Adapter<FieldsAdapter.ViewHolder> {
    private final Context context;
    private final List<FieldsApiResponse.ReturnDatum> data;
    private final FieldsSelection catSelection;
    private final List<FieldsApiResponse.ReturnDatum> selectedCat;

    public FieldsAdapter(Context context, List<FieldsApiResponse.ReturnDatum> categorydetails,
                         List<FieldsApiResponse.ReturnDatum> selectedCat, FieldsSelection catSelection) {
        this.context = context;
        this.data = categorydetails;
        this.catSelection = catSelection;
        this.selectedCat = selectedCat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_view_checkbox, parent, false);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FieldsApiResponse.ReturnDatum item = data.get(position);
        holder.cbCategory.setText(item.getFieldName());

        holder.cbCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catSelection.fieldsOnItemClick(item);
                notifyDataSetChanged();
            }
        });

        if (selectedCat.contains(item)) {
            holder.cbCategory.setChecked(true);
        } else {
            holder.cbCategory.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox cbCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cbCategory = itemView.findViewById(R.id.cbCategory);
        }
    }
}

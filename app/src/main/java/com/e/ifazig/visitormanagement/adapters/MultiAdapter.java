package com.e.visitormanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.visitormanagement.R;
import com.e.ifazig.visitormanagement.api_model.CategoryApiResponse;
import com.e.ifazig.visitormanagement.interfacess.CatSelection;


import java.util.List;

/**
 * Created by Kannan on 24,August,2019
 */
public class MultiAdapter extends RecyclerView.Adapter<MultiAdapter.ViewHolder> {
    private final Context context;
    private final List<CategoryApiResponse.ReturnDatum> data;
    private final CatSelection catSelection;
    private final List<CategoryApiResponse.ReturnDatum> selectedCat;

    public MultiAdapter(Context context, List<CategoryApiResponse.ReturnDatum> categorydetails,
                        List<CategoryApiResponse.ReturnDatum> selectedCat, CatSelection catSelection) {
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
        final CategoryApiResponse.ReturnDatum item = data.get(position);
            holder.cbCategory.setText(item.getCategoryName());

            holder.cbCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    catSelection.catOnItemClick(item);
                    notifyDataSetChanged();
                }
            });

            if (selectedCat.contains(item)){
                holder.cbCategory.setChecked(true);
            }else{
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

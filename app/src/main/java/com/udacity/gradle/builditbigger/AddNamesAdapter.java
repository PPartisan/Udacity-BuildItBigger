package com.udacity.gradle.builditbigger;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

class AddNamesAdapter extends RecyclerView.Adapter<AddNamesAdapter.ViewHolder> {

    private List<NameModel> mNameModels;

    AddNamesAdapter(List<NameModel> nameModels) {
        mNameModels = nameModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dialog_fragment_add_names_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.name.setText(mNameModels.get(position).name);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int adapterPosition = holder.getAdapterPosition();
                DataUtils.deleteNameFromDatabase(view.getContext(), mNameModels.get(adapterPosition));
                mNameModels.remove(adapterPosition);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mNameModels == null) ? 0 : mNameModels.size();
    }

    final static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageButton delete;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.add_name_list_row_text);
            delete = (ImageButton) itemView.findViewById(R.id.add_names_list_row_delete);
        }

    }

    void addNameModel(NameModel nameModel) {
        mNameModels.add(nameModel);
        notifyDataSetChanged();
    }

    void setNameModels(List<NameModel> nameModels) {
        mNameModels = nameModels;
        notifyDataSetChanged();
    }

}

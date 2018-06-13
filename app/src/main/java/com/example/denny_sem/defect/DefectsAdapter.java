package com.example.denny_sem.defect;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DefectsAdapter extends RecyclerView.Adapter<DefectsAdapter.MyViewHolder> {

    private List<Defect> defectsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView city, address, sender, createdAt;

        public MyViewHolder(View view) {
            super(view);
            city = (TextView) view.findViewById(R.id.city);
            address = (TextView) view.findViewById(R.id.address);
            sender = (TextView) view.findViewById(R.id.sender);
            createdAt = (TextView) view.findViewById(R.id.createdAt);
        }
    }


    public DefectsAdapter(List<Defect> moviesList) {
        this.defectsList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.defect_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Defect defect = defectsList.get(position);
        holder.city.setText("City: " + defect.getCity());
        holder.address.setText("Address: " + defect.getAddress());
        holder.sender.setText("By " + defect.getSender());
        holder.createdAt.setText("Created at " + defect.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return defectsList.size();
    }
}

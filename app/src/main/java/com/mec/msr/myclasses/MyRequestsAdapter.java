package com.mec.msr.myclasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mec.msr.R;

import java.util.ArrayList;

public class MyRequestsAdapter extends RecyclerView.Adapter<MyRequestsAdapter.MyViewHolder> {

    private ArrayList<MyRequest> _MyRequestsArrayList;
    private OnRequestClickedListener _OnRequestClickedListener;
    private OnRequestDeleteListener _OnRequestDeleteListener;

    public MyRequestsAdapter() {
        _MyRequestsArrayList = new ArrayList<>();
    }

    public void setNewData(ArrayList<MyRequest> newData) {
        _MyRequestsArrayList = newData;
        notifyDataSetChanged();
    }

    public void setNewData(MyRequest newData) {
        _MyRequestsArrayList.add(newData);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_request_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyRequest r = _MyRequestsArrayList.get(position);

        switch (r.getEquipmentType()) {
            case "Big Forklift":
                holder.imageViewEquipmentImage.setImageResource(R.drawable.fl_big);
                break;
            case "Container Forklift":
                holder.imageViewEquipmentImage.setImageResource(R.drawable.fl_containers);
                break;
            case "Teal-handler Forklift":
                holder.imageViewEquipmentImage.setImageResource(R.drawable.fl_talehandler);
                break;
            case "Medium Forklift":
                holder.imageViewEquipmentImage.setImageResource(R.drawable.fl_medium);
                break;
            case "Small Forklift":
                holder.imageViewEquipmentImage.setImageResource(R.drawable.fl_small);
                break;
            case "Stacker Forklift":
                holder.imageViewEquipmentImage.setImageResource(R.drawable.fl_stacker);
                break;
        }

        holder.textViewEquipmentType.setText(r.getUserid() + "/" + r.getEquipmentType());
        holder.textViewReserveTime.setText(r.getReserveTime());
        holder.textViewRequestedBy.setText(r.getRequestedBy());

        holder.imageButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _OnRequestDeleteListener.OnDeleteClicked(r.getId());
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _OnRequestClickedListener.onRequestClicked(r);
            }
        });

    }

    public void setOnItemClickListener(OnRequestClickedListener listener){
        _OnRequestClickedListener = listener;
    }

    public void setOnItemDeleteClickListener(OnRequestDeleteListener listener){
        _OnRequestDeleteListener = listener;
    }

    @Override
    public int getItemCount() {
        return _MyRequestsArrayList.size();
    }

    public void refresh() {
        int size = _MyRequestsArrayList.size();
        _MyRequestsArrayList.clear();
        notifyItemRangeRemoved(0, size);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewEquipmentImage;
        TextView textViewEquipmentType;
        TextView textViewReserveTime;
        TextView textViewRequestedBy;
        ImageButton imageButtonDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewEquipmentImage = itemView.findViewById(R.id.imageViewEquipmentImage);
            textViewEquipmentType = itemView.findViewById(R.id.textViewEquipmentType);
            textViewReserveTime = itemView.findViewById(R.id.textViewReserveTime);
            textViewRequestedBy = itemView.findViewById(R.id.textViewRequestedBy);
            imageButtonDelete = itemView.findViewById(R.id.imageButtonDelete);
        }
    }
}

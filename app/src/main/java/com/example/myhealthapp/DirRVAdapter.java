package com.example.myhealthapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DirRVAdapter extends RecyclerView.Adapter<DirRVAdapter.ViewHolder> {
    // variable for our array list and context
    private ArrayList<blood> dirModalArrayList;
    private Context context;

    // constructor
    public DirRVAdapter(ArrayList<blood> dirModalArrayList, Context context) {
        this.dirModalArrayList = dirModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DirRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.disp_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DirRVAdapter.ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        blood modal = dirModalArrayList.get(position);
        holder.dirIDTV.setText(String.valueOf(modal.getId()));
        holder.dirTVDATE.setText(String.valueOf(modal.getDate()));
        holder.dirTVTIME.setText(modal.getTime());
        holder.dirTVSYSTOLIC.setText(modal.getSystolic());
        holder.dirTVDIASTOLIC.setText(modal.getDiastolic());
        holder.dirTVRESULT.setText(modal.getResult());
    }

    @Override
    public int getItemCount() {
        return dirModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView dirIDTV,dirTVDATE,dirTVTIME,dirTVSYSTOLIC,dirTVDIASTOLIC,dirTVRESULT;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing TextView
            dirIDTV = itemView.findViewById(R.id.tvBil);
            dirTVDATE = itemView.findViewById(R.id.tvDate);
            dirTVTIME = itemView.findViewById(R.id.tvTime);
            dirTVSYSTOLIC= itemView.findViewById(R.id.tvSystolic);
            dirTVDIASTOLIC= itemView.findViewById(R.id.tvDiastolic);
            dirTVRESULT = itemView.findViewById(R.id.tvResult);

        }
    }



}

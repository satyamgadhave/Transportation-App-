package com.plutecoder.transpotation_main_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<Showadded_retrive_data> list;

    public MyAdapter(Context context, ArrayList<Showadded_retrive_data> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.item_cardview_for_retrivdata,parent,false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


      Showadded_retrive_data showadded_retrive_data = list.get(position);
     holder.vehicleNumber.setText(showadded_retrive_data.getVehiclenumber_retriv());
     holder.companyName.setText(showadded_retrive_data.getCompanyname_retriv());
     holder.usedFor.setText(showadded_retrive_data.getUsedfor_retriv());
     holder.fuleType.setText(showadded_retrive_data.getFuletype_retriv());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView vehicleNumber , companyName , usedFor , fuleType;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vehicleNumber = itemView.findViewById(R.id.vehicle_number_showadded_data_cardview);
            companyName = itemView.findViewById(R.id.company_name_showadded_data_cardview);
            usedFor = itemView.findViewById(R.id.used_for_showadded_data_cardview);
            fuleType = itemView.findViewById(R.id.fule_type_showadded_data_cardview);
        }
    }
}

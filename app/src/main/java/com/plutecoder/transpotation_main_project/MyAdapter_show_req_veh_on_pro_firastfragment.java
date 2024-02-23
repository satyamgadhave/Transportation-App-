package com.plutecoder.transpotation_main_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter_show_req_veh_on_pro_firastfragment extends RecyclerView.Adapter<MyAdapter_show_req_veh_on_pro_firastfragment.MyViewHolder> {

    Context context;

    ArrayList<Showadded_retrive_data> list;

    public MyAdapter_show_req_veh_on_pro_firastfragment(Context context, ArrayList<Showadded_retrive_data> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cardview_for_show_requtervehicle_on_provider_firstfragment,parent,false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        Showadded_retrive_data showadded_retrive_data = list.get(position);
        holder.vehicleNumber.setText(showadded_retrive_data.getVehiclenumber_retriv());
        holder.companyName.setText(showadded_retrive_data.getCompanyname_retriv());
        holder.usedFor.setText(showadded_retrive_data.getUsedfor_retriv());
        holder.fuleType.setText(showadded_retrive_data.getFuletype_retriv());
        holder.service_usedfor.setText(showadded_retrive_data.getServices_useed_retriv());



        // Set a click listener on the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Showadded_retrive_data showadded_retrive_data = list.get(position);

                // Handle the click event here
                // Open the NewActivity when the item is clicked
                Intent intent = new Intent(context, CardView_FirstFragProv_ClickedActivity.class);
                intent.putExtra("RID",showadded_retrive_data.getUid());
                context.startActivity(intent);


//                Intent intent = new Intent(getContext(), DetailsActivity.class);
//                intent.putExtra("RID",marker.getTag().toString());
//                startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView vehicleNumber , companyName , usedFor , fuleType, service_usedfor;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vehicleNumber = itemView.findViewById(R.id.vehicle_number_showadded_data_cardview);
            companyName = itemView.findViewById(R.id.company_name_showadded_data_cardview);
            usedFor = itemView.findViewById(R.id.used_for_showadded_data_cardview);
            fuleType = itemView.findViewById(R.id.fule_type_showadded_data_cardview);
            service_usedfor = itemView.findViewById(R.id.car_service_showadded_data_cardview);
        }
    }
}

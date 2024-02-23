package com.plutecoder.transpotation_main_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Setter_Getter.Showadded_retrive_data_on_Requester_firstfragmentrequester;

public class MyAdapter_show_pro_veh_on_req_firastfragmentRequester extends RecyclerView.Adapter<MyAdapter_show_pro_veh_on_req_firastfragmentRequester.MyViewHolder> {

    Context context;
    ArrayList<Showadded_retrive_data_on_Requester_firstfragmentrequester> list;

    public MyAdapter_show_pro_veh_on_req_firastfragmentRequester(Context context, ArrayList<Showadded_retrive_data_on_Requester_firstfragmentrequester> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.animation_button_req_firstfragment,parent,false);
        return new MyViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        Showadded_retrive_data_on_Requester_firstfragmentrequester showadded_retrive_data_on_requester_firstfragmentrequester = list.get(position);

        holder.carServicereq.setText(showadded_retrive_data_on_requester_firstfragmentrequester.getCarServicereq_retrive());
        holder.startre.setText(showadded_retrive_data_on_requester_firstfragmentrequester.getStartreq_retrive());
        holder.endreq.setText(showadded_retrive_data_on_requester_firstfragmentrequester.getEndreq_retrive());
        holder.capacityreq.setText(showadded_retrive_data_on_requester_firstfragmentrequester.getCapacityreq_retrive());
        holder.comapanyModelreq.setText(showadded_retrive_data_on_requester_firstfragmentrequester.getComapanyModelreq_retrive());
        holder.perkmacreq.setText(showadded_retrive_data_on_requester_firstfragmentrequester.getPerkmacreq_retrive());
        holder.perkmnonacreq.setText(showadded_retrive_data_on_requester_firstfragmentrequester.getPerkmnonacreq_retrive());
        holder.petrolreq.setText(showadded_retrive_data_on_requester_firstfragmentrequester.getPetrolreq_retrive());
        holder.vehicleNumberreq.setText(showadded_retrive_data_on_requester_firstfragmentrequester.getVehicleNumberreq_retrive());



//
//       //  Set a click listener on the item view
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Showadded_retrive_data showadded_retrive_data = list.get(position);
//
//                // Handle the click event here
//                // Open the NewActivity when the item is clicked
//                Intent intent = new Intent(context, CardView_FirstFragProv_ClickedActivity.class);
//                intent.putExtra("RID",showadded_retrive_data.getUid());
//                context.startActivity(intent);
//
//
////                Intent intent = new Intent(getContext(), DetailsActivity.class);
////                intent.putExtra("RID",marker.getTag().toString());
////                startActivity(intent);
//            }
//        });



    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView     carServicereq , startre, endreq , capacityreq , comapanyModelreq, perkmacreq , perkmnonacreq , petrolreq , vehicleNumberreq;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            carServicereq = itemView.findViewById(R.id.car_servicereq_showadded_data_cardview);
            startre = itemView.findViewById(R.id.startreq_showadded_data_cardview);
            endreq = itemView.findViewById(R.id.endreq_showadded_data_cardview);
            capacityreq = itemView.findViewById(R.id.capacityreq_showadded_data_cardview);
            comapanyModelreq = itemView.findViewById(R.id.comapny_modelreq_showadded_data_cardview);
            perkmacreq = itemView.findViewById(R.id.perkm_ac_showadded_data_cardview);
            perkmnonacreq = itemView.findViewById(R.id.perkm_nonac_showadded_data_cardview);
            petrolreq = itemView.findViewById(R.id.pertrol_showadded_data_cardview);
            vehicleNumberreq = itemView.findViewById(R.id.vehicle_numberreq_showadded_data_cardview);
        }
    }
}

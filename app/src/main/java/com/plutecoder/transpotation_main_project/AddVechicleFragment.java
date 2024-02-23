package com.plutecoder.transpotation_main_project;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Setter_Getter.Add_Vehicle_Setter_Getter;

public class AddVechicleFragment extends Fragment {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    EditText vnumber , vcompanyname , vmodel , vfueltype , vusedfor , vcapacity , vtotaltire , vpriceperkm , vstart , vend , vnonac;
    Button btnAddvehicle;
    RadioGroup radioGroup;
    RadioButton raddioprovider , radiorequester ;

    String selectedOption="Provider";

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_vechicle, container, false);
        vnumber = view.findViewById(R.id.VNumber);
        vcompanyname = view.findViewById(R.id.VCompanyName);
        vmodel = view.findViewById(R.id.VModel);
        vfueltype = view.findViewById(R.id.VFuleType);

        vcapacity = view.findViewById(R.id.VCapacity);
        vtotaltire = view.findViewById(R.id.VTotalTire);
        vpriceperkm = view.findViewById(R.id.Vdestinystart_priceperkm);
        vstart = view.findViewById(R.id.Vdestinystart_proadd);
        vend = view.findViewById(R.id.Vdestinyend_proadd);
        vnonac = view.findViewById(R.id.Vdestinynonac_priceperkm);
        btnAddvehicle = view.findViewById(R.id.add_botton);
        radioGroup = view.findViewById(R.id.radioGroup);
        raddioprovider = view.findViewById(R.id.radioProvider);
        radiorequester = view.findViewById(R.id.radioRequester);



        btnAddvehicle.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if(raddioprovider.isChecked()){
                    selectedOption = "Journet/Trip";



                }else{
                    selectedOption = "luggage transport";


                }

                addVehicle();
                Toast.makeText(getActivity(), "Vehicle added successfully", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }


    public void  addVehicle(){
        Add_Vehicle_Setter_Getter addVehicleSetterGetter = new Add_Vehicle_Setter_Getter();

        String userId = mAuth.getCurrentUser().getUid();

        DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();

        String strvnumber = vnumber.getText().toString();
        String strvcompany = vcompanyname.getText().toString();
        String strvmodel = vmodel.getText().toString();
        String strvfueltype = vfueltype.getText().toString();
//        String strvusedfor = vusedfor.getText().toString();
        String strvcapacity = vcapacity.getText().toString();
        String strvtotaltire = vtotaltire.getText().toString();
        String strvpriceperkm = vpriceperkm.getText().toString();
        String strvstert = vstart.getText().toString();
        String strvend = vend.getText().toString();
        String strvnonac = vnonac.getText().toString();
        String vuid = mdatabase.push().getKey();

        addVehicleSetterGetter.setVeh_No(strvnumber);
        addVehicleSetterGetter.setVeh_Company_name(strvcompany);
        addVehicleSetterGetter.setVeh_Model(strvmodel);
        addVehicleSetterGetter.setVeh_Fuel_Type(strvfueltype);
       //addVehicleSetterGetter.setVeh_Used_for(strvusedfor);
        addVehicleSetterGetter.setVeh_Capacity(strvcapacity);
        addVehicleSetterGetter.setVeh_Total_tire(strvtotaltire);
        addVehicleSetterGetter.setVeh_priceperkm(strvpriceperkm);
        addVehicleSetterGetter.setVeh_start(strvstert);
        addVehicleSetterGetter.setVeh_end(strvend);
        addVehicleSetterGetter.setVeh_non_ac(strvnonac);

        addVehicleSetterGetter.setRadioButtons(selectedOption);


        addVehicleSetterGetter.setUid(vuid);
        mdatabase.child("Provider Vehicle").child(userId).child(vuid).setValue(addVehicleSetterGetter);


    }



}
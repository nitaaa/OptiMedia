package com.example.partnersincode.optimedia.ui.shareLibrary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.partnersincode.optimedia.R;

public class ShareLibrary extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.share_library,container, false);

        Button btnQR = view.findViewById(R.id.btnGenQR);
        btnQR.setOnClickListener(v->
        {
            //Create bundle to send to QR code fragment. TODO: Pass the XML as the correct datatype
            Bundle bundle = new Bundle();
            //Test string
            bundle.putString("XML","8ZtVYoSPj8ZAceKpl9hE" +
                            "UM5ojHm852EnwBnVmD7f" +
                            "Qc0Mj9VEVYwlP4WzsmIA" +
                            "yVYo8sBGpwBi5NQWtueQ" +
                            "pVDD9diwIHE2X0GzoPer" +
                            "vv2tvMAJdEAZKBzH5WHi" +
                            "OHkAUQgtbr3BsclIuIl4" +
                            "BzUG3ql2FrKRmkeGx1Za" +
                            "UCvaaKo9tK7Ve6ZkaZxM" +
                            "bn4vbeLtzQtKWpdZEbHJ"

                    );

            Navigation.findNavController(view).navigate(R.id.nav_showQrCode,bundle); //takes to generate qr code

        });

        Button btnXML = view.findViewById(R.id.btnCreateXML);
        btnXML.setOnClickListener(v->
        {
            // Navigation.findNavController(view).navigate(R.id.); takes to generate xml
        });

        return view;
    }



}

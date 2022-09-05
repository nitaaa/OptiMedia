package com.example.partnersincode.optimedia.ui.shareLibrary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.partnersincode.optimedia.R;

public class ShareLibrary extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.share_library,container, false);

        Button btnQR = view.findViewById(R.id.btnGenQR);
        btnQR.setOnClickListener(v->
        {
           // Navigation.findNavController(view).navigate(R.id.); takes to generate qr code
        });

        Button btnXML = view.findViewById(R.id.btnCreateXML);
        btnXML.setOnClickListener(v->
        {
            // Navigation.findNavController(view).navigate(R.id.); takes to generate xml
        });

        return view;
    }



}

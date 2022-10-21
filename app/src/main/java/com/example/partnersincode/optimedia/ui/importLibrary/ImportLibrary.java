package com.example.partnersincode.optimedia.ui.importLibrary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Library;

public class ImportLibrary extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.import_library,container, false);

        Button btnQR = view.findViewById(R.id.btnScanQR);
        btnQR.setOnClickListener(v->
                {
                   Navigation.findNavController(v).navigate(R.id.nav_qrScanControl);
                });

        Button btnXML = view.findViewById(R.id.btnImportXML);
        btnXML.setOnClickListener(v->
        {
//            // Navigation.findNavController(view).navigate(R.id.); takes to generate xml
//            DatabaseHandler db = new DatabaseHandler(getContext());
//            Library lib = db.getAllLibraries().get(3);
//            Bundle bundle = new Bundle();
//            bundle.putParcelable("library",lib);
            Navigation.findNavController(view).navigate(R.id.nav_xmlImport);
        });

        return view;
    }






}

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

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Library;

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
                    {
                    bundle.putString("XML", "Herein lies a test string, where the string is" +"going to be quite long for testing purposes" + "Imagine a Neural network writing an essay after looking at memes, that would be my test string" + "*/(@&$(#!)$*$" +")#(*)(&)@(*$_*)@$*. Y=mx+c, the quadratic formula." +"Iorum epsom random latin to fill space/n" +"Today were are watching squid game, as all the kids are into it" +"and now netflix made a real life squid game bc people have too much time" +"The capital of England is London. Is the Capital of USA Washington?\n" +"America is so big, it's like a bunch of different tiny countries.\n\n" +"Use Case ID Use Case Name \n" +"A03200 Share Via QR Code \n" +"Primary Business Actors Other participating Actors \n" +"System \n" +"Description System generates a QR code for sharing media objects to another \n" +"User \n" +"Pre-Conditions Data must be selected for sharing \n" +"Triggers User clicked on Share via QR in A03000 \n" +"Post-Conditions XML document containing Media Object data is generated as a \n" +"QR code \n" +"Basic Flow of \n" +"Events\n" +"1. System generates a QR code for sharing (Calls A03210) \n" +"2. The screen displays the QR code \n\n\n" +"Use Case ID Use Case Name \n" +"A05000 Scan QR code \n" +"Primary Business Actors Other participating Actors \n" +"User \n" +
                            "Description Scan a QR code containing an XML file with media objects inside \n" +
                            "Pre-Conditions \n" +
                            "Triggers User clicked Import via QR \n" +
                            "Post-Conditions Media objects added to the local database. \n" +
                            "Optional: Objects added to a library \n" +
                            "Basic Flow of \n" +
                            "Events \n" +
                            "1. User lines QR code up with the crosshairs on screen \n" +
                            "2. System scans the QR code \n" +
                            "3. Inclusion point <ID> \n" +
                            "Alternate Flow of \n" +
                            "events \n" +
                            "2a There is a bad scan \n" +
                            "1. System prompts user to retry \n" +
                            "2. Use case restarts \n");
                    }

            Navigation.findNavController(view).navigate(R.id.nav_showQrCode,bundle); //takes to generate qr code

        });

        Button btnXML = view.findViewById(R.id.btnCreateXML);
        btnXML.setOnClickListener(v->
        {
            // Navigation.findNavController(view).navigate(R.id.); takes to generate xml
            DatabaseHandler db = new DatabaseHandler(getContext());
            Library lib = db.getAllLibraries().get(3);
            Bundle bundle = new Bundle();
            bundle.putParcelable("library",lib);
            Navigation.findNavController(view).navigate(R.id.nav_xmlExport,bundle);
        });

        return view;
    }



}

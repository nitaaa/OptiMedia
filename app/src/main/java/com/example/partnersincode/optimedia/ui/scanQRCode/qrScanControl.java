package com.example.partnersincode.optimedia.ui.scanQRCode;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.partnersincode.optimedia.Property;
import com.example.partnersincode.optimedia.R;
import com.google.zxing.client.android.Intents;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

/**
 * This is the Fragment that initated the qrScanAction, as well as stores and make use of
 * the scanned string
 * A simple {@link Fragment} subclass.
 * Use the {@link qrScanControl#newInstance} factory method to
 * create an instance of this fragment.
 */
public class qrScanControl extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Property Attributes
    Property<String> contentsScanned;
    Property<Integer> nrOfScans;
    String lastScan;

    //UI References
    TextView lblScannedContents;
    TextView lblScanNr;

//    //Request key for result
//    String requestKey = "ScanQR";

    private ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result ->
            {
                if(result.getContents()!=null) {
                    Intent origInent = result.getOriginalIntent();
                    if(origInent==null)
                    {
                        Toast.makeText(getContext(),"Scan Failed",Toast.LENGTH_SHORT).show();
                    }
                    else if(origInent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                        Toast.makeText(getContext(),"No Camera Permissions",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        contentsScanned.set(contentsScanned.get()+result.getContents());
                        nrOfScans.set(nrOfScans.get()+1);
                        lastScan = result.getContents();
                    }
                }
            });


    public qrScanControl() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment scanQrCode.
     */
    // TODO: Rename and change types and number of parameters
    public static qrScanControl newInstance(String param1, String param2) {
        qrScanControl fragment = new qrScanControl();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_scan_qr_code, container, false);

        getUIReferences(root);

        setUpProperties();

        setOnClickActions(root);
//        setOnResultReturned();



        return root;
    }

//    private void setOnResultReturned()
//    {
//        getParentFragmentManager().setFragmentResultListener(requestKey,this,(requestKey1, result) ->
//        {
//            String output = result.getString("result");
//
//            if(output!=null)
//            {
//                contentsScanned.set(contentsScanned.get()+output);
//                nrOfScans.set(nrOfScans.get()+1);
//            }
//        });
//    }

    private void getUIReferences(View view)
    {
        lblScanNr = view.findViewById(R.id.A05000_lblScanNr);
        lblScannedContents = view.findViewById(R.id.A05000_lblScannedContents);
    }
    
    private void setOnClickActions(View view)
    {
        Button scanMore = view.findViewById(R.id.A05000_btnScanMore);
        scanMore.setOnClickListener(this::onScanMoreClicked);

        Button submit = view.findViewById(R.id.A05000_btnSubmit);
        submit.setOnClickListener(this::onSubmitClicked);

        Button undo = view.findViewById(R.id.A05000_undo);
        undo.setOnClickListener(this::onUndoClicked);
    }

    private void setUpProperties()
    {
        //initialise
        contentsScanned = new Property<>();
        nrOfScans = new Property<>();

        //set onChangeListeners
        contentsScanned.addListener((property, oldValue, newValue) -> {
            lblScannedContents.setText(newValue);
        });

        nrOfScans.addListener((property, oldValue, newValue) -> {
            lblScanNr.setText("Scan number: "+newValue);
        });

        //set initial values (notify)
        contentsScanned.set("");
        nrOfScans.set(0);
        lastScan="";


    }


    private void onScanMoreClicked(View view)
    {
//        Navigation.findNavController(view).navigate(R.id.nav_qrScanAction);
//
//        getChildFragmentManager()
//                .beginTransaction()
//                .add(new qrScanAction(),"QR");

        ScanOptions scan = new ScanOptions();
        scan.setOrientationLocked(false);

        barcodeLauncher.launch(scan);


    }

    private void onSubmitClicked(View view)
    {
        //TODO: Integration


    }

    private void onUndoClicked(View view)
    {
        String last =  contentsScanned.get().replace(lastScan,"");
        contentsScanned.set(last);
        nrOfScans.set(nrOfScans.get()-1);
    }


    //TODO: Processing of results returned by the scanner

}
package com.example.partnersincode.optimedia.ui.scanQRCode;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.partnersincode.optimedia.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;
import com.journeyapps.barcodescanner.ViewfinderView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * This is the fragment which  scans a QR code
 * A simple {@link Fragment} subclass.
 * Use the {@link qrScanAction#newInstance} factory method to
 * create an instance of this fragment.
 */
public class qrScanAction extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Permission request code
    int requestcode = 1678;

    //Request key for sending back QR
    String requestKey = "ScanQR";

    //UI reference
    DecoratedBarcodeView barcodeView;
    ViewfinderView viewfinderView;



    //Scanner objects
    CaptureManager capture;




    public qrScanAction() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment qrScanAction.
     */
    // TODO: Rename and change types and number of parameters
    public static qrScanAction newInstance(String param1, String param2) {
        qrScanAction fragment = new qrScanAction();
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
        View root =  inflater.inflate(R.layout.fragment_qr_scan_action, container, false);

        permissionCheck();
        getUIReferences(root);
//        setCaptureManager(root);
        setUpBarcodeView();



        return root;
    }

    /**
     * Check to see if we have camera permissions and asks if needed
     */
    private void permissionCheck()
    {
        //check if we can use the camera
        if(getActivity().checkSelfPermission (Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA))
            {
                displayPermissionRequestMessage("Permission Needed", "Camera permissions needed:", (dialogInterface,i)->{
                    getActivity().requestPermissions(new String[]{Manifest.permission.CAMERA},requestcode);
                });
            }
            else getActivity().requestPermissions(new String[]{Manifest.permission.CAMERA},requestcode);



        }

        //Check if the user allowed camera usage
        if(getActivity().checkSelfPermission (Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            //If camera usage not granted, send user back to previous screen
            Toast.makeText(getContext(), getString(R.string.noCameraPermission),Toast.LENGTH_LONG).show();

            Bundle bundle = new Bundle();

            bundle.putString("result",null);


            //TODO: See if passing null will work
            getParentFragmentManager().setFragmentResult(requestKey,bundle);


        }
    }

    private void getUIReferences(View view)
    {
        barcodeView = view.findViewById(R.id.A05000_barcodeView);

        viewfinderView = view.findViewById(R.id.A05000_viewFinderView);

    }


    private void setUpBarcodeView()
    {
        barcodeView.decodeContinuous(result -> {
            String output = result.getText();
            Bundle bundle = new Bundle();
            bundle.putString("result",output);
            getParentFragmentManager().setFragmentResult(requestKey,bundle);
            closeFragment();

        });
        Collection<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39);
        barcodeView.getBarcodeView().setDecoderFactory(new DefaultDecoderFactory(formats));
    }




    //region Overrides Required by the QR Scan library
    @Override
    public void onResume() {
        super.onResume();
        barcodeView.resume();


    }

    @Override
    public void onPause() {
        super.onPause();
        barcodeView.pause();
    }



    //endregion



    private void displayPermissionRequestMessage(String title, String message, @NotNull DialogInterface.OnClickListener onClick)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,getString(R.string.ok), onClick);
        alertDialog.show();
    }

    private void closeFragment()
    {
        getParentFragmentManager().popBackStack();
    }
}
package com.example.partnersincode.optimedia.ui;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.partnersincode.optimedia.Property;
import com.example.partnersincode.optimedia.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowQrCode#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowQrCode extends Fragment {

    //UI references:
    private ImageView QRImageView;
    private TextView labelScanQR;
    private TextView lblIndex;
    private Button prev;
    private Button next;

    //QR generation related fields
    QRGEncoder encoder;
    ArrayList<Bitmap> partitionedBitmaps;
    Property<Bitmap> currentQRBitmap = new Property<>();
    Property<Integer> index = new Property<>(-1);
    //TODO: Use standard datatype
    String QRContents;
    int screenWidth;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShowQrCode() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowQrCode.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowQrCode newInstance(String param1, String param2) {
        ShowQrCode fragment = new ShowQrCode();
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
        //Code for getting passed XML, placeholder for now
        //TODO: Have to set this to the applied datatype passed later
        Bundle bundle = this.getArguments();
        if(bundle!=null)
        {
            //TODO: Set key to correct name
            QRContents = bundle.getString("XML");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_show_qr_code, container, false);

        //Set up UI, listeners etc
        initialize(root);

        //process the text passed
        processInput();





        return root;
    }

    /**
     * Gets all of the UI elements, sets up binding and listeners
     * @param root view of Fragment
     */
    private void initialize(View root)
    {
        //Get UI elements
        QRImageView = root.findViewById(R.id.A03200_qrImage);
        labelScanQR = root.findViewById(R.id.A03200_lblScanToAddLibrary);
        lblIndex = root.findViewById(R.id.A03200_lblIndex);
        prev = root.findViewById(R.id.A03200_btnPrevious);
        next = root.findViewById(R.id.A03200_btnNext);


        //Get width of screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        //Get width of screen to be used for sizing of QR (passed to external library.
        screenWidth = displayMetrics.widthPixels;

        //Initialize arraylist
        partitionedBitmaps = new ArrayList<>();




        //setOnChange listener for current bitmap
        currentQRBitmap.addListener((property, oldValue, newValue) ->
        {
            QRImageView.setImageBitmap(newValue);
        });
        index.addListener((property, oldValue, newValue) -> {
            currentQRBitmap.set(partitionedBitmaps.get(newValue));

            //Set to disable button if at edge of array
            prev.setEnabled(!(newValue<=0));
            next.setEnabled(!(newValue>=partitionedBitmaps.size()-1));
            lblIndex.setText(getResources().getString(R.string.A03200_lblIndex,index.get()+1,partitionedBitmaps.size()));
        });

        //set on click listener for buttons
        prev.setOnClickListener(this::onPrevClicked);
        next.setOnClickListener(this::onNextClicked);
    }

    private void processInput()
    {
        int limit = 1046;
        //Check to see if input contents would exceed the size limit for a version 18 QR code
        if(QRContents.length()>limit)
        {
            for(int x = 0; x< QRContents.length();x+=limit)
            {
                if(QRContents.length() - x<limit)addQRBitmap(QRContents.substring(x));
                else addQRBitmap(QRContents.substring(x,limit+x));

            }
        }
        else{
            //Just process one big QR code
            addQRBitmap(QRContents);

        }
        index.set(0);
    }


    private void addQRBitmap(String contents)
    {
        //Create the bitmap and add it to the array of bitmaps
        try {
            encoder = new QRGEncoder(contents, null, QRGContents.Type.TEXT, screenWidth);
            partitionedBitmaps.add(encoder.encodeAsBitmap());
        }catch (Exception e) { e.printStackTrace();}
    }

    private void onNextClicked(View view)
    {
        index.set(index.get()+1);
    }

    private void onPrevClicked(View view)
    {
        index.set(index.get()-1);
    }


}
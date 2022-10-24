package com.example.partnersincode.optimedia.ui;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Library;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link editLibrary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class editLibrary extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "editLibrary";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Library library;

    public editLibrary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment editLibrary.
     */
    // TODO: Rename and change types and number of parameters
    public static editLibrary newInstance(String param1, String param2) {
        editLibrary fragment = new editLibrary();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            library = bundle.getParcelable("libraryInfo");
            Log.d(TAG, "onCreate: library passed: " +library.getLibraryName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_library, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        TextView edtTxtEditLibName = rootView.findViewById(R.id.edtTxtEditLibName);
        edtTxtEditLibName.setText(library.getLibraryName());
        TextView txtLibraryType = rootView.findViewById(R.id.txtLibraryType);
        txtLibraryType.setText(library.getLibraryType());

        //Create alert box
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setCancelable(true);
        builder.setTitle("Title");
        builder.setMessage("Are you sure you want to delete "+library.getLibraryName()+"?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteLibrary(library.getLibraryID(), library.getLibraryType());
                        Navigation.findNavController(rootView).navigate(R.id.nav_home);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        Button btnDeleteLibrary = rootView.findViewById(R.id.btnDeleteLibrary);
        btnDeleteLibrary.setOnClickListener(view -> {
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        Button btnEditSave = rootView.findViewById(R.id.btnEditSave);
        btnEditSave.setOnClickListener(view -> {
            String newName = edtTxtEditLibName.getText().toString();
            dbHandler.updateLibrary(library.getLibraryID(), newName);
            library.setLibraryName(newName);
            getActivity().onBackPressed();
        });

        return rootView;
    }
}
package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Booklog;

public class createBooklog extends Fragment {
    private Booklog editBL;
    private int bookID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        Boolean bEdit;
        if (bundle.getString("Intent") == "Edit") {
            editBL = bundle.getParcelable("blInfo");
            bEdit = true;
        } else {
           bookID = bundle.getInt("blBookID");
            bEdit = false;
        }

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.add_logtobook_fragment, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        TextView edtTitle = rootView.findViewById(R.id.edtBLTitle);
        TextView edtNote = rootView.findViewById(R.id.edtBLNote);
        TextView edtPN = rootView.findViewById(R.id.edtBLPN);
        Button btnNewBL = rootView.findViewById(R.id.btnAddBookLog);

        if (bEdit) {
            btnNewBL.setText("Edit Booklog");
            edtTitle.setText(editBL.getBlTitle());
            edtNote.setText(editBL.getBlNote());
            edtPN.setText(String.valueOf(editBL.getBlPageNumber()));
            btnNewBL.setOnClickListener(view -> {
                String title = edtTitle.getText().toString();
                String note = edtNote.getText().toString();
                int pagenumber = Integer.parseInt(edtPN.getText().toString());
                dbHandler.updateBooklog(editBL.getBL_ID(), title, note, pagenumber);

                Toast.makeText(this.getContext(), "Booklog named "+title+" edited" , Toast.LENGTH_LONG).show();
                edtTitle.setText("");
                edtNote.setText("");
                edtPN.setText("");
                getActivity().onBackPressed();
            });
        } else {
            btnNewBL.setOnClickListener(view -> {
                String title = edtTitle.getText().toString();
                String note = edtNote.getText().toString();
                int pagenumber = Integer.parseInt(edtPN.getText().toString());
                dbHandler.addBooklog(bookID,title,note,pagenumber);

                Toast.makeText(this.getContext(), "Booklog named '"+title+"' added", Toast.LENGTH_LONG).show();
                edtTitle.setText("");
                edtNote.setText("");
                edtPN.setText("");
                getActivity().onBackPressed();
            });
        }

        return rootView;
    }
}
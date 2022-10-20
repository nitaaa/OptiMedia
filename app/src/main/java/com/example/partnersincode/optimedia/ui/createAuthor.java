package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Author;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link createAuthor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class createAuthor extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Author editAuthor;
    private Boolean editing;
    private static final String TAG = "CreateAuthor";

    public createAuthor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment createAuthor.
     */
    // TODO: Rename and change types and number of parameters
    public static createAuthor newInstance(String param1, String param2) {
        createAuthor fragment = new createAuthor();
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
            editAuthor = bundle.getParcelable("authorInfo");
            editing = true;
            Log.d(TAG, "onCreate: author passed: "+editAuthor.getFullName());
        } else {
            editing = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_author, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        TextView edtxtName = rootView.findViewById(R.id.edtxtName);
        TextView edtxtSurname = rootView.findViewById(R.id.edtxtSurname);
        Button btnAddNewAuthor = rootView.findViewById(R.id.btnSave);

        if (editing){
            btnAddNewAuthor.setText("Update Author");
            edtxtName.setText(editAuthor.getAuthorName());
            edtxtSurname.setText(editAuthor.getAuthorSurname());
            btnAddNewAuthor.setOnClickListener(view -> {
                editAuthor.setAuthorName(edtxtName.getText().toString());
                editAuthor.setAuthorSurname(edtxtSurname.getText().toString());

                //TODO: query is probably the problem here
                dbHandler.updateAuthor(editAuthor);
                Toast.makeText(this.getContext(), "Author Edited: " + editAuthor.getFullName(), Toast.LENGTH_LONG).show();
            });
        } else {
            btnAddNewAuthor.setOnClickListener(view -> {
                Author newAuthor = new Author();
                newAuthor.setAuthorName(edtxtName.getText().toString());
                newAuthor.setAuthorSurname(edtxtSurname.getText().toString());

                int i = dbHandler.createNewAuthor(newAuthor);
                Log.d(TAG, "Author Added:" + newAuthor.getAuthorName() + " " + newAuthor.getAuthorSurname());

                if (i > 0){
                    Toast.makeText(this.getContext(), "Author Added: " + newAuthor.getAuthorName()
                            + " " + newAuthor.getAuthorSurname(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this.getContext(), "Unable to add author.", Toast.LENGTH_LONG).show();
                }

                edtxtName.setText("");
                edtxtSurname.setText("");

                edtxtName.requestFocus();

            });
        }

        return rootView;
    }
}
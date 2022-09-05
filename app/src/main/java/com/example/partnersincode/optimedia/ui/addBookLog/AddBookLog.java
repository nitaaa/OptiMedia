package com.example.partnersincode.optimedia.ui.addBookLog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.MainActivity;
import com.example.partnersincode.optimedia.R;

public class AddBookLog extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
      //  bookID = getArguments().getString("bookID");//gets data from activity that called fragment
        String bookID="1";//test data

        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        View view = inflater.inflate(R.layout.add_logtobook_fragment,container, false);
        Button button = view.findViewById(R.id.btnAddBookLog);
        button.setOnClickListener(v->
        {
                String Title = ((EditText) view.findViewById(R.id.edtBLTitle)).getText().toString();
                String Note = ((EditText)  view.findViewById(R.id.edtBLNote)).getText().toString();
            int PageNumber =Integer.parseInt(((EditText)  view.findViewById(R.id.edtBLPN)).getText().toString());

                dbHandler.addLogToBook("1",Title,Note,PageNumber);
            Toast.makeText(this.getContext(),"Added bookmark",
                    Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        });
        return view;
    }



}

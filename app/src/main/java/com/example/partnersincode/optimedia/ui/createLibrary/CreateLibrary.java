package com.example.partnersincode.optimedia.ui.createLibrary;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.ui.gallery.GalleryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreateLibrary extends Fragment {

    private CreateLibraryViewModel mViewModel;

    public static CreateLibrary newInstance() {
        return new CreateLibrary();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        View view = inflater.inflate(R.layout.create_library_fragment,container, false);
        Button button = (Button) view.findViewById(R.id.btnCreate);
        button.setOnClickListener(v -> {
            //TODO: create library in DB here, move to next screen
            //https://stackoverflow.com/questions/40562226/how-can-i-navigate-from-fragment-to-fragment-on-button-click
            String libName = ((EditText) view.findViewById(R.id.edtTxtLibName)).getText().toString();
            int selectedType = ((RadioGroup) view.findViewById(R.id.radioType)).getCheckedRadioButtonId();
            String libType = ((RadioButton) view.findViewById(selectedType)).getText().toString();

            dbHandler.createLibrary(libName, libType);
            
            Toast.makeText(this.getContext(), libName + " added.", Toast.LENGTH_SHORT).show();
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CreateLibraryViewModel.class);
        // TODO: Use the ViewModel ?default
    }

}
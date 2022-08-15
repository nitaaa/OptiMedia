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

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.ui.gallery.GalleryViewModel;

public class CreateLibrary extends Fragment {

    private CreateLibraryViewModel mViewModel;

    public static CreateLibrary newInstance() {
        return new CreateLibrary();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.create_library_fragment,container, false);
        Button button = (Button) view.findViewById(R.id.btnCreate);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //create library in DB here, move to next screen
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CreateLibraryViewModel.class);
        // TODO: Use the ViewModel
    }

}
package com.example.partnersincode.optimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.partnersincode.optimedia.R;

public class AddToWatchLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_watch_library);


    }

    public void onSearchClicked(View view)
    {
        //Get search text from UI
        EditText searchField = findViewById(R.id.A02310_searchField);

        String searchTerm = searchField.getText().toString();

        //Do search using another use case (A07000)


    }
}
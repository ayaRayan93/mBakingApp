package com.example.ayaali.bakingapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ayaali.bakingapp.R;
import com.example.ayaali.bakingapp.fragments.DetailsFragment;

/**
 * Created by AyaAli on 02/05/2017.
 */

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (savedInstanceState == null)
        {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putSerializable("stepDetailID",
                    getIntent().getSerializableExtra("stepDetailID"));
            DetailsFragment fragment = new DetailsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container, fragment)
                    .commit();
        }


    }
}

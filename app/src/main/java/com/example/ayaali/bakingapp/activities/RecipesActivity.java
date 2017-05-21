package com.example.ayaali.bakingapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ayaali.bakingapp.R;
import com.example.ayaali.bakingapp.fragments.StepsFragment;

/**
 * Created by AyaAli on 02/05/2017.
 */

public class RecipesActivity extends AppCompatActivity {

    public static boolean mTwoPane;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        if (findViewById(R.id.detail_container) != null)
        {
            mTwoPane = true;

        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putSerializable("recipeID",
                    getIntent().getSerializableExtra("recipeID"));

            StepsFragment stepsfragment = new StepsFragment();
            stepsfragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.steps_container, stepsfragment).commit();
        }
    }
}

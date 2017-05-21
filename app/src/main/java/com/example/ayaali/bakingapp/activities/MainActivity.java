package com.example.ayaali.bakingapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ayaali.bakingapp.R;
import com.example.ayaali.bakingapp.fragments.RecipesListFragment;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecipesListFragment recipesListFragment =new RecipesListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.item_container, recipesListFragment).commit();

    }


}

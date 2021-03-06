package com.example.ayaali.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayaali.bakingapp.R;
import com.example.ayaali.bakingapp.activities.DetailsActivity;
import com.example.ayaali.bakingapp.activities.RecipesActivity;
import com.example.ayaali.bakingapp.fragments.DetailsFragment;
import com.example.ayaali.bakingapp.models.Recipe;
import com.example.ayaali.bakingapp.models.Step;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by AyaAli on 02/05/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder>  {
    public static List<Step> DataSet;
    private static Context context;

    public StepsAdapter(Context cont,List<Step> dataSet)
    {
        context=cont;
        DataSet = dataSet;

    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.description)TextView title;


        public ViewHolder(View v)
        {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getPosition() + " clicked.");
                    if (RecipesActivity.mTwoPane)
                    {
                        Bundle arguments = new Bundle();
                        arguments.putSerializable("stepDetailID", DataSet.get(getPosition()));

                        DetailsFragment fragment = new DetailsFragment();
                        fragment.setArguments(arguments);
                        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.detail_container, fragment)
                                .commit();
                    }
                    else
                    {
                        Context context2 = v.getContext();
                        Intent intent = new Intent(context2, DetailsActivity.class);
                        intent.putExtra("stepDetailID",  DataSet.get(getPosition()));
                        context2.startActivity(intent);
                    }
                }
            });

            ButterKnife.bind(this,v);

        }



        public TextView getTitle() {
            return title;
        }


    }

    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.steps_card, parent, false);

        return  new StepsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final StepsAdapter.ViewHolder holder, int position)
    {
        if (DataSet.get(position) != null)
        {
            Log.d("", "steps " + position + " set.");
           holder.getTitle().setText(DataSet.get(position).getShortDescription());
        }
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }
}

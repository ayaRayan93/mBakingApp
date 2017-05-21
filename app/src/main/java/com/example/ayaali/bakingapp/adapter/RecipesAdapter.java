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
import com.example.ayaali.bakingapp.models.Recipe;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by AyaAli on 28/04/2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private List<Recipe> DataSet;
    private static Context context;

    public RecipesAdapter(Context cont,List<Recipe> dataSet)
    {
        context=cont;
        DataSet = dataSet;

    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.image)ImageView poster;
        @BindView(R.id.title)TextView title;


        public ViewHolder(View v)
        {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context mContext=v.getContext();
                    Intent intent=new Intent("recipesActivity");

                    intent.putExtra("recipeID",DataSet.get(getPosition()));
                    mContext.startActivity(intent);

                }
            });

            ButterKnife.bind(this,v);

        }

        public ImageView getPoster() {
            return poster;
        }

        public TextView getTitle() {
            return title;
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent, false);

        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        if (DataSet.get(position) != null)
        {
            Log.d("", "Element " + position + " set.");
            holder.getTitle().setText(DataSet.get(position).getName());


            // Feed image


        }
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }
}

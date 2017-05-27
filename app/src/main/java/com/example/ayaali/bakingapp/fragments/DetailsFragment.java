package com.example.ayaali.bakingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.ayaali.bakingapp.R;
import com.example.ayaali.bakingapp.adapter.StepsAdapter;
import com.example.ayaali.bakingapp.models.Recipe;
import com.example.ayaali.bakingapp.models.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;
import java.util.logging.LogRecord;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AyaAli on 02/05/2017.
 */

public class DetailsFragment extends Fragment {

    SimpleExoPlayer player;
    String mURI;
    private int currentWindow;
    private long playbackPosition;
    private boolean playWhenReady;
    @BindView(R.id.floatingActionButton)FloatingActionButton mFloatingActionButton;
    @BindView(R.id.stepDescription)
    TextView stepDES;
    @BindView(R.id.button)Button nextStep;
    Step step;
    @BindView(R.id.player)SimpleExoPlayerView playerView;
    List<Step>lStep= StepsAdapter.DataSet;
    int count;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);



        if (getArguments().containsKey("stepDetailID")) {
            step = (Step) getArguments().getSerializable("stepDetailID");
            count=step.getId()-1;
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.step_details, container, false);
        ButterKnife.bind(this, view);




        mURI=step.getVideoURL();
        stepDES.setText(step.getDescription());

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("PlayerActivity");
                intent.putExtra("movieURI",mURI);
                Context context=v.getContext();
                context.startActivity(intent);
            }
        });
    nextStep.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(count<lStep.size()-1) {
                step = lStep.get(++count);
                stepDES.setText(step.getDescription());
                mURI=step.getVideoURL();
                initializePlayer(mURI);
            }else{
                count=0;
            }
        }
});
        return view;
    }
    private void initializePlayer(String movieUri) {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);

        Uri uri = Uri.parse(movieUri);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory("ua"),
                new DefaultExtractorsFactory(), null, null);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer(mURI);
        }
    }
    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }
}

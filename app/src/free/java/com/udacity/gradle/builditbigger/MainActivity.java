package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tom.myapplication.jokebackend.myApi.model.JokeWrapper;
import com.github.ppartisan.jokeviewer.JokeViewActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.lang.ref.WeakReference;

import static com.udacity.gradle.builditbigger.EndpointsAsyncTask.DOCTOR_DOCTOR_JOKE;

public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.OnJokeReady {

    private static final String TEST_INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";

    private InterstitialAd mInterstitialAd;
    private EndpointsAsyncTask task = null;
    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(TEST_INTERSTITIAL_AD_UNIT_ID);
        mInterstitialAd.setAdListener(new AdListener());
        mInterstitialAd.loadAd(requestNewInterstitial());

        mProgressView = findViewById(R.id.progress);
        mProgressView.setVisibility(View.GONE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mProgressView.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mProgressView.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            launchEndPointsAsyncTask(DOCTOR_DOCTOR_JOKE);
        }
    }

    @Override
    public void onJokeReady(JokeWrapper wrapper) {
        Intent jokeViewActivityIntent = JokeViewActivity.buildJokeViewActivityIntent(
                this, wrapper.getParagraphedJokeWithActors()
        );
        mProgressView.setVisibility(View.GONE);
        task = null;
        startActivity(jokeViewActivityIntent);
    }

    private void launchEndPointsAsyncTask(@EndpointsAsyncTask.JokeType int jokeType, String... actors) {
        if (task == null) {
            task = new EndpointsAsyncTask(new WeakReference<EndpointsAsyncTask.OnJokeReady>(this), jokeType);
            task.execute(actors);
            mProgressView.setVisibility(View.VISIBLE);
        }
    }

    private AdRequest requestNewInterstitial() {
        return new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
    }

    private class AdListener extends com.google.android.gms.ads.AdListener {
        @Override
        public void onAdClosed() {
            launchEndPointsAsyncTask(DOCTOR_DOCTOR_JOKE, "Tom", "Someone less cool than Tom");
        }
    }

}
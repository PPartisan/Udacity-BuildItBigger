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
import com.udacity.gradle.builditbigger.data.NameModel;
import com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask.JokeType;
import com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask.OnJokeReady;
import com.udacity.gradle.builditbigger.data.FetchNameModelsTask;
import com.udacity.gradle.builditbigger.data.FetchNameModelsTask.Callbacks;
import com.udacity.gradle.builditbigger.endpoint.EndpointsUtils;
import com.udacity.gradle.builditbigger.util.AppUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask.KNOCK_KNOCK_JOKE;

public class MainActivity extends AppCompatActivity implements OnJokeReady, Callbacks {

    private InterstitialAd mInterstitialAd;
    private EndpointsAsyncTask task = null;
    private View mProgressView;

    private AddNamesDialogFragment mAddNamesDialog;

    private ArrayList<NameModel> mNameModels = new ArrayList<>();
    private FetchNameModelsTask fetchNameModelsTask = null;

    //No option to change joke type on free version
    private final @JokeType int mJokeType = KNOCK_KNOCK_JOKE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(DebugKeys.TEST_INTERSTITIAL_AD_UNIT_ID);
        mInterstitialAd.setAdListener(new AdListener());
        mInterstitialAd.loadAd(requestNewInterstitial());

        mProgressView = findViewById(R.id.progress);
        mProgressView.setVisibility(View.GONE);

        if(fetchNameModelsTask == null) {
            fetchNameModelsTask = new FetchNameModelsTask(new WeakReference<Callbacks>(this));
            fetchNameModelsTask.execute(this);
        }

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

        switch (id) {
            case R.id.action_add_names:
                launchAddNamesDialog(mNameModels);
                break;
            case R.id.action_switch_joke_type:
                AppUtils.buildUpgradeToViewDoctorDoctorJokesToast(this).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            launchEndPointsAsyncTask(mJokeType, NameModel.namesToStringArray(mNameModels));
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

    @Override
    public void onJokeRetrievalError() {
        EndpointsUtils.buildRetrievalErrorToast(this).show();
        mProgressView.setVisibility(View.GONE);
        task = null;
    }

    private void launchEndPointsAsyncTask(@JokeType int jokeType, String... actors) {
        if (task == null) {
            task = new EndpointsAsyncTask(new WeakReference<OnJokeReady>(this), jokeType);
            task.execute(actors);
            mProgressView.setVisibility(View.VISIBLE);
        }
    }

    private AdRequest requestNewInterstitial() {
        AdRequest.Builder builder = new AdRequest.Builder();
        builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
        if (!DebugKeys.EXTRA_TEST_DEVICE_ID.equals("null")) {
            builder.addTestDevice(DebugKeys.EXTRA_TEST_DEVICE_ID);
        }
        return builder.build();
    }

    @Override
    public void onNameModelsReady(ArrayList<NameModel> nameModels) {
        mNameModels = nameModels;
        if(mAddNamesDialog != null) mAddNamesDialog.setNameModels(nameModels);
    }

    private class AdListener extends com.google.android.gms.ads.AdListener {
        @Override
        public void onAdClosed() {
            launchEndPointsAsyncTask(mJokeType, NameModel.namesToStringArray(mNameModels));
        }
    }

    private void launchAddNamesDialog(ArrayList<NameModel> nameModels) {

        mAddNamesDialog = (AddNamesDialogFragment)
                getSupportFragmentManager().findFragmentByTag(AddNamesDialogFragment.TAG);

        if (mAddNamesDialog == null || mAddNamesDialog.isHidden()) {
            mAddNamesDialog = AddNamesDialogFragment.newInstance(nameModels);
            mAddNamesDialog.show(getSupportFragmentManager(), AddNamesDialogFragment.TAG);
        }
    }


}
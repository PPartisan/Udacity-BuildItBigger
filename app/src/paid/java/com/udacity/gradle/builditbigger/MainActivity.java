package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tom.myapplication.jokebackend.myApi.model.JokeWrapper;
import com.github.ppartisan.jokeviewer.JokeViewActivity;
import com.udacity.gradle.builditbigger.data.FetchNameModelsTask;
import com.udacity.gradle.builditbigger.data.FetchNameModelsTask.Callbacks;
import com.udacity.gradle.builditbigger.data.NameModel;
import com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask.JokeType;
import com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask.OnJokeReady;
import com.udacity.gradle.builditbigger.endpoint.EndpointsUtils;
import com.udacity.gradle.builditbigger.util.AppUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask.DOCTOR_DOCTOR_JOKE;
import static com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask.KNOCK_KNOCK_JOKE;

public class MainActivity extends AppCompatActivity implements OnJokeReady, Callbacks {

    private EndpointsAsyncTask endpointsAsyncTask = null;
    private View mProgressView;

    private ArrayList<NameModel> mNameModels;
    private FetchNameModelsTask fetchNameModelsTask = null;

    private AddNamesDialogFragment mAddNamesDialog;

    private static final String JOKE_TYPE_KEY = "joke_type_key";
    private @JokeType
    int mJokeType = KNOCK_KNOCK_JOKE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(fetchNameModelsTask == null) {
            fetchNameModelsTask = new FetchNameModelsTask(new WeakReference<Callbacks>(this));
            fetchNameModelsTask.execute(this);
        }

        mProgressView = findViewById(R.id.progress);
        mProgressView.setVisibility(View.GONE);

        if (savedInstanceState != null) {
            mJokeType = savedInstanceState.getInt(JOKE_TYPE_KEY, mJokeType);
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(JOKE_TYPE_KEY, mJokeType);
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
                mJokeType = (mJokeType == DOCTOR_DOCTOR_JOKE)
                        ? KNOCK_KNOCK_JOKE
                        : DOCTOR_DOCTOR_JOKE;
                AppUtils.buildJokeTypeChangeToast(this, mJokeType).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        launchEndPointsAsyncTask(mJokeType, NameModel.namesToStringArray(mNameModels));
    }

    @Override
    public void onJokeReady(JokeWrapper wrapper) {
        Intent jokeViewActivityIntent = JokeViewActivity.buildJokeViewActivityIntent(
                this, wrapper.getParagraphedJokeWithActors()
        );
        mProgressView.setVisibility(View.GONE);
        endpointsAsyncTask = null;
        startActivity(jokeViewActivityIntent);
    }

    @Override
    public void onJokeRetrievalError() {
        EndpointsUtils.buildRetrievalErrorToast(this).show();
        mProgressView.setVisibility(View.GONE);
        endpointsAsyncTask = null;
    }

    private void launchEndPointsAsyncTask(@JokeType int jokeType, String... actors) {
        if (endpointsAsyncTask == null) {
            endpointsAsyncTask = new EndpointsAsyncTask(new WeakReference<OnJokeReady>(this), jokeType);
            endpointsAsyncTask.execute(actors);
            mProgressView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onNameModelsReady(ArrayList<NameModel> nameModels) {
        mNameModels = nameModels;
        if(mAddNamesDialog != null) mAddNamesDialog.setNameModels(nameModels);
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

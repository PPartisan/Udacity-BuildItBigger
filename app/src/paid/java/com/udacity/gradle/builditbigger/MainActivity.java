package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tom.myapplication.jokebackend.myApi.model.JokeWrapper;
import com.github.ppartisan.jokeviewer.JokeViewActivity;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask.JokeType;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask.OnJokeReady;

import java.lang.ref.WeakReference;

import static com.udacity.gradle.builditbigger.EndpointsAsyncTask.DOCTOR_DOCTOR_JOKE;
import static com.udacity.gradle.builditbigger.EndpointsAsyncTask.KNOCK_KNOCK_JOKE;

public class MainActivity extends AppCompatActivity implements OnJokeReady {

    private EndpointsAsyncTask task = null;
    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        launchEndPointsAsyncTask(DOCTOR_DOCTOR_JOKE, "Robert Mugabe", "An Irishman", "Julian Barrett");
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

    private void launchEndPointsAsyncTask(@JokeType int jokeType, String... actors) {
        if (task == null) {
            task = new EndpointsAsyncTask(new WeakReference<OnJokeReady>(this), jokeType);
            task.execute(actors);
            mProgressView.setVisibility(View.VISIBLE);
        }
    }

}

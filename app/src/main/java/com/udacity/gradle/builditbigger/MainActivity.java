package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.JokeSupplier;
import com.example.JokeSupplier.Factory;
import com.example.JokeUtils;

import com.github.ppartisan.jokeviewer.JokeViewActivity;

import static com.example.JokeSupplier.JokeCategory.*;


public class MainActivity extends AppCompatActivity {

    private JokeSupplier supplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        supplier = Factory.getJokeSupplier(KNOCK_KNOCK);

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
        final String jokeString = JokeUtils.toParagraphWithActors(supplier.getJoke(), "Amy", "Jez");
        Intent jokeActivityIntent = JokeViewActivity.buildJokeViewActivityIntent(this, jokeString);
        startActivity(jokeActivityIntent);
    }

}

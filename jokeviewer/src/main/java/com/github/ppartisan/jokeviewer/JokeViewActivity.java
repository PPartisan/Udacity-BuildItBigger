package com.github.ppartisan.jokeviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class JokeViewActivity extends AppCompatActivity {

    public static final String JOKE_STRING_EXTRA = "joke_extra";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, JokeViewFragment.newInstance(getJokeStringExtra()))
                    .commit();
        }

    }

    private String getJokeStringExtra() {
        return getIntent().getStringExtra(JOKE_STRING_EXTRA);
    }

    public static Intent buildJokeViewActivityIntent(Context context, String joke) {
        Intent intent = new Intent(context, JokeViewActivity.class);
        intent.putExtra(JOKE_STRING_EXTRA, joke);
        return intent;
    }

}

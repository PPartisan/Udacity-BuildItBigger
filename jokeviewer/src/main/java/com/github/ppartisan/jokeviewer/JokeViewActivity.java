package com.github.ppartisan.jokeviewer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeViewActivity extends AppCompatActivity {

    public static final String JOKE_STRING_EXTRA = "joke_extra";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_activity);

        String joke = getIntent().getStringExtra(JOKE_STRING_EXTRA);

        TextView textView = (TextView) findViewById(R.id.tv_joke);
        Typeface robotoLight = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        textView.setTypeface(robotoLight);
        textView.setText(joke);

    }

    public static Intent buildJokeViewActivityIntent(Context context, String joke) {
        Intent intent = new Intent(context, JokeViewActivity.class);
        intent.putExtra(JOKE_STRING_EXTRA, joke);
        return intent;
    }

}

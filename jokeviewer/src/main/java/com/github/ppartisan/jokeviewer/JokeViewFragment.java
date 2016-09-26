package com.github.ppartisan.jokeviewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public final class JokeViewFragment extends Fragment {

    public static JokeViewFragment newInstance(String joke) {

        Bundle args = new Bundle();
        args.putString(JokeViewActivity.JOKE_STRING_EXTRA, joke);

        JokeViewFragment fragment = new JokeViewFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_joke, container, false);

        TextView jokeText = (TextView) root.findViewById(R.id.joke_text);
        jokeText.setText(getJoke());

        return root;
    }

    private String getJoke() {
        return getArguments().getString(JokeViewActivity.JOKE_STRING_EXTRA);
    }

}

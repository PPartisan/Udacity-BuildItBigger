package com.udacity.gradle.builditbigger.util;

import android.content.Context;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask.JokeType;

import static com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask.DOCTOR_DOCTOR_JOKE;

public final class AppUtils {

    public static Toast buildJokeTypeChangeToast(Context context, @JokeType int jokeType) {

        final int messageId = (jokeType == DOCTOR_DOCTOR_JOKE)
                ? R.string.now_showing_dd_jokes : R.string.now_showing_kk_jokes;

        return Toast.makeText(context, messageId, Toast.LENGTH_LONG);

    }

}

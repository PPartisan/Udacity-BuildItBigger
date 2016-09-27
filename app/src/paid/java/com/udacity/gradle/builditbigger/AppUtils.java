package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask.JokeType;

public final class AppUtils {

    public static Toast buildJokeTypeChangeToast(Context context, @JokeType int jokeType) {

        final int messageId = (jokeType == EndpointsAsyncTask.DOCTOR_DOCTOR_JOKE)
                ? R.string.now_showing_dd_jokes : R.string.now_showing_kk_jokes;

        return Toast.makeText(context, messageId, Toast.LENGTH_LONG);

    }

}

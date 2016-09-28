package com.udacity.gradle.builditbigger.endpoint;

import android.os.AsyncTask;
import android.support.annotation.IntDef;

import com.example.tom.myapplication.jokebackend.myApi.model.JokeWrapper;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class EndpointsAsyncTask extends AsyncTask<String, Void, JokeWrapper> {

    @Retention(SOURCE)
    @IntDef({DOCTOR_DOCTOR_JOKE, KNOCK_KNOCK_JOKE})
    public @interface JokeType {}
    public static final int DOCTOR_DOCTOR_JOKE = 3050;
    public static final int KNOCK_KNOCK_JOKE = 3051;

    private final WeakReference<OnJokeReady> mWeakCallback;
    private final @JokeType int mJokeType;

    public EndpointsAsyncTask(WeakReference<OnJokeReady> contextWeakReference, @JokeType int jokeType) {
        this.mWeakCallback = contextWeakReference;
        mJokeType = jokeType;
    }

    @Override
    protected JokeWrapper doInBackground(String... strings) {

        JokeWrapper wrapper = null;

        try {
            List<String> names = Arrays.asList(strings);
            wrapper = getJokeWrapper(mJokeType, names);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wrapper;

    }

    @Override
    protected void onPostExecute(JokeWrapper result) {
        if (mWeakCallback.get() != null && result != null) {
            mWeakCallback.get().onJokeReady(result);
        } else if (mWeakCallback.get() != null && result == null) {
            mWeakCallback.get().onJokeRetrievalError();
        }
    }

    private static JokeWrapper getJokeWrapper(@JokeType int type, List<String> actors) throws IOException{
        if (type == DOCTOR_DOCTOR_JOKE) {
            return EndpointsUtils.getMyApiService().getDoctorDoctorJoke().setActors(actors).execute();
        } else {
            return EndpointsUtils.getMyApiService().getKnockKnockJoke().setActors(actors).execute();
        }
    }

    public interface OnJokeReady {
        void onJokeReady(JokeWrapper wrapper);
        void onJokeRetrievalError();
    }


}

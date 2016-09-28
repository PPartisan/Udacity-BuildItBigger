package com.udacity.gradle.builditbigger.data;

import android.content.Context;
import android.os.AsyncTask;

import com.udacity.gradle.builditbigger.util.DataUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public final class FetchNameModelsTask extends AsyncTask<Context, Void, ArrayList<NameModel>> {

    private WeakReference<Callbacks> mWeakCallbacks;

    public FetchNameModelsTask(WeakReference<Callbacks> weakCallbacks) {
        mWeakCallbacks = weakCallbacks;
    }

    @Override
    protected ArrayList<NameModel> doInBackground(Context... contexts) {
        return DataUtils.getNameModelsFromDatabase(contexts[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<NameModel> nameModels) {
        if (mWeakCallbacks.get() != null) {
            mWeakCallbacks.get().onNameModelsReady(nameModels);
        }
    }

    public interface Callbacks {
        void onNameModelsReady(ArrayList<NameModel> nameModels);
    }

}
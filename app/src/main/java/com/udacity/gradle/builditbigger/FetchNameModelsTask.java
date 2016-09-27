package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

class FetchNameModelsTask extends AsyncTask<Context, Void, ArrayList<NameModel>> {

    private WeakReference<Callbacks> mWeakCallbacks;

    FetchNameModelsTask(WeakReference<Callbacks> weakCallbacks) {
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

    interface Callbacks {
        void onNameModelsReady(ArrayList<NameModel> nameModels);
    }

}
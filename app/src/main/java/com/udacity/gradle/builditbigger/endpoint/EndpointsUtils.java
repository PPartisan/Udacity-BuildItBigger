package com.udacity.gradle.builditbigger.endpoint;

import android.content.Context;
import android.widget.Toast;

import com.example.tom.myapplication.jokebackend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.DebugKeys;
import com.udacity.gradle.builditbigger.R;


public final class EndpointsUtils {

    private static MyApi sMyApiService = null;

    private EndpointsUtils() { throw new AssertionError(); }

    static synchronized MyApi getMyApiService() {
        if (sMyApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),
                    null
            );
            builder.setRootUrl(DebugKeys.ENDPOINT_ROOT_URL);
            sMyApiService = builder.build();
        }
        return sMyApiService;
    }

    public static Toast buildRetrievalErrorToast(Context context) {
        return Toast.makeText(context, R.string.endpoint_on_joke_retrieval_error, Toast.LENGTH_LONG);
    }

}

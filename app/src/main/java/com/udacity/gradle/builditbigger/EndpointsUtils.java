package com.udacity.gradle.builditbigger;

import com.example.tom.myapplication.jokebackend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;


final class EndpointsUtils {

    private static final String ROOT_URL = "https://udacity-builditbigger-144513.appspot.com/_ah/api/";

    private static MyApi sMyApiService = null;

    private EndpointsUtils() { throw new AssertionError(); }

    static synchronized MyApi getMyApiService() {
        if (sMyApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),
                    null
            );
            builder.setRootUrl(ROOT_URL);
            sMyApiService = builder.build();
        }
        return sMyApiService;
    }

}

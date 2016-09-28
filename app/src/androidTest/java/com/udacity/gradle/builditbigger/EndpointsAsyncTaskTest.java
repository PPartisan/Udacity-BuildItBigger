package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.util.Log;

import com.example.tom.myapplication.jokebackend.myApi.model.JokeWrapper;
import com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;

import static com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask.*;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    private static final String TAG = EndpointsAsyncTaskTest.class.getSimpleName();

    private CountDownLatch signal = null;
    private JokeWrapper wrapper = null;

    @Before
    public void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Test
    public void testRetrieveJoke() throws Exception {

        OnJokeReady callback = new OnJokeReady() {
            @Override
            public void onJokeReady(JokeWrapper wrapper) {
                EndpointsAsyncTaskTest.this.wrapper = wrapper;
                EndpointsAsyncTaskTest.this.signal.countDown();
            }

            @Override
            public void onJokeRetrievalError() {
                Log.e(TAG, "Wrapper null. Occurs when not connected to network.");
                EndpointsAsyncTaskTest.this.signal.countDown();
            }
        };

        WeakReference<OnJokeReady> weakCallback = new WeakReference<>(callback);

        EndpointsAsyncTask task = new EndpointsAsyncTask(weakCallback, DOCTOR_DOCTOR_JOKE);
        task.execute();
        signal.await();
        Assert.assertNotNull(wrapper);
        Assert.assertFalse(TextUtils.isEmpty(wrapper.getParagraphedJoke()));
    }

    @After
    public void tearDown() throws Exception {
        signal.countDown();
    }

}

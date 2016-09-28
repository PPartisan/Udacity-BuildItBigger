package com.udacity.gradle.builditbigger;


public final class DebugKeys {

    private DebugKeys() { throw new AssertionError(); }

    /*
    Add Device ID for an additional testing device to view test ads in AdView.
    Ignored if value is "null".
    See {@link com.udacity.gradle.builditbigger.MainActivityFragment} in free build.
     */
    public static final String EXTRA_TEST_DEVICE_ID = "null";

    public static final String ENDPOINT_ROOT_URL =
            "https://udacity-builditbigger-144513.appspot.com/_ah/api/";

}

package com.udacity.gradle.builditbigger.util;

import android.content.Context;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.R;

public final class AppUtils {

    public static Toast buildUpgradeToViewDoctorDoctorJokesToast(Context context) {
        return Toast.makeText(context, R.string.upgrade_to_view_doctor_doctor_jokes, Toast.LENGTH_LONG);
    }

}

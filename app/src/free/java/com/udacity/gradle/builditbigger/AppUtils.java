package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.widget.Toast;

public final class AppUtils {

    public static Toast buildUpgradeToViewDoctorDoctorJokesToast(Context context) {
        return Toast.makeText(context, R.string.upgrade_to_view_doctor_doctor_jokes, Toast.LENGTH_LONG);

    }

}

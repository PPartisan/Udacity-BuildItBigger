/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.tom.myapplication.jokebackend;

import com.example.JokeSupplier;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;

import javax.inject.Named;

import static com.example.JokeSupplier.Factory.getJokeSupplier;
import static com.example.JokeSupplier.JokeCategory.DOCTOR_DOCTOR;
import static com.example.JokeSupplier.JokeCategory.KNOCK_KNOCK;

@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "jokebackend.myapplication.tom.example.com",
                ownerName = "jokebackend.myapplication.tom.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    private JokeSupplier supplier;

    @ApiMethod(name = "getDoctorDoctorJoke", path="get_doctor_doctor_joke")
    public JokeWrapper getDoctorDoctorJoke(@Named("actors") @Nullable String... actors) {
        supplier = getJokeSupplier(DOCTOR_DOCTOR);
        return new JokeWrapper(supplier.getJoke(), actors);
    }

    @ApiMethod(name = "getKnockKnockJoke", path="get_knock_knock_joke")
    public JokeWrapper getKnockKnockJoke(@Named("actors") @Nullable String... actors) {
        supplier = getJokeSupplier(KNOCK_KNOCK);
        return new JokeWrapper(supplier.getJoke(), actors);
    }

}

# Additional Notes

Submission for the fourth project in Udacity's Android Developer Nanodegree program, **Build It Bigger**.

* The `AdRequest` used for the `AdView` in the `free` flavor of this app only has the emulator added as a test device. To add a physical device, change the field [DebugKeys.EXTRA_TEST_DEVICE_ID](https://github.com/PPartisan/Udacity-BuildItBigger/blob/master/app/src/main/java/com/udacity/gradle/builditbigger/DebugKeys.java) from `"null"` to a device id, or add it manually to the `AdRequest.Builder` instances in [`MainActivity`](https://github.com/PPartisan/Udacity-BuildItBigger/blob/master/app/src/free/java/com/udacity/gradle/builditbigger/MainActivity.java) and [`MainActivityFragment`](https://github.com/PPartisan/Udacity-BuildItBigger/blob/master/app/src/free/java/com/udacity/gradle/builditbigger/MainActivityFragment.java).
* There is an option to switch between "Doctor Doctor" and "Knock Knock" jokes in the paid flavor only, via a button on the `Toolbar`.
* Both flavors offer the option to personalise jokes by adding custom names via a button on the `Toolbar`. 

# Gradle for Android and Java Final Project

In this project, you will create an app with multiple flavors that uses
multiple libraries and Google Cloud Endpoints. The finished app will consist
of four modules. A Java library that provides knockKnockJokes, a Google Cloud Endpoints
(GCE) project that serves those knockKnockJokes, an Android Library containing an
activity for displaying knockKnockJokes, and an Android app that fetches knockKnockJokes from the
GCE module and passes them to the Android Library for display.

## Why this Project

As Android projects grow in complexity, it becomes necessary to customize the
behavior of the Gradle build tool, allowing automation of repetitive tasks.
Particularly, factoring functionality into libraries and creating product
flavors allow for much bigger projects with minimal added complexity.

##What Will I Learn?

You will learn the role of Gradle in building Android Apps and how to use
Gradle to manage apps of increasing complexity. You'll learn to:

* Add free and paid flavors to an app, and set up your build to share code between them
* Factor reusable functionality into a Java library
* Factor reusable Android functionality into an Android library
* Configure a multi project build to compile your libraries and app
* Use the Gradle App Engine plugin to deploy a backend
* Configure an integration test suite that runs against the local App Engine development server

##How Do I Complete this Project?

### Step 0: Starting Point

This is the starting point for the final project, which is provided to you in
the [course repository](https://github.com/udacity/ud867/tree/master/FinalProject). It
contains an activity with a banner ad and a button that purports to tell a
joke, but actually just complains. The banner ad was set up following the
instructions here:

https://developers.google.com/mobile-ads-sdk/docs/admob/android/quick-start

You may need to download the Google Repository from the Extras section of the
Android SDK Manager.

When you can build an deploy this starter code to an emulator, you're ready to
move on.

### Step 1: Create a Java library

Your first task is to create a Java library that provides knockKnockJokes. Create a new
Gradle Java project either using the Android Studio wizard, or by hand. Then
introduce a project dependency between your app and the new Java Library. If
you need review, check out demo 4.01 from the course code.

Make the button display a toast showing a joke retrieved from your Java joke
telling library.

### Step 2: Create an Android Library

Create an Android Library containing an Activity that will display a joke
passed to it as an intent extra. Wire up project dependencies so that the
button can now pass the joke from the Java Library to the Android Library.

For review on how to create an Android library, check out demo 4.03. For a
refresher on intent extras, check out;

http://developer.android.com/guide/components/intents-filters.html

### Step 3: Create GCE Module

This next task will be pretty tricky. Instead of pulling knockKnockJokes directly from
our Java library, we'll set up a Google Cloud Endpoints development server,
and pull our knockKnockJokes from there. Follow the instructions in the following
tutorial to add a Google Could Endpoints module to your project:

https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints

Introduce a project dependency between your Java library and your GCE module,
and modify the GCE starter code to pull knockKnockJokes from your Java library. Create
an Async task to retrieve knockKnockJokes. Make the button kick off a task to retrieve a
joke, then launch the activity from your Android Library to display it.

### Step 4: Add Functional Tests

Add code to test that your Async task successfully retrieves a non-empty
string. For a refresher on setting up Android tests, check out demo 4.09.

### Step 5: Add a Paid Flavor

Add free and paid product flavors to your app. Remove the ad (and any
dependencies you can) from the paid flavor.

## Optional Tasks

For extra practice to make your project stand out, complete the following tasks.

### Add Interstitial Ad

Follow these instructions to add an interstitial ad to the free version.
Display the ad after the user hits the button, but before the joke is shown.

https://developers.google.com/mobile-ads-sdk/docs/admob/android/interstitial

### Add Loading Indicator

Add a loading indicator that is shown while the joke is being retrieved and
disappears when the joke is ready. The following tutorial is a good place to
start:

http://www.tutorialspoint.com/android/android_loading_spinner.htm

### Configure Test Task

To tie it all together, create a Gradle task that:

1. Launches the GCE local development server
2. Runs all tests
3. Shuts the server down again

# Rubric

### Required Components

* Project contains a Java library for supplying knockKnockJokes
* Project contains an Android library with an activity that displays knockKnockJokes passed to it as intent extras.
* Project contains a Google Cloud Endpoints module that supplies knockKnockJokes from the Java library. Project loads knockKnockJokes from GCE module via an async task.
* Project contains connected tests to verify that the async task is indeed loading knockKnockJokes.
* Project contains paid/free flavors. The paid flavor has no ads, and no unnecessary dependencies.

### Required Behavior

* App retrieves knockKnockJokes from Google Cloud Endpoints module and displays them via an Activity from the Android Library.

### Optional Components

Once you have a functioning project, consider adding more features to test your Gradle and Android skills. Here are a few suggestions:

* Make the free app variant display interstitial ads between the main activity and the joke-displaying activity.
* Have the app display a loading indicator while the joke is being fetched from the server.
* Write a Gradle task that starts the GCE dev server, runs all the Android tests, and shuts down the dev server.

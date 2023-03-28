package com.example.freeswitchandroid;

import android.app.Application;
import android.content.Intent;


public class App extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            Thread.setDefaultUncaughtExceptionHandler(
                    new Thread.UncaughtExceptionHandler() {
                        @Override
                        public void uncaughtException(Thread thread, Throwable e) {
                            handleUncaughtException(thread, e);
                        }
                    });
        }

    private void handleUncaughtException (Thread thread, Throwable e) {

        // The following shows what I'd like, though it won't work like this.
        Intent intent = new Intent(getApplicationContext(), CallsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        // Add some code logic if needed based on your requirement
    }
}
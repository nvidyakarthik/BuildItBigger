package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.jokedisplay.JokeActivity;

import java.io.IOException;

/**
 * Created by Vidya on 6/27/2016.
 */

public class EndpointsAsyncTask extends AsyncTask<OnJokeReceivedListener, Void, String> {
    private static MyApi myApiService = null;
    private OnJokeReceivedListener listener;

    @Override
    protected String doInBackground(OnJokeReceivedListener... params) {
        if (myApiService == null) {
            // testing code for local devappserver
            /*MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.1.14:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver*/
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://gcmbuilditbigger.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        listener = params[0];

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        /*Intent intent = new Intent(context, JokeActivity.class);


        intent.putExtra(JokeActivity.JOKE_KEY, result);
        context.startActivity(intent);*/
        listener.onReceived(result);

    }
}

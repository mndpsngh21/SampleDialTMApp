package com.dialtm.sample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import org.dialtm.sdk.Dialtm;
import org.dialtm.sdk.DialtmActivity;
import org.dialtm.sdk.DialtmConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Dialtm SDK conference configuration.
        URL serverURL;
        try {
            serverURL = new URL("https://meet.dialtm.in");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid server URL!");
        }
        DialtmConferenceOptions defaultOptions
                = new DialtmConferenceOptions.Builder()
                .setServerURL(serverURL)
                .setWelcomePageEnabled(false)
                .build();
        Dialtm.setDefaultConferenceOptions(defaultOptions);
    }

    public void onButtonClick(View v) {
        EditText editText = findViewById(R.id.conferenceName);
        String text = editText.getText().toString();

        if (text.length() > 0) {
            // Build options object for joining the conference. The SDK will merge the default
            // one we set earlier and this one when joining.
            DialtmConferenceOptions options
                    = new DialtmConferenceOptions.Builder()
                    .setRoom(text)
                    .build();
            // Launch the new activity with the given options. The launch() method takes care
            // of creating the required Intent and passing the options.
            DialtmActivity.launch(this, options);
        }
    }
}
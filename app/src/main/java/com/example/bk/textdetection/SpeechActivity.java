package com.example.bk.textdetection;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        speak();
    }

    private void speak() {
        try {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Xin mời nói");
            startActivityForResult(intent, 1000);
        } catch (ActivityNotFoundException e) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://market.android.com/details?id=com.google.android.googlequicksearchbox"));
            startActivity(browserIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        TextView txtView = findViewById(R.id.txtView);
        String text = "";
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                text = arrayList.get(0);
                if (text.equals("yes")) {
                    Intent moveMain = new Intent(SpeechActivity.this, MainActivity.class);
                    startActivity(moveMain);
                } else if (text.equals("no")) {
                    finish();
                } else if (text.equals("one") || text.equals("1")) {
                    Intent moveToAdmin = new Intent(SpeechActivity.this, AdminActivity.class);
                    startActivity(moveToAdmin);
                } else Toast.makeText(SpeechActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

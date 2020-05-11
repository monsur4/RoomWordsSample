package com.android.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetsAndRawTests extends AppCompatActivity {

    TextView textView;
    Button button;
    private StringBuilder textBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_and_raw_tests);

        textView = findViewById(R.id.textview_asset_raw);
        button = findViewById(R.id.button_asset_raw);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //using the asset manager
               /* AssetManager assetManager = getApplicationContext().getAssets();
                BufferedReader reader = null;
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open("file.txt"));
                    reader = new BufferedReader(inputStreamReader);
                    String lineText= reader.readLine();
                    while (lineText != null){
                        textBuilder.append(lineText);
                        textBuilder.append("\n");
                        lineText =reader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                textView.setText(textBuilder);*/

                //using raw directory -- using the Resource ID, called with 2 different ways
                /* 1) int id = getResources().getIdentifier("file", "raw", getPackageName());
                 InputStream inputStream = getResources().openRawResource(id);*/

                /* 2) InputStream inputStream = getResources().openRawResource(R.raw.file);

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(inputStreamReader);
                    String lineText = reader.readLine();
                    while (lineText != null) {
                        textBuilder.append(lineText);
                        textBuilder.append("\n");
                        lineText = reader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textView.setText(textBuilder);*/

               /* // using the uri to read the file from res/raw folder  --- The program didn't find the file, so this doesn't work
                String uri1 = "android.resource://" + getPackageName() + "/raw/file";
                String uri2 = "android.resource://" + getPackageName() + "/" + R.raw.file;
                File file = new File(uri2);
                BufferedReader reader = null;
                try {
                    InputStream fileStream = new FileInputStream(file);
                    InputStreamReader fileInputStreamReader = new InputStreamReader(fileStream);
                    reader = new BufferedReader(fileInputStreamReader);
                    String lineText = reader.readLine();
                    while (lineText != null) {
                        textBuilder.append(lineText);
                        textBuilder.append("\n");
                        lineText = reader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textView.setText(textBuilder);*/
            }
        });
    }
}

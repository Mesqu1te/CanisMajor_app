package ru.canismajor.brainwave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout courses = findViewById(R.id.main_linlay);

        try {
            File json = new File(getFilesDir(), "courses.json");
            FileInputStream fis = new FileInputStream(json);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String fileContent = sb.toString();
            LoadIntoListView(fileContent, courses);
            br.close();
            fis.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void LoadIntoListView(String json, LinearLayout linearLayout) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);

        String[] ids = new String[jsonArray.length()];
        String[] course_num = new String[jsonArray.length()];
        String[] course_name = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                ids[i] = object.getString("id");
                course_num[i] = object.getString("course_num");
                course_name[i] = object.getString("course_name");
            } catch (JSONException ignored) {}
        }

        FrameLayout.LayoutParams btnParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        final float scale = getResources().getDisplayMetrics().density;

        int leftMarginPx = (int) (0 * scale + 0.5f);
        int topMarginPx = (int) (0 * scale + 0.5f);
        int rightMarginPx = (int) (0 * scale + 0.5f);
        int bottomMarginPx = (int) (10 * scale + 0.5f);

        btnParams.setMargins(leftMarginPx, topMarginPx, rightMarginPx, bottomMarginPx);

        for (int i = 0; i < ids.length; i++) {
            final int j = i;
            View btn = (View) getLayoutInflater().inflate(R.layout.course_button, null);
            TextView text = btn.findViewById(R.id.course_button_text);
            text.setText(course_name[i]);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), TopicActivity.class);
                    intent.putExtra("course", course_num[j]);
                    startActivity(intent);
                }
            });

            btn.setLayoutParams(btnParams);
            linearLayout.addView(btn);
        }

    }
}
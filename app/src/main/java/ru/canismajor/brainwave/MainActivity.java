package ru.canismajor.brainwave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        LinearLayout courses = findViewById(R.id.main_linlay);

        for (int i = 1; i <= 15; i++) {
            View course = (View) getLayoutInflater().inflate(R.layout.course_button, null);

            TextView course_name = course.findViewById(R.id.course_button_text);
            ImageView course_icon = course.findViewById(R.id.course_button_icon);

            course_name.setText("Курс №" + i);

            course.setLayoutParams(btnParams);
            courses.addView(course);
        }
    }
}
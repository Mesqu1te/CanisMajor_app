package ru.canismajor.brainwave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        LinearLayout question_numbers = findViewById(R.id.test_question_numbers);

        FrameLayout.LayoutParams question_number_params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        final float scale = getResources().getDisplayMetrics().density;

        int leftMarginPx = (int) (0 * scale + 0.5f);
        int topMarginPx = (int) (0 * scale + 0.5f);
        int rightMarginPx = (int) (6 * scale + 0.5f);
        int bottomMarginPx = (int) (0 * scale + 0.5f);

        question_number_params.setMargins(leftMarginPx, topMarginPx, rightMarginPx, bottomMarginPx);

        for (int i = 1; i <= 24; i++) {
            View question_number = getLayoutInflater().inflate(R.layout.question_number, null);

            TextView num = question_number.findViewById(R.id.question_number_digit);

            question_number.setBackgroundColor(getResources().getColor(R.color.reseda_green));
            num.setText(String.valueOf(i));

            question_number.setLayoutParams(question_number_params);
            question_numbers.addView(question_number);
        }
    }
}
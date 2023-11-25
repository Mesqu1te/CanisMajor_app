package ru.canismajor.brainwave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    String course;
    String topic;

    ArrayList<Question> final_questions = new ArrayList<>();

    int right_answers = 0;

    int currentQuestion = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Bundle data = getIntent().getExtras();

        course = data.getString("course");

        topic = data.getString("topic");

        TextView name = findViewById(R.id.test_name);

        name.setText(data.getString("name"));

        try {
            File json = new File(getFilesDir(), "questions.json");
            FileInputStream fis = new FileInputStream(json);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String fileContent = sb.toString();
            LoadTest(fileContent);
            br.close();
            fis.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }

    void LoadTest (String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);

        Question[] questions = new Question[50];




        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                Question question = new Question();
                try{question.setId(object.getString("id"));}catch(JSONException e) {}

                try{question.setCourse_num(object.getString("course_num"));}catch(JSONException e) {}
                try{question.setTopic_num(object.getString("topic_num"));}catch(JSONException e) {}
                try{question.setQuestion_num(object.getString("question_num"));}catch(JSONException e) {}
                try{question.setType(object.getString("type"));}catch(JSONException e) {}
                try{question.setQuestion(object.getString("question"));}catch(JSONException e) {}
                try{question.setImage(object.getString("image"));}catch(JSONException e) {}
                try{question.setA(object.getString("a"));}catch(JSONException e) {}
                try{question.setB(object.getString("b"));}catch(JSONException e) {}
                try{question.setC(object.getString("c"));}catch(JSONException e) {}
                try{question.setD(object.getString("d"));}catch(JSONException e) {}
                try{question.setRight_answer(object.getString("right_answer"));}catch(JSONException e) {}

                questions[i] = question;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ShowQuestion(questions[1]);
    }

    void ShowQuestion(Question question)
    {

        View option = findViewById(R.id.test_option_view);
        TextView question_tv = option.findViewById(R.id.test_option_question);

        TextView a_tv = option.findViewById(R.id.test_option_a);
        TextView b_tv = option.findViewById(R.id.test_option_b);
        TextView c_tv = option.findViewById(R.id.test_option_c);
        TextView d_tv = option.findViewById(R.id.test_option_d);
        System.out.println(question.getQuestion());
        question_tv.setText(question.getQuestion());

        a_tv.setText(question.getA());
        b_tv.setText(question.getB());
        c_tv.setText(question.getC());
        d_tv.setText(question.getD());

        String right = question.getRight_answer();

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (right.equals("1")) {
                    a_tv.setBackgroundColor(getResources().getColor(R.color.teal));
                    b_tv.setBackgroundColor(getResources().getColor(R.color.pink));
                    c_tv.setBackgroundColor(getResources().getColor(R.color.pink));
                    d_tv.setBackgroundColor(getResources().getColor(R.color.pink));
                } else if (right.equals("2")) {
                    a_tv.setBackgroundColor(getResources().getColor(R.color.pink));
                    b_tv.setBackgroundColor(getResources().getColor(R.color.teal));
                    c_tv.setBackgroundColor(getResources().getColor(R.color.pink));
                    d_tv.setBackgroundColor(getResources().getColor(R.color.pink));
                } else if (right.equals("3")) {
                    a_tv.setBackgroundColor(getResources().getColor(R.color.pink));
                    b_tv.setBackgroundColor(getResources().getColor(R.color.pink));
                    c_tv.setBackgroundColor(getResources().getColor(R.color.teal));
                    d_tv.setBackgroundColor(getResources().getColor(R.color.pink));
                } else if (right.equals("4")) {
                    a_tv.setBackgroundColor(getResources().getColor(R.color.pink));
                    b_tv.setBackgroundColor(getResources().getColor(R.color.pink));
                    c_tv.setBackgroundColor(getResources().getColor(R.color.pink));
                    d_tv.setBackgroundColor(getResources().getColor(R.color.teal));
                }
            }
        };
        a_tv.setOnClickListener(click);
        b_tv.setOnClickListener(click);
        c_tv.setOnClickListener(click);
        d_tv.setOnClickListener(click);
    }

}
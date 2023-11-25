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

public class TestActivity extends AppCompatActivity {

    String course;
    String topic;

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

    void LoadTest (String json) throws JSONException{
        JSONArray jsonArray = new JSONArray(json);

        String[] ids = new String[jsonArray.length()];
        String[] course_nums = new String[jsonArray.length()];
        String[] topic_nums = new String[jsonArray.length()];
        String[] question_nums = new String[jsonArray.length()];
        String[] types = new String[jsonArray.length()];
        String[] questions = new String[jsonArray.length()];
        String[] images = new String[jsonArray.length()];
        String[] as = new String[jsonArray.length()];
        String[] bs = new String[jsonArray.length()];
        String[] cs = new String[jsonArray.length()];
        String[] ds = new String[jsonArray.length()];
        String[] right_answers = new String[jsonArray.length()];
        String[] a_alts = new String[jsonArray.length()];
        String[] b_alts = new String[jsonArray.length()];
        String[] c_alts = new String[jsonArray.length()];
        String[] d_alts = new String[jsonArray.length()];
        String[] pair_as = new String[jsonArray.length()];
        String[] pair_bs = new String[jsonArray.length()];
        String[] pair_cs = new String[jsonArray.length()];
        String[] pair_ds = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                ids[i] = object.getString("id");
                course_nums[i] = object.getString("course_num");
                topic_nums[i] =  object.getString("topic_num");
                question_nums[i] = object.getString("question_num");
                types[i] = object.getString("type");
                questions[i] = object.getString("question");
                images[i] = object.getString("image");
                as[i] = object.getString("a");
                bs[i] = object.getString("b");
                cs[i] = object.getString("c");
                ds[i] = object.getString("d");
                right_answers[i] = object.getString("right_answer");
                a_alts[i] = object.getString("a_alt");
                b_alts[i] = object.getString("b_alt");
                c_alts[i] = object.getString("c_alt");
                d_alts[i] = object.getString("d_alt");
                pair_as[i] = object.getString("pair_a");
                pair_bs[i] = object.getString("pair_b");
                pair_cs[i] = object.getString("pair_c");
                pair_ds[i] = object.getString("pair_d");
            } catch (JSONException ignored) {}
        }

        for (int i = 0; i < ids.length; i++) {
            if (types[i].equals("1")) { // выбор варианта
                View option = findViewById(R.id.test_option_view);
                TextView question = option.findViewById(R.id.test_option_question);

            }
        }
    }

    //void ShowQuestion(String id, String course_num, String topic_num, String question_num, String type,
    //                  String question, String image, String a, String b, String c, String d,
    //                  String right_answer, String a_alt, String b_alt, String c_alt, String d_alt,
    //                  String pair_a, String pair_b, String pair_c, String pair_d)
    //{
    //    if (type.equals("1")) {
    //        View option = findViewById(R.id.test_option_view);
    //        TextView question = option.findViewById(R.id.test_option_question);
    //        ImageView iv = option.findViewById(R.id.test_option_image);
    //        if (!image.equals("")) {
    //            Glide.with(this).load(image).into(iv);
    //        }
    //        TextView a_tv = option.findViewById(R.id.test_option_a);
    //        TextView b_tv = option.findViewById(R.id.test_option_b);
    //        TextView c_tv = option.findViewById(R.id.test_option_c);
    //        TextView d_tv = option.findViewById(R.id.test_option_d);
    //    }
    //}
}
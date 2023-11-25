package ru.canismajor.brainwave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread loadCourses = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.intervislab.ru/hackathon/coursesList.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    File file = new File(getFilesDir(), "courses.json");
                    FileOutputStream output = new FileOutputStream(file);
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                    output.close();
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        loadCourses.start();

        Thread loadTopics = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    loadCourses.join();
                    URL url = new URL("http://www.intervislab.ru/hackathon/topicsList.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    File file = new File(getFilesDir(), "topics.json");
                    FileOutputStream output = new FileOutputStream(file);
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                    output.close();
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        loadTopics.start();

        Thread loadQuestions = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    loadTopics.join();
                    URL url = new URL("http://www.intervislab.ru/hackathon/questionsList.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    File file = new File(getFilesDir(), "questions.json");
                    FileOutputStream output = new FileOutputStream(file);
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                    output.close();
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        loadQuestions.start();

        Thread startLoginActivity = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    loadQuestions.join();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        startLoginActivity.start();


    }
}
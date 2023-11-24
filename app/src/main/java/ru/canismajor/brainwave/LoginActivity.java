package ru.canismajor.brainwave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final int[] state = {0}; // 0 - auth, 1 - register

        TextView auth_button = findViewById(R.id.login_auth_button);
        TextView register_button = findViewById(R.id.login_register_button);

        EditText login = findViewById(R.id.login_login_field);
        EditText password = findViewById(R.id.login_password_field);
        EditText password_confirmation = findViewById(R.id.login_password_confirmation_field);

        TextView pass_confirmation_tv = findViewById(R.id.login_password_confirmation_text);

        TextView authenticate = findViewById(R.id.login_authenticate);

        auth_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state[0] = 0;
                auth_button.setBackgroundResource(R.drawable.rounded_left_french_gray_26dp);
                auth_button.setTextColor(getResources().getColor(R.color.onyx));
                register_button.setBackgroundResource(R.drawable.rounded_right_unchecked_french_gray_26dp);
                register_button.setTextColor(getResources().getColor(R.color.french_gray));
                pass_confirmation_tv.setVisibility(View.GONE);
                password_confirmation.setVisibility(View.GONE);
                authenticate.setText("Авторизоваться");
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state[0] = 1;
                auth_button.setBackgroundResource(R.drawable.rounded_left_unchecked_french_gray_26dp);
                auth_button.setTextColor(getResources().getColor(R.color.french_gray));
                register_button.setBackgroundResource(R.drawable.rounded_right_french_gray_26dp);
                register_button.setTextColor(getResources().getColor(R.color.onyx));
                pass_confirmation_tv.setVisibility(View.VISIBLE);
                password_confirmation.setVisibility(View.VISIBLE);
                authenticate.setText("Зарегистрироваться");
            }
        });

        authenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (state[0] == 0) {
                    String login_input = String.valueOf(login.getText());
                    String password_input = String.valueOf(password.getText());

                    if (password_input.isEmpty()) {
                        showErrorWindow(4, login, password, password_confirmation);
                    }
                    else if (password_input.length() < 8) {
                        showErrorWindow(1, login, password, password_confirmation);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                }
                else if(state[0] == 1) {
                    String login_input = String.valueOf(login.getText());
                    String password_input = String.valueOf(password.getText());
                    String password_confirmation_input = String.valueOf(password_confirmation.getText());

                    if (password_input.length() < 8) {
                        showErrorWindow(1, login, password, password_confirmation);
                    } else if (password_confirmation_input.isEmpty()) {
                        showErrorWindow(4, login, password, password_confirmation);
                    } else if (!password_input.equals(password_confirmation_input)) {
                        showErrorWindow(2, login, password, password_confirmation);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });


    }

    void showErrorWindow(int errorCode, EditText login, EditText pass, EditText pass_confirm) {
        View window = findViewById(R.id.login_error_window);
        TextView window_title = window.findViewById(R.id.error_window_title);
        TextView window_desc = window.findViewById(R.id.error_window_description);

        window_title.setText("Ошибка " + errorCode);
        if (errorCode == 1) {
            window_desc.setText("Пароль должен содержать не менее 8 символов.");
            login.setBackgroundResource(R.drawable.edittext_bordered);
            pass.setBackgroundResource(R.drawable.edittext_error_bordered);
            pass_confirm.setBackgroundResource(R.drawable.edittext_error_bordered);
        }
        else if (errorCode == 2) {
            window_desc.setText("Введённые пароли не совпадают");
            login.setBackgroundResource(R.drawable.edittext_bordered);
            pass.setBackgroundResource(R.drawable.edittext_error_bordered);
            pass_confirm.setBackgroundResource(R.drawable.edittext_error_bordered);
        }
        else if (errorCode == 3) {
            window_desc.setText("Произошла ошибка при отправке данных. Проверьте доступность Интернета.");
            login.setBackgroundResource(R.drawable.edittext_bordered);
            pass.setBackgroundResource(R.drawable.edittext_bordered);
            pass_confirm.setBackgroundResource(R.drawable.edittext_bordered);
        }
        else if (errorCode == 4) {
            window_desc.setText("Введите пароль ещё раз");
            login.setBackgroundResource(R.drawable.edittext_bordered);
            pass.setBackgroundResource(R.drawable.edittext_bordered);
            pass_confirm.setBackgroundResource(R.drawable.edittext_error_bordered);
        }
        window.setVisibility(View.VISIBLE);
    }
}
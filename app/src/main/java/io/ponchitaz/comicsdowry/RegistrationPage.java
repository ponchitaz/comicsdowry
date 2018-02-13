package io.ponchitaz.comicsdowry;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * This is the class for registration activity.
 */

public class RegistrationPage extends AppCompatActivity implements NavFragment.OnFragmentInteractionListener {

    private DatabaseReference myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        // задаем метсо БД, с которым будем работать - "users"
        myFirebaseRef = FirebaseDatabase.getInstance().getReference().child("users");

        Button switchToLoginBtn = (Button) findViewById(R.id.switchToLoginButton);
        final EditText loginInputField = (EditText) findViewById(R.id.loginInput);
        final EditText passwordInputFirst = (EditText) findViewById(R.id.passwordInput);
        final EditText passwordInputSecond = (EditText) findViewById(R.id.passwordRepeatInput);
        Button sendAndRegisterBtn = (Button) findViewById(R.id.sendAndRegisterButton);

        sendAndRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // проверка, заколнены ли поля
                if (loginInputField != null && passwordInputFirst != null && passwordInputSecond != null) {
                    // TODO: проверка ввода электронного адреса (т.е. на наличие @???)
                    if (loginInputField != null) {

                    } else {
                        // вывод сообщения о некорректном заполнении формы
                        Toast.makeText(RegistrationPage.this, "Введите действительный электронный адрес", Toast.LENGTH_SHORT).show();
                    }
                    //проверка одинаковости указанных паролей
                    if (passwordInputFirst == passwordInputSecond) {
                        // TODO: прокерка наличия логина в базе

                        // запись нового юзера в базу
                        int id = 000002;
                        String userID = "user" + id;
                        id++;
                        // TODO: описать правила изменения айдишника для новых юзеров
                        String email = loginInputField.getText().toString().trim();
                        // TODO: поработать с паролем для его шифрования
                        String password = passwordInputFirst.getText().toString().trim();

                        HashMap<String, String> newUser = new HashMap<String, String>();
                        newUser.put("name", userID);
                        newUser.put("e-mail", email);
                        newUser.put("password", password);

                        // push() назначает рандомное имя новому значению
                        //myFirebaseRef.push().setValue(newUser);

                        myFirebaseRef.child(userID).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegistrationPage.this, "Проверьте почту для \nподтвердения регистрации", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RegistrationPage.this, "Что-то пошло не так. \nПовторите попытку", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        // вывод сообщения о некорректном заполнении формы
                        Toast.makeText(RegistrationPage.this, "Пароли должны совпадать", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // вывод сообщение о некорректности заполнения формы
                    Toast.makeText(RegistrationPage.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationPage.this, LoginPage.class));
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
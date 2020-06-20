package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Users.Users;
import com.example.myapplication.base.ConnectionTobd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Reg extends AppCompatActivity {

    //    RelativeLayout root;
    private Button btn2, btnAddPeople;
    private EditText firstName = null, secondName = null, email;
    private EditText password;
    private boolean isTrue = true;
    private String emailForCheck;


    private void getDataFromDB(String eemail) { //Поиск в БД пользователя с эмейлом


        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (emailForCheck != null) emailForCheck = null;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Users luser = ds.getValue(Users.class);
                    assert luser != null;
                    emailForCheck = luser.getEmail();
                    if (emailForCheck.equals(email)) {
                        Toast.makeText(getApplicationContext(), emailForCheck, Toast.LENGTH_SHORT).show();
                        return;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        ConnectionTobd.getmDatabaseReference().addValueEventListener(vListener);
    }

    public void fncdb() {

//        firstName = (EditText) findViewById(R.id.NameForInUser);
//        secondName = (EditText) findViewById(R.id.secondName);
        password = (EditText) findViewById(R.id.PasswordForInUser);
        email = (EditText) findViewById(R.id.emailForRegByUser);
        btnAddPeople = (Button) findViewById(R.id.btnAddPeople);
        btnAddPeople.setOnClickListener(new View.OnClickListener() { //При нажатии книпки "Зарегестрироваться"

            @Override
            public void onClick(View view) {
                ConnectionTobd.connection(); //
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Toast.makeText(Reg.this, "Вы не ввели e-mail", Toast.LENGTH_LONG).show();//Выводит ошибки в сплывающих окнах
                    return;
                }
                ;
                if (password.getText().length() < 6) {
                    Toast.makeText(Reg.this, "Пароль должен быть длиннее 6 символов", Toast.LENGTH_LONG).show();//Выводит ошибки в сплывающих окнах
                    return;
                }
                ;
                ConnectionTobd.getmAuth().createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Users user = new Users(email.getText().toString(), password.getText().toString());
                        if (task.isSuccessful()) {
                            ConnectionTobd.getmDatabaseReference().child(encodeUserEmail(user.getEmail())).setValue(user);
                            Toast.makeText(getApplicationContext(), "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(".In"));
                        } else getDataFromDB(email.getText().toString());
                    }
                });
            }
        });
    }

    static String encodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    public void fncToNextWindow() {
        btn2 = (Button) findViewById(R.id.btnToIn);
        btn2.setOnClickListener( //При нажатии книпки "Войти"
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent reg = new Intent(".In");
                        startActivity(reg);
                    }
                }
        );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        fncToNextWindow();//Переход на другое окно приложения "Авторизации"
        fncdb();//Добавление пользователя в БД
    }
}
package com.example.myapplication.base;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConnectionTobd {
    private static FirebaseAuth mAuth; //авторизация
    private static FirebaseDatabase mFirebaseDatabase; // подключение к БД
    private static DatabaseReference mDatabaseReference; // для работы с таблицами внутри БД

    //инициализируем наше приложение для Firebase согласно параметрам в google-services.json
    // (google-services.json - файл, с настройками для firebase, кот. мы получили во время регистрации)
    public static void connection() {
        //Получаем переменную, для создания нового пользователя
        mAuth = FirebaseAuth.getInstance();
        //получаем точку входа для базы данных
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //получаем ссылку для работы с базой данных
        mDatabaseReference = mFirebaseDatabase.getReference("users");
    }

    public static FirebaseAuth getmAuth() {
        return mAuth;
    }

    public static void setmAuth(FirebaseAuth mAuth) {
        ConnectionTobd.mAuth = mAuth;
    }

    public static FirebaseDatabase getmFirebaseDatabase() {
        return mFirebaseDatabase;
    }

    public static void setmFirebaseDatabase(FirebaseDatabase mFirebaseDatabase) {
        ConnectionTobd.mFirebaseDatabase = mFirebaseDatabase;
    }

    public static DatabaseReference getmDatabaseReference() {
        return mDatabaseReference;
    }

    public static void setmDatabaseReference(DatabaseReference mDatabaseReference) {
        ConnectionTobd.mDatabaseReference = mDatabaseReference;
    }
}

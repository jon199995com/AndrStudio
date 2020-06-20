package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2;

    public void  fncToNextWindow(){
        btn1 = (Button) findViewById(R.id.btnReg);
        btn2 = (Button) findViewById(R.id.btnIn);
        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent reg = new Intent(".MainActivity2");
                        startActivity(reg);
                    }
                }
        );
        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in = new Intent(".In");
                        startActivity(in);
                    }
                }
        );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fncToNextWindow();
    }
}
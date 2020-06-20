package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class In extends AppCompatActivity {

    private Button btnToReg;



    public void  fncToNextWindow(){
        btnToReg = (Button) findViewById(R.id.btnToReg);
        btnToReg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent reg = new Intent(".MainActivity2");
                        startActivity(reg);
                    }
                }
        );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);
        fncToNextWindow();
    }
}
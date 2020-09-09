package com.rp4.fourzetaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rp4.fourzetaapp.Connection.HttpService;
import com.rp4.fourzetaapp.model.Circuito;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnInscreverse;
    private Button btnInscritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInscreverse = findViewById(R.id.btnInscreverse);
        btnInscreverse.setOnClickListener(this);

        btnInscritos = findViewById(R.id.btnInscritos);
        btnInscritos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.btnInscreverse:
                Intent it = new Intent(this, InscricaoActivity.class);
                startActivity(it);
                break;

            case R.id.btnInscritos:
                Intent it2 = new Intent(this, InscritosActivity.class);
                startActivity(it2);
                break;
        }

    }

}

package com.example.tennis_liga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Login extends AppCompatActivity {
    int sonido;
    SoundPool sp;
    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        welcome=(TextView)findViewById(R.id.tv_bienvenido);
        sp= new SoundPool(1,AudioManager.STREAM_MUSIC,1);
        sonido=sp.load(this,R.raw.ping,1);



        TimerTask timer= new TimerTask(){

            @Override
            public void run() {
                Intent in =new Intent(Login.this,Home.class);
                startActivity(in);

                play();
                finish();
            }
        };
        Bundle recibido= getIntent().getExtras();
        Personajes pers=null;
        if(recibido!=null){
            pers=(Personajes) recibido.getSerializable("usuario");
            welcome.setText(pers.getNombre().toString()+" "+pers.getL_name().toString());
        }


        Timer tiempo= new Timer();
        tiempo.schedule(timer,5000);
    }
    public void play(){
        sp.play(sonido,1,1,1,0,0);
    }
}
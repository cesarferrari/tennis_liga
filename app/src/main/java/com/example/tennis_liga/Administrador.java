package com.example.tennis_liga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;

public class Administrador extends AppCompatActivity {

    Fragment frag1;
    Fragment frag2;
    Fragment frag3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        //   crearCompetencia=(Button) findViewById(R.id.CrearEvento);
        //  resultados=(Button) findViewById(R.id.Resultados);
        //  rolAdmin=(Button) findViewById(R.id.CrearAdmin);
        frag1=new CrearCompetenciaFragment();
        frag2=new CrearAdministradorFragment();
        frag3=new ResultadosFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor,frag1).commit();
    }
    public void onclick(View vista){
        FragmentTransaction transaccion=getSupportFragmentManager().beginTransaction();
        switch (vista.getId()){
            case R.id.CrearEvento:
                transaccion.replace(R.id.contenedor,frag1);
                break;
            case R.id.CrearAdmin:
                transaccion.replace(R.id.contenedor,frag2);
                break;
            case R.id.Resultados:
                transaccion.replace(R.id.contenedor,frag3);
                break;
            case R.id.home:
                Intent in = new Intent(this,Home.class);
                startActivity(in);
                break;
        }
        transaccion.commit();
    }


}
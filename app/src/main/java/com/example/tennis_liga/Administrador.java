package com.example.tennis_liga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Administrador extends AppCompatActivity {

    Fragment frag1;
    Fragment frag2;
    Fragment frag3;
    Fragment frag4;

    String   recibeNombre = "";
    String    recibeApellido = "";
    String recibeNacimiento = "";
    String recibeNal = "";
    String recibePais = "";
    String recibeCamiseta = "";
    String recibeEstilo = "";
    String recibeGolpe = "";
    String recibeRaqueta = "";
    String recibeCordaje = "";
    String recibeId = "";
    String recibePass="";
    int recibeRol;
    Personajes pr=null;
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
        Bundle bundle=getIntent().getExtras();
        Personajes res1=null;


        if (bundle!=null) {
            res1 = (Personajes) bundle.getSerializable("usuario");

            recibeNombre = res1.getNombre();
            recibeApellido = res1.getL_name();
              recibeNacimiento = res1.getBirthday();
             recibeNal = res1.getNal();
             recibePais = res1.getPais();
             recibeCamiseta = res1.getSize();
             recibeEstilo = res1.getStyle();
             recibeGolpe = res1.getHit();
             recibeRaqueta = res1.getRacket();
             recibeCordaje = res1.getCord();
              recibeId = res1.getId();
                recibePass=res1.getPass();
                recibeRol=res1.getRol();
        }
        Toast.makeText(getApplicationContext(), "recibe "+recibeId+"y"+recibeNombre+"y"+recibeApellido+"y"
                +recibeEstilo+"y"+recibeGolpe+"y"+recibeRaqueta+"y"+recibeNal+"y"+recibePais+"y"+recibeCordaje+"y"+
                recibeCamiseta+"y"+recibePass+"rol"+recibeRol, Toast.LENGTH_SHORT).show();



    }

    @Override public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_admin,menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem opc) {
        frag1=new CrearCompetenciaFragment();
        frag2=new CrearAdministradorFragment();
        frag3=new ResultadosFragment();
        frag4=new FragMatch();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor,frag1).commit();
        int id=opc.getItemId();
        FragmentTransaction transaccion=getSupportFragmentManager().beginTransaction();
        if(id==R.id.admin1){
            transaccion.replace(R.id.contenedor,frag2);

        }else if(id==R.id.admin2){
            transaccion.replace(R.id.contenedor,frag3);
        }else if(id==R.id.admin3){
            transaccion.replace(R.id.contenedor,frag1);
        }else if(id==R.id.admin4){
             pr=new Personajes();
            Intent in= new Intent(getApplicationContext(),Home.class);
            Bundle bundl= new Bundle();
            pr.setL_name(recibeApellido);
            pr.setNombre(recibeNombre);
            pr.setNal(recibeNal);
            pr.setPais(recibePais);
            pr.setHit(recibeGolpe);
            pr.setRacket(recibeRaqueta);
            pr.setCord(recibeCordaje);
            pr.setStyle(recibeEstilo);
            pr.setSize(recibeCamiseta);
            pr.setPass(recibePass);
            pr.setRol(recibeRol);
            pr.setId(recibeId);
            bundl.putSerializable("usuario",pr);
            in.putExtras(bundl);
            startActivity(in);
        }else if(id==R.id.admin5){
            transaccion.replace(R.id.contenedor,frag4);
        }
        transaccion.commit();
        return super.onOptionsItemSelected(opc);
    }

}
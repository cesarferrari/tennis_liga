package com.example.tennis_liga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
ActionBarDrawerToggle actionBarDrawerToggle;
DrawerLayout drawerLayout;
Toolbar toolbar;
NavigationView navigationView;
Fragment datosUserFR,galeriaFrag,competFrag,UserFR,competFragUnica,StatisticsFR;
 private String recibeId,recibeNombre,recibeApellido,recibeNacimiento,recibeNal,recibePais,recibeCamiseta,recibeEstilo,recibeGolpe,recibeRaqueta,recibeCordaje;
 private String recibePass,recibeEfectividad;

 private int recibeFoto, recibeRol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

     //   recibeFoto=getIntent().getStringExtra("imagen");
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        navigationView=(NavigationView) findViewById(R.id.navigationView);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
   drawerLayout.addDrawerListener(actionBarDrawerToggle);
   actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
   actionBarDrawerToggle.syncState();

   datosUserFR= new DatosPerfil();
   galeriaFrag= new GaleriaFR();
   competFrag= new CompetenciasFR();
   UserFR=new PerfilFR();
   competFragUnica=new competencia_participante();
   StatisticsFR=new FrStatistics();

   getSupportFragmentManager().beginTransaction().add(R.id.container,UserFR).commit();
   navigationView.setNavigationItemSelectedListener(this);
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
         //   recibeEfectividad=res1.getEfectividad();
        }else{
            Toast.makeText(getApplicationContext(), "no llego ni madre", Toast.LENGTH_SHORT).show();
        }

        Bundle bdl= new Bundle();
      //  Bundle bundlex= new Bundle();
        bdl.putString("kid",recibeId);
        bdl.putInt("krol",recibeRol);
        bdl.putString("knombre",recibeNombre);
        bdl.putString("kestilo",recibeEstilo);
        bdl.putString("kgolpe",recibeGolpe);
        bdl.putString("kraqueta",recibeRaqueta);
        bdl.putString("knal",recibeNal);
        bdl.putString("kpais",recibePais);
        bdl.putString("kcordaje",recibeCordaje);
        bdl.putString("kapellido",recibeApellido);
        bdl.putString("kcamiseta",recibeCamiseta);
        bdl.putString("kpassword",recibePass);
      //  bdl.putString("kefectividad",recibeEfectividad);
        datosUserFR.setArguments(bdl);
        competFragUnica.setArguments(bdl);
        galeriaFrag.setArguments(bdl);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        FragmentTransaction transaccion=getSupportFragmentManager().beginTransaction();
      if(menuItem.getItemId()==R.id.home){
          transaccion.replace(R.id.container,datosUserFR);

      }
        if(menuItem.getItemId()==R.id.personas){
            transaccion.replace(R.id.container,competFrag);
        }
        if(menuItem.getItemId()==R.id.home2){
            transaccion.replace(R.id.container,UserFR);
        }
        if(menuItem.getItemId()==R.id.personas2){
            transaccion.replace(R.id.container,galeriaFrag);
        }
        if(menuItem.getItemId()==R.id.competicion1){
            transaccion.replace(R.id.container,competFragUnica);
        }


transaccion.commit();

        return false;
    }

}
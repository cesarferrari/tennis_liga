package com.example.tennis_liga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
ActionBarDrawerToggle actionBarDrawerToggle;
DrawerLayout drawerLayout;
Toolbar toolbar;
NavigationView navigationView;
Fragment datosUserFR,galeriaFrag,competFrag,UserFR;
 private String recibeNombre,recibeApellido,recibeNacimiento,recibeNal,recibePais,recibeCamiseta,recibeEstilo,recibeGolpe,recibeRaqueta,recibeCordaje;
 private int recibeFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recibeNombre=getIntent().getStringExtra("nombre");
        recibeApellido=getIntent().getStringExtra("apellido");
        recibeNacimiento=getIntent().getStringExtra("nacimiento");
        recibeNal=getIntent().getStringExtra("nacionalidad");
        recibePais=getIntent().getStringExtra("pais");
        recibeCamiseta=getIntent().getStringExtra("camiseta");
        recibeEstilo=getIntent().getStringExtra("estilo");
        recibeGolpe=getIntent().getStringExtra("golpe");
        recibeRaqueta=getIntent().getStringExtra("raqueta");
        recibeCordaje=getIntent().getStringExtra("cordaje");
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
   getSupportFragmentManager().beginTransaction().add(R.id.container,UserFR).commit();
   navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        FragmentTransaction transaccion=getSupportFragmentManager().beginTransaction();
      if(menuItem.getItemId()==R.id.home){
          transaccion.replace(R.id.container,datosUserFR);
          Bundle bundle= new Bundle();
          bundle.putString("knombre",recibeNombre);
          bundle.putString("kapellido",recibeApellido);
          bundle.putString("knacimiento",recibeNacimiento);
          bundle.putString("knal",recibeNal);
          bundle.putString("kestilo",recibeEstilo);
          bundle.putString("kcamiseta",recibeCamiseta);
          bundle.putString("kcordaje",recibeCordaje);
          bundle.putString("kgolpe",recibeGolpe);
          bundle.putString("kpais",recibePais);
          bundle.putString("kraqueta",recibeRaqueta);
          datosUserFR.setArguments(bundle);

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
transaccion.commit();

        return false;
    }
}
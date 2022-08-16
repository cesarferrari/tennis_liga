package com.example.tennis_liga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Prueba extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{
RequestQueue requestQueueImage,requestQueueGenerales;
TextView nombre,apellido,nacionalidad,paisActual,camiseta,estilo,golpe,raqueta,cordaje,nombreX,efectividadX;
ImageView imagenPrincipal;
private String urlgenerales;
private Url urlx= new Url();
private Personajes pers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        requestQueueImage= Volley.newRequestQueue(getApplicationContext());
        requestQueueGenerales=Volley.newRequestQueue(getApplicationContext());
        Bundle bundle=getIntent().getExtras();
        Personajes res1=null;
        nombre=(TextView) findViewById(R.id.txt_nombreY);
        apellido=(TextView) findViewById(R.id.txt_apellidoY);
        nacionalidad=(TextView) findViewById(R.id.txt_nacionalidadX);
        paisActual=(TextView) findViewById(R.id.txt_paisA);
        camiseta=(TextView) findViewById(R.id.txt_tallaX);
        estilo=(TextView) findViewById(R.id.txt_estiloY);
        golpe=(TextView) findViewById(R.id.txt_golpeA);
        raqueta=(TextView) findViewById(R.id.txt_raquetaA);
        cordaje=(TextView) findViewById(R.id.txt_cordajeB);
        nombreX=(TextView)findViewById(R.id.textView15);
        efectividadX=(TextView)findViewById(R.id.textView14);
        imagenPrincipal=(ImageView) findViewById(R.id.profile_image);
        pers=new Personajes();
        datosGenerales(1);
        try {
            if (bundle != null) {
                res1 = (Personajes) bundle.getSerializable("generales");

                nombre.setText(res1.getNombre());
                apellido.setText(res1.getL_name());
                nacionalidad.setText(res1.getNal());
                paisActual.setText(res1.getPais());
                camiseta.setText(res1.getSize());
                estilo.setText(res1.getStyle());
                golpe.setText(res1.getHit());
                raqueta.setText(res1.getRacket());
                cordaje.setText(res1.getCord());
                 nombreX.setText(res1.getNombre());
                 efectividadX.append(" "+res1.getEfectividad()+"%");

                imagenPrincipal(res1.getUrlImagePrincipal());
                Toast.makeText(getApplicationContext(), ""+res1.getUrlImagePrincipal(), Toast.LENGTH_SHORT).show();

            }


        }catch(Exception e){
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu1){
        getMenuInflater().inflate(R.menu.menu_galeria,menu1);

        return true;

    }
    @ Override public boolean onOptionsItemSelected(MenuItem opc){

        int id=opc.getItemId();
        FragmentTransaction transaccion=getSupportFragmentManager().beginTransaction();
        if(id==R.id.galeria){
            Intent in = new Intent(getApplicationContext(),Home.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("usuario",pers);
            in.putExtras(bundle);
            startActivity(in);

        }
        return super.onOptionsItemSelected(opc);

    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(getApplicationContext(),Home.class);
        startActivity(in);
        super.onBackPressed();
    }
    public void imagenPrincipal(String URLIMAGE){
        ImageRequest imageRequest= new ImageRequest(URLIMAGE,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imagenPrincipal.setImageBitmap(response);
                    }
                }, 0, 0, null, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueueImage.add(imageRequest);
    }
    public Personajes datosGenerales(int c){
        urlgenerales=urlx.getUrl()+"/padel/consulta_jugador4.php?id=";
        JsonObjectRequest jsonObjectGenerales=new JsonObjectRequest(Request.Method.GET, urlgenerales+c, null,
                this,this);

        requestQueueGenerales.add(jsonObjectGenerales);
        return pers;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray array=response.optJSONArray("jugador");
        try{
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject=array.getJSONObject(i);
                pers.setNombre(jsonObject.optString("nombre"));
                pers.setL_name(jsonObject.optString("apellido"));
                pers.setId(jsonObject.optString("id"));
                pers.setSize(jsonObject.optString("talla_camiseta"));
                pers.setStyle(jsonObject.optString("estilo_juego"));
                pers.setNal(jsonObject.optString("nacionalidad"));
                pers.setRacket(jsonObject.optString("raqueta"));
                pers.setCord(jsonObject.optString("cordaje"));
                pers.setHit(jsonObject.optString("mejor_golpe"));
                pers.setPais(jsonObject.optString("pais_actual"));
             //   pers.setEfectividad(""+porcientoW+"%");
                Toast.makeText(getApplicationContext(), ""+pers.getNombre()+"y"+pers.getAddress(), Toast.LENGTH_SHORT).show();

            }
        }catch(JSONException e){
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
        }
    }
}
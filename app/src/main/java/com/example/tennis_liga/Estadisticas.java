package com.example.tennis_liga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Estadisticas extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{
    public TextView txt_nombre,txt_efectividad,txt_nivel,txt_partido,txt_res1,txt_res2,txt_res3,txt_res4,txt_res5;
    private TextView txt_win1,txt_win2,title_partidosGanados,txt_set1,txt_set2,title_setGanados,txt_remont1,txt_remont2;
    private TextView txt_remontC1,txt_remontC2,txt_games1,txt_games2,text_ganados,txt_tercer_set1,txt_tercer_set2,txt_tercer_set;
    private TextView txt_tie_break1,txt_tie_break2,txt_tie_break,txt_remontadas,txt_contra,res_1,res_2,res_3,res_4,res_5;
    private ImageView civ1;
    private CardView c1D,c1L,c2D,c2L,c3D,c3L,c4L,c4D;
  private GaleriaFR frag_galeria;
  private RequestQueue requestQueueImage,requestQueueID,requestQueueGenerales;
     private double porcientoW=0,porcientoS=0,porcientoG=0,porcientoTie=0,porcientoTercer=0,remontadas=0,porcientoRemont=0,porcientoAgaints;
     private String idPlayer;
     private ProgressDialog progress;
     private Url urlx;
    private String urlId,urlgenerales;
    Personajes pers;
    List<Personajes> listaPers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
           urlx=new Url();
        pers=new Personajes();
           requestQueueID=Volley.newRequestQueue(getApplicationContext());
        txt_nombre=(TextView) findViewById(R.id.txt_1);
        txt_efectividad=(TextView) findViewById(R.id.txt_efectividad);
        txt_partido=(TextView)findViewById(R.id.txt_partidos);
        txt_nivel=(TextView) findViewById(R.id.txt_nivel);
        txt_res1=(TextView) findViewById(R.id.res_1);
        txt_res2=(TextView) findViewById(R.id.res_2);
        txt_res3=(TextView) findViewById(R.id.res_3);
        txt_res4=(TextView) findViewById(R.id.res_4);
        txt_res5=(TextView) findViewById(R.id.res_5);
        txt_win1=(TextView) findViewById(R.id.txt_win1);
        txt_win2=(TextView)findViewById(R.id.txt_win2);
        title_partidosGanados=(TextView)findViewById(R.id.text_partidos_ganados);
        txt_set1=(TextView) findViewById(R.id.txt_set1);
        txt_set2=(TextView)findViewById(R.id.txt_set2);
        title_setGanados=(TextView)findViewById(R.id.text_setGanados);
        txt_remont1=(TextView)findViewById(R.id.txt_remont1);
        txt_remont2=(TextView)findViewById(R.id.txt_remont2);
        txt_remontC1=(TextView)findViewById(R.id.txt_remontC1);
        txt_remontC2=(TextView)findViewById(R.id.txt_remontC2);
        txt_games1=(TextView)findViewById(R.id.txt_games1);
        txt_games2=(TextView)findViewById(R.id.txt_games2);
        text_ganados=(TextView)findViewById(R.id.text_ganados);
        txt_tercer_set1=(TextView)findViewById(R.id.txt_tercer_set1);
        txt_tercer_set2=(TextView)findViewById(R.id.text_tercer_set2);
        txt_tercer_set=(TextView)findViewById(R.id.text_tercerSet);
        txt_tie_break1=(TextView)findViewById(R.id.txt_tie_break1);
        txt_tie_break2=(TextView)findViewById(R.id.txt_tie_break2);
        txt_tie_break=(TextView)findViewById(R.id.text_tie_break);
        txt_remontadas=(TextView)findViewById(R.id.text_remontadasF);
        txt_contra=(TextView)findViewById(R.id.text_remontadasC);
        res_1=(TextView)findViewById(R.id.res_1);
        res_2=(TextView)findViewById(R.id.res_2);
        res_3=(TextView)findViewById(R.id.res_3);
        res_4=(TextView)findViewById(R.id.res_4);
        res_5=(TextView)findViewById(R.id.res_5);
        c1D=(CardView)findViewById(R.id.card1D);
        civ1=(ImageView)findViewById(R.id.CIV_id);
        requestQueueImage= Volley.newRequestQueue(getApplicationContext());
        requestQueueGenerales=Volley.newRequestQueue(getApplicationContext());
        Bundle bundle=getIntent().getExtras();
        idPlayer=getIntent().getStringExtra("user");
        Resultados1 res1=null;
        pers=new Personajes();
        datosGenerales(1);
        try {
            if (bundle!=null){
                res1=(Resultados1) bundle.getSerializable("resultados");
                Toast.makeText(getApplicationContext(), "imagen"+ res1.getUrlImagePrincipal()+"y"+res1.getFirst()+"y"+res1.getSecond()+"y"+
                       res1.getThird()+"y"+res1.getFourth()+"y"+res1.getFifth()+res1.getReassambled()+"y"+
                        res1.getWins()+"y"+res1.getAgaints()+"y"+res1.getSets()+"y"+res1.getGames()+"ademas"+idPlayer, Toast.LENGTH_SHORT).show();

                imagenPrincipal(res1.getUrlImagePrincipal());
                porcientoW=(res1.getWins()*100)/res1.getTotalW();
                porcientoS=(res1.getSets()*100)/res1.getSuperTotalS();
                porcientoG=(res1.getGames()*100)/res1.getSuperTotalG();
                porcientoTercer=(res1.getThird_set()*100)/res1.getRestaTercer();
                porcientoTie=(res1.getTie_break()*100)/res1.getRestaTie();
                porcientoRemont=(res1.getReassambled()*100)/res1.getTotalRemont();
                porcientoAgaints=(res1.getAgaints()*100)/res1.getTotalRemont();
                txt_nombre.setText(""+res1.getNombre());
                txt_partido.setText(""+res1.getTotalW());
                txt_win1.setText(""+res1.getWins());
                txt_win2.setText(""+res1.getLoses());
                title_partidosGanados.append(" "+porcientoW+"%("+res1.getWins()+" de "+res1.getTotalW()+")");
                txt_efectividad.setText(porcientoW+"%");
                txt_set1.setText(""+res1.getSets());
                txt_set2.setText(""+res1.getTotalS());
                title_setGanados.append(" "+porcientoS+"%("+res1.getSets()+" de "+res1.getSuperTotalS()+")");
                txt_remont1.setText(""+res1.getReassambled());
                txt_remont2.setText(""+res1.getAgaints());
                txt_remontadas.append(" "+porcientoRemont+"%("+res1.getReassambled()+" de "+res1.getTotalRemont()+")");
                txt_remontC2.setText(" "+res1.getReassambled());
                txt_remontC1.setText(" "+res1.getAgaints());
                txt_contra.append(" "+porcientoAgaints+"%("+res1.getAgaints()+" de "+res1.getTotalRemont()+")");
                txt_games1.setText(" "+res1.getGames());
                txt_games2.setText(" "+res1.getTotalG());
                text_ganados.append(" "+porcientoG+"%("+res1.getGames()+" de "+res1.getSuperTotalG()+")");
                txt_tie_break1.setText(" "+res1.getTie_break());
                txt_tie_break2.setText(" "+res1.getTotalTie());
                txt_tie_break.append(" "+porcientoTie+"%("+res1.getTie_break()+" de "+res1.getRestaTie()+")");
                txt_tercer_set1.setText(" "+res1.getThird_set());
                txt_tercer_set2.setText(" "+res1.getTotalTercer());
                txt_tercer_set.append(" "+porcientoTercer+"%("+res1.getThird_set()+" de "+res1.getRestaTercer()+")");
            }

        }catch(Exception exception){

        }
        if(res1.getFirst()==0){
            res_1.setText("L");
            res_1.setTextColor(getColor(R.color.tennis_rojo));
        }else if(res1.getFirst()==1){
            res_1.setText("W");
            res_1.setTextColor(getColor(R.color.admin_amarillo));
        }else if(res1.getFirst()==9999){
            res_1.setText("N");
        }
        if(res1.getSecond()==0){
            res_2.setText("L");
            res_2.setTextColor(getColor(R.color.tennis_rojo));
        }else if(res1.getSecond()==1){
            res_2.setText("W");
            res_2.setTextColor(getColor(R.color.admin_amarillo));
        }else if(res1.getSecond()==9999){
            res_2.setText("N");
        }
        if(res1.getThird()==0){
            res_3.setText("L");
            res_3.setTextColor(getColor(R.color.tennis_rojo));
        }else if(res1.getThird()==1){
            res_3.setText("W");
            res_3.setTextColor(getColor(R.color.admin_amarillo));
        }else if(res1.getThird()==9999){
            res_3.setText("N");
        }
        if(res1.getFourth()==0){
            res_4.setText("L");
            res_4.setTextColor(getColor(R.color.tennis_rojo));
        }else if(res1.getFourth()==1){
            res_4.setText("W");
            res_4.setTextColor(getColor(R.color.admin_amarillo));
        }else if(res1.getFourth()==9999){
            res_4.setText("N");
        }
        if(res1.getFifth()==0){
            res_5.setText("L");
            res_5.setTextColor(getColor(R.color.tennis_rojo));
        }else if(res1.getFifth()==1){
            res_5.setText("W");
            res_5.setTextColor(getColor(R.color.admin_amarillo));
        }else if(res1.getFifth()==9999){
            res_5.setText("N");
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
            Toast.makeText(getApplicationContext(), "id"+idPlayer, Toast.LENGTH_SHORT).show();
Personajes per=new Personajes();
per.setRacket("wilson");

            Intent intento = new Intent(getApplicationContext(),Home.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("usuario",pers);
            intento.putExtras(bundle);

           startActivity(intento);

        }
        return super.onOptionsItemSelected(opc);

    }

    @Override
    public void onBackPressed() {
        Personajes personaje= new Personajes();
        personaje.setId(idPlayer);
        Intent in = new Intent(getApplicationContext(),Home.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("usuario",pers
        );
        startActivity(in);
        super.onBackPressed();
    }
    public void imagenPrincipal(String URLIMAGE){
        ImageRequest imageRequest= new ImageRequest(URLIMAGE,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        civ1.setImageBitmap(response);
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

                Toast.makeText(getApplicationContext(), ""+pers.getNombre()+"y"+pers.getAddress(), Toast.LENGTH_SHORT).show();

            }
        }catch(JSONException e){
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
        }
    }
 /*   public Personajes ejecutar(){
        int player=Integer.parseInt(idPlayer);
        progress= new ProgressDialog(getApplicationContext());
        progress.setMessage(getString(R.string.cargando));
        progress.show();
        urlId=urlx.getUrl()+"/padel/consulta_jugador4.php?id="+player;
        urlId=urlId.replace(" ","%20");

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, urlId, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray array=response.optJSONArray("jugador");
                        try{
                            for(int i=0;i< array.length();i++){
                                JSONObject json=array.getJSONObject(i);
                                personaje.setL_name(json.optString("apellido"));
                                personaje.setNombre(json.optString("nombre"));
                                personaje.setNal(json.optString("nacionalidad"));
                                personaje.setPais(json.optString("pais_actual"));
                                personaje.setHit(json.optString("mejor_golpe"));
                                personaje.setRacket(json.optString("raqueta"));
                                personaje.setCord(json.optString("cordaje"));
                                personaje.setStyle(json.optString("estilo_juego"));
                                personaje.setSize(json.optString("talla_camiseta"));
                                personaje.setPass(json.optString("password"));
                                personaje.setRol(json.optInt("rol"));

                            }

                        }catch(Exception e){

                        }
                        progress.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
                progress.hide();
            }
        });
        requestQueueID.add(jsonObjectRequest);
        return personaje;
    }*/
}
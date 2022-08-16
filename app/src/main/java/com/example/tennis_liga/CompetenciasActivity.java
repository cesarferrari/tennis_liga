package com.example.tennis_liga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CompetenciasActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Match match;
    private ArrayList<Match>lista;
    private RequestQueue requestQueue;
    private ProgressDialog progress;
    private String url,id;
    private AdapterMatch adapter;
    private Url ur=new Url();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competencias);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        recyclerView=findViewById(R.id.recycler_competencias);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        lista=new ArrayList<>();
        match=new Match();


        id=getIntent().getStringExtra("compet");
        Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_SHORT).show();

ejecutar_servicio();
    }

    private void ejecutar_servicio() {
        progress=new ProgressDialog(getApplicationContext());
        progress.setMessage("cargando...");
        progress.show();

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,
                url=ur.getUrl()+"/padel/consulta_encuentro.php?competicion="+id
                , null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray array= response.optJSONArray("jugador");
                try{
                    for (int i=0;i< array.length();i++){
                        JSONObject objeto=null;
                        objeto=array.getJSONObject(i);
                        match.setCompeticion(objeto.optString("competicion"));
                        match.setNombre1(objeto.optString("nombre1"));
                        match.setNombre2(objeto.optString("nombre2"));
                        match.setApellido1(objeto.optString("apellido1"));
                        match.setApellido2(objeto.optString("apellido2"));
                        match.setTipo_evento(objeto.optString("encuentro"));
                        match.setPhotoP1(R.drawable.trofeo_tennis);
                        match.setPhotoP2(R.drawable.trofeo_tennis);
                        lista.add(match);
                        progress.hide();

                        recyclerView.setAdapter(adapter);
                        adapter= new AdapterMatch(lista);
                    }
                }catch(JSONException ex){

                }

                progress.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progress.hide();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
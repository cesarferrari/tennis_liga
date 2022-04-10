package com.example.tennis_liga;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class CompetenciasFR extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private String url="/",deber,err,conect,charge;
    private String uno="",dos="";
    private ProgressDialog progress;
    private JsonObjectRequest jsonObjectRequest;
    private ArrayList<Personajes>ListDatos;
    AdapterCompetencia adaptercompet;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_competencias_f_r, container, false);
        ListDatos= new ArrayList<Personajes>();
        recyclerView=vista.findViewById(R.id.recycler_competencias);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        err=getString(R.string.erroC);
        conect=getString(R.string.conexion);
        charge=getString(R.string.cargando);
        requestQueue= Volley.newRequestQueue(getActivity());
        AdapterCompetencia adapter = new AdapterCompetencia(ListDatos);
        recyclerView.setAdapter(adapter);
        Utilidades.visualizacion=Utilidades.LIST;
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      ejecutar_servicio();
      adapter.setOnClickListenter(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(getActivity(), "seleccion "
                              +ListDatos.get(recyclerView.getChildAdapterPosition(v)).getNombre()+" "+
                              ListDatos.get(recyclerView.getChildAdapterPosition(v)).getL_name(),
                      Toast.LENGTH_SHORT).show();
          }
      });
        return vista;
    }

    public void ejecutar_servicio(){
        progress= new ProgressDialog(getActivity());
        progress.setMessage(charge);
        progress.show();
        url="http://192.168.1.69/padel/consulta_competencia.php";

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progress.hide();
        Toast.makeText(getActivity(), err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getActivity(), conect, Toast.LENGTH_SHORT).show();
        Personajes personaje = null;
        JSONArray json = response.optJSONArray("competition");
        try {
            for (int i=0;i<json.length();i++){
                personaje= new Personajes();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                personaje.setNombre(jsonObject.optString("competicion"));
                personaje.setL_name(jsonObject.optString("tipo_evento"));
                personaje.setFoto(R.drawable.trofeo_tennis);
                ListDatos.add(personaje);
                progress.hide();
                adaptercompet= new AdapterCompetencia(ListDatos);
                recyclerView.setAdapter(adaptercompet);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), err, Toast.LENGTH_SHORT).show();
            progress.hide();
        }
    }
}
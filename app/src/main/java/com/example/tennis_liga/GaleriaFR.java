package com.example.tennis_liga;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class GaleriaFR extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

   private ArrayList<Personajes> ListDatos;
    private RecyclerView recyclerView;
    private Button grid;
    private Button list;
    private RequestQueue requestQueue;
    private String url="/",deber,error,conect,charge;
     private String uno="",dos="";
    private ProgressDialog progress;
    private JsonObjectRequest jsonObjectRequest;
    AdapterDatos adapterDatos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_galeria_f_r, container, false);
        ListDatos= new ArrayList<Personajes>();
        recyclerView=vista.findViewById(R.id.recycler_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        grid=vista.findViewById(R.id.btn_gridAdmin);
        list=vista.findViewById(R.id.btn_listAdmin);
        AdapterDatos adapter = new AdapterDatos(ListDatos);
        error=getString(R.string.erroC);
        conect=getString(R.string.conexion);
        charge=getString(R.string.cargando);
        requestQueue= Volley.newRequestQueue(getActivity());
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getActivity(), "modo lista", Toast.LENGTH_SHORT).show();
                //llenarPersonajes();
              //  AdapterDatos adapter = new AdapterDatos(ListDatos);
             //   recyclerView.setAdapter(adapter);
               // ejecutar_servicio();
                recyclerView.setAdapter(adapter);
                Utilidades.visualizacion=Utilidades.LIST;
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


            }
        });
        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getActivity(), "modo grid", Toast.LENGTH_SHORT).show();
              //  llenarPersonajes();
               // ejecutar_servicio();
                recyclerView.setAdapter(adapter);
                Utilidades.visualizacion=Utilidades.GRID;
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));


            }
        });
        //llenarPersonajes();
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
        url="http://192.168.1.69/padel/consulta_jugador.php";
       
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError errorc) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        progress.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getActivity(), conect, Toast.LENGTH_SHORT).show();
        Personajes personaje = null;
        JSONArray json = response.optJSONArray("jugador");
        try {
        for (int i=0;i<json.length();i++){
           personaje= new Personajes();
           JSONObject jsonObject=null;
           jsonObject=json.getJSONObject(i);
           personaje.setNombre(jsonObject.optString("nombre"));
           personaje.setL_name(jsonObject.optString("apellido"));
           personaje.setFoto(R.drawable.img_profile);
          ListDatos.add(personaje);
            progress.hide();
             adapterDatos= new AdapterDatos(ListDatos);
            recyclerView.setAdapter(adapterDatos);
        }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            progress.hide();
        }
    }
}
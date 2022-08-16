package com.example.tennis_liga;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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


public class CrearAdministradorFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {


    private ArrayList<Personajes> datos;
    private Button grid;
    private Button list;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private RequestQueue requestQueuePlayer,requestQueueAdmin;
    private String url = "/", deber, error, conect, charge;
    private String uno = "", dos = "";
    private ProgressDialog progress;
    private JsonObjectRequest jsonObjectRequest;
    private static final Url urlx = new Url();
    private TextView rol;
    private String recibeNombre, recibeRol, recibeId;
    private final CharSequence[] opciones={"asignar rol a usuario","cancelar"};
private int relax;
private final static String urlPlayer=urlx.getUrl()+"/padel/modificaRol.php?id=";
    private final static String urlAdmin=urlx.getUrl()+"/padel/modificaRolAdmin.php?id=";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      if (getArguments() != null) {
            recibeId = getArguments().getString("kid", "valor 0");
            recibeNombre = getArguments().getString("knombre", "valor 0");
            recibeRol = getArguments().getString("krol", "valor0");


        }
        Toast.makeText(getActivity(), "nada  q " + recibeId + recibeRol + recibeNombre, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_crear_administrador, container, false);
        datos = new ArrayList<Personajes>();
        recyclerView = vista.findViewById(R.id.recycler_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String rolA = getString(R.string.asigna_rol);
        String rolJ = getString(R.string.asigna_rolJ);
        rol = vista.findViewById(R.id.txt_role);
        grid = vista.findViewById(R.id.btn_gridAdmin);
        list = vista.findViewById(R.id.btn_asignaAdmin);
        error = getString(R.string.erroC);
        conect = getString(R.string.conexion);
        charge = getString(R.string.cargando);
        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueuePlayer=Volley.newRequestQueue(getActivity());
        requestQueueAdmin=Volley.newRequestQueue(getActivity());
        AdapterDatosAdmin adapter = new AdapterDatosAdmin(datos);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rol.setText(rolJ);
                recyclerView.setAdapter(adapter);
                Utilidades.visualizacion = Utilidades.LIST;
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    relax=5;
            }
        });
        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rol.setText(rolA);
                recyclerView.setAdapter(adapter);
                Utilidades.visualizacion = Utilidades.LIST;
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
               relax=10;
            }
        });
       ejecutar_servicio();

        adapter.setOnClickListenter(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "seleccion "
                                + datos.get(recyclerView.getChildAdapterPosition(v)).getNombre()+"relax="+relax,
                        Toast.LENGTH_SHORT).show();
                int c=Integer.parseInt(datos.get(recyclerView.getChildAdapterPosition(v)).getId());

               AlertDialog.Builder alerta=new AlertDialog.Builder(getActivity());
                alerta.setTitle("opciones");
                alerta.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(opciones[which].equals("asignar rol a usuario")){

                            if(relax==5){
                                modificaPlayer(c);
                            }else if(relax==10){
                                modificaAdmin(c);
                            }
                        }else if(opciones[which].equals("cancelar")){
                            dialog.dismiss();
                        }
                    }
                });
             alerta.show();
            }
        });
        return vista;
    }

    private void ejecutar_servicio() {
        progress = new ProgressDialog(getActivity());
        progress.setMessage(charge);
        progress.show();
        url = urlx.getUrl() + "/padel/consulta_jugador5.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError errorr) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        progress.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getActivity(), conect, Toast.LENGTH_SHORT).show();
        Personajes personaje = null;
        JSONArray json = response.optJSONArray("jugador");

        try {
            for (int i = 0; i < json.length(); i++) {
                personaje = new Personajes();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                personaje.setNombre(jsonObject.optString("nombre"));
                personaje.setL_name(jsonObject.optString("apellido"));
                personaje.setRol(jsonObject.optInt("rol"));
                personaje.setId(jsonObject.optString("id"));
                personaje.setFoto(R.drawable.img_profile);
                datos.add(personaje);
                progress.hide();
                AdapterDatosAdmin adapterDatos = new AdapterDatosAdmin(datos);
                recyclerView.setAdapter(adapterDatos);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            progress.hide();
        }
    }
    public void modificaPlayer(int c){
JsonObjectRequest jsonObjectPlayer=new JsonObjectRequest(Request.Method.GET, urlPlayer+c, null,
        new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



            }
        }, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
    }
});
requestQueuePlayer.add(jsonObjectPlayer);
    }
    public void modificaAdmin(int c){
    JsonObjectRequest jsonObjectAdmin=new JsonObjectRequest(Request.Method.GET, urlAdmin+c, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
        }
    });
    requestQueueAdmin.add(jsonObjectAdmin);
    }
}
package com.example.tennis_liga;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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


public class LoginFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{

    EditText user;
    EditText pass;
    Button btn_json,btn_nuevoUsuario;
    RequestQueue queue;
    JsonObjectRequest jsonObjectRequest;
    String url="";
     ProgressDialog progreso;
      private Url urlX;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        queue= Volley.newRequestQueue(getActivity());
        View vista= inflater.inflate(R.layout.fragment_login, container, false);
        user=vista.findViewById(R.id.txt_user);
        pass=vista.findViewById(R.id.txt_password);
        btn_json=vista.findViewById(R.id.button);
        btn_nuevoUsuario=vista.findViewById(R.id.button2);
        urlX=new Url();
        btn_nuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(getActivity(),NuevoUsuario.class);
                startActivity(in);
            }
        });
        btn_json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutar();
            }
        });



        return vista;
    }
    public void ejecutar(){
        progreso= new ProgressDialog(getActivity());
        progreso.setMessage(getString(R.string.cargando));
        progreso.show();
        url=urlX.getUrl()+"/padel/consulta_jugador2.php?nombre="+user.getText().toString()
                +"&and&password="+pass.getText().toString();

        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        queue.add(jsonObjectRequest);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getActivity(), getString(R.string.erroC), Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onResponse(JSONObject response) {
progreso.hide();
        Personajes personaje= new Personajes();
        JSONArray json=response.optJSONArray("jugador");
        JSONObject jsonObject=null;

        try {
            jsonObject=json.getJSONObject(0);

             personaje.setL_name(jsonObject.optString("apellido"));
            personaje.setNombre(jsonObject.optString("nombre"));
            personaje.setNal(jsonObject.optString("nacionalidad"));
            personaje.setPais(jsonObject.optString("pais_actual"));
            personaje.setHit(jsonObject.optString("mejor_golpe"));
            personaje.setRacket(jsonObject.optString("raqueta"));
            personaje.setCord(jsonObject.optString("cordaje"));
            personaje.setStyle(jsonObject.optString("estilo_juego"));
             personaje.setSize(jsonObject.optString("talla_camiseta"));
            personaje.setPass(jsonObject.optString("password"));
            personaje.setRol(jsonObject.optInt("rol"));
            personaje.setId(jsonObject.optString("id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intento = new Intent(getActivity(),Home.class);
  Bundle bundle= new Bundle();
        int roles=(personaje.getRol());
        if(personaje.getNombre().equalsIgnoreCase(user.getText().toString())&&personaje.getPass().equalsIgnoreCase
                (pass.getText().toString())&&(roles==2||roles==3)){
            Intent in = new Intent(getActivity(),Administrador.class);
             bundle.putSerializable("usuario",personaje);
             in.putExtras(bundle);
            Toast.makeText(getActivity(), ""+personaje.getId(), Toast.LENGTH_SHORT).show();

            startActivity(in);
        }else if(personaje.getNombre().equalsIgnoreCase(user.getText().toString())&&personaje.getPass().equalsIgnoreCase(pass.getText().toString())&&
                roles==1){
            bundle.putSerializable("usuario",personaje);
            intento.putExtras(bundle);
            startActivity(intento);
        }else if(personaje.getNombre().equalsIgnoreCase(user.getText().toString())&&personaje.getPass().equalsIgnoreCase(pass.getText().toString())&&
                roles==0){
            bundle.putSerializable("usuario",personaje);
            intento.putExtras(bundle);
             startActivity(intento);
        }else
            Toast.makeText(getActivity(), getString(R.string.no_encuentra), Toast.LENGTH_SHORT).show();
    }
    }



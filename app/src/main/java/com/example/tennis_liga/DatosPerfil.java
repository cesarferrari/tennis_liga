package com.example.tennis_liga;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class DatosPerfil extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{

   private String recibeNombre,recibeApellido,recibeNacimiento,recibeNal,recibePais,recibeCamiseta,recibeEstilo,recibeGolpe,recibeRaqueta,recibeCordaje;
   private String recibeEfectividad;
   private EditText etname, etapell, etnac, etcountry, etsize, etstyle, ethit, etrak, etcord;
   private TextView tv_nombre,tv_efectividad;
   private ImageButton btn_editar;
   private RequestQueue requestQueue;
   private String urlChange;
   private Url urlx=new Url();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           recibeNombre=getArguments().getString("knombre","valor 0");
           recibeApellido=getArguments().getString("kapellido","valor 0");
            recibeNacimiento=getArguments().getString("knacimiento","valor 0");
            recibeNal=getArguments().getString("knal","valor 0");
            recibePais=getArguments().getString("kpais","valor 0");
            recibeCamiseta=getArguments().getString("kcamiseta","valor 0");
            recibeCordaje=getArguments().getString("kcordaje","valor 0");
            recibeEstilo=getArguments().getString("kestilo","valor 0");
                recibeGolpe=getArguments().getString("kgolpe","valor 0");
                recibeRaqueta=getArguments().getString("kraqueta","valor 0");
                recibeEfectividad=getArguments().getString("kefectividad","valor 0");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.fragment_datos_perfil, container, false);
        Toast.makeText(getActivity(), "recibe"+recibeCordaje+"t"+recibeNombre+"y"+recibeNacimiento, Toast.LENGTH_SHORT).show();
        etname = vista.findViewById(R.id.txt_nombreY);
        etapell = vista.findViewById(R.id.txt_apellidoY);
        etnac = vista.findViewById(R.id.txt_nacionalidadX);
        etcountry = vista.findViewById(R.id.txt_paisA);
        etstyle = vista.findViewById(R.id.txt_estiloY);
        ethit = vista.findViewById(R.id.txt_golpeA);
        etrak = vista.findViewById(R.id.txt_raquetaA);
        etcord = vista.findViewById(R.id.txt_cordajeB);
        etsize = vista.findViewById(R.id.txt_tallaX);
        tv_nombre= vista.findViewById(R.id.textView15);
        tv_efectividad=vista.findViewById(R.id.textEfectividad);
        btn_editar=vista.findViewById(R.id.imageButton3);
        btn_editar.setEnabled(false);
        etname.setText(recibeNombre);
                etapell.setText(recibeApellido);
                etnac.setText(recibeNal);
                etcountry.setText(recibePais);
                etsize.setText(recibeCamiseta);
                etstyle.setText(recibeEstilo);
                ethit.setText(recibeGolpe);
                etrak.setText(recibeRaqueta);
                etcord.setText(recibeCordaje);
                tv_nombre.setText(recibeNombre);
              //  tv_efectividad.append(recibeEfectividad);
                requestQueue= Volley.newRequestQueue(getActivity());
               Toast.makeText(getActivity(), "recibe"+recibeGolpe+recibeEstilo+recibeRaqueta+recibePais+recibeCamiseta+recibeNombre+recibeApellido+recibeNal+recibeCordaje, Toast.LENGTH_SHORT).show();
               return vista;
    }
    public void modificaUsuario(){
        urlChange=urlx.getUrl()+"padel/modificaUsuario.php";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,urlChange
                ,null,this,this);
    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
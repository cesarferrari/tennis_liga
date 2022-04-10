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


public class DatosPerfil extends Fragment {

   private String recibeNombre,recibeApellido,recibeNacimiento,recibeNal,recibePais,recibeCamiseta,recibeEstilo,recibeGolpe,recibeRaqueta,recibeCordaje;
   private EditText etname, etapell, etnac, etcountry, etsize, etstyle, ethit, etrak, etcord;
   private TextView tv_nombre;
   private ImageButton btn_editar;

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

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.fragment_datos_perfil, container, false);

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
        btn_editar=vista.findViewById(R.id.imageButton3);


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

             //   Toast.makeText(getActivity(), "recibe"+recibeGolpe+recibeEstilo+recibeRaqueta+recibePais+recibeCamiseta+recibeNombre+recibeApellido+recibeNal+recibeCordaje, Toast.LENGTH_SHORT).show();




        return vista;
    }
}
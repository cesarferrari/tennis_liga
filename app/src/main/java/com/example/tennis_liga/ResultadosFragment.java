package com.example.tennis_liga;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ResultadosFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    private RequestQueue requestQueue,requestQueue1,requestQueue_match;
    private RequestQueue requestQueueComprueba;
    String url="/",deber;
    private static Url urlx=new Url();
    private  static final String URL1=urlx.getUrl()+"/padel/consulta_competencia_spinner.php?";
    private static final String URL_match= urlx.getUrl()+"/padel/consulta_encuentro.php?competicion=";
    private static final String url_encuentro= urlx.getUrl()+"/padel/consulta_encuentroComprueba.php?id_encuentro=";
    private ProgressDialog progress,progress1;
   private JsonObjectRequest jsonObjectRequest,jsonObjectRequest1;
    int id_Encuentro=0;
    private boolean bandera=true;
    List<String>listarCompetencia_match;
    List<String>listarCompetencia;
    List<Competicion>listaDatos;
    List<Encuentro>listaDatos_match;


   private Spinner spin_competition,spin_match;
   private TextView txt_tie_breakP1, txt_tie_breakP2, txt_remontadaP1, txt_remontadaP2, txt_rContraP1, txt_rContraP2, txt_winnerP1, txt_winnerP2;
   private TextView player1,player2;
   private EditText set1_p1,set1_p2,set2_p1,set2_p2,set3_p1,set3_p2;
    int p1_set1=0;int p1_set2=0;int p1_set3=0;int p2_set1=0;int p2_set2=0;int p2_set3=0;int p1_sets=0;
    int p2_sets=0;int tie_breakP1=0;int tie_breakP2=0;int remontadaP1=0;int remontadaP2=0;int remontadaContraP1=0;
    int remontadaContraP2=0;int winnerP1=0;int winnerP2=0;int loserP1=0;int loserP2=0;int tercerSetP1=0;
    int tercerSetP2=0;int gamesP1=0;int gamesP2=0;
    int contador=0;
    String  id_seleccion="",competition;
    Button btn_insertar,nuevo;
    private final CharSequence[] opciones={"ya se han asignado resultados para ese encuentro desea cambiarlo","cancelar"};
    private int relax;
    String month;
    ArrayAdapter adapter,adapter_match;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.fragment_resultados, container, false);

        txt_tie_breakP1 =(TextView) vista.findViewById(R.id.tie_breakP1);
        txt_tie_breakP2 =(TextView) vista.findViewById(R.id.tie_breakP2);
        txt_remontadaP1 =(TextView) vista.findViewById(R.id.remontadaP1);
        txt_remontadaP2 =(TextView) vista.findViewById(R.id.remotadaP2);
        txt_rContraP1 =(TextView) vista.findViewById(R.id.againtsP1);
        txt_rContraP2 =(TextView) vista.findViewById(R.id.againtsP2);
        txt_winnerP1 =(TextView) vista.findViewById(R.id.winnerP1);
        txt_winnerP2 =(TextView) vista.findViewById(R.id.winnerP2);
        set1_p1=(EditText) vista.findViewById(R.id.txt_set1P1);
        set1_p2=(EditText) vista.findViewById(R.id.txt_set1P2);
        set2_p1=(EditText) vista.findViewById(R.id.txt_set2P1);
        set2_p2=(EditText) vista.findViewById(R.id.txt_set2P2);
        set3_p1=(EditText) vista.findViewById(R.id.txt_set3P1);
        set3_p2=(EditText) vista.findViewById(R.id.txt_set3P2);
        player1=(TextView) vista.findViewById(R.id.txt_player1);
        player2=(TextView) vista.findViewById(R.id.txt_player2);
        competition=getString(R.string.opcion);
btn_insertar=(Button) vista.findViewById(R.id.button);
nuevo=(Button)vista.findViewById(R.id.btn_new);
nuevo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        limpiar();
        btn_insertar.setEnabled(true);
        spin_match.setAdapter(null);
        player1.setText("");
        player2.setText("");
        spin_competition.setSelection(0);
    }
});

        requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue1= Volley.newRequestQueue(getActivity());
        requestQueue_match= Volley.newRequestQueue(getActivity());
        requestQueueComprueba=Volley.newRequestQueue(getActivity());

        limpiar();
        ejecuta_consulta();
btn_insertar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        try{
            datos();
            // Toast.makeText(getActivity(), ""+bandera, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(getActivity(),""+e, Toast.LENGTH_SHORT).show();
        }
        clean();
    }
});
  spin_competition=(Spinner) vista.findViewById(R.id.spinner_competicion);
  spin_match=(Spinner) vista.findViewById(R.id.spinner_match);
       /* ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getActivity(),R.array.meses,
                android.R.layout.simple_spinner_item);*/
    //   List<String>compites=ejecuta_consulta();

        spin_competition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String number[]=spin_competition.getSelectedItem().toString().split("-");
                 id_seleccion=number[1];
               ejecuta_consulta_match();
               // Toast.makeText(getActivity(), ""+id_seleccion, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
spin_match.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String arr_match[]=spin_match.getSelectedItem().toString().split("-");
        String p1=arr_match[1];
        String p2=arr_match[2];
        String idEncuentro=arr_match[0];
         id_Encuentro=Integer.parseInt(idEncuentro);
        player1.setText(p1);
        player2.setText(p2);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});


        return vista;
    }



    public List<Competicion> ejecuta_consulta(){
        progress1=new ProgressDialog(getActivity());
        progress1.setMessage("cargando...");
        progress1.show();
    listaDatos=new ArrayList<>();
        listarCompetencia=new ArrayList<>();
       listarCompetencia.add(" -"+competition);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL1, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Competicion compet=null;
                        JSONArray json = response.optJSONArray("competition");

                        try {
                       for (int i=0;i<json.length();i++){
                        compet= new Competicion();
                         JSONObject jsonObject=null;
                         jsonObject=json.getJSONObject(i);
                         compet.setId_competition(jsonObject.optString("id_competition"));
                         compet.setCompeticion(jsonObject.optString("competicion"));

                         listaDatos.add(compet);
                         listarCompetencia.add(listaDatos.get(i).getId_competition()+"-"+listaDatos.get(i).getCompeticion());
                           ArrayAdapter<String>adapter=new ArrayAdapter(getContext(),
                                   R.layout.support_simple_spinner_dropdown_item,listarCompetencia);

                           spin_competition.setAdapter(adapter);
                         }
                           // Toast.makeText(getActivity(), ""+listarCompetencia, Toast.LENGTH_SHORT).show();
                            progress1.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                       // Toast.makeText(getActivity(), ""+listaDatos, Toast.LENGTH_SHORT).show();

                        progress1.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "error insertar", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue1.add(request);
        return listaDatos;
    }
    public List<Encuentro> ejecuta_consulta_match(){
      //  progress1=new ProgressDialog(getActivity());
        //progress1.setMessage("cargando...");
        //progress1.show();
        listaDatos_match=new ArrayList<>();
        listarCompetencia_match=new ArrayList<>();

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL_match+id_seleccion, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Encuentro match=null;
                        JSONArray json_match = response.optJSONArray("jugador");

                        try {
                            for (int i=0;i<json_match.length();i++){
                                match= new Encuentro();
                                JSONObject jsonObject_match=null;
                                jsonObject_match=json_match.getJSONObject(i);
                                 match.setId_encuentro(jsonObject_match.optInt("id_encuentro"));
                                 match.setName_p1(jsonObject_match.optString("nombre1"));
                                 match.setLast_name_p1(jsonObject_match.optString("apellido1"));
                                match.setName_p2(jsonObject_match.optString("nombre2"));
                                match.setLast_name_p2(jsonObject_match.optString("apellido2"));
                               match.setType_match(jsonObject_match.optString("encuentro"));
                                listaDatos_match.add(match);
                                listarCompetencia_match.add(listaDatos_match.get(i).getId_encuentro()+"-"+listaDatos_match.get(i).getName_p1()
                                +" "+listaDatos_match.get(i).getLast_name_p1()+" - "+listaDatos_match.get(i).getName_p2()+" "+
                                        listaDatos_match.get(i).getLast_name_p2()+"-"+listaDatos_match.get(i).getType_match());
                                ArrayAdapter<String>adapter_match=new ArrayAdapter(getContext(),
                                        R.layout.support_simple_spinner_dropdown_item,listarCompetencia_match);


                                   spin_match.setAdapter(adapter_match);

                            }

          //                  progress1.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();




                        }
                        btn_insertar.setEnabled(true);
                        // Toast.makeText(getActivity(), ""+listaDatos, Toast.LENGTH_SHORT).show();

            //            progress1.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                spin_match.setAdapter(null);
                btn_insertar.setEnabled(false);
                player1.setText("");
                player2.setText("");

            }
        });
        requestQueue_match.add(request);
        return listaDatos_match;
    }

    private void ejecutar_servicio(){
        progress= new ProgressDialog(getActivity());
        progress.setMessage("cargando...");
        progress.show();

        url=urlx.getUrl()+"/padel/inserta_resultados.php?id_encuentro="+id_Encuentro+"&games_p1="+gamesP1
                +"&games_p2="+gamesP2+"&winner_p1="+winnerP1+
                "&winner_p2="+winnerP2+"&loser_p1="+loserP1+"&loser_p2="+loserP2+"&sets_p1="+
              p1_sets+"&sets_p2="+p2_sets+"&tie_break_p1="+tie_breakP1+ "&tie_break_p2="+tie_breakP2+"&remontada_p1="+
                remontadaP1+"&remontada_p2="+remontadaP2+"&remontadaContra_p1="+
                remontadaContraP1+"&remontadaContra_p2="+remontadaContraP2+"&tercer_set_p1="+tercerSetP1+
                "&tercer_set_p2="+tercerSetP2;
        url=url.replace(" ","%20");
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(), "error al insersion", Toast.LENGTH_SHORT).show();
        progress.hide();
    }
    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
        progress.hide();

    }
    public void datos() {
        p1_set1 = Integer.parseInt(this.set1_p1.getText().toString());
        p1_set2 = Integer.parseInt(this.set2_p1.getText().toString());
        p1_set3 = Integer.parseInt(this.set3_p1.getText().toString());
        p2_set1 = Integer.parseInt(this.set1_p2.getText().toString());
        p2_set2 = Integer.parseInt(this.set2_p2.getText().toString());
        p2_set3 = Integer.parseInt(this.set3_p2.getText().toString());
        if (p1_set1 > 7 || p1_set2 > 7 || p1_set3 > 7 || p2_set1 > 7 || p2_set2 > 7 || p2_set3 > 7 || p1_set1 < 0 || p1_set2 < 0 || p1_set3 < 0 || p2_set1 < 0 || p2_set2 < 0 || p2_set3 < 0) {
            Toast.makeText(getActivity(), "marcador invalido", Toast.LENGTH_SHORT).show();
            limpiar();
        } else if ((p1_set1 == 5 && p2_set1 == 6) || (p2_set1 == 5 && p1_set1 == 6) || (p1_set2 == 5 && p2_set2 == 6) || (p2_set2 == 5 && p1_set2 == 6) || (p1_set3 == 5 && p2_set3 == 6) || (p2_set3 == 5 && p1_set3 == 6)) {
            Toast.makeText(getActivity(), "marcador invalido", Toast.LENGTH_SHORT).show();
            limpiar();
        } else if ((p1_set1 == 7 && p2_set1 != 6) || (p1_set1 != 6 && p2_set1 == 7) || (p1_set2 == 7 && p2_set2 != 6) || (p1_set2 != 6 && p2_set2 == 7) || (p1_set3 == 7 && p2_set3 != 6) || (p1_set3 != 6 && p2_set3 == 7)) {
            Toast.makeText(getActivity(), "marcador invalido", Toast.LENGTH_SHORT).show();
            limpiar();
        } else if ((p1_set1 == 6 && p2_set1 == 6) || (p1_set2 == 6 && p2_set2 == 6) || p1_set3 == 6 && p2_set3 == 6) {
            Toast.makeText(getActivity(), "marcador invalido", Toast.LENGTH_SHORT).show();
            limpiar();
        } else if ((p1_set1 < 6 && p2_set1 < 6) || (p2_set2 < 6 && p1_set2 < 6) || p1_set3 < 6 && p1_set3 > 0 && p2_set3 < 6 && p2_set3 > 0) {
            Toast.makeText(getActivity(), "marcador invalido", Toast.LENGTH_SHORT).show();
            limpiar();
        } else if ((p1_set1 > p2_set1 && p1_set2 > p2_set2 || p2_set1 > p1_set1 && p2_set2 > p1_set2)) {

            this.set3_p1.setText("0");
            this.set3_p2.setText("0");
            p1_set3 = 0;
            p2_set3 = 0;
        }
        if ((p1_set1 > p2_set1 && p1_set2 < p2_set2) && (p1_set3 == 0 && p2_set3 == 0)) {
            Toast.makeText(getActivity(), "marcador invalido", Toast.LENGTH_SHORT).show();
            limpiar();
        } else if ((p1_set1 < p2_set1 && p1_set2 > p2_set2) && (p1_set3 == 0 && p2_set3 == 0)) {
            Toast.makeText(getActivity(), "marcador invalido", Toast.LENGTH_SHORT).show();
            limpiar();
        }

        if (p1_set1 == 7 && p2_set1 == 6) {
            tie_breakP1++;
        }
        if (p1_set1 == 6 && p2_set1 == 7) {
            tie_breakP2++;
        }
        if (p1_set2 == 7 && p2_set2 == 6) {
            tie_breakP1++;
        }
        if (p1_set2 == 6 && p2_set2 == 7) {
            tie_breakP2++;
        }
        if (p1_set3 == 7 && p2_set3 == 6) {
            tie_breakP1++;
        }
        if (p1_set3 == 6 && p2_set3 == 7) {
            tie_breakP2++;
        }
        // JOptionPane.showMessageDialog(null,""+tie_breakP1+" jugador 2"+tie_breakP2);
        this.txt_tie_breakP1.setText("" + tie_breakP1);
        this.txt_tie_breakP2.setText("" + tie_breakP2);
        //  tie_breakP1=0;
        //  tie_breakP2=0;

        if (p1_set1 > p2_set1) {
            p1_sets++;
        } else if (p2_set1 > p1_set1) {
            p2_sets++;
        }
        if (p1_set2 > p2_set2) {
            p1_sets++;
        } else if (p2_set2 > p1_set2) {
            p2_sets++;
        }
        if (p1_set3 > p2_set3) {
            p1_sets++;
            tercerSetP1++;
        } else if (p2_set3 > p1_set3) {
            p2_sets++;
            tercerSetP2++;
        }
        // JOptionPane.showMessageDialog(null,""+p1_sets+"  "+p2_sets);
        //  p1_sets=0;p2_sets=0;

        if (p1_set1 > p2_set1 && p1_set2 < p2_set2 && p1_set3 < p2_set3) {

            remontadaP2++;
            remontadaContraP1++;
            this.txt_remontadaP2.setText("" + remontadaP2);
            this.txt_rContraP1.setText("" + remontadaContraP1);

        }
        if (p1_set1 < p2_set1 && p1_set2 > p2_set2 && p1_set3 > p2_set3) {
            remontadaP1++;
            remontadaContraP2++;
            this.txt_remontadaP1.setText("" + remontadaP1);
            this.txt_rContraP2.setText("" + remontadaContraP2);
        }
        //remontadaP1=0;
        //remontadaP2=0;
        //remontadaContraP1=0;
        //  remontadaContraP2=0;
        if (p1_sets > p2_sets) {
            this.txt_winnerP1.setText("W");
            this.txt_winnerP2.setText("L");
            winnerP1++;
            loserP2++;
        } else if (p1_sets < p2_sets) {
            this.txt_winnerP2.setText("W");
            this.txt_winnerP1.setText("L");
            winnerP2++;
            loserP1++;
        }
        gamesP1 = p1_set1 + p1_set2 + p1_set3;
        gamesP2 = p2_set1 + p2_set2 + p2_set3;
        ;
        Toast.makeText(getActivity(), "winner 1" + winnerP1 + "\nwninner " + winnerP2 + "\nloser" + loserP1 + "\nloser" + loserP2 + "\ntercer P1" + tercerSetP1 + "\ntercer p2"
                + tercerSetP2 + "games uno" + gamesP1 + "\ngames dos" + gamesP2, Toast.LENGTH_SHORT).show();
        if (p1_set1 == 0 && p1_set2 == 0 && p1_set3 == 0 && p2_set1 == 0 && p2_set2 == 0 && p2_set3 == 0 && p1_sets == 0 && p2_sets == 0 && tie_breakP1 == 0
                && tie_breakP2 == 0 && remontadaP1 == 0 && remontadaP2 == 0 && remontadaContraP1 == 0
                && remontadaContraP2 == 0 && winnerP1 == 0 && winnerP2 == 0 && loserP1 == 0 && loserP2 == 0 && tercerSetP1 == 0 && tercerSetP2 == 0) {

        } else {
            Compara(id_Encuentro);



        }
    }


    public void limpiar(){
        p1_set1=0;p1_set2=0;p1_set3=0;p2_set1=0;p2_set2=0;p2_set3=0;p1_sets=0;p2_sets=0;tie_breakP1=0;
        tie_breakP2=0;remontadaP1=0;remontadaP2=0;remontadaContraP1=0;
        remontadaContraP2=0;winnerP1=0;winnerP2=0;loserP1=0;loserP2=0;tercerSetP1=0;tercerSetP2=0;set1_p1.setText("");
        set2_p1.setText("");set3_p1.setText("");set1_p2.setText("");set2_p2.setText("");set3_p2.setText("");
        this.txt_tie_breakP1.setText("");this.txt_tie_breakP2.setText("");
        this.txt_rContraP1.setText("");this.txt_rContraP2.setText("");this.txt_remontadaP1.setText("");
        this.txt_remontadaP2.setText("");this.txt_winnerP1.setText("");this.txt_winnerP2.setText("");

    }
    public void clean(){
        p1_set1=0;p1_set2=0;p1_set3=0;p2_set1=0;p2_set2=0;p2_set3=0;p1_sets=0;p2_sets=0;tie_breakP1=0;
        tie_breakP2=0;remontadaP1=0;remontadaP2=0;remontadaContraP1=0;remontadaContraP2=0;winnerP1=0;
        winnerP2=0;loserP1=0;loserP2=0;tercerSetP1=0;tercerSetP2=0;
    }
    public int Compara(int c){
        JsonObjectRequest jsonObjectCompara=new JsonObjectRequest(Request.Method.GET, url_encuentro+c, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    JSONArray array=response.optJSONArray("jugador");
                    try{
                        for (int i=0;i<array.length();i++){
                            JSONObject json=array.getJSONObject(i);

                            if(json.optString("games_1")!=null){


                                contador++;
                                Toast.makeText(getActivity(), ""+contador, Toast.LENGTH_SHORT).show();
                            }

                        }

                    }catch(JSONException e){

                    }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        if(contador==0){
            ejecutar_servicio();
        }else {
            Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
        }
        if (contador==0) {
            Toast.makeText(getActivity(), "contador="+contador, Toast.LENGTH_SHORT).show();



        } else {

            AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
            alerta.setTitle("opciones");
            alerta.setItems(opciones, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (opciones[which].equals("ya se han asignado resultados para ese encuentro desea cambiarlo")) {

                        Toast.makeText(getActivity(), "metodo uodate ", Toast.LENGTH_SHORT).show();
                        contador=0;
                    } else if (opciones[which].equals("cancelar")) {
                        clean();
                        limpiar();
                        dialog.dismiss();
                        contador=0;
                    }
                }
            });
            alerta.show();
        }

        requestQueueComprueba.add(jsonObjectCompara);
      return contador;
    }
}




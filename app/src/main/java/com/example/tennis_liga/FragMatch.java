package com.example.tennis_liga;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class FragMatch extends Fragment {
Spinner spin_compet,spinPlayer1,spinPlayer2;
Button btn_selecciona,btn_nuevo;
EditText txt_evento;
private RequestQueue requestQueueExists;
ProgressDialog progress,progress1,progress2;
String players,competition,same;
private static Url urlx= new  Url();
private  String tipo_evento ,urlInsertar;
private  int id_jugador1,id_jugador2,id_competicion;
private static final String urlcompetition=urlx.getUrl()+"/padel/consulta_competencia_spinner.php";
private static final String urlPersonajes=urlx.getUrl()+"/padel/consulta_jugador.php";
private static final String urlExists=urlx.getUrl()+"/padel/consulta_match.php?id_competition=";
    private RequestQueue requestQueue_match,requestQueue_personajes,requestQueueInsert;
    private JsonObjectRequest jsonObjectRequest;
  private List<Competicion>listaData_match;
  private List<String>listaData;
  private List<Personajes>personajes;
  private List<String>listaPersonajes;
private Personajes pers=null;
private String validar="";
    String arrCompet[];
    String arrPlayer1[];
    String arrPlayer2[];
boolean bandera;
    private static final String resto="resto";
private int contador=0;
private int id_competition;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_frag_match, container, false);
        players=getString(R.string.choose_player1);
        competition=getString(R.string.opcion);
        same=getString(R.string.same_player);
bandera=true;

         spin_compet=(Spinner)vista.findViewById(R.id.spin_compet);
         txt_evento=(EditText) vista.findViewById(R.id.txt_match);

         spinPlayer1=(Spinner)vista.findViewById(R.id.spin_player1);
        spinPlayer2=(Spinner)vista.findViewById(R.id.spin_player2);
         btn_selecciona=(Button)vista.findViewById(R.id.btn_mostrar);
         btn_nuevo=(Button)vista.findViewById(R.id.btn_new);
         requestQueue_match= Volley.newRequestQueue(getActivity());
         requestQueue_personajes=Volley.newRequestQueue(getActivity());
         requestQueueInsert=Volley.newRequestQueue(getActivity());
         requestQueueExists=Volley.newRequestQueue(getActivity());
        ejecuta_consulta_match();
        ejecutaPersonajes();
        id_competicion=0;
        id_jugador1=0;
        id_jugador2=0;


         btn_selecciona.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
            try{


            //  datos();
                btn_selecciona.setEnabled(false);
                Toast.makeText(getActivity(), "out"+tipo_evento, Toast.LENGTH_SHORT).show();
                if(spin_compet.getSelectedItem().toString().equalsIgnoreCase(competition)||spinPlayer1.getSelectedItem().
                        toString().equalsIgnoreCase(players)||spinPlayer2.getSelectedItem().toString().equalsIgnoreCase(players)){
                    Toast.makeText(getActivity(), ""+competition, Toast.LENGTH_SHORT).show();
                }else if(spinPlayer1.getSelectedItem().toString().equalsIgnoreCase(spinPlayer2.getSelectedItem().toString())){
                    Toast.makeText(getActivity(), ""+same, Toast.LENGTH_SHORT).show();
                }else if(txt_evento.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "llene todos los campos", Toast.LENGTH_SHORT).show();
                }else {
                    insertar();
                }
            }catch(Exception e){
                Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
            }

             }
         });
         btn_nuevo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 btn_selecciona.setEnabled(true);
                 clean();
             }
         });




        return  vista;
    }
    public List<Competicion> ejecuta_consulta_match(){
          progress=new ProgressDialog(getActivity());
          progress.setMessage("cargando...");
        progress.show();

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, urlcompetition, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Competicion comp= null;
                        listaData=new ArrayList<>();
                        listaData_match=new ArrayList<>();
                        listaData.add(competition);
                        JSONArray json = response.optJSONArray("competition");

                        try {
                            for (int i=0;i<json.length();i++){
                                comp= new Competicion();
                                JSONObject jsonObject_match=null;
                                jsonObject_match=json.getJSONObject(i);
                                comp.setId_competition(jsonObject_match.optString("id_competition"));
                                comp.setCompeticion(jsonObject_match.optString("competicion"));
                                listaData_match.add(comp);
                                listaData.add(listaData_match.get(i).getId_competition()+"-"+listaData_match
                                .get(i).getCompeticion());
                                ArrayAdapter<String> adapter_match=new ArrayAdapter(getContext(),
                                        R.layout.support_simple_spinner_dropdown_item,listaData);

                                spin_compet.setAdapter(adapter_match);
                            }

                                              progress.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Toast.makeText(getActivity(), ""+listaDatos, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progress.hide();
                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_match.add(request);
        return listaData_match;
    }
    public void ejecutaPersonajes(){
        progress1=new ProgressDialog(getActivity());
        progress1.setMessage("chargin...");
        progress1.show();
        personajes=new ArrayList<>();
        listaPersonajes=new ArrayList<>();
        listaPersonajes.add(players);
        JsonObjectRequest requestPersonajes= new JsonObjectRequest(Request.Method.GET, urlPersonajes, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray json=response.optJSONArray("jugador");
                        try{
                            for (int i=0;i<json.length();i++){
                                pers=new Personajes();
                                JSONObject jsonObject=json.getJSONObject(i);
                                pers.setId(jsonObject.optString("id"));
                                pers.setName(jsonObject.optString("nombre"));
                                pers.setL_name(jsonObject.optString("apellido"));
                                personajes.add(pers);
                                listaPersonajes.add(personajes.get(i).getId()+"-"+personajes.get(i).getNombre()+"-"+personajes.get(i).getL_name());
                                ArrayAdapter<String> adapter=new ArrayAdapter(getContext(),
                                        R.layout.support_simple_spinner_dropdown_item,listaPersonajes);
                                spinPlayer1.setAdapter(adapter);
                                spinPlayer2.setAdapter(adapter);
                            }
                              progress1.hide();

                        }catch(JSONException exception){
                            Toast.makeText(getActivity(), ""+exception, Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress1.hide();
            }
        });
        requestQueue_personajes.add(requestPersonajes);
    }


    private void insertar() {
        String  arrCompet[]=spin_compet.getSelectedItem().toString().split("-");
        String arrPlayer1[]=spinPlayer1.getSelectedItem().toString().split("-");
        String   arrPlayer2[]=spinPlayer2.getSelectedItem().toString().split("-");
        id_competicion=Integer.parseInt(arrCompet[0]);
        id_jugador1=Integer.parseInt(arrPlayer1[0]);
        id_jugador2=Integer.parseInt(arrPlayer2[0]);
         progress2= new ProgressDialog(getActivity());
         progress2.setMessage("charging");
         progress2.show();
        urlInsertar=urlx.getUrl()+"/padel/inserta_match.php?id_competition="+id_competicion+"&player1="+id_jugador1
                +"&player2="+id_jugador2+"&tipo_encuentro="+txt_evento.getText().toString();
        urlInsertar=urlInsertar.replace(" ","%20");
         jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, urlInsertar, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progress2.hide();
                        Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "error al ingresar datos "+error, Toast.LENGTH_SHORT).show();
                      progress2.hide();
            }
        }
        );
        requestQueueInsert.add(jsonObjectRequest);
    }
    public void clean(){
        id_jugador2=0;
        id_jugador1=0;
        id_competicion=0;
         spin_compet.setSelection(0);
         spinPlayer2.setSelection(0);
         spinPlayer1.setSelection(0);

        txt_evento.setText("");
    }
    public void datos(){
        String  arrCompet[]=spin_compet.getSelectedItem().toString().split("-");
        String arrPlayer1[]=spinPlayer1.getSelectedItem().toString().split("-");
        String   arrPlayer2[]=spinPlayer2.getSelectedItem().toString().split("-");
        id_competicion=Integer.parseInt(arrCompet[0]);
        id_jugador1=Integer.parseInt(arrPlayer1[0]);
        id_jugador2=Integer.parseInt(arrPlayer2[0]);


         JsonObjectRequest jsonObjectGenerales= new JsonObjectRequest(Request.Method.GET, urlExists + id_competicion
                 , null, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 JSONArray array = response.optJSONArray("jugador");
                 try {
                     for (int i = 0; i < array.length(); i++) {
                         JSONObject json = array.getJSONObject(i);
                         if (txt_evento.getText().toString().equalsIgnoreCase(json.optString("tipo_encuentro"))) {
                             tipo_evento = "evento";
                         }


                     }


                 } catch (JSONException e) {

                 }
             }

         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
             }
         });
        if (tipo_evento.equalsIgnoreCase("evento")) {
            txt_evento.setText("");
            Toast.makeText(getActivity(), "el evento ya existe en la bd ", Toast.LENGTH_SHORT).show();

        } else {

        }
         requestQueueExists.add(jsonObjectGenerales);

        }
    }











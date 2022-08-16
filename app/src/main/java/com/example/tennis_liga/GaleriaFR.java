package com.example.tennis_liga;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import java.util.List;


public class GaleriaFR extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
  private TextView crazy;
   private List<Resultados>result;
   private List<Integer>enteros;
   private Button estadisticas;
   private ArrayList<Personajes> ListDatos;
   private Resultados1 res;
   private Imagenes imgs;
    private RecyclerView recyclerView;
    private Button grid,list,generales;
    private RequestQueue requestQueue,requestQueueGames1,requestQueueGames2,requestQueueSets1,requestQueueSets2;
    private RequestQueue requestQueueWinner1,requestQueueWinner2,requestQueueLoser1,requestQueueLoser2,requestQueueTie_break1;
    private RequestQueue requestQueueTie_break2,requestQueueTercer1,requestQueueTercer2,requestQueueRemontada1,requestQueueRemontada2;
    private RequestQueue requestQueueContra1,requestQueueContra2,requestQueuegamesT1,requestQueuegamesT2,requestQueueSetsT1,requestQueueSetsT2;
    private RequestQueue requestQueueTercerT1,requestQueueTercerT2,requestQueueTieT1,requestQueueTieT2,requestQueueLast;
   private RequestQueue requestQueueGenerales;
    private String url="/",deber,error,conect,charge,nombre,apellido;
     private String uno="",dos="",urlImagen;
     private List<String>title1,title2,title3,title4;
     private List<Integer>title;
     private Personajes personaje=null;
     private Personajes pers=null;
     private static  Url urlx=new Url();
     public int g1=0,g2=0,s1=0,s2=0,w1=0,w2=0,l1=0,l2=0,tie1=0,tie2=0,tercer1=0,tercer2=0,remont1=0,remont2=0,contra1=0,contra2=0,gamesT1=0,gamesT2=0,setsT1=0,setsT2=0,tieT1=0,tieT2=0,tercerT1=0,tercerT2=0,x=0;
     public int gx=0,sx=0,wx=0,lx=0,tiex=0,tercerx=0,remontx=0,contrax=0,c=0,porcientoEfe=0;
     public int totalW=0,totalRemont=0,totalTercer=0,totalG=0,totalS=0,totalTie=0, superTotalG=0, superTotalS=0,restaTercer=0,restaTie=0;
     private static final String urlGames1=urlx.getUrl()+"/padel/consulta_games1.php?player1=";
    private static final String urlGames2=urlx.getUrl()+"/padel/consulta_games2.php?player2=";
    private static final String urlSets1=urlx.getUrl()+"/padel/consulta_sets1.php?player1=";
    private static final String urlSets2=urlx.getUrl()+"/padel/consulta_sets2.php?player2=";
    private static final String urlWinner1=urlx.getUrl()+"/padel/consulta_winner1.php?player1=";
    private static final String urlWinner2=urlx.getUrl()+"/padel/consulta_winner2.php?player2=";
    private static final String urlLoser1=urlx.getUrl()+"/padel/consulta_loser1.php?player1=";
    private static final String urlLoser2=urlx.getUrl()+"/padel/consulta_loser2.php?player2=";
    private static final String urlTie_break1=urlx.getUrl()+"/padel/consulta_tie_break1.php?player1=";
    private static final String urlTie_break2=urlx.getUrl()+"/padel/consulta_tie_break2.php?player2=";
    private static final String urlTercer1=urlx.getUrl()+"/padel/consulta_tercer_set1.php?player1=";
    private static final String urlTercer2=urlx.getUrl()+"/padel/consulta_tercer_set2.php?player2=";
    private static final String urlRemontada1=urlx.getUrl()+"/padel/consulta_remontada1.php?player1=";
    private static final String urlRemontada2=urlx.getUrl()+"/padel/consulta_remontada2.php?player2=";
    private static final String urlContra1=urlx.getUrl()+"/padel/consulta_remontadaContra1.php?player1=";
    private static final String urlContra2=urlx.getUrl()+"/padel/consulta_remontadaContra2.php?player2=";
    private static final String urlGamesT1=urlx.getUrl()+"/padel/consulta_gamesT1.php?player1=";
    private static final String urlGamesT2=urlx.getUrl()+"/padel/consulta_gamesT2.php?player2=";
    private static final String urlSetsT1=urlx.getUrl()+"/padel/consulta_setsT1.php?player1=";
    private static final String urlSetsT2=urlx.getUrl()+"/padel/consulta_setsT2.php?player2=";
    private static final String urlTercerT1=urlx.getUrl()+"/padel/consulta_tercer_setT1.php?player1=";
    private static final String urlTercerT2=urlx.getUrl()+"/padel/consulta_tercer_setT2.php?player2=";
    private static final String urlTieT1=urlx.getUrl()+"/padel/consulta_tie_breakT1.php?player1=";
    private static final String urlTieT2=urlx.getUrl()+"/padel/consulta_tie_breakT2.php?player2=";
    private static final String urlgenerales=urlx.getUrl()+"/padel/consulta_jugador4.php?id=";
    private static  String urlLast=urlx.getUrl()+"/padel/consulta_last.php?player1=";
    private ProgressDialog progress;
    private JsonObjectRequest jsonObjectRequest;
    AdapterDatos adapterDatos;
    private String idPlayer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          idPlayer=getArguments().getString("kid","valor0");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_galeria_f_r, container, false);
        ListDatos= new ArrayList<Personajes>();
        res=new Resultados1();
        result= new ArrayList<>();
        crazy=(TextView) vista.findViewById(R.id.txt_g1);
        recyclerView=vista.findViewById(R.id.recycler_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        grid=vista.findViewById(R.id.btn_gridAdmin);
        list=vista.findViewById(R.id.btn_listAdmin);
        AdapterDatos adapter = new AdapterDatos(ListDatos);
        error=getString(R.string.erroC);
        conect=getString(R.string.conexion);
        charge=getString(R.string.cargando);
        requestQueue= Volley.newRequestQueue(getActivity());
        requestQueueGames1= Volley.newRequestQueue(getActivity());
        requestQueueGames2= Volley.newRequestQueue(getActivity());
        requestQueueSets1= Volley.newRequestQueue(getActivity());
        requestQueueSets2= Volley.newRequestQueue(getActivity());
        requestQueueTercer1= Volley.newRequestQueue(getActivity());
        requestQueueTercer2= Volley.newRequestQueue(getActivity());
        requestQueueWinner1= Volley.newRequestQueue(getActivity());
        requestQueueWinner2= Volley.newRequestQueue(getActivity());
        requestQueueLoser1= Volley.newRequestQueue(getActivity());
        requestQueueLoser2= Volley.newRequestQueue(getActivity());
        requestQueueRemontada1= Volley.newRequestQueue(getActivity());
        requestQueueRemontada2= Volley.newRequestQueue(getActivity());
        requestQueueContra1= Volley.newRequestQueue(getActivity());
        requestQueueContra2= Volley.newRequestQueue(getActivity());
        requestQueueTie_break1= Volley.newRequestQueue(getActivity());
        requestQueueTie_break2= Volley.newRequestQueue(getActivity());
        requestQueuegamesT1= Volley.newRequestQueue(getActivity());
        requestQueuegamesT2= Volley.newRequestQueue(getActivity());
        requestQueueSetsT1= Volley.newRequestQueue(getActivity());
        requestQueueSetsT2= Volley.newRequestQueue(getActivity());
        requestQueueTieT1= Volley.newRequestQueue(getActivity());
        requestQueueTieT2= Volley.newRequestQueue(getActivity());
        requestQueueTercerT1= Volley.newRequestQueue(getActivity());
        requestQueueTercerT2= Volley.newRequestQueue(getActivity());
        requestQueueLast=Volley.newRequestQueue(getActivity());
        requestQueueGenerales=Volley.newRequestQueue(getActivity());
        generales=(Button)vista.findViewById(R.id.btn_jugador);
        generales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generales();

            }
        });
        estadisticas=(Button)vista.findViewById(R.id.btn_estadisticas);

        estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(wx==0&&lx==0){
                   single();
               }else{
                   Toast.makeText(getActivity(), "no tienes partidos aun", Toast.LENGTH_SHORT).show();
               }


            }
        });
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
        ejecutar_servicio();
        //llenarPersonajes();

         adapter.setOnClickListenter(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 imgs=new Imagenes();
             String mark= ListDatos.get(recyclerView.getChildAdapterPosition(v)).getId();
                    nombre=ListDatos.get(recyclerView.getChildAdapterPosition(v)).getNombre();
              c=Integer.parseInt(mark);
                 estadisticas.setEnabled(true);
                 generales.setEnabled(true);
            try{
                urlImagen= imgs.getArray().get(c);
                Toast.makeText(getActivity(), ""+urlImagen, Toast.LENGTH_SHORT).show();
            }catch(Exception e){
                Toast.makeText(getActivity(), "imagen"+e, Toast.LENGTH_SHORT).show();
            }

              //   startActivity(in);
                 game1(c);
                 game2(c);
                 set1(c);
                  set2(c);
                 win1(c);
                  win2(c);
                  loser1(c);
                  loser2(c);
                  tie_break1(c);
                  tie_break2(c);
                  tercer_set1(c);
                 tercer_set2(c);
                  remontada1(c);
                  remontada2(c);
                  contra1(c);
                  contra2(c);
                  gamesT1(c);
                  gamesT2(c);
                  setsT1(c);
                  setsT2(c);
                  tercerT1(c);
                  tercerT2(c);
                  tieT1(c);
                  tieT2(c);
                  last(c);
                  datosGenerales(c);

                c=0;

             }
         });

        return vista;
    }
    public void lastGames(){
      title=new ArrayList<>();
        if(title1.size()==0){
            for(int i=0;i<5;i++){
                title.add(9999);
            }
        }
        if(title1.size()==1){
            for(int i=0;i<title1.size();i++){
                if(Integer.parseInt(title1.get(i))==x){
                    title.add(Integer.parseInt(title3.get(i)));
                }else if(Integer.parseInt(title2.get(i))==x){
                    title.add(Integer.parseInt(title4.get(i)));
                }
            }
            title.add(9999);
            title.add(9999);
            title.add(9999);
            title.add(9999);
        }
        if(title1.size()==2){
            for(int i=0;i<title1.size();i++){
                if(Integer.parseInt(title1.get(i))==x){
                    title.add(Integer.parseInt(title3.get(i)));
                }else if(Integer.parseInt(title2.get(i))==x){
                    title.add(Integer.parseInt(title4.get(i)));
                }
            }
            title.add(9999);
            title.add(9999);
            title.add(9999);

        }
        if(title1.size()==3){
            for(int i=0;i<title1.size();i++){
                if(Integer.parseInt(title1.get(i))==x){
                    title.add(Integer.parseInt(title3.get(i)));
                }else if(Integer.parseInt(title2.get(i))==x){
                    title.add(Integer.parseInt(title4.get(i)));
                }
            }
            title.add(9999);
            title.add(9999);


        }
        if(title1.size()==4){
            for(int i=0;i<title1.size();i++){
                if(Integer.parseInt(title1.get(i))==x){
                    title.add(Integer.parseInt(title3.get(i)));
                }else if(Integer.parseInt(title2.get(i))==x){
                    title.add(Integer.parseInt(title4.get(i)));
                }
            }
            title.add(9999);
        }

            for(int i=0;i<title1.size();i++){
                if(Integer.parseInt(title1.get(i))==x){
                    title.add(Integer.parseInt(title3.get(i)));
                }else if(Integer.parseInt(title2.get(i))==x){
                    title.add(Integer.parseInt(title4.get(i)));
                }
            }

    }

    public void generales(){
Intent in= new Intent(getActivity(),Prueba.class);

        lx=l1+l2;
        wx=w1+w2;
        totalW=wx+lx;
        porcientoEfe=(wx*100)/totalW;
        try{
            Bundle bd=new Bundle();
            pers.setUrlImagePrincipal(urlImagen);

            pers.setEfectividad(""+porcientoEfe);
            bd.putSerializable("generales",pers);
            // Toast.makeText(getActivity(), ""+porcientoEfe, Toast.LENGTH_SHORT).show();
            in.putExtras(bd);
            startActivity(in);
        }catch(Exception e){
            Toast.makeText(getActivity(), "intento"+e, Toast.LENGTH_SHORT).show();
        }

    }
    public void single(){

        Intent intento= new Intent(getActivity(),Estadisticas.class);

        Bundle bdl= new Bundle();
       intento.putExtra("user",idPlayer);
lastGames();

     gx=g1+g2;
     sx=s1+s2;
     wx=w1+w2;
     lx=l1+l2;
     tiex=tie1+tie2;
     tercerx=tercer1+tercer2;
     remontx=remont1+remont2;
     contrax=contra1+contra2;
     totalW=wx+lx;

     totalTercer=tercerT1+tercerT2;
     totalG=gamesT1+gamesT2;
     totalS=setsT1+setsT2;
     totalTie=tieT1+tieT2;
     totalRemont=remontx+contrax;
     superTotalG =totalG+gx;
     superTotalS =totalS+sx;
     restaTercer=totalTercer+tercerx;
     restaTie=totalTie+tiex;
     res.setGames(gx);
     res.setSets(sx);
     res.setWins(wx);
     res.setLoses(lx);
     res.setReassambled(remontx);
     res.setAgaints(contrax);
     res.setTie_break(tiex);
     res.setThird_set(tercerx);
     res.setTotalG(totalG);
     res.setTotalS(totalS);
     res.setSets(sx);
     res.setTotalRemont(totalRemont);
     res.setTotalTercer(totalTercer);
     res.setTotalTie(totalTie);
     res.setTotalW(totalW);
     res.setSuperTotalG(superTotalG);
     res.setSuperTotalS(superTotalS);
     res.setRestaTercer(restaTercer);
     res.setRestaTie(restaTie);
     res.setNombre(nombre);
     res.setFirst(title.get(0));
        res.setSecond(title.get(1));
        res.setThird(title.get(2));
        res.setFourth(title.get(3));
        res.setFifth(title.get(4));
        res.setUrlImagePrincipal(urlImagen);
        bdl.putSerializable("resultados",res);
     intento.putExtras(bdl);
     startActivity(intento);


    }

    public void ejecutar_servicio(){
        progress= new ProgressDialog(getActivity());
        progress.setMessage(charge);
        progress.show();
        url=urlx.getUrl()+"/padel/consulta_jugador.php";
       
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

        JSONArray json = response.optJSONArray("jugador");
        try {
        for (int i=0;i<json.length();i++){
           personaje= new Personajes();
           JSONObject jsonObject=null;
           jsonObject=json.getJSONObject(i);
           personaje.setNombre(jsonObject.optString("nombre"));
           personaje.setL_name(jsonObject.optString("apellido"));
           personaje.setId(jsonObject.optString("id"));
           personaje.setSize(jsonObject.optString("talla_camiseta"));
           personaje.setStyle(jsonObject.optString("estilo_juego"));
           personaje.setNal(jsonObject.optString("nacionalidad"));
           personaje.setRacket(jsonObject.optString("raqueta"));
           personaje.setCord(jsonObject.optString("cordaje"));
           personaje.setHit(jsonObject.optString("mejor_golpe"));
           personaje.setPais(jsonObject.optString("pais_actual"));


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

    public void game1(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlGames1+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("games1");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());

                            String title = jsonG1.getString("suma");
                            g1 = Integer.parseInt(title);
                    }
                    Toast.makeText(getActivity(), "clase "+g1, Toast.LENGTH_SHORT).show();
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
   requestQueueGames1.add(jsonObjectRequestG1);


    }
    public void game2(int c){


        JsonObjectRequest jsonObjectRequestG2=new JsonObjectRequest(Request.Method.GET, urlGames2+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray arrG2 = response.getJSONArray("games2");
                    for (int i = 0; i < arrG2.length(); i++) {
                        JSONObject jsonG2 = null;
                        jsonG2 = new JSONObject(arrG2.get(i).toString());
                        String title = jsonG2.getString("suma");
                        g2 = Integer.parseInt(title);

                    }




                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();

                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueueGames2.add(jsonObjectRequestG2);
//https://sofipo.000webhostapp.com

    }
    public void set1(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlSets1+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("sets1");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        s1 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueSets1.add(jsonObjectRequestG1);
    }
    public void set2(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlSets2+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("sets2");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        s2 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueSets2.add(jsonObjectRequestG1);
    }
    public void win1(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlWinner1+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("winner1");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        w1 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueWinner1.add(jsonObjectRequestG1);
    }
    public void win2(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlWinner2+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("winner2");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        w2 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueWinner2.add(jsonObjectRequestG1);
    }
    public void loser1(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlLoser1+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("loser1");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        l1 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueLoser1.add(jsonObjectRequestG1);
    }
    public void loser2(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlLoser2+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("loser2");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        l2 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueLoser2.add(jsonObjectRequestG1);
    }
    public void tie_break1(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlTie_break1+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("tie_break1");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        tie1 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueTie_break1.add(jsonObjectRequestG1);
    }
    public void tie_break2(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlTie_break2+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("tie_break2");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        tie2 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueTie_break2.add(jsonObjectRequestG1);
    }
    public void tercer_set1(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlTercer1+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("tercer_set1");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        tercer1 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueTercer1.add(jsonObjectRequestG1);
    }
    public void tercer_set2(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlTercer2+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("tercer_set2");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        tercer2 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueTercer2.add(jsonObjectRequestG1);
    }
    public void remontada1(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlRemontada1+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("remontada1");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        remont1 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueRemontada1.add(jsonObjectRequestG1);
    }
    public void remontada2(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlRemontada2+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("remontada2");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        remont2 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueRemontada2.add(jsonObjectRequestG1);
    }
    public void contra1(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlContra1+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("remontadaContra1");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        contra1 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueContra1.add(jsonObjectRequestG1);
    }
    public void contra2(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlContra2+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("remontadaContra2");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        contra2 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueContra2.add(jsonObjectRequestG1);
    }
    public void gamesT1(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlGamesT1+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("gamesT1");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        gamesT1 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueuegamesT1.add(jsonObjectRequestG1);
    }
    public void gamesT2(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlGamesT2+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("gamesT2");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        gamesT2 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueuegamesT2.add(jsonObjectRequestG1);
    }
    public void setsT1(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlSetsT1+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("setsT1");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        setsT1 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueSetsT1.add(jsonObjectRequestG1);
    }
    public void setsT2(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlSetsT2+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("setsT2");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        setsT2 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueSetsT2.add(jsonObjectRequestG1);
    }
    public void tercerT1(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlTercerT1+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("tercer_setT1");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        tercerT1 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueTercerT1.add(jsonObjectRequestG1);
    }
    public void tercerT2(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlTercerT2+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("tercer_setT2");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        tercerT2 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueTercerT2.add(jsonObjectRequestG1);
    }
    public void tieT1(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlTieT1+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("tie_breakT1");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        tieT1 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueTieT1.add(jsonObjectRequestG1);
    }
    public void tieT2(int c){
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET, urlTieT2+c, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("tie_breakT2");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                        String title = jsonG1.getString("suma");
                        tieT2 = Integer.parseInt(title);
                    }
                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueTieT2.add(jsonObjectRequestG1);
    }
    public void last(int c){
        x=c;
        title1=new ArrayList<>();
        title2=new ArrayList<>();
        title3=new ArrayList<>();
        title4=new ArrayList<>();

        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET,
                urlLast=urlx.getUrl()+"/padel/consulta_last.php?player1="+c+"&player2="+c,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrG1 = response.getJSONArray("last");
                    for (int i = 0; i < arrG1.length(); i++) {
                        JSONObject jsonG1 = null;
                        jsonG1 = new JSONObject(arrG1.get(i).toString());
                         title1 .add( jsonG1.getString("player1"));
                         title2 .add(jsonG1.getString("player2"));
                         title3 .add (jsonG1.getString("winner_p1"));
                         title4 .add(jsonG1.getString("winner_p2"));
                    }

                } catch (Exception er) {
                    Toast.makeText(getActivity(), "" + er, Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueueLast.add(jsonObjectRequestG1);
    }
   public void datosGenerales(int c){
        pers=new Personajes();
        JsonObjectRequest jsonObjectGenerales=new JsonObjectRequest(Request.Method.GET, urlgenerales+c, null,
                new Response.Listener<JSONObject>() {
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


                             pers.setFoto(R.drawable.img_profile);
                         }
                     }catch(JSONException e){
                         Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
                     }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueueGenerales.add(jsonObjectGenerales);
   }
}
package com.example.tennis_liga;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class NuevoUsuario extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    private EditText nombre;
    private TextView nacimiento;
    private   EditText nacionalidad;
    private   EditText pais;
    private   EditText direccion;
    private  TextView talla;
    private EditText estilo;
    private  EditText golpe;
    private EditText raqueta;
    private  EditText cordaje;
    private   EditText apellido;
    private Url urlx=new Url();

    private  String urldex;
    private   EditText email;
    private  EditText password;
    private RequestQueue requestQueue;
    private Button btn_ingresaJugador,fecha;
    private  String urlInsertar, sexo;
    private   String d,month,y,t;
    private Spinner spin,spinDay,spinYear,spinCamiseta;
    private RadioButton M,F;
    private CircleImageView fotografia;
    private Intent in;
    private RequestQueue  requestQueueGames1;

    private ProgressDialog progress ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);
           //  getSupportActionBar().setDisplayShowHomeEnabled(true);
          //   getSupportActionBar().setIcon(R.mipmap.pelot);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueueGames1=Volley.newRequestQueue(getApplicationContext());
        int arr[]= new int[100];
        String year[]=new String[100];
        spin=(Spinner)findViewById(R.id.spinner);
        spinDay=(Spinner)findViewById(R.id.spinner_day);
        spinYear=(Spinner)findViewById(R.id.spinner_year);
        spinCamiseta=(Spinner)findViewById(R.id.spinnerCamiseta);
        nombre=(EditText) findViewById(R.id.txt_nombre);
        nacimiento=(TextView) findViewById(R.id.txt_nacimiento);
        nacionalidad=(EditText) findViewById(R.id.txt_nacionalidad);
        pais=(EditText) findViewById(R.id.txt_pais);
        direccion=(EditText) findViewById(R.id.txt_direccion);
        talla=(TextView) findViewById(R.id.txt_size);
        estilo=(EditText) findViewById(R.id.txt_estilo);
        golpe=(EditText) findViewById(R.id.txt_golpe);
        raqueta=(EditText) findViewById(R.id.txt_raqueta);
        cordaje=(EditText) findViewById(R.id.txt_cordaje);
        apellido=(EditText) findViewById(R.id.txt_apellido);
        email=(EditText) findViewById(R.id.editTextTextEmailAddress);
        password=(EditText) findViewById(R.id.txt_pass);
        M=(RadioButton)findViewById(R.id.radioM);
        F=(RadioButton)findViewById(R.id.radioF);
        fotografia=(CircleImageView)findViewById(R.id.profile_image);



        btn_ingresaJugador=(Button)findViewById(R.id.btn_sigJugador);
        fecha=(Button)findViewById(R.id.btn_fecha);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(month().equalsIgnoreCase("02")&&d.equalsIgnoreCase("31")){
                    Toast.makeText(getApplicationContext(), "fecha incorrecta", Toast.LENGTH_LONG).show();
                }else if(month().equalsIgnoreCase("02")&&d.equalsIgnoreCase("30")){
                    Toast.makeText(getApplicationContext(), "fecha incorrecta", Toast.LENGTH_LONG).show();
                }else if(month().equalsIgnoreCase("06")&&d.equalsIgnoreCase("31")){
                    Toast.makeText(getApplicationContext(), "fecha incorrecta", Toast.LENGTH_LONG).show();
                }else if(month().equalsIgnoreCase("09")&&d.equalsIgnoreCase("31")){
                    Toast.makeText(getApplicationContext(), "fecha incorrecta", Toast.LENGTH_LONG).show();
                }else if(month().equalsIgnoreCase("11")&&d.equalsIgnoreCase("31")){
                    Toast.makeText(getApplicationContext(), "fecha incorrecta", Toast.LENGTH_LONG).show();
                }else
                    nacimiento.setText(y+"-"+month()+"-"+d);
            }
        });
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.meses,
                android.R.layout.simple_spinner_item);
        spin.setAdapter(adapter);

        String day[]={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18",
                "19","20","21","22","23","24","25","26","27","28","29","30","31"};
        ArrayAdapter <String> adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,day);
        spinDay.setAdapter(adapter2);
        for (int i = 0; i < 100; i++) {
            arr[i]=2022-i;
            year[i]=arr[i]+"";
        }
        //  String camiseta[]={"Seleccione una talla","S","M","L","XL","XXL","XXXL"};
        ArrayAdapter <CharSequence> adapter3=ArrayAdapter.createFromResource(this,R.array.camiseta,
                android.R.layout.simple_spinner_item);

        spinCamiseta.setAdapter(adapter3);
        ArrayAdapter <String> adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,year);
        spinYear.setAdapter(adapter1);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(parent.getContext(), "seleccionado"+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                month=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(parent.getContext(), "seleccionado"+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                y=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(parent.getContext(), "seleccionado"+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                d=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinCamiseta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t=parent.getItemAtPosition(position).toString();
                if (t.equalsIgnoreCase("choose your size")||t.equalsIgnoreCase("selecciona tu talla")){
                    Toast.makeText(getApplicationContext(), "selecciona una talla", Toast.LENGTH_LONG).show();

                }else
                    talla.setText(t);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_ingresaJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getApplicationContext(),Home.class);
                if(nombre.getText().toString().trim().isEmpty()||nacimiento.getText().toString().trim().isEmpty()||


                        apellido.getText().toString().trim().isEmpty()||email.getText().toString().trim().isEmpty()||
                        password.getText().toString().trim().isEmpty()
                ){
                    Toast.makeText(getApplicationContext(), "debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }else if(!nombre.getText().toString().equalsIgnoreCase("")&&!nacimiento.getText().toString().equalsIgnoreCase("")&&

                !apellido.getText().toString().equalsIgnoreCase("")&&!email.getText().toString().equalsIgnoreCase("")&&
                !password.getText().toString().equalsIgnoreCase("")){
                   if(M.isChecked()){
                       sexo="masculino";
                   }else if(F.isChecked()){
                       sexo="femenino";
                   }






                   game1(1);
                  startActivity(in);
                    /*Toast.makeText(getApplicationContext(), "name="+nombre.getText()+"last name="+apellido.getText()+"y"+
                            nacimiento.getText()+"talla="+talla.getText()+"y"+sexo+"cnacionalidad="+nacionalidad.getText()+"actual="+pais.getText()+
                            "estilo="+estilo.getText()+"golpe="+golpe.getText()+"raqueta="+raqueta.getText()+"cordaje="+cordaje.getText().toString()
                            , Toast.LENGTH_SHORT).show();*/
                }




            }
        });
    }
   @Override public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.barra,menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem opc) {
        int id=opc.getItemId();
        if(id==R.id.item1){
            clean();
            Toast.makeText(getApplicationContext(), "llena todos los campos", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(opc);
    }


    public String month(){
        String mes_numero="";
        if (month.equalsIgnoreCase("january")||month.equalsIgnoreCase("enero")){
            mes_numero="01";
        }else if(month.equalsIgnoreCase("february")||month.equalsIgnoreCase("febrero")){
            mes_numero="02";
        }else if(month.equalsIgnoreCase("march")||month.equalsIgnoreCase("marzo")){
            mes_numero="03";
        }else if(month.equalsIgnoreCase("april")||month.equalsIgnoreCase("abril")){
            mes_numero="04";
        }else if(month.equalsIgnoreCase("may")||month.equalsIgnoreCase("mayo")){
            mes_numero="05";
        }else if(month.equalsIgnoreCase("june")||month.equalsIgnoreCase("junio")){
            mes_numero="06";
        }else if(month.equalsIgnoreCase("july")||month.equalsIgnoreCase("julio")){
            mes_numero="07";
        }else if(month.equalsIgnoreCase("august")||month.equalsIgnoreCase("agosto")){
            mes_numero="08";
        }else if(month.equalsIgnoreCase("september")||month.equalsIgnoreCase("septiembre")){
            mes_numero="09";
        }else if(month.equalsIgnoreCase("october")||month.equalsIgnoreCase("octubre")){
            mes_numero="10";
        }else if(month.equalsIgnoreCase("november")||month.equalsIgnoreCase("noviembre")){
            mes_numero="11";
        }else if(month.equalsIgnoreCase("december")||month.equalsIgnoreCase("diciembre")){
            mes_numero="12";
        }

        return mes_numero;
    }
    public void clean(){
        nombre.setText("");
        nacimiento.setText("");
        nacionalidad.setText("");
        pais.setText("");
        direccion.setText("");
        talla.setText("");
        estilo.setText("");
        golpe.setText("");
        raqueta.setText("");
        cordaje.setText("");
        apellido.setText("");
        email.setText("");
        password.setText("");
        Toast.makeText(getApplicationContext(),"debes llenar todos los campos", Toast.LENGTH_SHORT).show();
    }
    public void onClick(View vista){
        cargarFoto();
    }
    private void cargarFoto(){
        Intent in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        in .setType("image/");
        startActivityForResult(in.createChooser(in,"selecciona la aplicacion"),10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri path=data.getData();
            fotografia.setImageURI(path);
        }
    }
    public void game1(int c){
        urldex=urlx.getUrl()+"/padel/inserta_jugador1.php?nombre="+nombre.getText().toString()+"&apellido="+apellido.getText().toString()
                +"&fecha_nacimiento="+nacimiento.getText().toString()+"&email="+email.getText().toString()+"&password="
                +password.getText().toString()+"&pais_actual="+pais.getText().toString()+"&nacionalidad="+nacionalidad.getText().toString()
        +"&talla_camiseta="+talla.getText().toString()+"&estilo_juego="+estilo.getText().toString()+"&raqueta="+raqueta.getText()
        .toString()+"&cordaje="+ cordaje.getText().toString()+"&mejor_golpe="+golpe.getText().toString()+"&sexo="+sexo+
        "&direccion="+direccion.getText().toString();
        urldex=urldex.replace(" ","%20");
        JsonObjectRequest jsonObjectRequestG1=new JsonObjectRequest(Request.Method.GET,

                urldex, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();

            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueueGames1.add(jsonObjectRequestG1);


    }


    @Override
    public void onErrorResponse(VolleyError error) {
        progress.hide();
        Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
progress.hide();
        Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();
    }
}
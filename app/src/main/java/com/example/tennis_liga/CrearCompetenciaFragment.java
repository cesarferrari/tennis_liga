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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.time.Month;


public class CrearCompetenciaFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
    private EditText et_competencia,et_evento,et_premio,et_categoria,et_sede;

    RequestQueue requestQueue;
    String url="/",deber;

    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;
    private Spinner spin,spinDay,spinYear,spinX,spinDayX,spinYearX;
    private   String d,month,y,dx,monthx,yx;
    private int int_d,int_month,int_y,int_dx,int_month_x,int_yx;
    private TextView fechaInicio,fechaFIn;
    private Button fecha,fechax,btn_compet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista =inflater.inflate(R.layout.fragment_crear_competencia, container, false);
       deber=getString(R.string.deber);
        fechaInicio=(TextView)vista.findViewById(R.id.txt_fecha_inicio);
        fechaFIn=(TextView)vista.findViewById(R.id.txt_fecha_fin);
        fecha=(Button)vista.findViewById(R.id.btn_Finicio);
        fechax=(Button)vista.findViewById(R.id.btn_Ffinal);
        et_competencia=vista. findViewById(R.id.Et_competicion);
        et_evento=vista. findViewById(R.id.Et_evento);
        et_categoria=vista. findViewById(R.id.Et_categoria);
        et_sede=vista. findViewById(R.id.Et_sede);
        et_premio=vista. findViewById(R.id.Et_premio);
        btn_compet=vista.findViewById(R.id.btn_insertarCompetencia);
        btn_compet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_competencia.getText().toString().equalsIgnoreCase("") ||
                        et_evento.getText().toString().equalsIgnoreCase("") || et_categoria.getText().toString()
                        .equalsIgnoreCase("") || et_premio.getText().toString().equalsIgnoreCase("")
                       ||et_sede.getText().toString().equalsIgnoreCase("") || fechaInicio.getText().toString()
                        .equalsIgnoreCase("") || fechaFIn.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getActivity(), deber, Toast.LENGTH_SHORT).show();
                } else
                    ejecutar_servicio();
                    clean();
            }
        });

        requestQueue= Volley.newRequestQueue(getActivity());
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(month(month).equalsIgnoreCase("02")&&d.equalsIgnoreCase("31")){
                    Toast.makeText(getActivity(), "fecha incorrecta", Toast.LENGTH_LONG).show();
                }else if(month(month).equalsIgnoreCase("02")&&d.equalsIgnoreCase("30")){
                    Toast.makeText(getActivity(), "fecha incorrecta", Toast.LENGTH_LONG).show();
                }else if(month(month).equalsIgnoreCase("06")&&d.equalsIgnoreCase("31")){
                    Toast.makeText(getActivity(), "fecha incorrecta", Toast.LENGTH_LONG).show();
                }else if(month(month).equalsIgnoreCase("09")&&d.equalsIgnoreCase("31")){
                    Toast.makeText(getActivity(), "fecha incorrecta", Toast.LENGTH_LONG).show();
                }else if(month(month).equalsIgnoreCase("11")&&d.equalsIgnoreCase("31")){
                    Toast.makeText(getActivity(), "fecha incorrecta", Toast.LENGTH_LONG).show();
                }else
                    fechaInicio.setText(y+"-"+month(month)+"-"+d);


            }

        });
        fechax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            validaFecha();

            }
        });

        int arr[]= new int[5];
        String year[]=new String[5];
        spin=(Spinner)vista.findViewById(R.id.spinner_monthFI);
        spinDay=(Spinner)vista.findViewById(R.id.spinner_dayFI);
        spinYear=(Spinner)vista.findViewById(R.id.spinner_yearFI);
        spinX=(Spinner)vista.findViewById(R.id.spinner_monthFF);
        spinDayX=(Spinner)vista.findViewById(R.id.spinner_dayFF);
        spinYearX=(Spinner)vista.findViewById(R.id.spinner_yearFF);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getActivity(),R.array.meses,
                android.R.layout.simple_spinner_item);
        spin.setAdapter(adapter);

        String day[]={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18",
                "19","20","21","22","23","24","25","26","27","28","29","30","31"};
        ArrayAdapter <String> adapter2=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,day);
        spinDay.setAdapter(adapter2);
        for (int i = 0; i < arr.length; i++) {
            arr[i]=2022+i;
            year[i]=arr[i]+"";
        }

        ArrayAdapter <String> adapter1=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,year);
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
        spinDayX.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dx=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinX.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                monthx=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinYearX.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yx=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return vista;
    }

    private String month(String month){
        this.month=month;
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
    private void validaFecha(){
        int_d=Integer.parseInt(d);
        int_dx=Integer.parseInt(dx);
        int_month=Integer.parseInt(month(month));
        int_month_x=Integer.parseInt(month(monthx));
        int_y=Integer.parseInt(y);
        int_yx=Integer.parseInt(yx);
        if(month(monthx).equalsIgnoreCase("02")&&dx.equalsIgnoreCase("31")){
            Toast.makeText(getActivity(), "fecha incorrecta", Toast.LENGTH_LONG).show();
        }else if(month(monthx).equalsIgnoreCase("02")&&dx.equalsIgnoreCase("30")){
            Toast.makeText(getActivity(), "fecha incorrecta", Toast.LENGTH_LONG).show();
        }else if(month(monthx).equalsIgnoreCase("06")&&dx.equalsIgnoreCase("31")){
            Toast.makeText(getActivity(), "fecha incorrecta", Toast.LENGTH_LONG).show();
        }else if(month(monthx).equalsIgnoreCase("09")&&dx.equalsIgnoreCase("31")){
            Toast.makeText(getActivity(), "fecha incorrecta", Toast.LENGTH_LONG).show();
        }else if(month(monthx).equalsIgnoreCase("11")&&dx.equalsIgnoreCase("31")){
            Toast.makeText(getActivity(), "fecha incorrecta", Toast.LENGTH_LONG).show();
        }else if(int_yx<int_y){
            Toast.makeText(getActivity(), "fecha incorrecta cheque el año", Toast.LENGTH_SHORT).show();
        }else if(int_yx==int_y&&int_month_x<int_month){
            Toast.makeText(getActivity(), "fecha incorrecta cheque el mes ", Toast.LENGTH_SHORT).show();
        }else if(int_yx==int_y&&int_month_x==int_month&&int_dx<int_d){
            Toast.makeText(getActivity(), "fecha incorrecta cheque el dia", Toast.LENGTH_SHORT).show();
        }else
            fechaFIn.setText(yx+"-"+month(monthx)+"-"+dx);


      //  fechaFIn.setText(int_yx+int_y);

    }
    private void ejecutar_servicio(){
        progress= new ProgressDialog(getActivity());
        progress.setMessage("cargando...");
        progress.show();
        url="http://192.168.1.69/padel/crear_competencia.php?competicion="+et_competencia.getText().toString()+"&tipo_evento="+et_evento.getText().toString()
                +"&categoria="+et_categoria.getText().toString()+"&premio="+et_premio.getText().toString()+"&" +
                "sede="+et_sede.getText().toString()+"&fecha_inicio="+fechaInicio.getText().toString()+"&fecha_fin="+fechaFIn.getText().toString();
        url=url.replace(" ","%20");
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(), "error al conectar", Toast.LENGTH_SHORT).show();
        progress.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getActivity(), "conexion exitosa", Toast.LENGTH_SHORT).show();
        progress.hide();
    }
    private void clean(){
        et_competencia.setText("");
        et_categoria.setText("");
        et_sede.setText("");
        et_evento.setText("");
        et_premio.setText("");
        fechaInicio.setText("");
        fechaFIn.setText("");
    }
}
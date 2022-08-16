package com.example.tennis_liga;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterCompetencia extends RecyclerView.Adapter<AdapterCompetencia.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<Competicion> ListDatos;
    private View.OnClickListener listener;

    public AdapterCompetencia(ArrayList<Competicion> listDatos) {
        ListDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layout=R.layout.item_copa;

        View view= LayoutInflater.from(parent.getContext()).inflate(layout,null,false);
        view.setOnClickListener(this);
        return new AdapterCompetencia.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
     holder.competicion.setText(ListDatos.get(position).getCompeticion());
     holder.evento.setText(ListDatos.get(position).getTipo_evento());
     holder.foto.setImageResource(ListDatos.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return ListDatos.size();
    }
    public void setOnClickListenter(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView competicion,evento;
        ImageView foto;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            competicion=itemView.findViewById(R.id.txt_compet);
            evento=itemView.findViewById(R.id.txt_sede);
            foto=itemView.findViewById(R.id.img_copa);
        }
    }
}

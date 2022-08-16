package com.example.tennis_liga;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterMatch extends RecyclerView.Adapter<AdapterMatch.ViewHolderDatos> {


    ArrayList<Match>listaMatch;


    public AdapterMatch(ArrayList<Match> listaMatch) {
        this.listaMatch = listaMatch;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_competencia,null,false);
        return new AdapterMatch.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
holder.fecha.setText(listaMatch.get(position).getFecha());
holder.tipo_evento.setText(listaMatch.get(position).getTipo_evento());
holder.competicion.setText(listaMatch.get(position).getCompeticion());
holder.photo1.setImageResource(listaMatch.get(position).getPhotoP1());
holder.photo2.setImageResource(listaMatch.get(position).getPhotoP2());
    }

    @Override
    public int getItemCount() {
        return listaMatch.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView fecha,tipo_evento,competicion;
        ImageView photo1,photo2;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            fecha=(TextView) itemView.findViewById(R.id.txt_competencia);
            tipo_evento=(TextView) itemView.findViewById(R.id.txt_competencia1);
            competicion=(TextView) itemView.findViewById(R.id.txt_competencia2);
            photo1=(ImageView) itemView.findViewById(R.id.photo1);
            photo2=(ImageView) itemView.findViewById(R.id.photo2);
        }
    }
}

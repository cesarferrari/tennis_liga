package com.example.tennis_liga;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterDatosAdmin extends RecyclerView.Adapter<AdapterDatosAdmin.ViewHolderDatos> implements View.OnClickListener{
    ArrayList<Personajes> ListDatos;
    private View.OnClickListener listener;
    public AdapterDatosAdmin(ArrayList<Personajes> listDatos) {
        ListDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout=0;
        if(Utilidades.visualizacion==Utilidades.LIST){
            layout=R.layout.item_list1;
        }else{
            layout=R.layout.item_grid;
        }

        View view= LayoutInflater.from(parent.getContext()).inflate(layout,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.nombre.setText(ListDatos.get(position).getNombre());


            holder.descripcion.setText(ListDatos.get(position).getL_name());

        holder.id.setText(ListDatos.get(position).getId());
        holder.foto.setImageResource(ListDatos.get(position).getFoto());
        if(ListDatos.get(position).getRol()==0){
            holder.rol.setText("Espectador");

        }else if(ListDatos.get(position).getRol()==1){
            holder.rol.setText("Jugador");
        }else if(ListDatos.get(position).getRol()==2){
            holder.rol.setText("jugador/Administrador");
        }
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
        TextView nombre,descripcion,id,rol;
        CircleImageView foto;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

                descripcion=(TextView) itemView.findViewById(R.id.txt_2);

            nombre=(TextView) itemView.findViewById(R.id.txt_1);

            foto=(CircleImageView) itemView.findViewById(R.id.CIV_id);
            id=(TextView) itemView.findViewById(R.id.txt_idP);
            rol=(TextView) itemView.findViewById(R.id.txt_rol);


        }

    }


   }


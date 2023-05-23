package com.example.proyectocliente.activities.logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectocliente.R;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolder> {
    // root?
    private LayoutInflater inflater;
    public void setData(List<Usuario> usuarioList, List<Oficio> oficioList){
        this.usuarios = usuarioList;
        this.oficios = oficioList;
    }

    public AdaptadorRecycler(Context context){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public AdaptadorRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simple_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecycler.ViewHolder holder, int position) {
        //information
        //         ImageDownloader.downloadImage(Parameters.URL_IMAGE + oficios.imageurl, holder.image);
    }

    @Override
    public int getItemCount() {
        //root.size
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombre;
        private TextView oficio;
        private ImageView image;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nombre = itemView.findViewById(R.id.textViewNombre);
            oficio = itemView.findViewById(R.id.textViewOficio);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}

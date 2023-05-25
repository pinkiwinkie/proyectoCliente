package com.example.proyectocliente.activities.logic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectocliente.R;
import com.example.proyectocliente.activities.FormularioUpdate;
import com.example.proyectocliente.activities.model.Oficio;
import com.example.proyectocliente.activities.model.Usuario;
import com.example.proyectocliente.base.ImageDownloader;
import com.example.proyectocliente.base.Parameters;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolder> {
    // root?
    private List<Usuario> usuarios;
    private List<Oficio> oficios;
    private LayoutInflater inflater;
    private Context context;
    private View.OnClickListener onClickListener;


    public AdaptadorRecycler(Context context){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        usuarios = new ArrayList<>();
        oficios = new ArrayList<>();
    }
    @NonNull
    @Override
    public AdaptadorRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simple_element, parent, false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecycler.ViewHolder holder, int position) {
        Usuario user = usuarios.get(position);
        holder.nombre.setText(user.getLastName().concat(", ").concat(user.getName()));
        Oficio oficio = oficios.get(usuarios.get(position).getIdOficio()-1);
        holder.oficio.setText(oficio.getDescription());
        ImageDownloader.downloadImage(inflater.getContext(),Parameters.URL_IMAGE + oficio.getImageUrl(), holder.image,R.mipmap.ic_launcher);

//        //clicar
//        holder.itemView.setOnClickListener(view -> {
//            Intent intent = new Intent(view.getContext(), FormularioUpdate.class);
////            intent.putExtra("root",root); //poner getSerializable cuando se reciba en 3_activity
////            intent.putExtra("position", position);
//            view.getContext().startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }
    public void setData(List<Usuario> usuarioList, List<Oficio> oficioList) {
        this.usuarios = usuarioList;
        this.oficios = oficioList;
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombre;
        private TextView oficio;
        private ImageView image;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nombre = itemView.findViewById(R.id.textViewNombre);
            oficio = itemView.findViewById(R.id.textViewOficio);
            image = itemView.findViewById(R.id.imageViewFormulario);
        }
    }


}

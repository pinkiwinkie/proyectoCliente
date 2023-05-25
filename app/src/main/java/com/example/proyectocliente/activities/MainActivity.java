package com.example.proyectocliente.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.proyectocliente.API.APIService;
import com.example.proyectocliente.API.Connector;
import com.example.proyectocliente.activities.logic.AdaptadorRecycler;
import com.example.proyectocliente.R;
import com.example.proyectocliente.activities.model.Oficio;
import com.example.proyectocliente.activities.model.Usuario;
import com.example.proyectocliente.base.BaseActivity;
import com.example.proyectocliente.base.CallInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements CallInterface {
    private RecyclerView recyclerView;
    private FloatingActionButton addUser;

    private APIService apiService;
    private List<Usuario> usuarios;
    private List<Oficio> oficios;
    private AdaptadorRecycler adaptadorRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        addUser = findViewById(R.id.addUser);

        adaptadorRecycler = new AdaptadorRecycler(this);
        recyclerView.setAdapter(adaptadorRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ActivityResultLauncher<Intent> launcherAddUser =
                registerForActivityResult(

                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();
                                Usuario usuario;
                                if (data != null) {
                                    usuario = (Usuario)
                                            data.getExtras().getSerializable("usuario");
                                    System.out.println(usuario);
                                    usuarios.add(usuario);
                                    adaptadorRecycler.notifyDataSetChanged();
                                    Toast.makeText(this, "Nuevo Usuario " +
                                                    usuario.getName() + " " + usuario.getLastName(),
                                            Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(this, "No puede haber campos vacios", Toast.LENGTH_LONG).show();
                            } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                                Toast.makeText(this, "Cancelado por el usuario",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
        ActivityResultLauncher<Intent> launcherUpdateUser =
                registerForActivityResult(

                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                        });

        addUser.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
            intent.putExtra("oficios", (ArrayList) oficios);
            launcherAddUser.launch(intent);
        });

        recyclerView.setOnClickListener(view -> {
            Intent intent = new Intent(this, FormularioUpdate.class);
            launcherUpdateUser.launch(intent);
        });
        //dinamizacion del recycler

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;   //para ordenar
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                //remove usuario
                // adaptadorRecycler.notifyItemRemoved(position);
                Snackbar.make(recyclerView, "deleted", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //addUser
                        //adaptadorRecycler.notifyItemInserted(position);
                    }
                }).show();
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);

        showProgress();
        executeCall(this);
    }

    @Override
    public void doInBackground() {
        usuarios = Connector.getConector().getAsList(Usuario.class, "usuariosdb/");
        oficios = Connector.getConector().getAsList(Oficio.class, "oficiosdb/");
    }

    @Override
    public void doInUI() {
        hideProgress();
        adaptadorRecycler.setData(usuarios, oficios);
        adaptadorRecycler.notifyDataSetChanged();
    }

}
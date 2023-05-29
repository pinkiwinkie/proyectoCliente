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

public class MainActivity extends BaseActivity implements CallInterface, View.OnClickListener {
    private RecyclerView recyclerView;
    private FloatingActionButton addUser;
    private List<Usuario> usuarios;
    private List<Oficio> oficios;
    private AdaptadorRecycler adaptadorRecycler;
    private ActivityResultLauncher<Intent> launcherUpdateUser;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        addUser = findViewById(R.id.addUser);

        adaptadorRecycler = new AdaptadorRecycler(this);
        recyclerView.setAdapter(adaptadorRecycler);
        adaptadorRecycler.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ActivityResultLauncher<Intent> launcherAddUser =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();
                                Usuario usuario;
                                if (data != null) {
                                    usuario = (Usuario)
                                            data.getExtras().getSerializable("usuario");
                                    System.out.println(usuario);
                                    usuarios.add(usuario);
                                    adaptadorRecycler.notifyItemInserted(usuarios.indexOf(usuario));
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
        launcherUpdateUser =
                registerForActivityResult(

                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();
                                Usuario usuarioActualizado;
                                if (data != null) {
                                    usuarioActualizado = (Usuario)
                                            data.getExtras().getSerializable("usuarioActualizado");
                                    System.out.println(usuarioActualizado);
                                    int posicionUsuario = usuarios.indexOf(usuarioActualizado); // el error esta qui. no se porque da -1
                                    System.out.println(posicionUsuario);
                                    if (posicionUsuario != -1) {
                                        usuarios.set(posicionUsuario, usuarioActualizado);
                                        adaptadorRecycler.actualizarUsuarios(usuarios);
                                        adaptadorRecycler.notifyDataSetChanged();
                                    }
                                    Toast.makeText(this, "Usuario Actualizado " +
                                                    usuarioActualizado.getName() + " " + usuarioActualizado.getLastName(),
                                            Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(this, "No actualizado ", Toast.LENGTH_LONG).show();
                            } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                                Toast.makeText(this, "Cancelado por el usuario",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

        addUser.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
            intent.putExtra("oficios", (ArrayList) oficios);
            launcherAddUser.launch(intent);
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

                executeCall(new CallInterface() {
                    @Override
                    public void doInBackground() {
                        usuario = usuarios.get(position);
                        int i = usuario.getId();
                        usuarios.remove(usuario);
                        usuario = Connector.getConector().delete(Usuario.class, "usuariosdb/" + i);

                    }

                    @Override
                    public void doInUI() {
                        adaptadorRecycler.notifyItemRemoved(position);
                        adaptadorRecycler.notifyDataSetChanged();
                    }
                });

                Snackbar.make(recyclerView, "deleted", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //addUser
                        executeCall(new CallInterface() {
                            @Override
                            public void doInBackground() {
                                Connector.getConector().post(Usuario.class, usuario, "usuariosdb/");
                                usuarios.add(position, usuario);
                            }

                            @Override
                            public void doInUI() {
                                adaptadorRecycler.notifyItemInserted(position);
                                adaptadorRecycler.notifyDataSetChanged();
                            }
                        });
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

    @Override
    public void onClick(View view) {
        int position = recyclerView.getChildAdapterPosition(view);
        Usuario user = usuarios.get(position);

        Intent intent = new Intent(this, FormularioUpdate.class);
        intent.putExtra("oficios2", (ArrayList) oficios);
        intent.putExtra("userSelected", user);
        launcherUpdateUser.launch(intent);
    }
}
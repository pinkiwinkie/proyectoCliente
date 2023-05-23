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
import com.example.proyectocliente.activities.model.Usuario;
import com.example.proyectocliente.base.BaseActivity;
import com.example.proyectocliente.base.CallInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends BaseActivity implements CallInterface {
    private RecyclerView recyclerView;
    private FloatingActionButton addUser;

    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        addUser = findViewById(R.id.addUser);

        AdaptadorRecycler adaptadorRecycler = new AdaptadorRecycler(this);
        recyclerView.setAdapter(adaptadorRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ActivityResultLauncher<Intent> someActivityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == RESULT_CANCELED)
                                Toast.makeText(this, "Cancelado por el usuario",
                                        Toast.LENGTH_LONG).show();
                            else if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();

                            }
                        });

        addUser.setOnClickListener(view -> {
            Intent intent = new Intent(this, FormularioActivity.class);
            someActivityResultLauncher.launch(intent);
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
                adaptadorRecycler.notifyItemRemoved(position);
                Snackbar.make(recyclerView,"deleted", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //addUser
                        adaptadorRecycler.notifyItemInserted(position);
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
        usuarios = Connector.getConector().getAsList(Usuario.class,"usuariosdb/");
        oficios...

    }

    @Override
    public void doInUI() {
        hideProgress();
        AdaptadorRecycler adaptador = new AdaptadorRecycler(this);


        adaptador.setData(usuarios, oficios);
    }
}
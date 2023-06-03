package com.example.proyectocliente.activities.preferencias;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectocliente.R;

public class PreferenciasActivity extends AppCompatActivity {
    private ImageButton ibAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        PreferenciasFragment fragment = new PreferenciasFragment();
        Bundle args = new Bundle();
        args.putString("context", getApplicationContext().getPackageName());
        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorAjustes, new PreferenciasFragment()).commit();

        ibAtras = findViewById(R.id.btnAtras);

        ibAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

package com.example.proyectocliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.proyectocliente.R;
import com.example.proyectocliente.activities.model.Oficio;
import com.example.proyectocliente.activities.model.Usuario;
import com.example.proyectocliente.base.BaseActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class FormularioUpdate extends BaseActivity {
    private TextInputEditText tietnombre,
            tietapellidos;
    private Spinner spinner;
    private Button bAceptar,
            bCancelar;
    private ImageView imagennnn;
    private Usuario usuarioExtra;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_updatear);

        tietnombre = findViewById(R.id.tietNombreUpdate);
        tietapellidos = findViewById(R.id.tietApellidosUpdate);
        spinner = findViewById(R.id.spinnerUpdate);
        bAceptar = findViewById(R.id.buttonGuardar);
        bCancelar = findViewById(R.id.buttonCancelarUpdate);
        imagennnn = findViewById(R.id.imageViewFormularioUpdate);


        Bundle bundle = getIntent().getExtras();
        ArrayList<Oficio> oficioList = (ArrayList) bundle.getSerializable("oficios2");
        ArrayAdapter<Oficio> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                oficioList);
        spinner.setAdapter(myAdapter);

        bCancelar.setOnClickListener(v -> {
            Intent i = new Intent();
            setResult(RESULT_CANCELED, i);
            finish();
        });

    }
}

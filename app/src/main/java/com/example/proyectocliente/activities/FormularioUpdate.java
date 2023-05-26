package com.example.proyectocliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.proyectocliente.R;
import com.example.proyectocliente.activities.model.Oficio;
import com.example.proyectocliente.activities.model.Usuario;
import com.example.proyectocliente.base.BaseActivity;
import com.example.proyectocliente.base.CallInterface;
import com.example.proyectocliente.base.ImageDownloader;
import com.example.proyectocliente.base.Parameters;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class FormularioUpdate extends BaseActivity {
    private TextInputEditText tietnombre,
            tietapellidos;
    private Spinner spinner;
    private Button bGuardar,
            bCancelar;
    private ImageView imagennnn;
    private Usuario usuarioSelected;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_updatear);

        tietnombre = findViewById(R.id.tietNombreUpdate);
        tietapellidos = findViewById(R.id.tietApellidosUpdate);
        spinner = findViewById(R.id.spinnerUpdate);
        bGuardar = findViewById(R.id.buttonGuardar);
        bCancelar = findViewById(R.id.buttonCancelarUpdate);
        imagennnn = findViewById(R.id.imageViewFormularioUpdate);

        Bundle bundle = getIntent().getExtras();
        ArrayList<Oficio> oficioList = (ArrayList) bundle.getSerializable("oficios2");
        ArrayAdapter<Oficio> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                oficioList);
        spinner.setAdapter(myAdapter);

        usuarioSelected = (Usuario) bundle.getSerializable("userSelected");
        tietnombre.setText(usuarioSelected.getName());
        tietapellidos.setText(usuarioSelected.getLastName());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ImageDownloader.downloadImage(view.getContext(), Parameters.URL_IMAGE + oficioList.get(usuarioSelected.getIdOficio()-1).getImageUrl(),imagennnn, R.mipmap.ic_launcher);
                spinner.setSelection(usuarioSelected.getIdOficio()-1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bCancelar.setOnClickListener(v -> {
            Intent i = new Intent();
            setResult(RESULT_CANCELED, i);
            finish();
        });

        bGuardar.setOnClickListener(view -> {
            executeCall(new CallInterface() {
                @Override
                public void doInBackground() {

                }

                @Override
                public void doInUI() {

                }
            });
        });
    }
}

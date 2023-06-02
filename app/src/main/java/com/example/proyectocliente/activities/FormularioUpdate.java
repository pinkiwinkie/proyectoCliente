package com.example.proyectocliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.proyectocliente.API.Connector;
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
    private ImageView imageOficio;
    private Usuario usuarioSelected, usuarioExtra;
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
        imageOficio = findViewById(R.id.imageViewFormularioUpdate);

        Bundle bundle = getIntent().getExtras();
        ArrayList<Oficio> oficioList = (ArrayList) bundle.getSerializable("oficios2");
        ArrayAdapter<Oficio> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                oficioList);
        spinner.setAdapter(myAdapter);

        usuarioSelected = (Usuario) bundle.getSerializable("userSelected");
        int position = bundle.getInt("position");
        tietnombre.setText(usuarioSelected.getName());
        tietapellidos.setText(usuarioSelected.getLastName());

        int selectedPosition = usuarioSelected.getIdOficio() - 1;
        spinner.setSelection(selectedPosition);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String imageUrl = oficioList.get(i).getImageUrl();
                ImageDownloader.downloadImage(view.getContext(), Parameters.URL_IMAGE + imageUrl, imageOficio, R.mipmap.ic_launcher);
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
            i = new Intent();
            String nombre = tietnombre.getText().toString();
            String apellidos = tietapellidos.getText().toString();
            Oficio oficio = (Oficio) spinner.getSelectedItem();
            int id = usuarioSelected.getId();
            Usuario usuario = new Usuario(id,nombre, apellidos, oficio.getId());
            executeCall(new CallInterface() {
                @Override
                public void doInBackground() {
//                   usuarioExtra = Connector.getConector().put(Usuario.class,usuario,"usuariosdb/");
                }

                @Override
                public void doInUI() {
                    i = new Intent(FormularioUpdate.this, MainActivity.class);
                    i.putExtra("usuarioActualizado", usuarioExtra);
                    i.putExtra("position", position);
                    setResult(RESULT_OK,i);
                    finish();
                }
            });
        });
    }
}

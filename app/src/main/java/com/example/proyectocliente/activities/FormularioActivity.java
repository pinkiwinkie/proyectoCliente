package com.example.proyectocliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.List;

public class FormularioActivity extends BaseActivity {
    private TextInputEditText tietnombre,
            tietapellidos;
    private Spinner spinner;
    private Button bAceptar,//primero recoger los datos y luego implementar executeCall(new Call Interface){ // y asi poder hacer la llamada en el bg
            bCancelar;          //se implementan los metodos
    private ImageView imagennnn;
    private Usuario usuarioExtra;
    private  Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        tietnombre = findViewById(R.id.tietNombre);
        tietapellidos = findViewById(R.id.tietApellidos);
        spinner = findViewById(R.id.spinner);
        bAceptar = findViewById(R.id.buttonAceptar);
        bCancelar = findViewById(R.id.buttonCancelar);
        imagennnn = findViewById(R.id.imageViewFormulario);

        Bundle bundle = getIntent().getExtras();
        ArrayList<Oficio> oficioList = (ArrayList) bundle.getSerializable("oficios");
        ArrayAdapter<Oficio> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                oficioList);
        spinner.setAdapter(myAdapter);

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i >= 0 && i < oficioList.size()) {
//                    String imageUrl = Parameters.URL_IMAGE + oficioList.get(i - 1).getImageUrl();
//                    ImageDownloader.downloadImage(view.getContext(), imageUrl, imagennnn, R.mipmap.ic_launcher);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                imagennnn.setImageResource(R.mipmap.ic_launcher);
//            }
//        });

        bCancelar.setOnClickListener(v -> {
            Intent i = new Intent();
            setResult(RESULT_CANCELED, i);
            finish();
        });

        bAceptar.setOnClickListener(v -> {
            i = new Intent();
            String nombre = tietnombre.getText().toString();
            String apellidos = tietapellidos.getText().toString();
            Oficio oficio = (Oficio) spinner.getSelectedItem();
            Usuario usuario = new Usuario(nombre, apellidos, oficio.getId());

            //DAR DE ALTA EN BBDD AQUÍ
            executeCall(new CallInterface() {
                @Override
                public void doInBackground() {
                    usuarioExtra = Connector.getConector().post(Usuario.class, usuario, "usuariosdb/");
                }

                @Override
                public void doInUI() {
                    i = new Intent(FormularioActivity.this, MainActivity.class);
                    i.putExtra("usuario", usuarioExtra);
                    setResult(RESULT_OK, i);
                    finish();
//                    enviar usuario a la otra actividad
                }
            });
        });
    }
}

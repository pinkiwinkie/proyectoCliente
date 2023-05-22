package com.example.proyectocliente.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectocliente.R;
import com.google.android.material.textfield.TextInputEditText;

public class FormularioActivity extends AppCompatActivity {
    private TextInputEditText tietnombre,
            tietapellidos;
    private Spinner spinner;
    private Button bAceptar,//primero recoger los datos y luego implementar executeCall(new Call Interface){ // y asi poder hacer la llamada en el bg
            bCancelar;          //se implementan los metodos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
    }
}

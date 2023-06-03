package com.example.proyectocliente.activities.preferencias;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;

import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.proyectocliente.R;
import com.example.proyectocliente.activities.MainActivity;
import com.example.proyectocliente.base.Parameters;

public class PreferenciasFragment extends PreferenceFragmentCompat {

    public PreferenciasFragment(){
    }
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        Context context = (Context) getArguments().getSerializable("context");
        GestionPreferencias.getInstance().inicializa(context);
        setPreferencesFromResource(R.xml.preferencias, rootKey);


    }
}
//        EditTextPreference editTextPreference = findPreference("etpPassword");
//        editTextPreference.setOnBindEditTextListener(editText -> {
//            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        });

package com.example.proyectocliente.activities.preferencias;

import android.os.Bundle;
import android.text.InputType;

import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.proyectocliente.R;

public class PreferenciasFragment extends PreferenceFragmentCompat {
    public PreferenciasFragment(){

    }
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferencias, rootKey);
//        EditTextPreference editTextPreference = findPreference("etpPassword");
//        editTextPreference.setOnBindEditTextListener(editText -> {
//            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        });
        //crear varibles y mandar la informacion al Main
    }
}

package com.example.proyectocliente.activities.preferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class GestionPreferencias {
    private SharedPreferences pref;
    private static GestionPreferencias gestionPreferencias;

    private GestionPreferencias() {
    }

    public static GestionPreferencias getInstance() {
        if (gestionPreferencias == null)
            gestionPreferencias = new GestionPreferencias();
        return gestionPreferencias;
    }

    private void inicializa(Context context) {
        if (pref == null)
            pref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getIP(Context context) {
        inicializa(context);
        return pref.getString("etpIP", "192.168.1.22");
    }

    public String getPort(Context context) {
        inicializa(context);
        return pref.getString("etpPort", "3306");
    }

//    public String getUser(Context context) {
//        inicializa(context);
//        return pref.getString("etpUser", "");
//    }
//
//    public String getPassword(Context context) {
//        inicializa(context);
//        return pref.getString("etpPassword", "");
//    }

    public String getSchema(Context context) {
        inicializa(context);
        return pref.getString("etpSchema", "java");
    }

    public String getPath(Context context) {
        inicializa(context);
        return pref.getString("etpPathImage", "/dashboard/images/images/");
    }
}

package com.example.proyectocliente.base;

import android.content.Context;

import com.example.proyectocliente.activities.MainActivity;
import com.example.proyectocliente.activities.preferencias.GestionPreferencias;

public class Parameters {
//    private static Context context;
//
//    public static void setContext(Context ctx){
//        context = ctx;
//    }
    //CLASE
//    public final static String URL = "http://10.11.19.18:8080/apidb/";
//    // icon ruta de apache de la imagen
//    public final static String URL_IMAGE = "http://10.11.19.18:80/image/";

    // CASA
    public static String URL = "http://192.168.1.22:8080/apidb/";
    public static String URL_IMAGE = "http://192.168.1.22/dashboard/images/images/";

    //GENERAL
//    public static String URL = "http://"+ GestionPreferencias.getInstance().getIP(MainActivity.context)+":"+
//            GestionPreferencias.getInstance().getPort(MainActivity.context)+"/"+
//            GestionPreferencias.getInstance().getSchema(MainActivity.context) +"/";
//    public static String URL_IMAGE = "http://"+GestionPreferencias.getInstance().getIP(MainActivity.context)
//            +GestionPreferencias.getInstance().getPath(MainActivity.context);
}

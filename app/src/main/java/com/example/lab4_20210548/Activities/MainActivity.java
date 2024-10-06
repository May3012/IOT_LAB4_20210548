package com.example.lab4_20210548.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab4_20210548.R;

public class MainActivity extends AppCompatActivity {

    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnIngresar = findViewById(R.id.btn_ingresar);

        // Verificar la conexión a Internet al iniciar la actividad
        if (!isConnectedToInternet()) {
            showNoInternetDialog();
        } else {
            // Si hay conexión a Internet, permitir el acceso normal
            btnIngresar.setOnClickListener(v -> {
                // Navegar a AppActivity
                Intent intent = new Intent(this,AppActivity.class);
                startActivity(intent);
            });
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método para verificar si hay conexión a Internet, código tomado del ppt de Internet Connection
    private boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean tieneInternet = false;
        if (connectivityManager != null) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    //Log.i("msg-Internet", "NetworkCapabilities.TRANSPORT_CELLULAR");
                    tieneInternet = true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    //Log.i("msg-Internet", "NetworkCapabilities.TRANSPORT_WIFI");
                    tieneInternet = true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    //Log.i("msg-Internet", "NetworkCapabilities.TRANSPORT_ETHERNET");
                    tieneInternet = true;
                }
            }
        }
        return tieneInternet;
    }

    // Mostrar un dialog si no hay conexión a Internet
    private void showNoInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sin conexión a Internet");
        builder.setMessage("Por favor, asegúrate de estar conectado a Internet para usar esta aplicación.")
                .setCancelable(false)
                .setPositiveButton("Configuración", new DialogInterface.OnClickListener() {  // CORRECCIÓN AQUÍ
                    public void onClick(DialogInterface dialog, int id) {
                        // Abrir los ajustes de red del dispositivo
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {  // CORRECCIÓN AQUÍ
                    public void onClick(DialogInterface dialog, int id) {
                        // Cerrar la aplicación
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
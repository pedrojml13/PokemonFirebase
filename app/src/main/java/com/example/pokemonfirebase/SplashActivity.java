package com.example.pokemonfirebase;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.activity.ComponentActivity;
import androidx.core.splashscreen.SplashScreen;

public class SplashActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Usar SplashScreen API en Android 12+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
            splashScreen.setKeepOnScreenCondition(() -> false);
        }

        // Establecer el contenido de la actividad
        setContentView(R.layout.splash);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 3000);  // Espera 3 segundos antes de continuar a MainActivity
    }
}

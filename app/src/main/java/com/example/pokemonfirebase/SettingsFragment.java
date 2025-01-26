package com.example.pokemonfirebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.example.pokemonfirebase.databinding.FragmentSettingsBinding;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Locale;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;  // Agregar el binding generado

    private CapturedPokemonFragment capturedPokemonFragment;
    private PokedexFragment pokedexFragment;
    private MainActivity mainActivity;

    public SettingsFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);  // Inflar el binding
        View rootView = binding.getRoot();  // Obtener la raíz del layout

        mainActivity = (MainActivity)requireActivity();
        capturedPokemonFragment = mainActivity.getCapturedPokemonFragment();
        pokedexFragment = mainActivity.getPokedexFragment();

        // Cargar preferencias
        SharedPreferences prefs = requireActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);

        // Listener para el botón de cambiar idioma con PopupMenu
        binding.languageLayout.setOnClickListener(v -> {
            // Crear PopupMenu
            PopupMenu popupMenu = new PopupMenu(requireActivity(), binding.languageLayout);
            MenuInflater inflaterMenu = requireActivity().getMenuInflater();
            inflaterMenu.inflate(R.menu.language_menu, popupMenu.getMenu()); // Inflar el menú desde XML

            // Listener para manejar la selección del idioma
            popupMenu.setOnMenuItemClickListener(item -> {
                String newLanguage = "en"; // Por defecto en inglés

                if (item.getItemId() == R.id.menu_english) {
                    newLanguage = "en";
                } else if (item.getItemId() == R.id.menu_spanish) {
                    newLanguage = "es";
                }

                // Actualizar el idioma en SharedPreferences
                prefs.edit().putString("Language", newLanguage).apply();

                // Llamar al método para cambiar el idioma
                changeLanguage(newLanguage);

                Toast.makeText(this.getContext(), R.string.current_languaje, Toast.LENGTH_SHORT).show();

                return true;
            });

            // Mostrar el menú
            popupMenu.show();
        });

        // Configura el listener para el Switch
        binding.switchDeletePokemon.setOnCheckedChangeListener((buttonView, isChecked) -> {


                if(capturedPokemonFragment.getAdapter() != null){

                    capturedPokemonFragment.getAdapter().setDeletionEnabled(isChecked);

                    if(isChecked){
                        Toast.makeText(this.getContext(), R.string.deletion_enabled, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this.getContext(), R.string.deletion_disabled, Toast.LENGTH_SHORT).show();
                    }
                }
        });

        // Configura el botón about
        binding.aboutLayout.setOnClickListener(v -> showAboutDialog());

        // Configura el botón de cerrar sesión
        binding.logoutLayout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        });

        return rootView;
    }


    private void changeLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);

        // Actualizar la configuración de recursos de la actividad
        requireActivity().getResources().updateConfiguration(config, requireActivity().getResources().getDisplayMetrics());

        /*
        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        startActivity(intent);
        */

        mainActivity.RefreshLanguaje();
        capturedPokemonFragment.refreshLanguaje();
        pokedexFragment.refreshLanguaje();

        refreshLanguaje();

    }


    private void showAboutDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.about_title))
                .setMessage(getString(R.string.about_message))
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void refreshLanguaje(){
        binding.changeLanguaje.setText(R.string.change_languaje);
        binding.deleteCapturedPokemon.setText(R.string.delete_captured_pokemons);
        binding.about.setText(R.string.about);
        binding.logoff.setText(R.string.logout);
    }
}

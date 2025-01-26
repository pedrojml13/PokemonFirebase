package com.example.pokemonfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pokemonfirebase.databinding.FragmentCapturedPokemonBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CapturedPokemonFragment extends Fragment {

    private FragmentCapturedPokemonBinding binding;
    private CapturedPokemonAdapter capturedPokemonAdapter;
    private List<PokemonFirestore> capturedPokemonList = new ArrayList<>();
    private FirebaseFirestore db;

    public CapturedPokemonFragment() {
        // Constructor vacío necesario
    }


    @Override
    public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout usando View Binding
        binding = FragmentCapturedPokemonBinding.inflate(inflater, container, false);
        android.view.View view = binding.getRoot();

        // Configurar el RecyclerView
        binding.recyclerCapturedPokemon.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar el adaptador si no está configurado
        if (capturedPokemonAdapter == null) {
            capturedPokemonAdapter = new CapturedPokemonAdapter(capturedPokemonList, pokemon -> {
                // Mostrar un mensaje de carga
                Toast.makeText(this.getContext(), R.string.load_details, Toast.LENGTH_SHORT).show();

                // Al hacer clic en un Pokémon, pasar a la pantalla de detalles
                Intent intent = new Intent(this.getActivity(), PokemonDetailActivity.class);
                intent.putExtra("pokemon", pokemon); // Pasar el objeto Pokémon como Serializable
                startActivity(intent);
            });

            binding.recyclerCapturedPokemon.setAdapter(capturedPokemonAdapter);
        }

        // Inicializar FirebaseFirestore
        db = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser(); // Obtener el usuario actual
        if (currentUser != null) {
            String userId = currentUser.getUid(); // Obtener el UID del usuario

            // Escuchar cambios en la subcolección 'captured_pokemon' del usuario
            db.collection("users") // Usar la colección 'users'
                    .document(userId) // Documento del usuario actual
                    .collection("captured_pokemon") // Subcolección de Pokémon capturados
                    .addSnapshotListener((querySnapshot, e) -> {
                        if (e != null) {
                            return;
                        }

                        if (querySnapshot != null) {
                            capturedPokemonList.clear(); // Limpiar lista antes de añadir nuevos elementos
                            for (DocumentSnapshot document : querySnapshot) {
                                // Convertir el DocumentSnapshot en un objeto PokemonFirestore
                                PokemonFirestore pokemon = document.toObject(PokemonFirestore.class);
                                if (pokemon != null) {
                                    capturedPokemonList.add(pokemon); // Añadir Pokémon capturado a la lista
                                }
                            }
                            capturedPokemonAdapter.notifyDataSetChanged(); // Notificar al adaptador
                        }
                    });
        }


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Liberar el binding para evitar fugas de memoria
        binding = null;
    }

    public void refreshLanguaje(){
        binding.tvCapturedPokemon.setText(R.string.captured_pokemons);
        binding.recyclerCapturedPokemon.getAdapter().notifyDataSetChanged();
    }

    public CapturedPokemonAdapter getAdapter(){
        return capturedPokemonAdapter;
    }
}

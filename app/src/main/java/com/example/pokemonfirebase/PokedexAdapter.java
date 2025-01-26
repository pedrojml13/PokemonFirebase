package com.example.pokemonfirebase;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonfirebase.databinding.ItemPokemonBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokemonViewHolder> {

    private List<PokemonGSON> pokemonGSONList;
    private OnPokemonClickListener onPokemonClickListener;

    public interface OnPokemonClickListener {
        void onPokemonClick(PokemonGSON pokemonGSON);
    }

    public PokedexAdapter(List<PokemonGSON> pokemonGSONList, OnPokemonClickListener onPokemonClickListener) {
        this.pokemonGSONList = pokemonGSONList;
        this.onPokemonClickListener = onPokemonClickListener;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar la vista usando View Binding
        ItemPokemonBinding binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PokemonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        PokemonGSON pokemonGSON = pokemonGSONList.get(position);
        holder.bind(pokemonGSON);
    }

    @Override
    public int getItemCount() {
        return pokemonGSONList.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {
        private final ItemPokemonBinding binding;

        public PokemonViewHolder(@NonNull ItemPokemonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PokemonGSON pokemonGSON) {
            // Usar binding para configurar las vistas
            binding.pokemonName.setText(pokemonGSON.getName());

            // Consultar Firestore para ver si el Pokémon está capturado
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser(); // Obtener el usuario actual
            if (currentUser != null) {
                String userId = currentUser.getUid(); // Obtener el UID del usuario

                // Consultar en la subcolección 'captured_pokemon' del usuario
                db.collection("users") // Usar la colección 'users'
                        .document(userId) // Documento del usuario actual
                        .collection("captured_pokemon") // Subcolección de Pokémon capturados
                        .document(pokemonGSON.getName()) // Usar el nombre del Pokémon como documento
                        .addSnapshotListener((documentSnapshot, e) -> {
                            if (e != null) {
                                return;
                            }

                            if (documentSnapshot != null && documentSnapshot.exists()) {
                                // Fondo rojo si está capturado
                                binding.getRoot().setBackgroundResource(R.drawable.pokemon_pokedex_captured);
                                binding.pokeball.setVisibility(View.VISIBLE);
                            } else {
                                // Fondo blanco si no está capturado
                                binding.getRoot().setBackgroundResource(R.drawable.pokemon_pokedex_no_captured);
                                binding.pokeball.setVisibility(View.GONE);
                            }
                        });
            }


            // Asegurarse de que el clic sea manejado correctamente
            binding.getRoot().setOnClickListener(v -> {
                onPokemonClickListener.onPokemonClick(pokemonGSON);
            });
        }
    }
}

package com.example.pokemonfirebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pokemonfirebase.databinding.FragmentPokedexBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokedexFragment extends Fragment {
    private FragmentPokedexBinding binding;
    private PokedexAdapter pokedexAdapter;
    private List<PokemonGSON> pokemonGSONList = new ArrayList<>();
    private PokemonApiService pokemonApiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Configurar ViewBinding
        binding = FragmentPokedexBinding.inflate(inflater, container, false);

        // Configurar RecyclerView
        binding.recyclerPokedex.setLayoutManager(new LinearLayoutManager(getContext()));
        pokedexAdapter = new PokedexAdapter(pokemonGSONList, this::capturePokemon);
        binding.recyclerPokedex.setAdapter(pokedexAdapter);

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pokemonApiService = retrofit.create(PokemonApiService.class);
        fetchPokemonList(0, 150);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void fetchPokemonList(int offset, int limit) {
        pokemonApiService.getPokemonList(offset, limit).enqueue(new Callback<PokedexResponse>() {
            @Override
            public void onResponse(Call<PokedexResponse> call, Response<PokedexResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pokemonGSONList.addAll(response.body().getResults());
                    pokedexAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PokedexResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void fetchPokemonDetails(String pokemonName) {
        pokemonApiService.getPokemonDetails(pokemonName).enqueue(new Callback<PokemonGSON>() {
            @Override
            public void onResponse(Call<PokemonGSON> call, Response<PokemonGSON> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonGSON pokemonGSON = response.body();
                    checkIfPokemonCaptured(pokemonGSON.getName(), pokemonGSON);
                }
            }

            @Override
            public void onFailure(Call<PokemonGSON> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void checkIfPokemonCaptured(String pokemonName, PokemonGSON pokemonGSON) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser(); // Obtener el usuario actual
        if (currentUser != null) {
            String userId = currentUser.getUid(); // Obtener el UID del usuario
            db.collection("users") // Usar una colección 'users' para almacenar datos de usuarios
                    .document(userId) // Buscar el documento del usuario actual
                    .collection("captured_pokemon") // Subcolección para los Pokémon capturados por el usuario
                    .document(pokemonName) // Usar el nombre del Pokémon como el ID del documento
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            Toast.makeText(getContext(), R.string.pokemon_already_captured, Toast.LENGTH_SHORT).show();
                        } else {
                            savePokemonToFirestore(pokemonGSON); // Guardar el Pokémon si no ha sido capturado
                            Toast.makeText(getContext(), R.string.pokemon_capture, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(Throwable::printStackTrace);
        }
    }


    private void capturePokemon(PokemonGSON pokemonGSON) {
        fetchPokemonDetails(pokemonGSON.getName());
    }

    private void savePokemonToFirestore(PokemonGSON pokemonGSON) {
        List<String> typeNames = new ArrayList<>();
        for (PokemonGSON.TypeSlot type : pokemonGSON.getTypes()) {
            if (type != null) {
                typeNames.add(type.getType().getTypeName());
            }
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            Map<String, Object> pokemonData = new HashMap<>();
            pokemonData.put("name", pokemonGSON.getName());
            pokemonData.put("height", pokemonGSON.getHeight());
            pokemonData.put("weight", pokemonGSON.getWeight());
            pokemonData.put("imageUrl", pokemonGSON.getSprites().getFront_default());
            pokemonData.put("imageDetailsUrl", pokemonGSON.getSprites().getOther().getOfficialArtwork().getFront_default());
            pokemonData.put("types", typeNames);
            pokemonData.put("index", pokemonGSON.getIndex());

            db.collection("users") // Usar la colección 'users'
                    .document(userId) // Documento del usuario actual
                    .collection("captured_pokemon") // Subcolección para Pokémon capturados
                    .document(pokemonGSON.getName()) // Usar el nombre del Pokémon como documento
                    .set(pokemonData)
                    .addOnSuccessListener(aVoid -> pokedexAdapter.notifyDataSetChanged())
                    .addOnFailureListener(Throwable::printStackTrace);
        }
    }

    public void refreshLanguaje(){
        binding.tvPokedex.setText(R.string.pokedex);
    }

}

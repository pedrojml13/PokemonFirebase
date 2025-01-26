package com.example.pokemonfirebase;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;
import com.example.pokemonfirebase.databinding.ActivityPokemonDetailBinding;

public class PokemonDetailActivity extends AppCompatActivity {

    private ActivityPokemonDetailBinding binding;
    private GradientDrawable backgroundType1, backgroundType2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializamos el ViewBinding
        binding = ActivityPokemonDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Recuperamos el objeto PokemonFirestore del Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("pokemon")) {
            PokemonFirestore pokemon = (PokemonFirestore) intent.getSerializableExtra("pokemon");

            if (pokemon != null) {
                // Asignamos los datos a las vistas
                Picasso.get().load(pokemon.getImageDetailsUrl()).into(binding.pokemonDetailsImageView);

                binding.pokemonNameTextView.setText(pokemon.getName());
                binding.pokemonIndexTextView.setText(getString(R.string.index) + ": " + pokemon.getIndex());
                binding.pokemonWeightTextView.setText(getString(R.string.weight) + ": " + String.valueOf(pokemon.getWeight()/10.0) + "kg");
                binding.pokemonHeightTextView.setText(getString(R.string.height) + ": " + String.valueOf(pokemon.getHeight()/10.0) + "m");

                // Tipo 1
                backgroundType1 = (GradientDrawable) binding.pokemonType1TextView.getBackground();
                switch (pokemon.getTypes().get(0)) {
                    case "normal":
                        binding.pokemonType1TextView.setText(getString(R.string.type_normal));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.normal));
                        break;
                    case "poison":
                        binding.pokemonType1TextView.setText(getString(R.string.type_poison));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.poison));
                        break;
                    case "psychic":
                        binding.pokemonType1TextView.setText(getString(R.string.type_psychic));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.psychic));
                        break;
                    case "food":
                        binding.pokemonType1TextView.setText(getString(R.string.type_food));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.food));
                        break;
                    case "grass":
                        binding.pokemonType1TextView.setText(getString(R.string.type_grass));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.grass));
                        break;
                    case "ground":
                        binding.pokemonType1TextView.setText(getString(R.string.type_ground));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.ground));
                        break;
                    case "ice":
                        binding.pokemonType1TextView.setText(getString(R.string.type_ice));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.ice));
                        break;
                    case "slug":
                        binding.pokemonType1TextView.setText(getString(R.string.type_slug));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.slug));
                        break;
                    case "fire":
                        binding.pokemonType1TextView.setText(getString(R.string.type_fire));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.fire));
                        break;
                    case "rock":
                        binding.pokemonType1TextView.setText(getString(R.string.type_rock));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.rock));
                        break;
                    case "dragon":
                        binding.pokemonType1TextView.setText(getString(R.string.type_dragon));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.dragon));
                        break;
                    case "plastic":
                        binding.pokemonType1TextView.setText(getString(R.string.type_plastic));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.plastic));
                        break;
                    case "water":
                        binding.pokemonType1TextView.setText(getString(R.string.type_water));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.water));
                        break;
                    case "bug":
                        binding.pokemonType1TextView.setText(getString(R.string.type_bug));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.bug));
                        break;
                    case "dark":
                        binding.pokemonType1TextView.setText(getString(R.string.type_dark));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.dark));
                        break;
                    case "wind":
                        binding.pokemonType1TextView.setText(getString(R.string.type_wind));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.wind));
                        break;
                    case "fighting":
                        binding.pokemonType1TextView.setText(getString(R.string.type_fighting));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.fighting));
                        break;
                    case "ghost":
                        binding.pokemonType1TextView.setText(getString(R.string.type_ghost));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.ghost));
                        break;
                    case "steel":
                        binding.pokemonType1TextView.setText(getString(R.string.type_steel));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.steel));
                        break;
                    case "crystal":
                        binding.pokemonType1TextView.setText(getString(R.string.type_crystal));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.crystal));
                        break;
                    case "flying":
                        binding.pokemonType1TextView.setText(getString(R.string.type_flying));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.flying));
                        break;
                    case "electric":
                        binding.pokemonType1TextView.setText(getString(R.string.type_electric));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.electric));
                        break;
                    case "fairy":
                        binding.pokemonType1TextView.setText(getString(R.string.type_fairy));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.fairy));
                        break;
                    case "light":
                        binding.pokemonType1TextView.setText(getString(R.string.type_light));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.light));
                        break;
                    default:
                        binding.pokemonType1TextView.setText(getString(R.string.type_unknown));
                        backgroundType1.setColor(ContextCompat.getColor(this, R.color.grey));
                        break;
                }

                // Tipo 2
                if (pokemon.getTypes().size() == 2) {
                    binding.pokemonType2TextView.setVisibility(View.VISIBLE);
                    backgroundType2 = (GradientDrawable) binding.pokemonType2TextView.getBackground();

                    switch (pokemon.getTypes().get(1)) {
                        case "normal":
                            binding.pokemonType2TextView.setText(getString(R.string.type_normal));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.normal));
                            break;
                        case "poison":
                            binding.pokemonType2TextView.setText(getString(R.string.type_poison));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.poison));
                            break;
                        case "psychic":
                            binding.pokemonType2TextView.setText(getString(R.string.type_psychic));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.psychic));
                            break;
                        case "food":
                            binding.pokemonType2TextView.setText(getString(R.string.type_food));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.food));
                            break;
                        case "grass":
                            binding.pokemonType2TextView.setText(getString(R.string.type_grass));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.grass));
                            break;
                        case "ground":
                            binding.pokemonType2TextView.setText(getString(R.string.type_ground));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.ground));
                            break;
                        case "ice":
                            binding.pokemonType2TextView.setText(getString(R.string.type_ice));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.ice));
                            break;
                        case "slug":
                            binding.pokemonType2TextView.setText(getString(R.string.type_slug));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.slug));
                            break;
                        case "fire":
                            binding.pokemonType2TextView.setText(getString(R.string.type_fire));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.fire));
                            break;
                        case "rock":
                            binding.pokemonType2TextView.setText(getString(R.string.type_rock));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.rock));
                            break;
                        case "dragon":
                            binding.pokemonType2TextView.setText(getString(R.string.type_dragon));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.dragon));
                            break;
                        case "plastic":
                            binding.pokemonType2TextView.setText(getString(R.string.type_plastic));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.plastic));
                            break;
                        case "water":
                            binding.pokemonType2TextView.setText(getString(R.string.type_water));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.water));
                            break;
                        case "bug":
                            binding.pokemonType2TextView.setText(getString(R.string.type_bug));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.bug));
                            break;
                        case "dark":
                            binding.pokemonType2TextView.setText(getString(R.string.type_dark));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.dark));
                            break;
                        case "wind":
                            binding.pokemonType2TextView.setText(getString(R.string.type_wind));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.wind));
                            break;
                        case "fighting":
                            binding.pokemonType2TextView.setText(getString(R.string.type_fighting));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.fighting));
                            break;
                        case "ghost":
                            binding.pokemonType2TextView.setText(getString(R.string.type_ghost));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.ghost));
                            break;
                        case "steel":
                            binding.pokemonType2TextView.setText(getString(R.string.type_steel));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.steel));
                            break;
                        case "crystal":
                            binding.pokemonType2TextView.setText(getString(R.string.type_crystal));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.crystal));
                            break;
                        case "flying":
                            binding.pokemonType2TextView.setText(getString(R.string.type_flying));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.flying));
                            break;
                        case "electric":
                            binding.pokemonType2TextView.setText(getString(R.string.type_electric));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.electric));
                            break;
                        case "fairy":
                            binding.pokemonType2TextView.setText(getString(R.string.type_fairy));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.fairy));
                            break;
                        case "light":
                            binding.pokemonType2TextView.setText(getString(R.string.type_light));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.light));
                            break;
                        default:
                            binding.pokemonType2TextView.setText(getString(R.string.type_unknown));
                            backgroundType2.setColor(ContextCompat.getColor(this, R.color.grey));
                            break;
                    }
                }
                binding.backButton.setOnClickListener(v -> {
                            getOnBackPressedDispatcher().onBackPressed(); // Volver a la actividad anterior
                        });
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Liberamos el binding
    }
}

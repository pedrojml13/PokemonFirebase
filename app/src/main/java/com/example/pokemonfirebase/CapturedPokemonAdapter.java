package com.example.pokemonfirebase;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonfirebase.databinding.PokemonCardviewBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CapturedPokemonAdapter extends RecyclerView.Adapter<CapturedPokemonAdapter.CapturedPokemonViewHolder> {

    private List<PokemonFirestore> capturedPokemonList;
    private OnItemClickListener onItemClickListener;
    private boolean isDeletionEnabled;

    public void setDeletionEnabled(boolean isDeletionEnabled){
        this.isDeletionEnabled = isDeletionEnabled;
        notifyDataSetChanged();
        
    }

    public interface OnItemClickListener {
        void onItemClick(PokemonFirestore pokemon);
    }

    public CapturedPokemonAdapter(List<PokemonFirestore> capturedPokemonList, OnItemClickListener onItemClickListener) {
        this.capturedPokemonList = capturedPokemonList;
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public CapturedPokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonCardviewBinding binding = PokemonCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CapturedPokemonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CapturedPokemonViewHolder holder, int position) {
        PokemonFirestore pokemon = capturedPokemonList.get(position);
        holder.bind(pokemon);
    }

    @Override
    public int getItemCount() {
        return capturedPokemonList.size();
    }

    public class CapturedPokemonViewHolder extends RecyclerView.ViewHolder {
        private final PokemonCardviewBinding binding;

        public CapturedPokemonViewHolder(@NonNull PokemonCardviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            // Configurar el clic en el elemento
            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(capturedPokemonList.get(getAdapterPosition()));
                }
            });
        }

        public void bind(PokemonFirestore pokemon) {
            binding.pokemonNameTextView.setText(pokemon.getName());

            // Configurar Tipo 1
            String type1 = pokemon.getTypes().get(0);
            setType(binding.pokemonType1TextView, type1);

            // Configurar Tipo 2
            if (pokemon.getTypes().size() > 1) {
                binding.pokemonType2TextView.setVisibility(View.VISIBLE);
                String type2 = pokemon.getTypes().get(1);
                setType(binding.pokemonType2TextView, type2);
            } else {
                binding.pokemonType2TextView.setVisibility(View.GONE);
            }

            // Cargar imagen del Pokémon
            Picasso.get()
                    .load(pokemon.getImageUrl())
                    .into(binding.pokemonImageView);

            // Mostrar u ocultar el botón de eliminar
            if (isDeletionEnabled) {
                binding.btnDeletePokemon.setVisibility(View.VISIBLE);
                binding.btnDeletePokemon.setOnClickListener(v -> {

                    // Obtener el usuario actual
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (currentUser != null) {
                        String userId = currentUser.getUid(); // Obtener el UID del usuario

                        // Eliminar el Pokémon de la subcolección 'captured_pokemon' del usuario
                        FirebaseFirestore.getInstance().collection("users")
                                .document(userId)
                                .collection("captured_pokemon")
                                .document(pokemon.getName()) // Usar el nombre del Pokémon como ID del documento
                                .delete()
                                .addOnSuccessListener(aVoid -> {
                                    // Si la eliminación es exitosa, eliminar el Pokémon de la lista local
                                    capturedPokemonList.remove(pokemon); // Eliminar el objeto Pokémon específico
                                    // Notificar al adaptador que los datos han cambiado
                                    notifyDataSetChanged();

                                })
                                .addOnFailureListener(e -> {

                                });
                    }
                });
            } else {
                binding.btnDeletePokemon.setVisibility(View.GONE);
            }



        }

        private void setType(TextView textView, String type) {
            GradientDrawable background = (GradientDrawable) textView.getBackground();
            int color;
            int stringRes;

            switch (type) {
                case "normal":
                    stringRes = R.string.type_normal;
                    color = R.color.normal;
                    break;
                case "fire":
                    stringRes = R.string.type_fire;
                    color = R.color.fire;
                    break;
                case "water":
                    stringRes = R.string.type_water;
                    color = R.color.water;
                    break;
                case "grass":
                    stringRes = R.string.type_grass;
                    color = R.color.grass;
                    break;
                case "electric":
                    stringRes = R.string.type_electric;
                    color = R.color.electric;
                    break;
                case "ice":
                    stringRes = R.string.type_ice;
                    color = R.color.ice;
                    break;
                case "fighting":
                    stringRes = R.string.type_fighting;
                    color = R.color.fighting;
                    break;
                case "poison":
                    stringRes = R.string.type_poison;
                    color = R.color.poison;
                    break;
                case "ground":
                    stringRes = R.string.type_ground;
                    color = R.color.ground;
                    break;
                case "flying":
                    stringRes = R.string.type_flying;
                    color = R.color.flying;
                    break;
                case "psychic":
                    stringRes = R.string.type_psychic;
                    color = R.color.psychic;
                    break;
                case "bug":
                    stringRes = R.string.type_bug;
                    color = R.color.bug;
                    break;
                case "rock":
                    stringRes = R.string.type_rock;
                    color = R.color.rock;
                    break;
                case "ghost":
                    stringRes = R.string.type_ghost;
                    color = R.color.ghost;
                    break;
                case "dragon":
                    stringRes = R.string.type_dragon;
                    color = R.color.dragon;
                    break;
                case "dark":
                    stringRes = R.string.type_dark;
                    color = R.color.dark;
                    break;
                case "steel":
                    stringRes = R.string.type_steel;
                    color = R.color.steel;
                    break;
                case "fairy":
                    stringRes = R.string.type_fairy;
                    color = R.color.fairy;
                    break;
                default:
                    stringRes = R.string.type_unknown;
                    color = R.color.grey;
                    break;
            }

            textView.setText(itemView.getContext().getString(stringRes));
            background.setColor(itemView.getContext().getResources().getColor(color));


        }
    }
}

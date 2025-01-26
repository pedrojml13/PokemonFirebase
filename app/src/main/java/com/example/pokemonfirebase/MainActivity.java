package com.example.pokemonfirebase;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pokemonfirebase.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // Declaración de View Binding
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Inflar el layout con View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar ViewPager y TabLayout
        viewPagerAdapter = new ViewPagerAdapter(this);
        binding.viewPager.setAdapter(viewPagerAdapter);

        // Configurar el PageTransformer
        binding.viewPager.setPageTransformer(new CustomPageTransformer());


        // Vincular TabLayout con ViewPager
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(R.string.captured_pokemons);
                    tab.setIcon(R.drawable.pokeball_icon);
                    break;
                case 1:
                    tab.setText(R.string.pokedex);
                    tab.setIcon(R.drawable.pokedex_icon);
                    break;
                case 2:
                    tab.setText(R.string.settings);
                    tab.setIcon(R.drawable.settings_icon);
                    break;
            }
        }).attach();



    }

    public void RefreshLanguaje(){
            Objects.requireNonNull(binding.tabLayout.getTabAt(0)).setText(R.string.captured_pokemons);
            Objects.requireNonNull(binding.tabLayout.getTabAt(1)).setText(R.string.pokedex);
            Objects.requireNonNull(binding.tabLayout.getTabAt(2)).setText(R.string.settings);
    }

    public CapturedPokemonFragment getCapturedPokemonFragment() {
        return viewPagerAdapter.getCapturedPokemonFragment();
    }

    public PokedexFragment getPokedexFragment(){
        return viewPagerAdapter.getPokedexFragment();
    }




}

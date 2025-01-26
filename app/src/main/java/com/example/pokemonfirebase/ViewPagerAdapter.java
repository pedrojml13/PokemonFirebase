package com.example.pokemonfirebase;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private CapturedPokemonFragment capturedPokemonFragment;
    private PokedexFragment pokedexFragment;
    private SettingsFragment settingsFragment;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                if (capturedPokemonFragment == null) {
                    capturedPokemonFragment = new CapturedPokemonFragment();
                }
                return capturedPokemonFragment;

            case 1:
                if(pokedexFragment == null){
                    pokedexFragment = new PokedexFragment();
                }
                return pokedexFragment;
            case 2:
                if(settingsFragment == null){
                    settingsFragment = new SettingsFragment();
                }
                return settingsFragment;

            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public CapturedPokemonFragment getCapturedPokemonFragment() {
        return capturedPokemonFragment;
    }
    public SettingsFragment getSettingFragment(){
        return settingsFragment;
    }
    public PokedexFragment getPokedexFragment(){
        return pokedexFragment;
    }

}

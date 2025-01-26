package com.example.pokemonfirebase;

import java.io.Serializable;
import java.util.List;

// Clase para manejar los datos de Firestore
public class PokemonFirestore implements Serializable {

    private String name;
    private int height;
    private int weight;
    private String imageUrl;
    private String imageDetailsUrl;
    private int index;
    private List<String> types;  // Lista de tipos como cadenas (por ejemplo, "grass", "poison")

    // Constructor vacío necesario para Firebase
    public PokemonFirestore() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageDetailsUrl() {
        return imageDetailsUrl;
    }

    public void setImageDetailsUrlUrl(String imageDetailsUrlUrl) {
        this.imageDetailsUrl = imageDetailsUrlUrl;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

}

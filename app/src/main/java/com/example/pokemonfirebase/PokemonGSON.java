package com.example.pokemonfirebase;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// Clase que representa la información de un Pokémon obtenida desde la API de PokeAPI
public class PokemonGSON {
    @SerializedName("name")
    private String name;

    @SerializedName("height")
    private int height;

    @SerializedName("weight")
    private int weight;

    @SerializedName("sprites")
    private Sprites sprites;

    @SerializedName("types")
    private List<TypeSlot> types;

    @SerializedName("id")
    private int index;

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

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public List<TypeSlot> getTypes() {
        return types;
    }

    public void setTypes(List<TypeSlot> types) {
        this.types = types;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    // Clase interna que representa las imágenes del Pokémon
    public static class Sprites {
        @SerializedName("front_default")
        private String front_default;

        @SerializedName("other")
        private OtherSprites other;

        public String getFront_default() {
            return front_default;
        }

        public void setFront_default(String front_default) {
            this.front_default = front_default;
        }

        public OtherSprites getOther() {
            return other;
        }

        public void setOther(OtherSprites other) {
            this.other = other;
        }

        // Clase interna que representa la sección "other" en los sprites
        public static class OtherSprites {
            @SerializedName("official-artwork")
            private OfficialArtwork officialArtwork;

            public OfficialArtwork getOfficialArtwork() {
                return officialArtwork;
            }

            public void setOfficialArtwork(OfficialArtwork officialArtwork) {
                this.officialArtwork = officialArtwork;
            }

            // Clase interna que representa "official-artwork"
            public static class OfficialArtwork {
                @SerializedName("front_default")
                private String front_default;

                public String getFront_default() {
                    return front_default;
                }

                public void setFront_default(String front_default) {
                    this.front_default = front_default;
                }
            }
        }
    }


    // Clase interna que representa los tipos de un Pokémon
    public static class TypeSlot {
        @SerializedName("type")
        private Type type;

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        // Clase interna que representa el tipo específico de un Pokémon
        public static class Type {
            @SerializedName("name")
            private String name;

            public String getTypeName() {
                return name;
            }

            public void setTypeName(String name) {
                this.name = name;
            }
        }
    }
}

package com.example.pokemonfirebase;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonApiService {
    @GET("pokemon")
    Call<PokedexResponse> getPokemonList(@Query("offset") int offset, @Query("limit") int limit);

    @GET("pokemon/{name}")
    Call<PokemonGSON> getPokemonDetails(@Path("name") String name);
}


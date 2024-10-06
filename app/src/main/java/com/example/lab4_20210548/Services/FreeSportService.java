package com.example.lab4_20210548.Services;

import com.example.lab4_20210548.Objets.LeagueXCountry;
import com.example.lab4_20210548.Objets.ResultDTO;
import com.example.lab4_20210548.Objets.TeamDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FreeSportService {

    // Metodos para Ligas
    @GET("/api/v1/json/3/all_leagues.php")
    Call<LeagueXCountry> getLigasList();
    @GET("/api/v1/json/3/search_all_leagues.php")
    Call<LeagueXCountry> getLigasXPais(@Query("c") String pais);

    // Metodos para Posiciones
    @GET("/api/v1/json/3/lookuptable.php")
    Call<TeamDTO> getPosiciones(@Query("l") String id, @Query("s") String season);

    // Metodos para Resultados
    @GET("/api/v1/json/3/eventsround.php")
    Call<ResultDTO> getResultados(@Query("id") String id, @Query("s") String season, @Query("r") String ronda);
}

package com.example.lab4_20210548.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab4_20210548.Adapters.MyLigaRecycleAdapter;
import com.example.lab4_20210548.Objets.League;
import com.example.lab4_20210548.Objets.LeagueXCountry;
import com.example.lab4_20210548.R;
import com.example.lab4_20210548.Services.FreeSportService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLigas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLigas extends Fragment {

    MyLigaRecycleAdapter adapterLigas;
    RecyclerView recyclerView;
    Button btnBuscar;
    EditText editTextCountry;
    FreeSportService service;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentLigas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLigas.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLigas newInstance(String param1, String param2) {
        FragmentLigas fragment = new FragmentLigas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ligas, container, false);

        // Inicializacion el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewLigas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializacion el botón y el EditText
        btnBuscar = view.findViewById(R.id.searchButton);
        editTextCountry = view.findViewById(R.id.searchEditText);

        // Inicializar Retrofit
        service = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FreeSportService.class);

        // metodo que muestra todas las ligas por defecto cuando se abre el fragmento
        obtenerLigas();

        // Busqueda por país
        btnBuscar.setOnClickListener(v-> {
            String pais = editTextCountry.getText().toString().trim();
            if (pais.isEmpty()) {
                obtenerLigas();  // Si el país está vacío, se muestra todas las listas
            } else {
                buscarLigasPorPais(pais);
            }
        });

        return view;
    }

    // Método para obtener todas las ligas
    private void obtenerLigas() {
        Call<LeagueXCountry> call = service.getLigasList();
        call.enqueue(new Callback<LeagueXCountry>() {
            @Override
            public void onResponse(Call<LeagueXCountry> call, Response<LeagueXCountry> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<League> listaLigas = response.body().getLeagues();
                    actualizarRecyclerView(listaLigas);
                } else {
                    Toast.makeText(getContext(), ":c Error al obtener las ligas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LeagueXCountry> call, Throwable t) {
                Toast.makeText(getContext(), ":c Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para buscar ligas por país
    private void buscarLigasPorPais(String pais) {
        Call<LeagueXCountry> call = service.getLigasXPais(pais);
        call.enqueue(new Callback<LeagueXCountry>() {
            @Override
            public void onResponse(Call<LeagueXCountry> call, Response<LeagueXCountry> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<League> listaLigas = response.body().getLeagues();
                    if (listaLigas.isEmpty()) {
                        Toast.makeText(getContext(), "No se encontraron ligas para " + pais, Toast.LENGTH_SHORT).show();
                    } else {
                        actualizarRecyclerView(listaLigas);
                    }
                } else {
                    Toast.makeText(getContext(), "Error al buscar ligas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LeagueXCountry> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para actualizar el RecyclerView
    private void actualizarRecyclerView(List<League> listaLigas) {
        adapterLigas = new MyLigaRecycleAdapter(listaLigas, getContext());
        recyclerView.setAdapter(adapterLigas);
    }
}
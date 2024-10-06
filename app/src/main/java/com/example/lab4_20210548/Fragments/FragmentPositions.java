package com.example.lab4_20210548.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab4_20210548.Adapters.MyPositionsRecycleAdapter;
import com.example.lab4_20210548.Objets.Team;
import com.example.lab4_20210548.Objets.TeamDTO;
import com.example.lab4_20210548.R;
import com.example.lab4_20210548.Services.FreeSportService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPositions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPositions extends Fragment {
    private RecyclerView recyclerView;
    private MyPositionsRecycleAdapter adapter;
    private EditText editTextIdLiga, editTextTemporada;
    private FreeSportService apiService;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPositions() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPositions.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPositions newInstance(String param1, String param2) {
        FragmentPositions fragment = new FragmentPositions();
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
        View view = inflater.inflate(R.layout.fragment_positions, container, false);


        recyclerView = view.findViewById(R.id.recyclerViewPosiciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        editTextIdLiga = view.findViewById(R.id.searchEditTextLiga);
        editTextTemporada = view.findViewById(R.id.searchEditTextTemporada);

        view.findViewById(R.id.searchButtonPosition).setOnClickListener(v -> {
            String idLiga = editTextIdLiga.getText().toString();
            String temporada = editTextTemporada.getText().toString();

            if (!idLiga.isEmpty() && !temporada.isEmpty()) {
                buscarPosiciones(idLiga, temporada);
            } else {
                Toast.makeText(getContext(), "Debe ingresar el ID de la liga y la temporada", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void buscarPosiciones(String idLiga, String temporada) {
        // Llamada a la API
        Call<TeamDTO> call = apiService.getPosiciones(idLiga, temporada);
        call.enqueue(new Callback<TeamDTO>() {
            @Override
            public void onResponse(Call<TeamDTO> call, Response<TeamDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Team> teams = response.body().getPosiciones();
                    if (teams != null && !teams.isEmpty()) {
                        adapter = new MyPositionsRecycleAdapter(teams);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(getContext(), "No se encontraron equipos para la liga o temporada", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TeamDTO> call, Throwable t) {
                Toast.makeText(getContext(), "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.lab4_20210548.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_20210548.Objets.Team;
import com.example.lab4_20210548.Objets.TeamDTO;
import com.example.lab4_20210548.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyPositionsRecycleAdapter extends RecyclerView.Adapter<MyPositionsRecycleAdapter.ViewHolder> {
    private List<Team> teams;

    public MyPositionsRecycleAdapter(List<Team> teams) {
        this.teams = teams;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_position, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Team team = teams.get(position);
        holder.teamName.setText(team.getStrTeam());
        holder.teamRanking.setText(team.getIntRank());
        // Setear victorias, empates y derrotas
        TextView textViewPartidos = holder.itemView.findViewById(R.id.text_view_estadisticas);
        String nuevoTextoPartidos = "Victorias: " + team.getIntWin() +
                ", Empates: " + team.getIntDraw() +
                ", Derrotas: " + team.getIntLoss();
        textViewPartidos.setText(nuevoTextoPartidos);

        // Seteo de goles anotados, goles concedidos y diferencia
        TextView textViewGoles = holder.itemView.findViewById(R.id.text_view_goles);
        String nuevoTextoGoles = "Goles anotados: " + team.getIntGoalsFor() +
                ", Goles concedidos: " + team.getIntGoalsAgainst() +
                ", Diferencia: " + team.getIntGoalDifference();
        textViewGoles.setText(nuevoTextoGoles);

        // Cargamos la imagen del badge del equipo con Picasso
        Picasso.get().load(team.getStrBadge()).into(holder.teamBadge);
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView teamName, teamRanking, teamRecord, teamGoals;
        ImageView teamBadge;

        public ViewHolder(View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.text_view_name_equipo);
            teamRanking = itemView.findViewById(R.id.text_view_ranking);
            teamBadge = itemView.findViewById(R.id.bagfe);
        }
    }
}

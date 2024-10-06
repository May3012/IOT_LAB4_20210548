package com.example.lab4_20210548.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab4_20210548.Objets.League;
import com.example.lab4_20210548.R;

import java.util.List;

public class MyLigaRecycleAdapter extends RecyclerView.Adapter<MyLigaRecycleAdapter.ViewHolder> {

    private List<League> listaLigas;
    private Context context;

    public MyLigaRecycleAdapter(List<League> listaLigas, Context context) {
        this.listaLigas = listaLigas;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_liga, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        League liga = listaLigas.get(position);
        holder.textViewName.setText(liga.getStrLeague());
        holder.textViewId.setText(liga.getIdLeague());
        // Obtener la cadena de ligas alternativas y dividirla
        String strLeagueAlternate = liga.getStrLeagueAlternate();
        if (strLeagueAlternate != null && !strLeagueAlternate.isEmpty()) {
            String[] ligasAlternativas = strLeagueAlternate.split(",");

            // Limpiar espacios en blanco y asignar los valores a los TextView
            if (ligasAlternativas.length > 0) {
                holder.textViewAlternate1.setText(ligasAlternativas[0].trim());
            }

            if (ligasAlternativas.length > 1) {
                holder.textViewAlternate2.setText(ligasAlternativas[1].trim());
            }
        } else {
            // Si no hay ligas alternativas, limpiar los TextView
            holder.textViewAlternate1.setText("");
            holder.textViewAlternate2.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return listaLigas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewId;
        TextView textViewAlternate1;
        TextView textViewAlternate2;

        ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewId = itemView.findViewById(R.id.text_view_id);
            textViewAlternate1 = itemView.findViewById(R.id.text_view_alternate1);
            textViewAlternate2 = itemView.findViewById(R.id.text_view_alternate2);
        }
    }
}

package com.example.lab4_20210548.Objets;

import java.io.Serializable;
import java.util.List;

public class TeamDTO {
    List<Team> posiciones;

    public List<Team> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<Team> posiciones) {
        this.posiciones = posiciones;
    }
}

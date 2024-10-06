package com.example.lab4_20210548.Objets;

import java.io.Serializable;
import java.util.List;

public class TeamDTO implements Serializable {
    List<TeamDTO> posiciones;

    public List<TeamDTO> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<TeamDTO> posiciones) {
        this.posiciones = posiciones;
    }
}

package com.example.lab4_20210548.Objets;

import java.io.Serializable;
import java.util.List;

public class LeagueXCountry {
    List<League> leagues;
    List<League> countries;

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public List<League> getCountries() {
        return countries;
    }

    public void setCountries(List<League> countries) {
        this.countries = countries;
    }
}

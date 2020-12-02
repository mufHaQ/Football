package com.mufhaq.football;

import io.realm.RealmObject;

public class ModelDataFavourite extends RealmObject {
    int id;
    String teamName, teamNameAlternate, teamCountry, teamYear, badge, teamDesc;

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamNameAlternate() {
        return teamNameAlternate;
    }

    public void setTeamNameAlternate(String teamNameAlternate) {
        this.teamNameAlternate = teamNameAlternate;
    }

    public String getTeamCountry() {
        return teamCountry;
    }

    public void setTeamCountry(String teamCountry) {
        this.teamCountry = teamCountry;
    }

    public String getTeamYear() {
        return teamYear;
    }

    public void setTeamYear(String teamYear) {
        this.teamYear = teamYear;
    }
}

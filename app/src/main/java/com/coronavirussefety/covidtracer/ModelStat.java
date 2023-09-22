package com.coronavirussefety.covidtracer;

public class ModelStat {

    /*{
        "ID": "3fa63f3a-af66-4651-8e84-449d77be3565",
            "Country": "Afghanistan",
            "CountryCode": "AF",
            "Slug": "afghanistan",
            "NewConfirmed": 0,
            "TotalConfirmed": 182072,
            "NewDeaths": 0,
            "TotalDeaths": 7717,
            "NewRecovered": 0,
            "TotalRecovered": 0,
            "Date": "2022-06-26T09:34:36.863Z",
            "Premium": {}
    },*/

    String Country, CountryCode, Slug, NewConfirmed, TotalConfirmed, NewDeaths, TotalDeaths, NewRecovered,TotalRecovered, Date;

    public ModelStat(String country, String countryCode, String slug, String newConfirmed, String totalConfirmed, String newDeaths, String totalDeaths, String newRecovered, String totalRecovered, String date) {
        Country = country;
        CountryCode = countryCode;
        Slug = slug;
        NewConfirmed = newConfirmed;
        TotalConfirmed = totalConfirmed;
        NewDeaths = newDeaths;
        TotalDeaths = totalDeaths;
        NewRecovered = newRecovered;
        TotalRecovered = totalRecovered;
        Date = date;
    }


    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getSlug() {
        return Slug;
    }

    public void setSlug(String slug) {
        Slug = slug;
    }

    public String getNewConfirmed() {
        return NewConfirmed;
    }

    public void setNewConfirmed(String newConfirmed) {
        NewConfirmed = newConfirmed;
    }

    public String getTotalConfirmed() {
        return TotalConfirmed;
    }

    public void setTotalConfirmed(String totalConfirmed) {
        TotalConfirmed = totalConfirmed;
    }

    public String getNewDeaths() {
        return NewDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        NewDeaths = newDeaths;
    }

    public String getTotalDeaths() {
        return TotalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        TotalDeaths = totalDeaths;
    }

    public String getNewRecovered() {
        return NewRecovered;
    }

    public void setNewRecovered(String newRecovered) {
        NewRecovered = newRecovered;
    }

    public String getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        TotalRecovered = totalRecovered;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}

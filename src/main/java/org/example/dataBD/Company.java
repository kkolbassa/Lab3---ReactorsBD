package org.example.dataBD;

public class Company {
    private int id;
    private String companies_name;
    private String full_name;
    private int country_id;

    public Company(int id, String companies_name, String full_name, int country_id) {
        this.id = id;
        this.companies_name = companies_name;
        this.full_name = full_name;
        this.country_id = country_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanies_name() {
        return companies_name;
    }

    public void setCompanies_name(String companies_name) {
        this.companies_name = companies_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}

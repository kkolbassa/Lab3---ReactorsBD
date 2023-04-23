package org.example.dataBD;

import org.apache.commons.lang3.StringUtils;

public class Site {
    private int id;
    private String npp_name;
    private int place;
    private int owner_id;
    private int operator;
    private int builder;

    public Site(int id, String npp_name, int place, int owner_id, int operator, int builder) {
        this.id = id;
        this.npp_name = StringUtils.trimToNull(npp_name);
        this.place = place;
        this.owner_id = owner_id;
        this.operator = operator;
        this.builder = builder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNpp_name() {
        return npp_name;
    }

    public void setNpp_name(String npp_name) {
        this.npp_name = npp_name;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public int getBuilder() {
        return builder;
    }

    public void setBuilder(int builder) {
        this.builder = builder;
    }
}

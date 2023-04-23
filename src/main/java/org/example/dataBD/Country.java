package org.example.dataBD;

import org.apache.commons.lang3.StringUtils;

public class Country {
    private int id;
    private String country_name;
    private String subregion;
    private String region;
    private int region_id;

    public Country(int id, String country_name, String subregion, String region, int region_id) {
        this.id = id;
        this.country_name = StringUtils.trimToNull(country_name);
        this.subregion = StringUtils.trimToNull(subregion);
        this.region = StringUtils.trimToNull(region);
        this.region_id = region_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }
}

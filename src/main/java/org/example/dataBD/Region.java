package org.example.dataBD;


import org.apache.commons.lang3.StringUtils;

public class Region {

    private int id;
    private String region_name;

    public Region(int id, String region_name) {
        this.id = id;
        this.region_name = StringUtils.trimToNull(region_name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}

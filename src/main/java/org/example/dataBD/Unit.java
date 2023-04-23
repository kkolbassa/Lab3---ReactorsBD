package org.example.dataBD;

public class Unit {
    private int id;
    private String code;
    private String unit_name;
    private int site;
    private String status;
    private String type;
    private String model;
    private String class_;
    private String ru_design;
    private int operator;
    private int nsss_supplier;
    private int thermal_capacity;
    private int gross_capacity;
    private int net_capacity;
    private String construction_start;
    private String commercial_operation;
    private String date_shutdown;
    private double enrichment;
    private int load_factor;
    private double burnup;
    private double fuelConsumption;


    public Unit(int id, String code, String unit_name, int site, String status, String type, String model, String class_, String ru_design, int operator, int nsss_supplier, int thermal_capacity, int gross_capacity, int net_capacity, String construction_start, String commercial_operation, String date_shutdown, double enrichment, int load_factor) {
        this.id = id;
        this.code = code;
        this.unit_name = unit_name;
        this.site = site;
        this.status = status;
        this.type = type;
        this.model = model;
        this.class_ = class_;
        this.ru_design = ru_design;
        this.operator = operator;
        this.nsss_supplier = nsss_supplier;
        this.thermal_capacity = thermal_capacity;
        this.gross_capacity = gross_capacity;
        this.net_capacity = net_capacity;
        this.construction_start = construction_start;
        this.commercial_operation = commercial_operation;
        this.date_shutdown = date_shutdown;
        this.enrichment = enrichment;
        this.load_factor = load_factor;
    }

    public void setNet_capacity(int net_capacity) {
        this.net_capacity = net_capacity;
    }

    public double getBurnup() {
        return burnup;
    }

    public void setBurnup(double burnup) {
        this.burnup = burnup;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getRu_design() {
        return ru_design;
    }

    public void setRu_design(String ru_design) {
        this.ru_design = ru_design;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public int getNsss_supplier() {
        return nsss_supplier;
    }

    public void setNsss_supplier(int nsss_supplier) {
        this.nsss_supplier = nsss_supplier;
    }

    public int getThermal_capacity() {
        return thermal_capacity;
    }

    public void setThermal_capacity(int thermal_capacity) {
        this.thermal_capacity = thermal_capacity;
    }

    public int getGross_capacity() {
        return gross_capacity;
    }

    public void setGross_capacity(int gross_capacity) {
        this.gross_capacity = gross_capacity;
    }

    public int getNet_capacity() {
        return net_capacity;
    }

    public void setNet_capacity(short net_capacity) {
        this.net_capacity = net_capacity;
    }

    public String getConstruction_start() {
        return construction_start;
    }

    public void setConstruction_start(String construction_start) {
        this.construction_start = construction_start;
    }

    public String getCommercial_operation() {
        return commercial_operation;
    }

    public void setCommercial_operation(String commercial_operation) {
        this.commercial_operation = commercial_operation;
    }

    public String getDate_shutdown() {
        return date_shutdown;
    }

    public void setDate_shutdown(String date_shutdown) {
        this.date_shutdown = date_shutdown;
    }

    public double getEnrichment() {
        return enrichment;
    }

    public void setEnrichment(double enrichment) {
        this.enrichment = enrichment;
    }

    public int getLoad_factor() {
        return load_factor;
    }

    public void setLoad_factor(int load_factor) {
        this.load_factor = load_factor;
    }
}

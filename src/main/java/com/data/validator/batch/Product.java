package com.data.validator.batch;

public class Product {

    private Long brand_id;
    private String brand_name;

    private String building_footprint_wkt;

    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(long brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBuilding_footprint_wkt() {
        return building_footprint_wkt;
    }

    public void setBuilding_footprint_wkt(String building_footprint_wkt) {
        this.building_footprint_wkt = building_footprint_wkt;
    }
}

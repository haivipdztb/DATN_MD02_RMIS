package com.ph48845.datn_qlnh_rmis.data.model;



public class Table {
    private String id;
    private String name;
    private String status;

    public Table(String id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
}
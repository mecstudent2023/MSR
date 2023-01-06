package com.mec.msr.myclasses;

import java.io.Serializable;

public class MyRequest implements Serializable {
    private int id;
    private String equipmentType;
    private String reserveTime;
    private String requestedBy;

    public MyRequest() {
    }

    public MyRequest( String equipmentType, String reserveTime, String requestedBy) {
        this.id = id;
        this.equipmentType = equipmentType;
        this.reserveTime = reserveTime;
        this.requestedBy = requestedBy;
    }
    public MyRequest(int id, String equipmentType, String reserveTime, String requestedBy) {
        this.id = id;
        this.equipmentType = equipmentType;
        this.reserveTime = reserveTime;
        this.requestedBy = requestedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
}

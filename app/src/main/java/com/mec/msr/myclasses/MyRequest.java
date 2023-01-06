package com.mec.msr.myclasses;

import java.io.Serializable;

public class MyRequest implements Serializable {
    private int id;
    private int userid;
    private String equipmentType;
    private String reserveTime;
    private String requestedBy;

    public MyRequest() {
    }

    public MyRequest(int userid, String equipmentType, String reserveTime, String requestedBy) {
        this.userid = userid;
        this.equipmentType = equipmentType;
        this.reserveTime = reserveTime;
        this.requestedBy = requestedBy;
    }
    public MyRequest(int id, int userid, String equipmentType, String reserveTime, String requestedBy) {
        this.id = id;
        this.userid = userid;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

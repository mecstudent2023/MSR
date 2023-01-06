package com.mec.msr;

public class MyRequests {
    private int id;
    private String forkliftType;
    private String reserveTime;
    private String requestedBy;

    public MyRequests() {
    }

    public MyRequests(int id, String forkliftType, String reserveTime, String requestedBy) {
        this.id = id;
        this.forkliftType = forkliftType;
        this.reserveTime = reserveTime;
        this.requestedBy = requestedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForkliftType() {
        return forkliftType;
    }

    public void setForkliftType(String forkliftType) {
        this.forkliftType = forkliftType;
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

package com.czvisual.entity;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.format.DateTimeFormat;

public class Data {
    @ExcelIgnore
    private Integer id;
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private String date;
    private float zzmlc;
    private String time;
    private float zzmMiddle;
    private float zzmLeft;
    private float zzmRight;
    private float zzmAverage;
    private float zzmWater;
    private float zzmFlowRate;
    private float temp1;
    private float temp2;
    private float temp3;
    private float tempAverage;
    private float moisture1;
    private float moisture2;
    private float moisture3;
    private float moistureAverage;
    private float wbgt1;
    private float wbgt2;
    private float wbgt3;
    private float wbgtAverage;
    private int dkFlowRate;
    private String status;
    private String basis;
    private String description;
    private float depth;
    @ExcelIgnore
    private String table;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getZzmlc() {
        return zzmlc;
    }

    public void setZzmlc(float zzmlc) {
        this.zzmlc = zzmlc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getZzmMiddle() {
        return zzmMiddle;
    }

    public void setZzmMiddle(float zzmMiddle) {
        this.zzmMiddle = zzmMiddle;
    }

    public float getZzmLeft() {
        return zzmLeft;
    }

    public void setZzmLeft(float zzmLeft) {
        this.zzmLeft = zzmLeft;
    }

    public float getZzmRight() {
        return zzmRight;
    }

    public void setZzmRight(float zzmRight) {
        this.zzmRight = zzmRight;
    }

    public float getZzmAverage() {
        return zzmAverage;
    }

    public void setZzmAverage(float zzmAverage) {
        this.zzmAverage = zzmAverage;
    }

    public float getZzmFlowRate() {
        return zzmFlowRate;
    }

    public void setZzmFlowRate(float zzmFlowRate) {
        this.zzmFlowRate = zzmFlowRate;
    }

    public float getTemp1() {
        return temp1;
    }

    public void setTemp1(float temp1) {
        this.temp1 = temp1;
    }

    public float getTemp2() {
        return temp2;
    }

    public void setTemp2(float temp2) {
        this.temp2 = temp2;
    }

    public float getTemp3() {
        return temp3;
    }

    public void setTemp3(float temp3) {
        this.temp3 = temp3;
    }

    public float getTempAverage() {
        return tempAverage;
    }

    public void setTempAverage(float tempAverage) {
        this.tempAverage = tempAverage;
    }

    public float getMoisture1() {
        return moisture1;
    }

    public void setMoisture1(float moisture1) {
        this.moisture1 = moisture1;
    }

    public float getMoisture2() {
        return moisture2;
    }

    public void setMoisture2(float moisture2) {
        this.moisture2 = moisture2;
    }

    public float getMoisture3() {
        return moisture3;
    }

    public void setMoisture3(float moisture3) {
        this.moisture3 = moisture3;
    }

    public float getMoistureAverage() {
        return moistureAverage;
    }

    public void setMoistureAverage(float moistureAverage) {
        this.moistureAverage = moistureAverage;
    }

    public float getWbgt1() {
        return wbgt1;
    }

    public void setWbgt1(float wbgt1) {
        this.wbgt1 = wbgt1;
    }

    public float getWbgt2() {
        return wbgt2;
    }

    public void setWbgt2(float wbgt2) {
        this.wbgt2 = wbgt2;
    }

    public float getWbgt3() {
        return wbgt3;
    }

    public void setWbgt3(float wbgt3) {
        this.wbgt3 = wbgt3;
    }

    public float getWbgtAverage() {
        return wbgtAverage;
    }

    public void setWbgtAverage(float wbgtAverage) {
        this.wbgtAverage = wbgtAverage;
    }

    public int getDkFlowRate() {
        return dkFlowRate;
    }

    public void setDkFlowRate(int dkFlowRate) {
        this.dkFlowRate = dkFlowRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public float getZzmWater() {
        return zzmWater;
    }

    public void setZzmWater(float zzmWater) {
        this.zzmWater = zzmWater;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "Data{" +
                "date=" + date +
                ", zzmlc=" + zzmlc +
                ", time='" + time + '\'' +
                ", zzmMiddle=" + zzmMiddle +
                ", zzmLeft=" + zzmLeft +
                ", zzmRight=" + zzmRight +
                ", zzmAverage=" + zzmAverage +
                ", zzmWater=" + zzmWater +
                ", zzmFlowRate=" + zzmFlowRate +
                ", temp1=" + temp1 +
                ", temp2=" + temp2 +
                ", temp3=" + temp3 +
                ", tmpAverage=" + tempAverage +
                ", moisture1=" + moisture1 +
                ", moisture2=" + moisture2 +
                ", moisture3=" + moisture3 +
                ", moistureAverage=" + moistureAverage +
                ", wbgt1=" + wbgt1 +
                ", wbgt2=" + wbgt2 +
                ", wbgt3=" + wbgt3 +
                ", wbgtAverage=" + wbgtAverage +
                ", dkFlowRate=" + dkFlowRate +
                ", status='" + status + '\'' +
                ", basis='" + basis + '\'' +
                ", description='" + description + '\'' +
                ", depth=" + depth +
                '}';
    }
}

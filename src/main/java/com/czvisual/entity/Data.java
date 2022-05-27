package com.czvisual.entity;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.format.DateTimeFormat;

public class Data {
    @ExcelIgnore
    private Integer id;
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private String date;
    private Float zzmlc;
    private String time;
    private Float zzmMiddle;
    private Float zzmLeft;
    private Float zzmRight;
    private Float zzmAverage;
    private Float zzmWater;
    private Float zzmFlowRate;
    private Float temp1;
    private Float temp2;
    private Float temp3;
    private Float tempAverage;
    private Float moisture1;
    private Float moisture2;
    private Float moisture3;
    private Float moistureAverage;
    private Float wbgt1;
    private Float wbgt2;
    private Float wbgt3;
    private Float wbgtAverage;
    private int dkFlowRate;
    private String status;
    private String basis;
    private String description;
    private Float depth;
    @ExcelIgnore
    private String table;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getZzmlc() {
        return zzmlc;
    }

    public void setZzmlc(Float zzmlc) {
        this.zzmlc = zzmlc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getZzmMiddle() {
        return zzmMiddle;
    }

    public void setZzmMiddle(Float zzmMiddle) {
        this.zzmMiddle = zzmMiddle;
    }

    public Float getZzmLeft() {
        return zzmLeft;
    }

    public void setZzmLeft(Float zzmLeft) {
        this.zzmLeft = zzmLeft;
    }

    public Float getZzmRight() {
        return zzmRight;
    }

    public void setZzmRight(Float zzmRight) {
        this.zzmRight = zzmRight;
    }

    public Float getZzmAverage() {
        return zzmAverage;
    }

    public void setZzmAverage(Float zzmAverage) {
        this.zzmAverage = zzmAverage;
    }

    public Float getZzmFlowRate() {
        return zzmFlowRate;
    }

    public void setZzmFlowRate(Float zzmFlowRate) {
        this.zzmFlowRate = zzmFlowRate;
    }

    public Float getTemp1() {
        return temp1;
    }

    public void setTemp1(Float temp1) {
        this.temp1 = temp1;
    }

    public Float getTemp2() {
        return temp2;
    }

    public void setTemp2(Float temp2) {
        this.temp2 = temp2;
    }

    public Float getTemp3() {
        return temp3;
    }

    public void setTemp3(Float temp3) {
        this.temp3 = temp3;
    }

    public Float getTempAverage() {
        return tempAverage;
    }

    public void setTempAverage(Float tempAverage) {
        this.tempAverage = tempAverage;
    }

    public Float getMoisture1() {
        return moisture1;
    }

    public void setMoisture1(Float moisture1) {
        this.moisture1 = moisture1;
    }

    public Float getMoisture2() {
        return moisture2;
    }

    public void setMoisture2(Float moisture2) {
        this.moisture2 = moisture2;
    }

    public Float getMoisture3() {
        return moisture3;
    }

    public void setMoisture3(Float moisture3) {
        this.moisture3 = moisture3;
    }

    public Float getMoistureAverage() {
        return moistureAverage;
    }

    public void setMoistureAverage(Float moistureAverage) {
        this.moistureAverage = moistureAverage;
    }

    public Float getWbgt1() {
        return wbgt1;
    }

    public void setWbgt1(Float wbgt1) {
        this.wbgt1 = wbgt1;
    }

    public Float getWbgt2() {
        return wbgt2;
    }

    public void setWbgt2(Float wbgt2) {
        this.wbgt2 = wbgt2;
    }

    public Float getWbgt3() {
        return wbgt3;
    }

    public void setWbgt3(Float wbgt3) {
        this.wbgt3 = wbgt3;
    }

    public Float getWbgtAverage() {
        return wbgtAverage;
    }

    public void setWbgtAverage(Float wbgtAverage) {
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

    public Float getDepth() {
        return depth;
    }

    public void setDepth(Float depth) {
        this.depth = depth;
    }

    public Float getZzmWater() {
        return zzmWater;
    }

    public void setZzmWater(Float zzmWater) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

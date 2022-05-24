package com.czvisual;

import java.util.Date;

public class DemoData {
    private String string;
    private Date date;
    private Double doubleData;


    @Override
    public String toString() {
        return "DemoData{" +
                "string='" + string + '\'' +
                ", date=" + date +
                ", doubleData=" + doubleData +
                '}';
    }
}
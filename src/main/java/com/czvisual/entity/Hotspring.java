package com.czvisual.entity;

import java.io.Serializable;


public class Hotspring implements Serializable {

    private String hotspringname;
    private String tablename;
    private String area;
    private String commontype;
    private String heatdisplaytype;
    private String heatdamagetype;
    private String chemicalcompositiontype;
    private static final long serialVersionUID = 1L;

    public String getHotspringname() {
        return hotspringname;
    }

    public void setHotspringname(String hotspringname) {
        this.hotspringname = hotspringname;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCommontype() {
        return commontype;
    }

    public void setCommontype(String commontype) {
        this.commontype = commontype;
    }

    public String getHeatdisplaytype() {
        return heatdisplaytype;
    }

    public void setHeatdisplaytype(String heatdisplaytype) {
        this.heatdisplaytype = heatdisplaytype;
    }

    public String getHeatdamagetype() {
        return heatdamagetype;
    }

    public void setHeatdamagetype(String heatdamagetype) {
        this.heatdamagetype = heatdamagetype;
    }

    public String getChemicalcompositiontype() {
        return chemicalcompositiontype;
    }

    public void setChemicalcompositiontype(String chemicalcompositiontype) {
        this.chemicalcompositiontype = chemicalcompositiontype;
    }
}
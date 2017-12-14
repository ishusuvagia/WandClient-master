package com.bench.wand.POJO;

/**
 * Created by isquare3 on 29/06/17.
 */

public class Event_Model_new {
    public String getWandID() {
        return WandID;
    }

    public void setWandID(String wandID) {
        WandID = wandID;
    }

    public String getSupplyID() {
        return supplyID;
    }

    public void setSupplyID(String supplyID) {
        this.supplyID = supplyID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getHasInventoryContent() {
        return hasInventoryContent;
    }

    public void setHasInventoryContent(String hasInventoryContent) {
        this.hasInventoryContent = hasInventoryContent;
    }

    public String getVendorPartNumber() {
        return vendorPartNumber;
    }

    public void setVendorPartNumber(String vendorPartNumber) {
        this.vendorPartNumber = vendorPartNumber;
    }

    public String getlCDescription() {
        return lCDescription;
    }

    public void setlCDescription(String lCDescription) {
        this.lCDescription = lCDescription;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    String WandID;
    String supplyID;
    String comment;

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    String lable;

    String quantity;
    String hasInventoryContent;
    String vendorPartNumber;
    String lCDescription;
    String vendorName;


}

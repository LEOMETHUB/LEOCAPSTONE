package sporteventmanagement.entity;

import java.util.Date;

public class Field {

    private int fieldID;
    private String fieldName;
    private String fieldAddress;
    private int fieldCapacity;
    private Date lastUpdate;

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldAddress() {
        return fieldAddress;
    }

    public void setFieldAddress(String fieldAddress) {
        this.fieldAddress = fieldAddress;
    }

    public int getFieldCapacity() {
        return fieldCapacity;
    }

    public void setFieldCapacity(int fieldCapacity) {
        this.fieldCapacity = fieldCapacity;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

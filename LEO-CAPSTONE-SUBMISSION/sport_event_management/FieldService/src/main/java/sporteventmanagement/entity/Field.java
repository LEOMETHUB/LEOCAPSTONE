package sporteventmanagement.entity;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "field")
@Table(name = "fields", schema = "field_schema")
public class Field {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private int fieldID;
    @Column(name = "field_name")
    private String fieldName;
    @Column(name = "field_address")
    private String fieldAddress;
    @Column(name = "field_capacity")
    private int fieldCapacity;
    @Column(name = "last_update")
    private Date lastUpdate;

    public Field() {
    }

    public Field(int fieldID, String fieldName, String fieldAddress, int fieldCapacity, Date lastUpdate) {
        this.fieldID = fieldID;
        this.fieldName = fieldName;
        this.fieldAddress = fieldAddress;
        this.fieldCapacity = fieldCapacity;
        this.lastUpdate = lastUpdate;
    }

    public Field(String fieldName, String fieldAddress, int fieldCapacity, Date lastUpdate) {
        this.fieldName = fieldName;
        this.fieldAddress = fieldAddress;
        this.fieldCapacity = fieldCapacity;
        this.lastUpdate = lastUpdate;
    }

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

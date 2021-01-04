package njust.lmsbackend.lms.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "tb_computerlab")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ComputerLabPOJO {
    @Id
    @Column(name = "id")
    int id;
    private String address;
    private int capacity;
    @Transient
    private int rest;

    public ComputerLabPOJO() {
    }

    public ComputerLabPOJO(int id, String address, int capacity, int rest) {
        this.id = id;
        this.address = address;
        this.capacity = capacity;
        this.rest = rest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public int getRest() {
        return rest;
    }
}

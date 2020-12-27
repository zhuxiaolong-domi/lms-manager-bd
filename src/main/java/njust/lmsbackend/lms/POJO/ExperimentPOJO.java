package njust.lmsbackend.lms.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_experiment")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ExperimentPOJO {
    @Id
    @Column(name = "id")
    String id;

    @Column(name = "name")
    String name;

    private Date start;
    private Date end;
    private String description;
    private int max;


    public ExperimentPOJO() {
    }

    public ExperimentPOJO(String id, String name, Date start, Date end, String description, int max) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.description = description;
        this.max = max;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}

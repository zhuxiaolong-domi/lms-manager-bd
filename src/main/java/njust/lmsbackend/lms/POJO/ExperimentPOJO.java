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
    String expId;

    @Column(name = "name")
    String name;

    @Transient
    String teacherName;

    private Date start;
    private Date end;
    private String description;
    private int max;


    public ExperimentPOJO() {
    }

    public ExperimentPOJO(String expId, String name, String teacherName, Date start, Date end, String description, int max) {
        this.expId = expId;
        this.name = name;
        this.teacherName = teacherName;
        this.start = start;
        this.end = end;
        this.description = description;
        this.max = max;
    }

    public String getId() {
        return expId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setId(String expId) {
        this.expId = expId;
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

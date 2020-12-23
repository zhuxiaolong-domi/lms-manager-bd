package njust.lmsbackend.lms.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_participation")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ParticipitionPOJO {
    @Id
    @Column(name = "student_id")
    String student_id;

    private String exp_id;
    private int score;
    private String report;

    public ParticipitionPOJO() {
    }

    public ParticipitionPOJO(String exp_id, String student_id) {
        this.student_id = student_id;
        this.exp_id = exp_id;
    }

    public ParticipitionPOJO(String student_id, String exp_id, int score, String report) {
        this.student_id = student_id;
        this.exp_id = exp_id;
        this.score = score;
        this.report = report;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getExp_id() {
        return exp_id;
    }

    public void setExp_id(String exp_id) {
        this.exp_id = exp_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}

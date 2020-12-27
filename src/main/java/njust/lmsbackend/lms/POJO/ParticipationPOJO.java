package njust.lmsbackend.lms.POJO;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_participation")
@Component
public class ParticipationPOJO {

    @Id
    @Column(name = "student_id", length = 12)
    private String studentId;

    @Column(name = "exp_id", length = 32)
    private String expId;

    @Column(name = "score")
    private int score;

    @Column(name = "report")
    private  String report;

    public ParticipationPOJO(){}

    public ParticipationPOJO(String studentId, String expId, Integer score, String report) {
        this.studentId = studentId;
        this.expId = expId;
        this.score = score;
        this.report = report;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getExpId() {
        return expId;
    }

    public void setExpId(String expId) {
        this.expId = expId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}

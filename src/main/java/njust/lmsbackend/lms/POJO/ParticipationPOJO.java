package njust.lmsbackend.lms.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "tb_participation")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ParticipationPOJO {
    @Id
    @Column(name = "student_id")
    String studentId;

    @Column(name = "exp_id")
    String expId;
    private int score;
    private String report;

    public ParticipationPOJO() {
    }

    public ParticipationPOJO(String expId, String studentId) {
        this.studentId = studentId;
        this.expId = expId;
    }

    public ParticipationPOJO(String studentId, String expId,  String report) {
        this.studentId = studentId;
        this.expId = expId;
        this.report = report;
    }

    public ParticipationPOJO(String studentId, String expId, int score, String report) {
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

    public String getExp_id() {
        return expId;
    }

    public void setExp_id(String expId) {
        this.expId = expId;
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

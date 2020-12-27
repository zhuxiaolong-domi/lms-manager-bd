package njust.lmsbackend.lms.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_appointment")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AppointmentPOJO {
    @Id
    @Column(name = "student_id")
    String studentId;

    private int lab_id;
    private int seat_id;
    /**
     * 时间段
     * 08-10 1
     * 10-12 2
     * 14-16 3
     * 16-18 4
     * 19-21 5
     */
    private int time;

    public AppointmentPOJO() {
    }

    public AppointmentPOJO(String studentId, int lab_id, int seat_id, int time) {
        this.studentId = studentId;
        this.lab_id = lab_id;
        this.seat_id = seat_id;
        this.time = time;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String student_id) {
        this.studentId = student_id;
    }

    public int getLab_id() {
        return lab_id;
    }

    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

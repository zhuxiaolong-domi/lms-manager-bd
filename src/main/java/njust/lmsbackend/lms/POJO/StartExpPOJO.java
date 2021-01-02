package njust.lmsbackend.lms.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "tb_start_exp")
@Component
@AllArgsConstructor
@NoArgsConstructor
public class StartExpPOJO {
    @Id
    @Column(name = "exp_id", length = 32)
    private String expId;

    @Column(name = "teacher_id", length = 12)
    private String teacherId;

    public String getExpId() {
        return expId;
    }

    public void setExpId(String expId) {
        this.expId = expId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
